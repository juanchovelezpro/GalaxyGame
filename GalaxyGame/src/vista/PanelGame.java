package vista;

import javax.swing.*;
import java.awt.*;

public class PanelGame extends JPanel {

	public static final Image BACKGROUND = Toolkit.getDefaultToolkit().createImage("./resources/galaxy3.jpg");

	public PanelGame() {

	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.drawImage(BACKGROUND, 0, 0, null);

		repaint();

	}

}
