package animation;

import java.awt.image.BufferedImage;

/**
 * 
 * This class represents a sprite sheet of an image.
 * @author Juan Camilo Vélez Olaya
 *
 */
public class SpriteSheet {

	/**
	 * This is the image to get sprite sheets
	 */
	private BufferedImage image;

	/**
	 * Constructor to create a sprite sheet.
	 * @param image
	 */
	public SpriteSheet(BufferedImage image) {

		this.image = image;

	}

	/**
	 * This method grab the sub image (Sprite Sheet) of an image. 
	 * @param row The row of the sprite
	 * @param col The column of the sprite
	 * @param width The width of the sprite 
	 * @param height The height of the sprite
	 * @return The sprite sheet of the image.
	 */
	public BufferedImage grabImage(int row, int col, int width, int height) {

		BufferedImage img = image.getSubimage((col * width) - width, (row * height) - height, width, height);

		return img;

	}

}
