package modelo;

import java.util.LinkedList;

public class Fisica {

	public static boolean colision(Objeto disparo, LinkedList<Enemigo> enemigos) {

		boolean colision = false;

		for (int i = 0; i < enemigos.size(); i++) {

			if (disparo.getBounds().intersects(enemigos.get(i).getBounds())) {

				colision = true;

			}

		}
		return colision;
	}

}
