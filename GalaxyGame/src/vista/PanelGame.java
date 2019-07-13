package vista;

import javax.swing.*;

import hilos.HiloJuego;
import modelo.Jugador;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PanelGame extends JPanel implements KeyListener {

	public static final Image BACKGROUND = Toolkit.getDefaultToolkit().createImage("./resources/galaxy3.jpg");
	public static final Image JUGADOR = Toolkit.getDefaultToolkit().createImage("./resources/nave_jugador.png");
	public static final Image DISPARO = Toolkit.getDefaultToolkit().createImage("./resources/laserJugador.png");

	private PanelOpciones opciones;

	public PanelGame(PanelOpciones opciones) {

		this.opciones = opciones;

		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);

	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.drawImage(BACKGROUND, 0, 0, null);

		Jugador jugador = opciones.getGame().getJuego().getJugador();

		g.drawImage(JUGADOR, jugador.getPosx(), jugador.getPosy(), null);
		g.drawImage(DISPARO, jugador.getDisparoTemporal().getPosx(),  jugador.getDisparoTemporal().getPosy(), jugador.getDisparoTemporal().getAncho(),  jugador.getDisparoTemporal().getAltura(), null);

		repaint();

	}

	@Override
	public void keyPressed(KeyEvent e) {

		Jugador jugador = opciones.getGame().getJuego().getJugador();
		HiloJuego hilo = opciones.getGame().getHiloJuego();

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

			jugador.irDerecha();

		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {

			jugador.irIzquierda();

		}

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {

			if (hilo.isAlive()) {

				jugador.agregarDisparo();

			} else {

				jugador.agregarDisparo();
				hilo.start();

			}

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
