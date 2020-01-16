package interfaz;

import javax.swing.*;

import hilos.HiloAbstract;
import hilos.HiloAlternarDisparoEnemigo;
import hilos.HiloAlternarEsquivarEnemigo;
import hilos.HiloDesplegarEnemigos;
import hilos.HiloDisparoEnemigo;
import hilos.HiloDisparoJugador;
import hilos.HiloMovimientoEnemigos;
import hilos.HiloMovimientoJugador;
import hilos.HiloMovimientoPotenciadores;
import hilos.HiloRevivirJugador;
import modelo.Explosion;
import modelo.Juego;
import modelo.Juego.MODO;
import tools.GameManager;

import java.awt.*;
import java.util.LinkedList;

public class Game extends JFrame {

	private Juego juego;
	private PanelOpciones opciones;

	public Game() {

		GameManager.loadResources();

		setTitle("Galaxy Game");
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(GameManager.WIDTH, GameManager.HEIGHT);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setIconImage(GameManager.imagenes.get("icono"));

		opciones = new PanelOpciones(this);

		setContentPane(opciones);

		setLocationRelativeTo(null);



	}

	public Juego getJuego() {
		return juego;
	}

	public void setJuego(Juego juego) {
		this.juego = juego;
	}

	public void refresh() {

		invalidate();
		revalidate();
		repaint();

	}

}
