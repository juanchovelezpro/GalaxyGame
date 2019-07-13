package vista;

import javax.swing.*;

import hilos.HiloDisparoJugador;
import modelo.Juego;

import java.awt.*;

public class Game extends JFrame {

	
	private Juego juego;
	private PanelOpciones opciones;
	private HiloDisparoJugador hiloDisparoJugador;

	public Game() {

		setTitle("Galaxy Game");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(850, 850);
		setResizable(false);

		
		juego = new Juego();
		
		hiloDisparoJugador = new HiloDisparoJugador(juego);
		
		opciones = new PanelOpciones(this);

		add(opciones);

		setLocationRelativeTo(null);

		setVisible(true);

	}
	
	public Juego getJuego() {
		
		return juego;
		
	}
	
	public HiloDisparoJugador getHiloDisparoJugador() {
		
	return hiloDisparoJugador;	
		
	}
	
	public void refresh() {
		
	invalidate();
	revalidate();
	repaint();
		
	}

}
