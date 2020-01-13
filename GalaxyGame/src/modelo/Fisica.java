package modelo;

import java.awt.Rectangle;
import java.util.LinkedList;

import tools.SoundPlayer;

/**
 * Esta clase permite verificar las interacciones fisicas entre objetos de tipo
 * {@code GameObject}
 * 
 * @author juanchovelezpro
 *
 */
public class Fisica {

	/**
	 * Verifica si un {@code GameObject} colisiona con algun {@code Enemigo} de una
	 * {@code LinkedList<Enemigo>}.
	 * 
	 * @param objeto   Un {@code GameObject}
	 * @param enemigos La {@code LinkedList} de enemigos
	 * @param juego    El {@code Juego} que contiene el objeto y los enemigos.
	 * @return {@code true} si el objeto y algun {@code Enemigo} de la
	 *         {@code LinkedList} han colisionado, {@code false} en caso contrario.
	 */
	public static boolean colision(GameObject objeto, LinkedList<Enemigo> enemigos, Juego juego) {

		boolean colision = false;

		for (int i = 0; i < enemigos.size(); i++) {

			if (objeto.getBounds().intersects(enemigos.get(i).getBounds())) {

				colision = true;
				SoundPlayer.play("/sounds/explosion.wav");

				enemigos.get(i).setSalud(enemigos.get(i).getSalud() - juego.getJugador().getDamage());

				if (enemigos.get(i).getSalud() <= 0) {

					enemigos.get(i).morir();

				}
			}

		}
		return colision;
	}

	/**
	 * Verifica si un {@code GameObject} colisiona con otro {@code GameObject}.
	 * 
	 * @param objeto  El {@code GameObject} uno.
	 * @param objeto2 El {@code GameObject} dos.
	 * @return {@code true} si los dos {@code GameObject} han colisionado,
	 *         {@code false} en caso contrario.
	 */
	public static boolean colision(GameObject objeto, GameObject objeto2) {

		boolean colision = false;

		if (objeto.getBounds().intersects(objeto2.getBounds())) {

			colision = true;
			SoundPlayer.play("/sounds/explosion.wav");

		}
		return colision;

	}
	
	/**
	 * Verifica si un {@code Jugador} colisiona con un {@code Potenciador}.
	 * @param jugador El {@code Jugador} con el que se va a verificar la colision.
	 * @param powerUp El {@code Potenciador} con el que se va a verificar la colision.
	 * @return Retorna {@code true} si se presenta la colision, {@code false} en caso contrario.
	 */
	public static boolean colision(Jugador jugador, Potenciador powerUp) {
		
		boolean colision = false;
		
		if(jugador.getBounds().intersects(powerUp.getBounds())) {
			
			colision = true;
			SoundPlayer.play(powerUp.getSonido());
			
		}
		
		
		return colision;
	}

	/**
	 * Retorna si la vision de algun {@code GameObject} intersecta, es decir detecta
	 * algun disparo de la lista.
	 * 
	 * @param vision   La vision del {@code GameObject}.
	 * @param disparos Una lista de disparos de un {@code GameObject}.
	 * @return Retorna si la vision detecta algun {@code Disparo}.
	 */
	public static boolean detect(Rectangle vision, LinkedList<Disparo> disparos) {

		boolean detected = false;

		for (int i = 0; i < disparos.size(); i++) {

			if (vision.getBounds().intersects(disparos.get(i).getBounds())) {

				detected = true;

			}

		}

		return detected;

	}

	/**
	 * Retorna si la vision de un {@code Enemigo} detecta a otro {@code Enemigo}.
	 * 
	 * @param enemigo  El {@code Enemigo} con el que se detectara a otro
	 *                 {@code Enemigo}
	 * @param enemigos La lista de enemigos.
	 * @return {@code true} la vision del {@code Enemigo} ha detectado a otro
	 *         {@code Enemigo}.
	 */
	public static boolean detect(Enemigo enemigo, LinkedList<Enemigo> enemigos) {

		boolean detected = false;

		for (int i = 0; i < enemigos.size(); i++) {

			if (enemigo.getVision().getBounds().intersects(enemigos.get(i).getBounds())) {

				detected = true;

			}

		}

		return detected;

	}

}
