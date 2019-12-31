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
	public static final int X_DEATH = -100;

	public static final String SKIN_NORMAL = "naves/nave_jugador.png";
	public static final String SKIN_INVULNERABLE = "naves/skinInvulnerableJugador.png";
	public static final String DISPARO = "disparos/laserJugador.png";

	private String nick;
	private long puntaje;
	private LinkedList<Disparo> disparos;
	private Juego juego;
	private int vidas;
	private int damage;
	private boolean vivo;
	private boolean recargaDisparo;
	private boolean invulnerable;

	public Jugador(Juego juego) {

		super(WIDTH, HEIGHT);
		super.setSkin(SKIN_NORMAL);
		super.setPosx(SPAWN_X);
		super.setPosy(SPAWN_Y);

		disparos = new LinkedList<>();

		nick = "";
		puntaje = 0;
		vidas = 3;
		damage = 1;
		vivo = true;
		recargaDisparo = false;
		invulnerable = false;

		this.juego = juego;

	}

	public boolean isInvulnerable() {
		return invulnerable;
	}

	public void setInvulnerable(boolean invulnerable) {
		this.invulnerable = invulnerable;
	}

	public boolean isRecargaDisparo() {
		return recargaDisparo;
	}

	public void setRecargaDisparo(boolean recargaDisparo) {
		this.recargaDisparo = recargaDisparo;
	}

	public boolean isVivo() {
		return vivo;
	}

	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}

	public int getVidas() {
		return vidas;
	}

	public void setVidas(int vidas) {
		this.vidas = vidas;
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

		if (vivo) {
			if (super.getPosx() >= X_RIGHT_LIMIT) {
				super.setPosx(X_RIGHT_LIMIT);
			} else if (super.getPosx() <= X_LEFT_LIMIT) {
				super.setPosx(X_LEFT_LIMIT);
			}

			super.setPosx(super.getPosx() + super.getVelocidad());
		}
	}

	public void revivir() {

		setPosx(SPAWN_X);
		setPosy(SPAWN_Y);
		setVivo(true);

	}

	public void morir() {

		setPosx(X_DEATH);
		setVelocidad(0);
		setVivo(false);
		setVidas(vidas - 1);

	}

	public LinkedList<Disparo> getDisparos() {
		return disparos;
	}

	public void setDisparos(LinkedList<Disparo> disparos) {
		this.disparos = disparos;
	}

	public void agregarDisparo() {
		disparos.add(
				new Disparo(DISPARO, super.getPosx() + SHOT_OFFSET_X, super.getPosy() + SHOT_OFFSET_Y, 30, 70, 15));
	}

	public void eliminarDisparo(Disparo d) {
		disparos.remove(d);
	}

	public void disparar() {

		Disparo disparoTemporal = null;

		for (int i = 0; i < disparos.size(); i++) {
			disparoTemporal = disparos.get(i);

			if (Fisica.colision(disparoTemporal, juego.getEnemigos())) {
				eliminarDisparo(disparoTemporal);

			}

			if (disparoTemporal.getPosy() < SHOT_LIMIT)
				eliminarDisparo(disparoTemporal);

			disparoTemporal.avanzarDisparo();
		}
	}

}
