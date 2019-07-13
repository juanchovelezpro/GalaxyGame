package vista;

import javax.swing.*;

import modelo.Juego;

import java.awt.*;

public class Game extends JFrame {

	
	private Juego juego;
	private PanelOpciones opciones;

	public Game() {

		setTitle("Galaxy Game");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(850, 1000);
		setResizable(false);

		
		juego = new Juego();
		
		opciones = new PanelOpciones(this);

		add(opciones);

		setLocationRelativeTo(null);

		setVisible(true);

	}
	
	public Juego getJuego() {
		
		return juego;
		
	}
	
	public void refresh() {
		
	invalidate();
	revalidate();
	repaint();
		
	}

}
