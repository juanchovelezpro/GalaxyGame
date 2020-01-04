package interfaz;

import javax.swing.*;

import animation.Animation;
import hilos.HiloDisparoJugador;
import modelo.Disparo;
import modelo.Enemigo;
import modelo.Explosion;
import modelo.Fisica;
import modelo.Juego;
import modelo.Jugador;
import tools.ManagerFPS;
import tools.ScreenResolution;
import tools.SoundPlayer;
import tools.ToolManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class PanelGame extends JPanel implements KeyListener {

	public static final Image BACKGROUND = ToolManager.cargarImagen("fondos/galaxy3.jpg");

	private PanelOpciones opciones;
	private PanelPausa panelPausa;

	public PanelGame(PanelOpciones opciones) {

		this.opciones = opciones;

		setBounds(ScreenResolution.WIDTH / 4, 0, ScreenResolution.WIDTH_GAME, ScreenResolution.HEIGHT_GAME);

		iniciar();

		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);

	}

	public PanelOpciones getOpciones() {
		return opciones;
	}

	public void setOpciones(PanelOpciones opciones) {
		this.opciones = opciones;
	}

	public PanelPausa getPanelPausa() {
		return panelPausa;
	}

	public void setPanelPausa(PanelPausa panelPausa) {
		this.panelPausa = panelPausa;
	}

	@Override
	public void paintComponent(Graphics g) {

		ManagerFPS.StartCounter();

		super.paintComponent(g);

		g.drawImage(BACKGROUND, 0, 0, null);

		opciones.getLabEnemigosRestantes()
				.setText("Enemigos restantes: " + opciones.getGame().getJuego().getEnemigosRestantes());
		opciones.getLabFPS().setText("FPS: " + ManagerFPS.fps);

		renderAccionesJugador(g);

		renderAccionesEnemigos(g);

		renderExplosiones(g);

		repaint();

		ManagerFPS.StopAndPost();

	}

	public void iniciar() {

		opciones.getGame().getHiloMovimientoJugador().start();
		opciones.getGame().getHiloDisparoJugador().start();
		opciones.getGame().getHiloRevivirJugador().start();

		opciones.getGame().getHiloDesplegarEnemigos().start();

		opciones.getGame().getHiloMovimientoEnemigos().start();
		opciones.getGame().getHiloAlternarDisparoEnemigo().start();
		opciones.getGame().getHiloDisparoEnemigo().start();

	}

	public void reanudar() {

		opciones.getGame().getHiloRevivirJugador().reanudar();
		opciones.getGame().getHiloDesplegarEnemigos().reanudar();
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

			jugador.setVelocidad(2);

		}

		if (event == KeyEvent.VK_LEFT || event == KeyEvent.VK_A) {

			jugador.setVelocidad(-2);

		}

		if (event == KeyEvent.VK_SPACE && !juego.getJugador().isRecargaDisparo()) {

			if (!juego.isPausa()) {

				if (jugador.isVivo()) {
					jugador.agregarDisparo();

					SoundPlayer.play("/sounds/laser.wav");

					jugador.setRecargaDisparo(true);
				}
			}
		}

		if (event == KeyEvent.VK_P) {

			if (!juego.isPausa()) {

				panelPausa = new PanelPausa(this);
				juego.setPausa(true);
				add(panelPausa);

			} else {

				juego.setPausa(false);
				reanudar();
				remove(panelPausa);
				panelPausa = null;
			}

		}

	}

	public PanelOpciones getPanelOpciones() {
		return opciones;
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

		if (event == KeyEvent.VK_SPACE) {

			jugador.setRecargaDisparo(false);

		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public void renderExplosiones(Graphics g) {

		LinkedList<Explosion> explosiones = opciones.getGame().getJuego().getExplosiones();

		Explosion temp = null;

		for (int i = 0; i < explosiones.size(); i++) {

			temp = explosiones.get(i);

			if (temp.isAlive()) {

				temp.getAnimation().drawAnimation(g, temp.getX(), temp.getY() - 30, 25);

			}
		}

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

			// Render disparo de los enemigos
			Disparo temp = null;
			for (int j = 0; j < tempEnemy.getDisparos().size(); j++) {

				temp = tempEnemy.getDisparos().get(j);

				Image skinDisparoEnemy = ToolManager.cargarImagen(temp.getSkin());

				g.drawImage(skinDisparoEnemy, temp.getPosx(), temp.getPosy(), null);

			}

		}

	}

}
