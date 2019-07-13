package modelo;

import java.util.LinkedList;

public class Fisica {

	public static boolean colision(Objeto uno, LinkedList<Enemigo> dos) {

		boolean colision = false;

		for (int i = 0; i < dos.size(); i++) {

			if (uno.getBounds().intersects(dos.get(i).getBounds())) {

				colision = true;

			}

		}
		return colision;
	}

}
