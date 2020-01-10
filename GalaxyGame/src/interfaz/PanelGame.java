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
import tools.ImageLoader;

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

	public static final Image BACKGROUND = ImageLoader.cargarImagen("fondos/galaxy3.jpg");

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

		if (!opciones.getGame().getJuego().isPausa())
			renderHUD();

		renderJugador(g);

		renderEnemigos(g);

		renderExplosiones(g);

		repaint();

		ManagerFPS.StopAndPost();

	}

	public void iniciar() {

		for (int i = 0; i < opciones.getGame().getThreads().size(); i++) {

			opciones.getGame().getThreads().get(i).start();

		}

	}

	public void reanudar() {

		for (int i = 0; i < opciones.getGame().getThreads().size(); i++) {

			opciones.getGame().getThreads().get(i).reanudar();

		}

	}

	@Override
	public void keyPressed(KeyEvent e) {

		int event = e.getKeyCode();

		Juego juego = opciones.getGame().getJuego();
		Jugador jugador = opciones.getGame().getJuego().getJugador();

		if (event == KeyEvent.VK_RIGHT || event == KeyEvent.VK_D) {

			jugador.setVelX(2);

		}

		if (event == KeyEvent.VK_LEFT || event == KeyEvent.VK_A) {

			jugador.setVelX(-2);

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

			jugador.setVelX(0);

		}

		if (event == KeyEvent.VK_LEFT || event == KeyEvent.VK_A) {

			jugador.setVelX(0);
		}

		if (event == KeyEvent.VK_SPACE) {

			jugador.setRecargaDisparo(false);

		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public void renderHUD() {

		opciones.getLabEnemigosRestantes()
				.setText("Enemigos restantes: " + opciones.getGame().getJuego().getEnemigosRestantes());
		opciones.getLabFPS().setText("FPS: " + ManagerFPS.fps);

	}

	public void renderExplosiones(Graphics g) {

		LinkedList<Explosion> explosiones = opciones.getGame().getJuego().getExplosiones();

		for (int i = 0; i < explosiones.size(); i++) {

			Explosion temp = explosiones.get(i);

			if (temp.isAlive()) {

				temp.getAnimation().drawAnimation(g, temp.getX(), temp.getY() - 30, 25);

			}
		}

	}

	public void renderJugador(Graphics g) {

		opciones.getGame().getJuego().getJugador().render(g);

	}

	public void renderEnemigos(Graphics g) {

		for (int i = 0; i < opciones.getGame().getJuego().getEnemigos().size(); i++) {

			opciones.getGame().getJuego().getEnemigos().get(i).render(g);

		}

	}

}
