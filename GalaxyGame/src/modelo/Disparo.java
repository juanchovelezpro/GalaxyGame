package modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

/**
 * Representa un disparo en el juego. 
 * @author juanchovelezpro
 *
 */
public class Disparo extends GameObject {

	/**
	 * Constructor que permite crear un {@code Disparo}
	 * @param skin La skin del {@code Disparo}
	 * @param x La coordenada X del {@code Disparo}
	 * @param y La coordenada Y del {@code Disparo}
	 * @param ancho El ancho del {@code Disparo}
	 * @param altura La altura del {@code Disparo}
	 * @param velX La velocidad en el eje X del {@code Disparo}
	 * @param velY La velocidad en el eje Y del {@code Disparo}
	 */
	public Disparo(Image skin, int x, int y, int ancho, int altura, int velX, int velY) {
		super(skin, x, y, ancho, altura, 0, velY);
	}

	/**
	 * <p>Este metodo permite realizar el movimiento de abajo hacia arriba del {@code Disparo}.</p>
	 * <p> Es utilizado por la clase {@link Jugador} para realizar los movimientos de sus disparos.</p> 
	 */
	public void avanzarDisparo() {

		super.setY(super.getY() - super.getVelY());
	}

	/**
	 * <p>Este metodo permite realizar el movimiento de arriba hacia abajo del {@code Disparo}.</p>
	 * <p> Es utilizado por la clase {@link Enemigo} para realizar los movimientos de sus disparos. </p>
	 */
	public void avanzarDisparoEnemigo() {

		super.setY(super.getY() + super.getVelY());
	}

	@Override
	public void render(Graphics g) {
		
		g.drawImage(getSkin(), getX(), getY(), null);
		
//		g.setColor(Color.RED.brighter());
//		g.drawRect((int)getBounds().getX(), (int)getBounds().getY(), (int)getBounds().getWidth(), (int)getBounds().getHeight());
	}
}
