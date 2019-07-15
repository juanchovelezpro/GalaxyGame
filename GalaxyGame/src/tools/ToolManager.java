package tools;

import javax.swing.*;
import java.awt.*;

public class ToolManager {

	public static Image cargarImagen(String ruta) {
		
		Image image = null;
		
		ImageIcon icon = new ImageIcon(ToolManager.class.getClassLoader().getResource(ruta));
		
		image = icon.getImage();
		
		return image;
		
	}
	
}
