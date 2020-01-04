package tools;

import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ToolManager {

	public static Image cargarImagen(String ruta) {

		Image image = null;

		ImageIcon icon = new ImageIcon(ToolManager.class.getClassLoader().getResource(ruta));

		image = icon.getImage();

		return image;

	}

	public static BufferedImage cargarSprites(String ruta) {

		Image image = null;

		ImageIcon icon = new ImageIcon(ToolManager.class.getClassLoader().getResource(ruta));

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
