package vista;

import javax.swing.*;

import hilos.HiloJuego;
import modelo.Juego;

import java.awt.*;

public class Game extends JFrame {

	
	private Juego juego;
	private PanelOpciones opciones;
	private HiloJuego hiloJuego;

	public Game() {

		setTitle("Galaxy Game");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(850, 850);
		setResizable(false);

		
		juego = new Juego();
		
		hiloJuego = new HiloJuego(juego, this);
		
		opciones = new PanelOpciones(this);

		add(opciones);

		setLocationRelativeTo(null);

		setVisible(true);

	}
	
	public Juego getJuego() {
		
		return juego;
		
	}
	
	public HiloJuego getHiloJuego() {
		
	return hiloJuego;	
		
	}
	
	public void refresh() {
		
	invalidate();
	revalidate();
	repaint();
		
	}

}
