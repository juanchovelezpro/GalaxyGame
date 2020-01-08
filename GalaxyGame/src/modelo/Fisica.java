package modelo;

import java.util.LinkedList;

import tools.SoundPlayer;

public class Fisica {

	public static boolean colision(GameObject objeto, LinkedList<Enemigo> enemigos) {

		boolean colision = false;

		for (int i = 0; i < enemigos.size(); i++) {

			if (objeto.getBounds().intersects(enemigos.get(i).getBounds())) {

				colision = true;
				SoundPlayer.play("/sounds/explosion.wav");
				
				enemigos.get(i).setVida(enemigos.get(i).getVida()-1);

				if (enemigos.get(i).getVida() <= 0) {
					
					enemigos.get(i).morir();
					
				}
			}

		}
		return colision;
	}

	public static boolean colision(GameObject objeto, GameObject objeto2) {

		boolean colision = false;

		if (objeto.getBounds().intersects(objeto2.getBounds())) {

			colision = true;
			SoundPlayer.play("/sounds/explosion.wav");

		}

		return colision;
	}

}
