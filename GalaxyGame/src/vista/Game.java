package vista;

import javax.swing.*;
import java.awt.*;


public class Game extends JFrame{

	
	private PanelOpciones opciones;
	
	
	public Game() {
		
	setTitle("Galaxy Game");
	setLayout(new BorderLayout());
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setSize(850,1000);
	setResizable(false);

	
	opciones = new PanelOpciones(this);
	
	
	add(opciones);
	
	
	
	setLocationRelativeTo(null);
	
	
	
	
	
	setVisible(true);
	
	
		
	}
	
	
}
