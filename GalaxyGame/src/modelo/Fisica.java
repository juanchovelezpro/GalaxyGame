package modelo;

import java.util.LinkedList;

public class Fisica {

	public static boolean colision(Objeto objeto, LinkedList<Enemigo> enemigos) {

		boolean colision = false;

		for (int i = 0; i < enemigos.size(); i++) {

			if (objeto.getBounds().intersects(enemigos.get(i).getBounds())) {

				colision = true;
				enemigos.remove(i);

			}

		}
		return colision;
	}

	public static boolean colision(Objeto objeto, Objeto objeto2) {

		boolean colision = false;

		if (objeto.getBounds().intersects(objeto2.getBounds())) {

			colision = true;

		}

		return colision;
	}

}
