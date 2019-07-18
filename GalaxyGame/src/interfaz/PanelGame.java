package interfaz;

import javax.swing.*;

import hilos.HiloDisparoJugador;
import modelo.Disparo;
import modelo.Enemigo;
import modelo.Fisica;
import modelo.Juego;
import modelo.Jugador;
import tools.ManagerFPS;
import tools.ScreenResolution;
import tools.ToolManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.LinkedList;

public class PanelGame extends JPanel implements KeyListener {

	public static final Image BACKGROUND = ToolManager.cargarImagen("fondos/galaxy3.jpg");

	private PanelOpciones opciones;

	public PanelGame(PanelOpciones opciones) {

		this.opciones = opciones;

		setBounds(ScreenResolution.WIDTH / 4, 0, ScreenResolution.WIDTH_GAME, ScreenResolution.HEIGHT_GAME);

		iniciar();

		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);

	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.drawImage(BACKGROUND, 0, 0, null);

		ManagerFPS.StartCounter();

		renderAccionesJugador(g);

		renderAccionesEnemigos(g);

		repaint();

		ManagerFPS.StopAndPost();

	}

	public void iniciar() {

		opciones.getGame().getHiloMovimientoJugador().start();
		opciones.getGame().getHiloDisparoJugador().start();

		opciones.getGame().getHiloMovimientoEnemigos().start();
		opciones.getGame().getHiloAlternarDisparoEnemigo().start();
		opciones.getGame().getHiloDisparoEnemigo().start();

	}
	
	public void reanudar() {
		
		opciones.getGame().getHiloMovimientoJugador().reanudar();
		opciones.getGame().getHiloDisparoJugador().reanudar();

		opciones.getGame().getHiloMovimientoEnemigos().reanudar();
		opciones.getGame().getHiloAlternarDisparoEnemigo().reanudar();
		opciones.getGame().getHiloDisparoEnemigo().reanudar();
		
	}

	@Override
	public void keyPressed(KeyEvent e) {

		int event = e.getKeyCode();

		Juego juego = opciones.getGame().getJuego();
		Jugador jugador = opciones.getGame().getJuego().getJugador();

		if (event == KeyEvent.VK_RIGHT || event == KeyEvent.VK_D) {

			jugador.setVelocidad(5);

		}

		if (event == KeyEvent.VK_LEFT || event == KeyEvent.VK_A) {

			jugador.setVelocidad(-5);

		}

		if (event == KeyEvent.VK_SPACE) {

			if(!juego.isPausa())
			jugador.agregarDisparo();

		}

		if (event == KeyEvent.VK_P) {

			if (!juego.isPausa()) {

				juego.setPausa(true);

			} else {

				juego.setPausa(false);
				reanudar();

			}

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

		int event = e.getKeyCode();

		Jugador jugador = opciones.getGame().getJuego().getJugador();

		if (event == KeyEvent.VK_RIGHT || event == KeyEvent.VK_D) {

			jugador.setVelocidad(0);

		}

		if (event == KeyEvent.VK_LEFT || event == KeyEvent.VK_A) {

			jugador.setVelocidad(0);
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public void renderAccionesJugador(Graphics g) {

		Jugador jugador = opciones.getGame().getJuego().getJugador();

		Image skinJugador = ToolManager.cargarImagen(jugador.getSkin());

		// Render del jugador.
		g.drawImage(skinJugador, jugador.getPosx(), jugador.getPosy(), null);

		// Render disparos del jugador.
		Disparo temp = null;
		for (int i = 0; i < jugador.getDisparos().size(); i++) {

			temp = jugador.getDisparos().get(i);

			Image skinDisparoJugador = ToolManager.cargarImagen(temp.getSkin());

			g.drawImage(skinDisparoJugador, temp.getPosx(), temp.getPosy(), null);

		}

	}

	public void renderAccionesEnemigos(Graphics g) {

		LinkedList<Enemigo> enemigos = opciones.getGame().getJuego().getEnemigos();
		// Render de los enemigos.
		Enemigo tempEnemy = null;
		for (int i = 0; i < enemigos.size(); i++) {

			tempEnemy = enemigos.get(i);

			Image skinEnemy = ToolManager.cargarImagen(tempEnemy.getSkin());

			g.drawImage(skinEnemy, tempEnemy.getPosx(), tempEnemy.getPosy(), null);

		}

		// Render disparos de enemigos.
		Disparo temp = null;
		for (int i = 0; i < enemigos.size(); i++) {

			if (i < enemigos.size()) {
				for (int j = 0; j < enemigos.get(i).getDisparos().size(); j++) {

					temp = enemigos.get(i).getDisparos().get(j);

					Image skinDisparoEnemy = ToolManager.cargarImagen(temp.getSkin());

					g.drawImage(skinDisparoEnemy, temp.getPosx(), temp.getPosy(), null);
				}

			}
		}
	}

}
