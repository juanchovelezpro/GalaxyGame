package modelo;

import java.awt.Graphics;
import java.awt.Rectangle;
import tools.GameManager;

/**
 * Representa un potenciador en el {@code Juego}. Un potenciador "PowerUp" es un
 * poder con el cual el {@code Jugador} puede obtener ventajas en el
 * {@code Juego}.
 * 
 * @author juanchovelezpro
 *
 */
public class Potenciador extends GameObject {

	/**
	 * El {@code PODER} que va a brindar el {@code Potenciador}
	 */
	private PODER poder;

	/**
	 * El {@code Juego} donde se encuentra el {@code Potenciador}
	 */
	private Juego juego;

	/**
	 * Permite verificar si el {@code Potenciador} cuando este moviendose y el
	 * {@code Jugador} aun no lo haya capturado, este disponible.
	 */
	private boolean disponible;

	private String sonido;

	/**
	 * Los poderes que puede tener un {@code Potenciador}
	 * 
	 * @author juanchovelezpro
	 *
	 */
	public enum PODER {

		FUEGO, CONGELAR,

	}

	/**
	 * Construye un {@code Potenciador} con un {@code PODER}, en las coordenadas
	 * (x,y) del {@code Juego}.
	 * 
	 * @param poder El {@code PODER} del {@code Potenciador}
	 * @param x     La coordenada en el eje X
	 * @param y     La coordenada en el eje Y.
	 * @param juego El {@code Juego} al que pertenece.
	 */
	public Potenciador(PODER poder, int x, int y, Juego juego) {

		super(x, y, juego);
		this.poder = poder;
		setVelX(1);
		setVelY(1);
		crearPotenciador(poder);
		disponible = true;
		this.juego = juego;
		sonido = "";

	}

	/**
	 * Consturye un {@code Potenciador} con un {@code PODER}
	 * 
	 * @param poder El {@code PODER} del {@code Potenciador}
	 * @param juego El {@code Juego}.
	 */
	public Potenciador(PODER poder, Juego juego) {

		super(juego);
		setX(-100);
		this.poder = poder;
		setVelX(1);
		setVelY(1);
		crearPotenciador(poder);
		disponible = true;
		this.juego = juego;

	}

	/**
	 * Crea un {@code Potenciador} con el {@code PODER} que se le asigna.
	 * 
	 * @param poder El {@code PODER} que va a tener el {@code Potenciador}
	 */
	private void crearPotenciador(PODER poder) {

		switch (poder) {
		case CONGELAR:
			setSkin(GameManager.imagenes.get("CONGELAR"));
			setAltura(getSkin().getHeight(null));
			setAncho(getSkin().getWidth(null));
			sonido = "/sounds/explosion.wav";
			break;

		default:
			break;
		}

	}

	/**
	 * Se encarga de realizar el moviemiento del {@code Potenciador}.
	 */
	public void mover() {

		if (getX() >= GameManager.WIDTH_GAME - getAncho() || getX() <= 0)
			setVelX(getVelX() * -1);

		if (getY() >= GameManager.HEIGHT_GAME - getAltura() || getY() <= 0)
			setVelY(getVelY() * -1);

		setX(getX() + getVelX());
		setY(getY() + getVelY());

		if (Fisica.colision(juego.getJugador(), this)) {

			juego.getJugador().agregarPowerUp(this);

			disponible = false;

		}

	}

	public String getSonido() {
		return sonido;
	}

	public void setSonido(String sonido) {
		this.sonido = sonido;
	}

	@Override
	public void render(Graphics g) {

		g.drawImage(getSkin(), getX(), getY(), null);

		if (GameManager.TEST) {

			GameManager.renderBounds(g, this);

		}

	}

	@Override
	public Rectangle getVision() {

		return null;
	}

	/**
	 * Retorna el {@code PODER} del {@code Potenciador}
	 * 
	 * @return El {@code PODER} del {@code Potenciador}
	 */
	public PODER getPoder() {
		return poder;
	}

	/**
	 * Modifica el {@code PODER} del {@code Potenciador}
	 * 
	 * @param poder El nuevo {@code PODER} del {@code Potenciador}
	 */
	public void setPoder(PODER poder) {
		this.poder = poder;
	}

	/**
	 * Retorna si el {@code Potenciador} se encuentra disponible, {@code true} si
	 * esta disponible, {@code false} en caso contrario.
	 * 
	 * @return
	 */
	public boolean isDisponible() {
		return disponible;
	}

	/**
	 * Modifica la disponibilidad del {@code Potenciador}
	 * 
	 * @param disponible La nueva disponibilidad del {@code Potenciador.}
	 */
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	@Override
	public void run() {

		int seconds = 15;

		while (disponible) {
			try {
				if (!juego.isPausa()) {

					Thread.sleep(1000);
					seconds--;
					if (seconds == 0) {

						disponible = false;

					}

				} else {

					synchronized (this) {
						wait();
					}

				}

			} catch (Exception ex) {

				ex.printStackTrace();

			}
		}

	}

}
