package modelo;

import java.util.LinkedList;
import java.util.Random;

import tools.ScreenResolution;

public class Enemigo extends Objeto {

	public static final int WIDTH = 80;
	public static final int HEIGHT = 80;
	public static final int SPEED = 1;
	public static final int Y_LIMIT = ScreenResolution.HEIGHT_GAME + HEIGHT;
	public static final int X_BOUND = ScreenResolution.WIDTH_GAME - WIDTH;
	public static final int Y_MAX = -100;
	public static final int Y_MIN = -600;
	public static final int SHOT_LIMIT = ScreenResolution.HEIGHT_GAME + 50;
	public static final int SHOT_OFFSET_X = 25;
	public static final int SHOT_OFFSET_Y = 40;
	public static final int X_DEATH = -250;

	public static final String SKIN_NORMAL = "naves/nave_enemigo.png";
	public static final String DISPARO_NORMAL = "disparos/laserEnemigo.png";

	private int tipo;
	private int vida;
	private LinkedList<Disparo> disparos;
	private Juego juego;
	private Random r;

	public Enemigo(int tipo, Juego juego) {

		super(WIDTH, HEIGHT);
		super.setSkin(SKIN_NORMAL);
		super.setVelocidad(SPEED);

		r = new Random();

		super.setPosx(r.nextInt(X_BOUND));
		super.setPosy(r.nextInt(Y_MAX + 1 - Y_MIN) + Y_MIN);

		this.tipo = tipo;

		crearPorTipo(tipo);

		disparos = new LinkedList<>();
		this.juego = juego;

	}

	public void crearPorTipo(int tipo) {

		switch (tipo) {
		case 1:
			vida = 2;
			break;
		case 2:
			vida = 3;
			break;
		case 3:
			vida = 4;
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

		if (super.getPosy() >= Y_LIMIT) {
			super.setPosy(r.nextInt(Y_MAX + 1 - Y_MIN) + Y_MIN);
			super.setPosx(r.nextInt(X_BOUND));
		}

		if (Fisica.colision(this, juego.getJugador())) {

			morir();
			juego.getJugador().morir();

		}

		super.setPosy(super.getPosy() + super.getVelocidad());
	}

	public void morir() {

		setPosx(X_DEATH);
		setVelocidad(0);

	}

	public LinkedList<Disparo> getDisparos() {
		return disparos;
	}

	public void setDisparos(LinkedList<Disparo> disparos) {
		this.disparos = disparos;
	}

	public void agregarDisparo() {
		disparos.add(new Disparo(DISPARO_NORMAL, super.getPosx() + SHOT_OFFSET_X, super.getPosy() + SHOT_OFFSET_Y, 25,
				65, 15));
	}

	public void eliminarDisparo(Disparo d) {
		disparos.remove(d);
	}

	public void disparar() {

		Disparo disparoTemporal = null;

		for (int i = 0; i < disparos.size(); i++) {
			disparoTemporal = disparos.get(i);

			if (Fisica.colision(disparoTemporal, juego.getJugador())) {
				eliminarDisparo(disparoTemporal);
				juego.getJugador().morir();

			}
			if (disparoTemporal.getPosy() > SHOT_LIMIT)
				eliminarDisparo(disparoTemporal);

			disparoTemporal.avanzarDisparoEnemigo();
		}

	}

}
