package tools;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;

import modelo.GameObject;

public class GameManager {

	// Screen
	public static final int HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	public static final int WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public static final int HEIGHT_GAME = WIDTH / 2;
	public static final int WIDTH_GAME = HEIGHT_GAME;

	// Resources
	// Here all measures are calculated with a 1920x1080 display resolution as a
	// reference.
	public static final int SCALE_PLAYER = 324; // (1920*1080)/(80*80). 80 is the width and the height of the original
												// spaceship in 1920*1080.
	public static final int WIDTH_PLAYER = (int) Math.sqrt((HEIGHT * WIDTH) / SCALE_PLAYER);
	public static final int HEIGHT_PLAYER = WIDTH_PLAYER;

	public static final int SCALE_WIDTH_SHOT = 1920 / 30;
	public static final int SCALE_HEIGHT_SHOT = 1080 / 70;
	public static final int WIDTH_SHOT = WIDTH / SCALE_WIDTH_SHOT;
	public static final int HEIGHT_SHOT = HEIGHT / SCALE_HEIGHT_SHOT;

	public static final boolean TEST = true;

	public static HashMap<String, Image> imagenes = new HashMap<String, Image>();

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
		
		
	}

	public static void renderBounds(Graphics g, GameObject go) {

		g.setColor(Color.RED.brighter());
		g.drawRect((int) go.getBounds().getX(), (int) go.getBounds().getY(), (int) go.getBounds().getWidth(),
				(int) go.getBounds().getHeight());
		g.setColor(Color.BLUE.brighter());
		
		if(go.getVision() != null)
		g.drawRect((int) go.getVision().getX(), (int)go.getVision().getY(), (int)go.getVision().getWidth(), (int)go.getVision().getHeight());
		
	}

}
