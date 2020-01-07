package modelo;

import java.util.LinkedList;
import java.util.Random;

import tools.ScreenResolution;

public class Enemigo extends Objeto {

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

	public static final String SKIN_NORMAL = "naves/nave_enemigo.png";
	public static final String SKIN_FAST = "naves/fastEnemy.png";
	public static final String DISPARO_NORMAL = "disparos/laserEnemigo.png";
	public static final String DISPARO_FAST = "disparos/laserGama.png";

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
		super.setVelocidad(SPEED);

		r = new Random();

		super.setPosx(r.nextInt(X_BOUND));
		super.setPosy(r.nextInt(Y_MAX + 1 - Y_MIN) + Y_MIN);

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
			setVelocidad(SPEED_FAST);
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
			if (super.getPosy() >= Y_LIMIT) {
				super.setPosy(r.nextInt(Y_MAX + 1 - Y_MIN) + Y_MIN);
				super.setPosx(r.nextInt(X_BOUND));
			}

			if (vida <= 0 && vivo)
				morir();

			if (Fisica.colision(this, juego.getJugador())) {

				vida--;

				juego.getExplosiones().add(new Explosion(this.getPosx() + WIDTH / 2, this.getPosy() + HEIGHT));
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

		super.setPosy(super.getPosy() + super.getVelocidad());

	}

	public void movimientoZigZag() {

		if (auxMovs >= Math.PI * 2) {

			auxMovs = 0.0;

		}

		if (auxMovs < Math.PI * 2) {

			int posX = (int) (Math.sin(auxMovs) * 15);
			int posY = (int) (Math.asin(auxMovs) * 2);

			super.setPosx(super.getPosx() + posX);
			super.setPosy(super.getPosy() + posY + 4);

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

			super.setPosx(super.getPosx() + posX);
			super.setPosy(super.getPosy() + posY + 2);

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

		setPosx(X_DEATH);
		setVelocidad(0);
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
			disparos.add(new Disparo(DISPARO_FAST, super.getPosx() + SHOT_OFFSET_X - 3, super.getPosy() + SHOT_OFFSET_Y,
					30, 70, 15));
		}

		if (tipo == 2) {
			disparos.add(new Disparo(DISPARO_NORMAL, super.getPosx() + SHOT_OFFSET_X, super.getPosy() + SHOT_OFFSET_Y,
					35, 85, 20));
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

				juego.getExplosiones().add(new Explosion(disparoTemporal.getPosx(),
						disparoTemporal.getPosy() + disparoTemporal.getAltura()));
				juego.getExplosiones().getLast().start();

				eliminarDisparo(disparoTemporal);

				if (!juego.getJugador().isInvulnerable())
					juego.getJugador().morir();

			}
			if (disparoTemporal.getPosy() > SHOT_LIMIT)
				eliminarDisparo(disparoTemporal);

			disparoTemporal.avanzarDisparoEnemigo();
		}

	}

}
