package interfaz;

import javax.swing.*;

import hilos.HiloAlternarDisparoEnemigo;
import hilos.HiloDesplegarEnemigos;
import hilos.HiloDisparoEnemigo;
import hilos.HiloDisparoJugador;
import hilos.HiloMovimientoEnemigos;
import hilos.HiloMovimientoJugador;
import modelo.Juego;
import tools.ScreenResolution;
import tools.ToolManager;

import java.awt.*;

public class Game extends JFrame {

	private Juego juego;
	private PanelOpciones opciones;
	private HiloDisparoJugador hiloDisparoJugador;
	private HiloMovimientoJugador hiloMovimientoJugador;
	private HiloMovimientoEnemigos hiloMovimientoEnemigos;
	private HiloDisparoEnemigo hiloDisparoEnemigo;
	private HiloAlternarDisparoEnemigo hiloAlternarDisparoEnemigo;
	private HiloDesplegarEnemigos hiloDesplegarEnemigos;

	public Game() {

		setTitle("Galaxy Game");
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(ScreenResolution.WIDTH, ScreenResolution.HEIGHT);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setIconImage(ToolManager.cargarImagen("iconos/icon2.jpg"));

		juego = new Juego();

		crearProcesos(juego);

		opciones = new PanelOpciones(this);

		setContentPane(opciones);

		setLocationRelativeTo(null);

	}

	public void crearProcesos(Juego juego) {

		hiloMovimientoJugador = new HiloMovimientoJugador(juego);
		hiloDisparoJugador = new HiloDisparoJugador(juego);
		hiloMovimientoEnemigos = new HiloMovimientoEnemigos(juego);
		hiloAlternarDisparoEnemigo = new HiloAlternarDisparoEnemigo(juego);
		hiloDisparoEnemigo = new HiloDisparoEnemigo(juego);
		hiloDesplegarEnemigos = new HiloDesplegarEnemigos(juego, 10);

	}

	public HiloMovimientoJugador getHiloMovimientoJugador() {
		return hiloMovimientoJugador;
	}

	public HiloAlternarDisparoEnemigo getHiloAlternarDisparoEnemigo() {
		return hiloAlternarDisparoEnemigo;
	}

	public HiloDisparoEnemigo getHiloDisparoEnemigo() {
		return hiloDisparoEnemigo;
	}

	public HiloMovimientoEnemigos getHiloMovimientoEnemigos() {
		return hiloMovimientoEnemigos;
	}

	public HiloDisparoJugador getHiloDisparoJugador() {

		return hiloDisparoJugador;

	}

	public HiloDesplegarEnemigos getHiloDesplegarEnemigos() {
		return hiloDesplegarEnemigos;
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
