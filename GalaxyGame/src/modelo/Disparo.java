package modelo;

import java.awt.Graphics;
import java.awt.Rectangle;

import tools.GameManager;

/**
 * Representa un disparo en el {@code Juego}.
 * 
 * @author juanchovelezpro
 *
 */
public class Disparo extends GameObject {

	/**
	 * Numero entero que representa el tipo de disparo.
	 */
	private int tipo;

	/**
	 * Constructor que permite crear un {@code Disparo}
	 * 
	 * @param tipo El tipo de {@code Disparo} a crear.
	 * @param x    La coordenada X del {@code Disparo}.
	 * @param y    La coordenada Y del {@code Disparo}.
	 * @param velX La velocidad en el eje X del {@code Disparo}.
	 * @param velY La velocidad en el eje Y del {@code Disparo}.
	 */
	public Disparo(int tipo, int x, int y, int velX, int velY, Juego juego) {
		super(x, y, juego);
		setVelX(velX);
		setVelY(velY);

		crearPorTipo(tipo);
	}

	/**
	 * Crea un {@code Disparo} segun el tipo especificado.
	 * 
	 * @param tipo El tipo de {@code Disparo} a crear.
	 */
	private void crearPorTipo(int tipo) {

		switch (tipo) {

		case 1:

			crearPorSkin("disparo1");

			break;

		case 2:

			crearPorSkin("disparo2");

			break;

		case 3:

			crearPorSkin("disparo3");

			break;

		case 4:

			crearPorSkin("disparo4");

			break;

		}

	}

	/**
	 * Modifica la skin junto con el ancho y la altura del {@code Disparo}.
	 * 
	 * @param skin La skin a poner al {@code Disparo}.
	 */
	private void crearPorSkin(String skin) {

		setSkin(GameManager.imagenes.get(skin));
		setAncho(getSkin().getWidth(null));
		setAltura(getSkin().getHeight(null));

	}

	/**
	 * <p>
	 * Este metodo permite realizar el movimiento de abajo hacia arriba del
	 * {@code Disparo}.
	 * </p>
	 * <p>
	 * Es utilizado por la clase {@link Jugador} para realizar los movimientos de
	 * sus disparos.
	 * </p>
	 */
	public void avanzarDisparo() {

		super.setY(super.getY() - super.getVelY());
	}

	/**
	 * <p>
	 * Este metodo permite realizar el movimiento de arriba hacia abajo del
	 * {@code Disparo}.
	 * </p>
	 * <p>
	 * Es utilizado por la clase {@link Enemigo} para realizar los movimientos de
	 * sus disparos.
	 * </p>
	 */
	public void avanzarDisparoEnemigo() {

		super.setY(super.getY() + super.getVelY());
	}

	/**
	 * Retorna el tipo del {@code Disparo}.
	 * @return El tipo del {@code Disparo}.
	 */
	public int getTipo() {
		return tipo;
	}

	/**
	 * Modifica el tipo del {@code Disparo}.
	 * @param tipo El nuevo tipo del {@code Disparo}.
	 */
	public void setTipo(int tipo) {
		this.tipo = tipo;
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
}
