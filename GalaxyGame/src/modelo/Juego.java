package modelo;

import java.util.LinkedList;
import java.util.Random;

public class Juego {

	private Jugador jugador;
	private boolean pausa;
	private int enemigosRestantes;

	private LinkedList<Enemigo> enemigos = new LinkedList<>();

	public Juego() {

		pausa = false;
		jugador = new Jugador(this);
		

	}

	public boolean isPausa() {
		return pausa;
	}

	public void setPausa(boolean pausa) {
		this.pausa = pausa;
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
		
		enemigosRestantes++;
		
	}

	public int getEnemigosRestantes() {
		return enemigosRestantes;
	}

	public void setEnemigosRestantes(int enemigosRestantes) {
		this.enemigosRestantes = enemigosRestantes;
	}
}
