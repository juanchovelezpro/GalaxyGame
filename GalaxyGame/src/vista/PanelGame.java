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

public class PanelGame extends JPanel implements KeyListener {

	public static final Image BACKGROUND = Toolkit.getDefaultToolkit().createImage("./resources/galaxy3.jpg");
	public static final Image JUGADOR = Toolkit.getDefaultToolkit().createImage("./resources/nave_jugador.png");
	public static final Image DISPARO = Toolkit.getDefaultToolkit().createImage("./resources/laserJugador.png");

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

		Jugador jugador = opciones.getGame().getJuego().getJugador();

		// Render del jugador.
		g.drawImage(JUGADOR, jugador.getPosx(), jugador.getPosy(), null);

		
		renderEnemigos(g);
		
		renderDisparosEnemigos(g);
		
		renderDisparosJugador(g);

		repaint();

	}

	@Override
	public void keyPressed(KeyEvent e) {

		Jugador jugador = opciones.getGame().getJuego().getJugador();

		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {

			jugador.setVelocidad(16);
			jugador.irDerecha();

		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {

			jugador.setVelocidad(16);
			jugador.irIzquierda();

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
		// TODO Auto-generated method stub

	}

	public void renderEnemigos(Graphics g) {

		// Render enemigo prueba.
		Enemigo tempEnemy = null;
		for (int i = 0; i < opciones.getGame().getJuego().getEnemigos().size(); i++) {

			tempEnemy = opciones.getGame().getJuego().getEnemigos().get(i);
			g.drawImage(JUGADOR, tempEnemy.getPosx(), tempEnemy.getPosy(), null);

		}

	}

	public void renderDisparosJugador(Graphics g) {

		Jugador jugador = opciones.getGame().getJuego().getJugador();

		// Render disparos del jugador.
		Disparo temp = null;
		for (int i = 0; i < jugador.getDisparos().size(); i++) {

			temp = jugador.getDisparos().get(i);

			g.drawImage(DISPARO, temp.getPosx(), temp.getPosy(), null);

		}

	}
	
	public void renderDisparosEnemigos(Graphics g) {
		
		Juego juego = opciones.getGame().getJuego();
		
	
		Disparo temp = null;
		
		for(int i = 0; i< juego.getEnemigos().get(0).getDisparos().size(); i++) {
			
			temp = juego.getEnemigos().get(0).getDisparos().get(i);
			
			g.drawImage(DISPARO, temp.getPosx(), temp.getPosy(), null);
		}
	
			
			
		}
	

}
