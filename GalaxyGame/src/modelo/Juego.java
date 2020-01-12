package modelo;

import java.util.LinkedList;
import java.util.Random;

/**
 * Esta clase se encarga de manipular los objetos {@code Jugador} y {@code Enemigo}. Asi como pausar o reanudar el juego. 
 * @author juanchovelezpro
 *
 */
public class Juego {

	/**
	 * El {@code Jugador} del {@code Juego}.
	 */
	private Jugador jugador;

	/**
	 * Para verificar si el {@code Juego} se encuentra en pausa.
	 */
	private boolean pausa;

	/**
	 * Una lista de enemigos.
	 */
	private LinkedList<Enemigo> enemigos;

	/**
	 * Una lista de explosiones.
	 */
	private LinkedList<Explosion> explosiones;

	/**
	 * La cantidad de enemigos restantes.
	 */
	private int enemigosRestantes;

	/**
	 * Constructor que crea un {@code Juego} con un {@code Jugador} y una lista de enemigos y explosiones.
	 */
	public Juego() {

		pausa = false;
		jugador = new Jugador(this);
		explosiones = new LinkedList<>();
		enemigos = new LinkedList<>();

	}

	/**
	 * Retorna la lista de explosiones del {@code Juego}.
	 * @return
	 */
	public LinkedList<Explosion> getExplosiones() {
		return explosiones;
	}

	/**
	 * Modifica la lista de explosiones del {@code Juego}.
	 * @param explosiones La nueva lista de explosiones del {@code Juego}.
	 */
	public void setExplosiones(LinkedList<Explosion> explosiones) {
		this.explosiones = explosiones;
	}

	/**
	 * Retorna si el {@code Juego} se encuentra pausado.
	 * @return {@code true} si el {@code Juego} se encuentra pausado, {@code false} en caso contrario.
	 */
	public boolean isPausa() {
		return pausa;
	}

	/**
	 * Modificar el estado de pausa del {@code Juego}.
	 * @param pausa El nuevo estado de pausa del {@code Juego}, {@code true} pausa el {@code Juego}, {@code false} en caso contrario.
	 */
	public void setPausa(boolean pausa) {
		this.pausa = pausa;
	}

	/**
	 * Retorna el {@code Jugador} del {@code Juego}.
	 * @return El {@code Jugador} del {@code Juego}.
	 */
	public Jugador getJugador() {
		return jugador;
	}

	/**
	 * Modifica el {@code Jugador} del {@code Juego}.
	 * @param jugador El nuevo {@code Jugador} del {@code Juego}.
	 */
	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	/**
	 * Retorna los enemigos del {@code Juego} en una lista.
	 * @return Una lista de enemigos.
	 */
	public LinkedList<Enemigo> getEnemigos() {
		return enemigos;
	}

	/**
	 * Modifica la lista de enemigos del {@code Juego}.
	 * @param enemigos La nueva lista de enemigos del {@code Juego}.
	 */
	public void setEnemigos(LinkedList<Enemigo> enemigos) {
		this.enemigos = enemigos;
	}

	/**
	 * Crea y agrega un enemigo de tipo aleatorio a la lista de enemigos del {@code Juego}. 
	 */
	public void agregarEnemigo() {

		Random r = new Random();

		enemigos.add(new Enemigo(r.nextInt(2) + 1, this));

		enemigosRestantes++;

	}

	/**
	 * Retorna la cantidad de enemigos restantes del {@code Juego}.
	 * @return La cantidad de enemigos restantes del {@code Juego}.
	 */
	public int getEnemigosRestantes() {
		return enemigosRestantes;
	}

	/**
	 * Modifica la cantidad de enemigos restantes del {@code Juego}.
	 * @param enemigosRestantes La nueva cantidad de enemigos restantes del {@code Juego}.
	 */
	public void setEnemigosRestantes(int enemigosRestantes) {
		this.enemigosRestantes = enemigosRestantes;
	}
}
