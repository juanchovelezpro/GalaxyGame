package tools;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import modelo.GameObject;

/**
 * Esta clase es la Manager del juego. Se encarga de brindar informacion acerca
 * de la resolucion de la pantalla en la que se ejecuta el juego, asi como
 * tambien cargar los recursos que requiere el juego.
 * 
 * @author juanchovelezpro
 *
 */
public class GameManager {

	/**
	 * La altura de la pantalla en la que se ejecuta el juego.
	 */
	public static final int HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

	/**
	 * El ancho de la pantalla en la que se ejecuta el juego.
	 */
	public static final int WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();

	/**
	 * La altura que va a tener el juego.
	 */
	public static final int HEIGHT_GAME = WIDTH / 2;

	/**
	 * El ancho que va a tener el juego.
	 */
	public static final int WIDTH_GAME = HEIGHT_GAME;

	// Resources
	// Here all measures are calculated with a 1920x1080 display resolution as a
	// reference.

	/**
	 * La escala del jugador basada en una resolucion 1920x1080
	 */
	public static final int SCALE_PLAYER = 324; // (1920*1080)/(80*80). 80 is the width and the height of the original
												// spaceship in 1920*1080.

	/**
	 * El ancho del jugador en otra resolucion.
	 */
	public static final int WIDTH_PLAYER = (int) Math.sqrt((HEIGHT * WIDTH) / SCALE_PLAYER);

	/**
	 * La altura del jugador en otra resolucion.
	 */
	public static final int HEIGHT_PLAYER = WIDTH_PLAYER;

	/**
	 * Permite realizar pruebas al juego. (Utilizar en modo debugger)
	 */
	public static final boolean TEST = false;

	/**
	 * Las imagenes del juego.
	 */
	public static HashMap<String, Image> imagenes = new HashMap<String, Image>();
	public static HashMap<String, BufferedImage> sprites = new HashMap<String, BufferedImage>();

	public static void loadResources() {

		// Fondos
		imagenes.put("galaxy1", ImageLoader.cargarImagen("fondos/galaxy1.jpg"));
		imagenes.put("galaxy2", ImageLoader.cargarImagen("fondos/galaxy2.jpg"));
		imagenes.put("galaxy3", ImageLoader.cargarImagen("fondos/galaxy3.jpg"));

		// Naves
		imagenes.put("nave1", ImageLoader.cargarImagen("naves/nave1.png"));
		imagenes.put("nave2", ImageLoader.cargarImagen("naves/nave2.png"));
		imagenes.put("nave3", ImageLoader.cargarImagen("naves/nave3.png"));
		imagenes.put("nave4", ImageLoader.cargarImagen("naves/nave4.png"));
		imagenes.put("nave5", ImageLoader.cargarImagen("naves/nave5.png"));
		imagenes.put("nave6", ImageLoader.cargarImagen("naves/nave6.png"));

		imagenes.put("nave1invulnerable", ImageLoader.cargarImagen("naves/nave1invulnerable.png"));

		// Disparos
		imagenes.put("disparo1", ImageLoader.cargarImagen("disparos/laser1.png"));
		imagenes.put("disparo2", ImageLoader.cargarImagen("disparos/laser2.png"));
		imagenes.put("disparo3", ImageLoader.cargarImagen("disparos/laser3.png"));
		imagenes.put("disparo4", ImageLoader.cargarImagen("disparos/laser4.png"));

		// Iconos
		imagenes.put("icono", ImageLoader.cargarImagen("iconos/icon.jpg"));

		// Potenciadores
		imagenes.put("CONGELAR", ImageLoader.cargarImagen("potenciadores/iceBall.png"));
		
		
		//Sprites
		sprites.put("EXPLOSION", ImageLoader.cargarSprites("sprites/explosion.png"));
		

	}

	/**
	 * Se encarga de renderizar los limites que un {@code GameObject} tiene en el {@code Juego}
	 * @param g Los {@code Graphics} que se encargan de renderizar.
	 * @param go El {@code GameObject} al cual se le va a renderizar los limites.
	 */
	public static void renderBounds(Graphics g, GameObject go) {

		g.setColor(Color.RED.brighter());
		g.drawRect((int) go.getBounds().getX(), (int) go.getBounds().getY(), (int) go.getBounds().getWidth(),
				(int) go.getBounds().getHeight());
		g.setColor(Color.BLUE.brighter());

		if (go.getVision() != null)
			g.drawRect((int) go.getVision().getX(), (int) go.getVision().getY(), (int) go.getVision().getWidth(),
					(int) go.getVision().getHeight());

	}

}
