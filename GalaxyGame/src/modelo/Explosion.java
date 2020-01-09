package modelo;

import java.awt.image.BufferedImage;

import animation.Animation;
import tools.ImageLoader;

/**
 * <p>
 * Representa la animacion de una explosion en el juego.
 * </p>
 * 
 * <p>
 * Un objeto de esta clase crea un {@code Thread} que permite realizar la
 * animacion de una explosion en la coordenadas (x,y).
 * </p>
 * 
 * <p>
 * La animacion se realizan con la clase {@link Animation}.
 * </p>
 * 
 * @author juanchovelezpro
 *
 */
public class Explosion extends Thread {

	/**
	 * La imagen que contiene los Sprites para realizar la animacion de una
	 * explosion.
	 */
	public static final BufferedImage BOOM = ImageLoader.cargarSprites("sprites/explosion.png");

	/**
	 * Es un objeto de tipo {@code Animation} que permite realizar la animacion de
	 * los sprites.
	 */
	private Animation animation;

	/**
	 * La coordenada X de la {@code Explosion}
	 */
	private int x;

	/**
	 * La coordenadas Y de la {@code Explosion}
	 */
	private int y;

	/**
	 * Constructor que crea una explosion en las coordenadas (x,y).
	 * 
	 * @param x La coordenada X de la {@code Explosion}
	 * @param y La coordenadas Y de la {@code Explosion}
	 */
	public Explosion(int x, int y) {

		this.x = x;
		this.y = y;

		animation = new Animation(BOOM, 81, 1, 9, 9);

	}

	/**
	 * Retorna el objeto de tipo {@code Animation} con el que se realiza la
	 * animacion de la explosion.
	 * 
	 * @return un objeto de tipo {@code Animation}
	 */
	public Animation getAnimation() {
		return animation;
	}

	/**
	 * Permite modificar la {@code Animation} de la {@code Explosion}
	 * 
	 * @param animation La nueva {@code Animation} de la {@code Explosion}
	 */
	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	/**
	 * Retorna la coordenada X de la {@code Explosion}
	 * 
	 * @return La coordenada X de la {@code Explosion}
	 */
	public int getX() {
		return x;
	}

	/**
	 * Permite modificar la coordenada X de la {@code Explosion}
	 * 
	 * @param x La nueva coordenada X de la {@code Explosion}
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Retorna la coordenada Y de la {@code Explosion}
	 * 
	 * @return La coordenada Y de la {@code Explosion}
	 */
	public int getY() {
		return y;
	}

	/**
	 * Permite modificar la coordenada Y de la {@code Explosion}
	 * 
	 * @param x La nueva coordenada Y de la {@code Explosion}
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Ejecuta la animacion de una explosion. Si el objeto {@code Animation} realiza
	 * la animacion y llega al ultimo sprite de la imagen de {@code Explosion}
	 * entonces este {@code Thread} muere.
	 * 
	 * @see Animation
	 */
	@Override
	public void run() {

		while (animation.isAlive()) {

			try {

				Thread.sleep(5);
				animation.runAnimation();

			} catch (Exception ex) {

				ex.printStackTrace();

			}

		}

	}

}
