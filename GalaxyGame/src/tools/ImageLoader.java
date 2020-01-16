package tools;

import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Esta clase se encarga de cargar las imagenes en el {@code Juego}.
 * @author juanchovelezpro
 *
 */
public class ImageLoader {

	/**
	 * Carga la imagen.
	 * @param ruta La ruta donde se encuentra la imagen.
	 * @return La imagen de tipo {@code Image}.
	 */
	public static Image cargarImagen(String ruta) {

		Image image = null;

		ImageIcon icon = new ImageIcon(ImageLoader.class.getClassLoader().getResource(ruta));

		image = icon.getImage();

		return image;

	}

	/**
	 * Carga una imagen en un objeto {@code BufferedImage}.
	 * @param ruta La ruta donde se encuentra la imagen.
	 * @return La imagen de tipo {@code BufferedImage}
	 */
	public static BufferedImage cargarBufferedImage(String ruta) {

		Image image = null;

		ImageIcon icon = new ImageIcon(ImageLoader.class.getClassLoader().getResource(ruta));

		image = icon.getImage();

		// Create a buffered image with transparency
		BufferedImage bimage = new BufferedImage(image.getWidth(null), image.getHeight(null),
				BufferedImage.TYPE_INT_ARGB);

		// Draw the image on to the buffered image
		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(image, 0, 0, null);
		bGr.dispose();

		// Return the buffered image
		return bimage;

	}

}
