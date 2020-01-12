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
	 * @return {@code true} si el objeto y algun {@code Enemigo} de la
	 *         {@code LinkedList} han colisionado, {@code false} en caso contrario.
	 */
	public static boolean colision(GameObject objeto, LinkedList<Enemigo> enemigos) {

		boolean colision = false;

		for (int i = 0; i < enemigos.size(); i++) {

			if (objeto.getBounds().intersects(enemigos.get(i).getBounds())) {

				colision = true;
				SoundPlayer.play("/sounds/explosion.wav");

				enemigos.get(i).setVida(enemigos.get(i).getVida() - 1);

				if (enemigos.get(i).getVida() <= 0) {

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

	public static boolean detect(Rectangle vision, LinkedList<Disparo> disparos) {

		boolean detected = false;

		for (int i = 0; i < disparos.size(); i++) {

			if (vision.getBounds().intersects(disparos.get(i).getBounds())) {

				detected = true;

			}

		}

		return detected;

	}

}
