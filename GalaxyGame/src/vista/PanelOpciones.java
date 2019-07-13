package vista;

import javax.swing.*;
import java.awt.*;

public class PanelOpciones extends JPanel {

	public static final Image BACKGROUND = Toolkit.getDefaultToolkit().createImage("./resources/galaxy2.jpg");
	
	
	private Game game;
	private JButton butJugar;
	
	
	public PanelOpciones(Game game) {

		this.game = game;

		setLayout(null);
		
		componentes();
		

	}
	
	
	public void componentes() {
	
	Font fuente= new Font("Garamond", 1, 36);	
	Color background = new Color(255,255,255,125);	
		
	butJugar = new JButton("JUGAR");
	butJugar.setFont(fuente);
	butJugar.setBackground(background);
	butJugar.setBounds(200,250,450,60);
	
	add(butJugar);
		
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
	super.paintComponent(g);
	
	
	g.drawImage(BACKGROUND, 0, 0, null);
	
	repaint();
	
	
	}

}
