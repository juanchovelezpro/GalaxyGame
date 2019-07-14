package vista;

import javax.swing.*;

import hilos.HiloDisparoJugador;
import hilos.HiloMovimientoEnemigos;
import modelo.Juego;

import java.awt.*;

public class Game extends JFrame {

	
	private Juego juego;
	private PanelOpciones opciones;
	private HiloDisparoJugador hiloDisparoJugador;
	private HiloMovimientoEnemigos hiloMovimientoEnemigos;

	public Game() {

		setTitle("Galaxy Game");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(850, 850);
		setResizable(false);

		
		juego = new Juego();
		
		hiloDisparoJugador = new HiloDisparoJugador(juego);
		hiloMovimientoEnemigos = new HiloMovimientoEnemigos(juego);
		
		opciones = new PanelOpciones(this);

		add(opciones);

		setLocationRelativeTo(null);

		setVisible(true);

	}
	
	public HiloMovimientoEnemigos getHiloMovimientoEnemigos() {
		return hiloMovimientoEnemigos;
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
