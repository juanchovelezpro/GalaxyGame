package modelo;

import java.util.LinkedList;

import tools.ScreenResolution;

public class Jugador extends Objeto {

	public static final int HEIGHT = 80;
	public static final int WIDTH = 80;
	public static final int SPEED = 0;
	public static final int X_RIGHT_LIMIT = ScreenResolution.WIDTH_GAME - WIDTH;
	public static final int X_LEFT_LIMIT = 0;
	public static final int SHOT_LIMIT = -70;
	public static final int SHOT_OFFSET_X = 25;
	public static final int SHOT_OFFSET_Y = -35;
	public static final int SPAWN_X = ScreenResolution.WIDTH_GAME / 2 - Jugador.WIDTH / 2;
	public static final int SPAWN_Y = ScreenResolution.HEIGHT_GAME - Jugador.HEIGHT;

	public static final String SKIN_NORMAL = "naves/nave_jugador.png";
	public static final String DISPARO = "disparos/laserJugador.png";

	private String nick;
	private long puntaje;
	private LinkedList<Disparo> disparos;
	private Juego juego;
	private int vida;
	private int damage;

	public Jugador(Juego juego) {
		
		super(WIDTH, HEIGHT);
		super.setSkin(SKIN_NORMAL);
		super.setPosx(SPAWN_X);
		super.setPosy(SPAWN_Y);
		
		disparos = new LinkedList<>();

		nick = "";
		puntaje = 0;
		vida = 3;
		damage = 1;

		this.juego = juego;

	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public long getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(long puntaje) {
		this.puntaje = puntaje;
	}

	public void mover() {

		if (super.getPosx() >= X_RIGHT_LIMIT) {
			super.setPosx(X_RIGHT_LIMIT);
		} else if (super.getPosx() <= X_LEFT_LIMIT) {
			super.setPosx(X_LEFT_LIMIT);
		}

		super.setPosx(super.getPosx() + super.getVelocidad());

	}

	public LinkedList<Disparo> getDisparos() {
		return disparos;
	}

	public void setDisparos(LinkedList<Disparo> disparos) {
		this.disparos = disparos;
	}

	public void agregarDisparo() {
		disparos.add(new Disparo(DISPARO, super.getPosx() + SHOT_OFFSET_X, super.getPosy()+SHOT_OFFSET_Y, 25, 65, 15));
	}

	public void eliminarDisparo(Disparo d) {
		disparos.remove(d);
	}

	public void disparar() {

		Disparo disparoTemporal = null;

		for (int i = 0; i < disparos.size(); i++) {
			disparoTemporal = disparos.get(i);

			if (Fisica.colision(disparoTemporal, juego.getEnemigos()))
				eliminarDisparo(disparoTemporal);

			if (disparoTemporal.getPosy() < SHOT_LIMIT)
				eliminarDisparo(disparoTemporal);

			disparoTemporal.avanzarDisparo();
		}
	}

}
