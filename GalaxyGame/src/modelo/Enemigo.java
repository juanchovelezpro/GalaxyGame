package modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.LinkedList;
import java.util.Random;

import tools.ScreenResolution;
import tools.ToolManager;

public class Enemigo extends GameObject {

	public static final int WIDTH = 80;
	public static final int HEIGHT = 80;
	public static final int SPEED = 1;
	public static final int SPEED_FAST = 3;
	public static final int Y_LIMIT = ScreenResolution.HEIGHT_GAME + HEIGHT;
	public static final int X_BOUND = ScreenResolution.WIDTH_GAME - WIDTH;
	public static final int Y_MAX = -100;
	public static final int Y_MIN = -600;
	public static final int SHOT_LIMIT = ScreenResolution.HEIGHT_GAME + 50;
	public static final int SHOT_OFFSET_X = 25;
	public static final int SHOT_OFFSET_Y = 40;
	public static final int X_DEATH = -500;
	public static final int MOVER_NORMAL = 1;
	public static final int MOVER_ZIGZAG = 2;

	public static final Image SKIN_NORMAL = ToolManager.cargarImagen("naves/nave_enemigo.png");
	public static final Image SKIN_FAST = ToolManager.cargarImagen("naves/fastEnemy.png");
	public static final Image DISPARO_NORMAL = ToolManager.cargarImagen("disparos/laserEnemigo.png");
	public static final Image DISPARO_FAST = ToolManager.cargarImagen("disparos/laserGama.png");

	private int tipo;
	private int vida;
	private boolean vivo;
	private LinkedList<Disparo> disparos;
	private Juego juego;

	private int movimiento;
	private double auxMovs;

	private Random r;

	public Enemigo(int tipo, Juego juego) {

		super(WIDTH, HEIGHT);
		super.setSkin(SKIN_NORMAL);
		super.setVelY(SPEED);

		r = new Random();

		super.setX(r.nextInt(X_BOUND));
		super.setY(r.nextInt(Y_MAX + 1 - Y_MIN) + Y_MIN);

		this.tipo = tipo;
		vivo = true;

		movimiento = 0;
		auxMovs = 0.0;

		crearPorTipo(tipo);

		disparos = new LinkedList<>();
		this.juego = juego;

	}

	public void crearPorTipo(int tipo) {

		switch (tipo) {
		case 1:
			vida = 1;
			movimiento = 1;
			setSkin(SKIN_NORMAL);
			break;
		case 2:
			vida = 2;
			movimiento = 1;
			setSkin(SKIN_FAST);
			setVelY(SPEED_FAST);
			break;
		case 3:
			vida = 3;
			break;
		case 4:
			vida = 4;
			break;
		default:
			vida = 5;
			break;
		}

	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public void mover() {

		if (vivo) {
			if (super.getY() >= Y_LIMIT) {
				super.setY(r.nextInt(Y_MAX + 1 - Y_MIN) + Y_MIN);
				super.setX(r.nextInt(X_BOUND));
			}

			if (vida <= 0 && vivo)
				morir();

			if (Fisica.colision(this, juego.getJugador())) {

				vida--;

				juego.getExplosiones().add(new Explosion(this.getX() + WIDTH / 2, this.getY() + HEIGHT));
				juego.getExplosiones().getLast().start();

				if (!juego.getJugador().isInvulnerable())
					juego.getJugador().morir();

			}

			switch (movimiento) {

			case 1:
				movimientoNormal();
				break;

			case 2:
				movimientoZigZag();
				break;

			case 3:
				movimientoCircular();
				break;

			}
		}
	}

	public void movimientoNormal() {

		super.setY(super.getY() + super.getVelY());

	}

	public void movimientoZigZag() {

		if (auxMovs >= Math.PI * 2) {

			auxMovs = 0.0;

		}

		if (auxMovs < Math.PI * 2) {

			int posX = (int) (Math.sin(auxMovs) * 15);
			int posY = (int) (Math.asin(auxMovs) * 2);

			super.setX(super.getX() + posX);
			super.setY(super.getY() + posY + 4);

			auxMovs += 0.1;

		}

	}

	public void movimientoCircular() {

		if (auxMovs >= Math.PI * 2) {

			auxMovs = 0.0;

		}

		if (auxMovs < Math.PI * 2) {

			int posX = (int) (Math.sin(auxMovs) * 15);
			int posY = (int) (Math.cos(auxMovs) * 15);

			super.setX(super.getX() + posX);
			super.setY(super.getY() + posY + 2);

			auxMovs += 0.1;

		}

	}

	public boolean isVivo() {
		return vivo;
	}

	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}

	public void morir() {

		setX(X_DEATH);
		setVelY(0);
		vivo = false;
		juego.setEnemigosRestantes(juego.getEnemigosRestantes() - 1);

	}

	public LinkedList<Disparo> getDisparos() {
		return disparos;
	}

	public void setDisparos(LinkedList<Disparo> disparos) {
		this.disparos = disparos;
	}

	public void agregarDisparo() {

		if (tipo == 1) {
			disparos.add(new Disparo(DISPARO_FAST, super.getX() + SHOT_OFFSET_X - 3, super.getY() + SHOT_OFFSET_Y, 30,
					70, 0, 15));
		}

		if (tipo == 2) {
			disparos.add(new Disparo(DISPARO_NORMAL, super.getX() + SHOT_OFFSET_X, super.getY() + SHOT_OFFSET_Y, 35, 85,
					0, 20));
		}
	}

	public void eliminarDisparo(Disparo d) {
		disparos.remove(d);
	}

	public void disparar() {

		Disparo disparoTemporal = null;

		for (int i = 0; i < disparos.size(); i++) {
			disparoTemporal = disparos.get(i);

			if (Fisica.colision(disparoTemporal, juego.getJugador())) {

				juego.getExplosiones().add(
						new Explosion(disparoTemporal.getX(), disparoTemporal.getY() + disparoTemporal.getAltura()));
				juego.getExplosiones().getLast().start();

				eliminarDisparo(disparoTemporal);

				if (!juego.getJugador().isInvulnerable())
					juego.getJugador().morir();

			}
			if (disparoTemporal.getY() > SHOT_LIMIT)
				eliminarDisparo(disparoTemporal);

			disparoTemporal.avanzarDisparoEnemigo();
		}

	}

	@Override
	public void render(Graphics g) {

		g.drawImage(getSkin(), getX(), getY(), null);
		
//		g.setColor(Color.RED.brighter());
//		g.drawRect((int)getBounds().getX(), (int)getBounds().getY(), (int)getBounds().getWidth(), (int)getBounds().getHeight());

		for (int i = 0; i < disparos.size(); i++) {

			Disparo temp = disparos.get(i);

			temp.render(g);

		}

	}

}
