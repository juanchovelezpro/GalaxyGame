package animation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This class load an image.
 * @author Juan Camilo Vélez Olaya
 *
 */
public class BufferedImageLoader {

	/**
	 * The image that will be loaded.
	 */
	private BufferedImage image;

	/**
	 * Constructor to create an Image Loader 
	 */
	public BufferedImageLoader() {

	}

	/**
	 * This method load the image.
	 * @param path The file (image) that will be read as a image.
	 * @return The image loaded
	 * @throws IOException If the file it is not an image.
	 */
	public BufferedImage loadImage(String path) throws IOException {

		image = ImageIO.read(new File(path));

		return image;

	}

}
