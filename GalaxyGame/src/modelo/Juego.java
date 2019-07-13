package modelo;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class Juego {
	private Jugador jugador;
	private LinkedList<Enemigo> enemigos = new LinkedList<>();
	public Juego() {

		jugador = new Jugador(345, 740, 80, 80, 10, "",0);

		
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
	
}
