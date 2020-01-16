package modelo;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import hilos.HiloAbstract;

/**
 * <p>
 * Clase abstracta para representar cualquier objeto del juego. Hereda de
 * {@code HiloAbstract}, por lo que un objeto {@code GameObject} tambien puede
 * ser un objeto {@code Thread}.
 * </p>
 * <p>
 * Posee las caracteristicas necesarias para representar un objeto de un juego
 * 2D, tales como coordenadas (x,y) de tipo {@code int}, el ancho y la altura de
 * tipo {@code int} sus velocidades en los ejes (x,y) de tipo {@code int} y una
 * skin de tipo {@code Image} para mostrar en el juego.
 * </p>
 * 
 * <p>
 * El metodo {@code getBounds()} retorna un {@code Rectangle} el cual permite
 * realizar colisiones entre {@code GameObject} con el metodo
 * {@code colision(GameObject objeto, GameObject objeto2)} de la clase
 * {@link Fisica}
 * </p>
 * 
 * @author juanchovelezpro
 *
 */
public abstract class GameObject extends HiloAbstract {

	/**
	 * La skin del {@code GameObject} representada como {@link Image}
	 */
	private Image skin;

	/**
	 * La coordenada x del {@code GameObject}.
	 */
	private int x;

	/**
	 * La coordenada y del {@code GameObject}.
	 */
	private int y;

	/**
	 * El ancho del {@code GameObject}.
	 */
	private int ancho;

	/**
	 * La altura del {@code GameObject}.
	 */
	private int altura;

	/**
	 * La velocidad en el eje X del {@code GameObject}.
	 */
	private int velX;

	/**
	 * La velocidad en el eje Y del {@code GameObject}.
	 */
	private int velY;

	/**
	 * Constructor que permite crear un {@code GameObject} con todos sus parametros.
	 * 
	 * @param skin   La skin del {@code GameObject} de tipo {@code Image}
	 * @param x      La coordenada x del {@code GameObject} de tipo {@code int}
	 * @param y      La coordenada y del {@code GameObject} de tipo {@code int}
	 * @param ancho  El ancho del {@code GameObject} de tipo {@code int}
	 * @param altura La altura del {@code GameObject} de tipo {@code int}
	 * @param velX   La velocidad en el eje X del {@code GameObject} de tipo
	 *               {@code int}
	 * @param velY   La velocidad en el eje Y del {@code GameObject} de tipo
	 *               {@code int}
	 */
	public GameObject(Image skin, int x, int y, int ancho, int altura, int velX, int velY, Juego juego) {

		super(juego);
		this.skin = skin;
		this.x = x;
		this.y = y;
		this.altura = altura;
		this.ancho = ancho;
		this.velX = velX;
		this.velY = velY;
	}

	/**
	 * Constructor que permite crear un {@code GameObject} sin la velocidad en el
	 * eje Y y sin la skin del objeto. La velocidad en el -eje Y- queda inicializada
	 * en {@code 0}. La skin del objeto queda inicializada en {@code null}.
	 * 
	 * @param x      La coordenada x del {@code GameObject} de tipo {@code int}
	 * @param y      La coordenada y del {@code GameObject} de tipo {@code int}
	 * @param ancho  El ancho del {@code GameObject} de tipo {@code int}
	 * @param altura La altura del {@code GameObject} de tipo {@code int}
	 * @param velX   La velocidad en el eje X del {@code GameObject} de tipo
	 *               {@code int}
	 */
	public GameObject(int x, int y, int ancho, int altura, int velX, Juego juego) {

		super(juego);
		skin = null;
		velY = 0;
		this.x = x;
		this.y = y;
		this.altura = altura;
		this.ancho = ancho;
		this.velX = velX;
	}

	/**
	 * Constructor que permite crear un {@code GameObject} solo con la coordenada x
	 * y la coodernada y del {@code GameObject}.
	 * 
	 * @param x La coordenada x del {@code GameObject}
	 * @param y La coordenada y del {@code GameObject}
	 */
	public GameObject(int x, int y, Juego juego) {

		super(juego);
		this.x = x;
		this.y = y;
		skin = null;
		altura = 0;
		ancho = 0;
		velX = 0;
		velY = 0;

	}

	/**
	 * Constructor vacio que crea un {@code GameObject} con todas sus variables
	 * iniciadas en {@code 0} o en {@code null} segun el caso.
	 */
	public GameObject(Juego juego) {

		super(juego);
		x = 0;
		y = 0;
		skin = null;
		altura = 0;
		ancho = 0;
		velX = 0;
		velY = 0;

	}

	/**
	 * Metodo para renderizar la <b> skin </b> del {@code GameObject} en las
	 * coordenadas (X,Y)
	 * 
	 * @param g Los graficos para renderizar de tipo {@link Graphics}
	 */
	public abstract void render(Graphics g);

	/**
	 * Retorna la coordenada X del {@code GameObject} en precision {@code int}
	 * 
	 * @return La coordenada X del {@code GameObject}.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Permite modificar la coordenada X del {@code GameObject}.
	 * 
	 * @param x La nueva coordenada X de tipo {@code int}
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Retorna la coordenada Y del {@code GameObject} en precision {@code int}
	 * 
	 * @return La coordenada Y del {@code GameObject}
	 */
	public int getY() {
		return y;
	}

	/**
	 * Permite modificar la coordenada Y del {@code GameObject}
	 * 
	 * @param y La nueva coordenada Y de tipo {@code int}
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Retorna la velocidad en el eje X del {@code GameObject}
	 * 
	 * @return La velocidad en el eje X del {@code GameObject}
	 */
	public int getVelX() {
		return velX;
	}

	/**
	 * Permite modificar la velocidad en el eje X del {@code GameObject}
	 * 
	 * @param velX La nueva velocidad en el eje X del {@code GameObject}
	 */
	public void setVelX(int velX) {
		this.velX = velX;
	}

	/**
	 * Retorna la velocidad en el eje Y del {@code GameObject}
	 * 
	 * @return La velocidad en el eje Y del {@code GameObject}
	 */
	public int getVelY() {
		return velY;
	}

	/**
	 * Permite modificar la velocidad en el eje Y del {@code GameObject}
	 * 
	 * @param velX La nueva velocidad en el eje Y del {@code GameObject}
	 */
	public void setVelY(int velY) {
		this.velY = velY;
	}

	/**
	 * Retorna la skin del {@code GameObject}
	 * 
	 * @return La skin del {@code GameObject}
	 */
	public Image getSkin() {
		return skin;
	}

	/**
	 * Permite modificar la skin del {@code GameObject}
	 * 
	 * @param skin La nueva skin del {@code GameObject}
	 */
	public void setSkin(Image skin) {
		this.skin = skin;
	}

	/**
	 * Retorna el ancho del {@code GameObject}
	 * 
	 * @return El ancho del {@code GameObject}
	 */
	public int getAncho() {
		return ancho;
	}

	/**
	 * Permite modificar el ancho del {@code GameObject}
	 * 
	 * @param ancho El nuevo ancho del {@code GameObject}
	 */
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	/**
	 * Retorna la altura del {@code GameObject}
	 * 
	 * @return La altura del {@code GameObject}
	 */
	public int getAltura() {
		return altura;
	}

	/**
	 * Permite modificar la altura del {@code GameObject}
	 * 
	 * @param altura La nueva altura del {@code GameObject}
	 */
	public void setAltura(int altura) {
		this.altura = altura;
	}

	/**
	 * Retorna un {@code Rectangle} que tiene las coordenadas (x,y) del
	 * {@code GameObject} y el ancho y la altura del {@code GameObject}
	 * 
	 * @return El {@code Rectangle} con coordenadas (x,y) y ancho y la altura del
	 *         {@code GameObject}
	 */
	public abstract Rectangle getBounds();

	/**
	 * Retorna un rectangulo que representa la visibilidad frontal de un
	 * {@code GameObject}.
	 * 
	 * @return Retorna el rectangulo de la visibilidad frontal de un
	 *         {@code GameObject}
	 */
	public abstract Rectangle getVision();

}
