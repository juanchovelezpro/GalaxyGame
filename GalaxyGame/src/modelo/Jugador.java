package modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.LinkedList;

import tools.ScreenResolution;
import tools.SoundPlayer;
import tools.ToolManager;

public class Jugador extends GameObject {

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

	public static final Image SKIN_NORMAL = ToolManager.cargarImagen("naves/nave_jugador.png");
	public static final Image SKIN_INVULNERABLE = ToolManager.cargarImagen("naves/skinInvulnerableJugador.png");
	public static final Image DISPARO = ToolManager.cargarImagen("disparos/laserJugador.png");

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
		super.setX(SPAWN_X);
		super.setY(SPAWN_Y);

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
			if (super.getX() >= X_RIGHT_LIMIT) {
				super.setX(X_RIGHT_LIMIT);
			} else if (super.getX() <= X_LEFT_LIMIT) {
				super.setX(X_LEFT_LIMIT);
			}

			super.setX(super.getX() + super.getVelX());
		}
	}

	public void revivir() {

		setX(SPAWN_X);
		setY(SPAWN_Y);
		setVivo(true);

	}

	public void morir() {

		setX(X_DEATH);
		setVelX(0);
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
		disparos.add(new Disparo(DISPARO, super.getX() + SHOT_OFFSET_X, super.getY() + SHOT_OFFSET_Y, 30, 70, 0, 15));
	}

	public void eliminarDisparo(Disparo d) {
		disparos.remove(d);
	}

	public void disparar() {

		Disparo disparoTemporal = null;

		for (int i = 0; i < disparos.size(); i++) {
			disparoTemporal = disparos.get(i);

			if (Fisica.colision(disparoTemporal, juego.getEnemigos())) {

				juego.getExplosiones().add(new Explosion(disparoTemporal.getX(), disparoTemporal.getY()));
				juego.getExplosiones().getLast().start();

				eliminarDisparo(disparoTemporal);

			}

			if (disparoTemporal.getY() < SHOT_LIMIT)
				eliminarDisparo(disparoTemporal);

			disparoTemporal.avanzarDisparo();
		}
	}

	@Override
	public void render(Graphics g) {

		g.drawImage(super.getSkin(), super.getX(), super.getY(), null);
//		g.setColor(Color.RED.brighter());
//		g.drawRect((int)getBounds().getX(), (int)getBounds().getY(), (int)getBounds().getWidth(), (int)getBounds().getHeight());
		
		for (int i = 0; i < disparos.size(); i++) {

			Disparo temp = disparos.get(i);

			temp.render(g);

		}

	}

}
