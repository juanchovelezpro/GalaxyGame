package vista;

import javax.swing.*;

import hilos.HiloDisparoJugador;
import modelo.Disparo;
import modelo.Enemigo;
import modelo.Fisica;
import modelo.Juego;
import modelo.Jugador;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

public class PanelGame extends JPanel implements KeyListener {

	public static final Image BACKGROUND = Toolkit.getDefaultToolkit().createImage("./resources/galaxy3.jpg");
	public static final Image JUGADOR = Toolkit.getDefaultToolkit().createImage("./resources/nave_jugador.png");
	public static final Image DISPARO = Toolkit.getDefaultToolkit().createImage("./resources/laserJugador.png");
	public static final Image DISPARO_ENEMIGO = Toolkit.getDefaultToolkit().createImage("./resources/laserEnemigo.png");
	public static final Image ENEMIGO = Toolkit.getDefaultToolkit().createImage("./resources/nave_enemigo.png");

	private PanelOpciones opciones;

	public PanelGame(PanelOpciones opciones) {

		this.opciones = opciones;

		opciones.getGame().getHiloDisparoJugador().start();
		opciones.getGame().getHiloDisparoEnemigo().start();

		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);

	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.drawImage(BACKGROUND, 0, 0, null);

		renderAccionesJugador(g);

		renderAccionesEnemigos(g);

		repaint();

	}

	@Override
	public void keyPressed(KeyEvent e) {

		Jugador jugador = opciones.getGame().getJuego().getJugador();

		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {

			jugador.setVelocidad(5);

		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {

			jugador.setVelocidad(-5);

		}

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {

			jugador.agregarDisparo();

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

		Jugador jugador = opciones.getGame().getJuego().getJugador();

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

			jugador.setVelocidad(0);

		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {

			jugador.setVelocidad(0);
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public void renderAccionesJugador(Graphics g) {

		Jugador jugador = opciones.getGame().getJuego().getJugador();

		// Render del jugador.
		g.drawImage(JUGADOR, jugador.getPosx(), jugador.getPosy(), null);

		// Render disparos del jugador.
		Disparo temp = null;
		for (int i = 0; i < jugador.getDisparos().size(); i++) {

			temp = jugador.getDisparos().get(i);

			g.drawImage(DISPARO, temp.getPosx(), temp.getPosy(), null);

		}

	}

	public void renderAccionesEnemigos(Graphics g) {

		LinkedList<Enemigo> enemigos = opciones.getGame().getJuego().getEnemigos();
		// Render de los enemigos.
		Enemigo tempEnemy = null;
		for (int i = 0; i < enemigos.size(); i++) {

			tempEnemy = enemigos.get(i);
			g.drawImage(ENEMIGO, tempEnemy.getPosx(), tempEnemy.getPosy(), null);

		}

		
		// Render disparos de enemigos.
		Disparo temp = null;
		for (int i = 0; i < enemigos.size(); i++) {
			
		for (int j = 0; j < enemigos.get(i).getDisparos().size(); j++) {

			temp = enemigos.get(i).getDisparos().get(j);

			g.drawImage(DISPARO_ENEMIGO, temp.getPosx(), temp.getPosy(), null);
		}
		
	}
	}

}
