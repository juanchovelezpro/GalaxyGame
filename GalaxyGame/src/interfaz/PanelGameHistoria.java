package interfaz;

import javax.swing.*;

import modelo.Bloque;
import modelo.Bloque.ID;
import modelo.Camara;
import modelo.GameObject;
import modelo.Juego;
import modelo.Jugador;
import tools.GameManager;
import tools.SoundPlayer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class PanelGameHistoria extends JPanel implements KeyListener {

	public static final Image BACKGROUND = GameManager.imagenes.get("galaxy2");

	private PanelOpciones opciones;

	public PanelGameHistoria(PanelOpciones opciones) {

		this.opciones = opciones;
		setBounds(0, 0, GameManager.WIDTH, GameManager.HEIGHT);
		cargarMapa(GameManager.sprites.get("MAPA"));
		opciones.getGame().getJuego().iniciarProcesos();
		opciones.getGame().getJuego().getCamara().setX(opciones.getGame().getJuego().getJugador().getX());
		opciones.getGame().getJuego().getCamara().setY(opciones.getGame().getJuego().getJugador().getY());

		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);

	}

	public void cargarMapa(BufferedImage mapa) {

		int ancho = mapa.getWidth();
		int alto = mapa.getHeight();

		for (int x = 0; x < ancho; x++) {

			for (int y = 0; y < alto; y++) {

				int pixel = mapa.getRGB(x, y);

				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				if (red == 255) {

					opciones.getGame().getJuego().getObjetos()
							.add(new Bloque(ID.ROCA, x, y, opciones.getGame().getJuego()));

				}

				if (blue == 255) {

					opciones.getGame().getJuego().getObjetos()
							.add(new Bloque(ID.BRICK, x, y, opciones.getGame().getJuego()));

				}

				if (green == 255) {

					Jugador jugador = new Jugador(opciones.getGame().getJuego());
					jugador.setX(x * jugador.getAncho());
					jugador.setY(y * jugador.getAltura());

					opciones.getGame().getJuego().setJugador(jugador);
				}
			}
		}

	}

	public void renderMapa(Graphics g) {

		for (int i = 0; i < opciones.getGame().getJuego().getObjetos().size(); i++) {

			GameObject temp = opciones.getGame().getJuego().getObjetos().get(i);

			temp.render(g);

		}

	}

	public void renderJugador(Graphics g) {

		opciones.getGame().getJuego().getJugador().render(g);

	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Camara cam = opciones.getGame().getJuego().getCamara();

		g.drawImage(BACKGROUND, 0, 0, null);

		Graphics2D g2 = (Graphics2D) g;

		g2.translate(-cam.getX(), -cam.getY());

		renderMapa(g);
		renderJugador(g);

		g2.translate(cam.getX(), cam.getY());

		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int event = e.getKeyCode();

		Juego juego = opciones.getGame().getJuego();
		Jugador jugador = opciones.getGame().getJuego().getJugador();

		if (event == KeyEvent.VK_RIGHT || event == KeyEvent.VK_D) {

			jugador.setVelX(1);

		}

		if (event == KeyEvent.VK_LEFT || event == KeyEvent.VK_A) {

			jugador.setVelX(-1);

		}
		
		if(event == KeyEvent.VK_UP) {
			
			jugador.setVelY(-1);
			
		}
		
		if(event == KeyEvent.VK_DOWN) {
			
			jugador.setVelY(1);
			
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

	}

	@Override
	public void keyReleased(KeyEvent e) {

		
		int event = e.getKeyCode();

		Juego juego = opciones.getGame().getJuego();
		Jugador jugador = opciones.getGame().getJuego().getJugador();

		if (event == KeyEvent.VK_RIGHT || event == KeyEvent.VK_D) {

			jugador.setVelX(0);

		}

		if (event == KeyEvent.VK_LEFT || event == KeyEvent.VK_A) {

			jugador.setVelX(0);

		}
		
		if(event == KeyEvent.VK_UP) {
			
			jugador.setVelY(0);
			
		}
		
		if(event == KeyEvent.VK_DOWN) {
			
			jugador.setVelY(0);
			
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
