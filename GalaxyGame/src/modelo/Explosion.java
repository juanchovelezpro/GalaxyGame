package modelo;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import animation.Animation;
import hilos.HiloAbstract;
import tools.GameManager;
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
public class Explosion extends GameObject {

	/**
	 * Es un objeto de tipo {@code Animation} que permite realizar la animacion de
	 * los sprites.
	 */
	private Animation animation;

	/**
	 * Constructor que crea una explosion en las coordenadas (x,y).
	 * 
	 * @param x  La coordenada X de la {@code Explosion}
	 * @param y  La coordenadas Y de la {@code Explosion}
	 * @param El {@code Juego} al que pertenece la {@code Explosion}
	 */
	public Explosion(int x, int y, Juego juego) {

		super(x, y, juego);

		animation = new Animation(GameManager.sprites.get("EXPLOSION"), 81, 1, 9, 9);

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

				if (!getJuego().isPausa()) {
					Thread.sleep(5);
					animation.runAnimation();
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

	@Override
	public void render(Graphics g) {

		animation.drawAnimation(g, getX(), getY() - 30, 25);

	}

	@Override
	public Rectangle getVision() {

		return null;
	}

}
