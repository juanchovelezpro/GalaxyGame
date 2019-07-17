package modelo;

import java.util.LinkedList;
import java.util.Random;

public class Juego {

	private Jugador jugador;
	private LinkedList<Enemigo> enemigos = new LinkedList<>();

	public Juego() {

		jugador = new Jugador(this);
		spawnEnemigos(15);

	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public LinkedList<Enemigo> getEnemigos() {
		return enemigos;
	}

	public void setEnemigos(LinkedList<Enemigo> enemigos) {
		this.enemigos = enemigos;
	}

	public void spawnEnemigos(int cantidad) {
		for (int i = 0; i < cantidad; i++) {
			agregarEnemigo();
		}

	}

	public void agregarEnemigo() {

		Random r = new Random();

		enemigos.add(new Enemigo(r.nextInt(6), this));
	}
}
