package modelo;

import java.util.LinkedList;

public class Juego {

	private Jugador jugador;
	private LinkedList<Enemigo> enemigos = new LinkedList<>();

	public Juego() {

		jugador = new Jugador(345, 740, 80, 80, 15, null, 0, this);

		spawnEnemigos();

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

	public void spawnEnemigos() {

	enemigos.add(new Enemigo(200, 200, 80, 80, 10, 1));
	enemigos.add(new Enemigo(400, 400, 80, 80, 10, 1));

	}

}
