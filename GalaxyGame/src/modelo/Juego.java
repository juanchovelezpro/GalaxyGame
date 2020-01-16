package modelo;

import java.util.LinkedList;
import java.util.Random;

import hilos.HiloAbstract;
import hilos.HiloAlternarDisparoEnemigo;
import hilos.HiloAlternarEsquivarEnemigo;
import hilos.HiloDesplegarEnemigos;
import hilos.HiloDisparoEnemigo;
import hilos.HiloDisparoJugador;
import hilos.HiloMovimientoCamara;
import hilos.HiloMovimientoEnemigos;
import hilos.HiloMovimientoJugador;
import hilos.HiloMovimientoPotenciadores;
import hilos.HiloRevivirJugador;

/**
 * Esta clase se encarga de manipular los objetos {@code Jugador} y
 * {@code Enemigo}. Asi como pausar o reanudar el juego.
 * 
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
	 * Lista de procesos del {@code Juego}.
	 */
	private LinkedList<HiloAbstract> threads;
	
	/**
	 * 
	 */
	private MODO modo;

	/**
	 * 
	 */
	private LinkedList<GameObject> objetos;
	
	/**
	 * 
	 */
	private Camara camara;

	/**
	 * Contiene los modos de juego que se pueden jugar.
	 * 
	 * @author juanchovelezpro
	 *
	 */
	public enum MODO {

		ORIGINAL, HISTORIA, INVASION,

	}

	/**
	 * Constructor que crea un {@code Juego} con un {@code Jugador} y una lista de
	 * enemigos y explosiones.
	 */
	public Juego(MODO modo) {

		this.modo = modo;
		inicializarJuego(modo);

	}

	public void inicializarJuego(MODO modo) {

		switch (modo) {
		case ORIGINAL:
			pausa = false;
			jugador = new Jugador(this);
			explosiones = new LinkedList<>();
			enemigos = new LinkedList<>();
			threads = new LinkedList<>();
			crearProcesos();
			break;

		case HISTORIA:
			objetos = new LinkedList<GameObject>();
			camara = new Camara(0,0);
			threads = new LinkedList<>();
			
			crearHistoria();

			break;

		case INVASION:

			break;

		default:
			break;
		}

	}

	public Camara getCamara() {
		return camara;
	}

	public void setCamara(Camara camara) {
		this.camara = camara;
	}

	public LinkedList<GameObject> getObjetos() {
		return objetos;
	}

	public void setObjetos(LinkedList<GameObject> objetos) {
		this.objetos = objetos;
	}
	
	public MODO getModo() {
		return modo;
	}

	public void setModo(MODO modo) {
		this.modo = modo;
	}

	public void crearHistoria() {
		
		threads.add(new HiloMovimientoCamara(this));
		threads.add(new HiloMovimientoJugador(this));
		
	}

	public void crearProcesos() {

		threads.add(new HiloMovimientoJugador(this));
		threads.add(new HiloDisparoJugador(this));
		threads.add(new HiloRevivirJugador(this));
		threads.add(new HiloMovimientoEnemigos(this));
		threads.add(new HiloAlternarDisparoEnemigo(this));
		threads.add(new HiloDisparoEnemigo(this));
		threads.add(new HiloMovimientoPotenciadores(this));
		threads.add(new HiloAlternarEsquivarEnemigo(this));
		threads.add(new HiloDesplegarEnemigos(this, 15));

	}

	public void iniciarProcesos() {

		for (int i = 0; i < threads.size(); i++) {

			threads.get(i).start();

		}

	}

	public void reanudarProcesos() {

		// Reanuda los procesos principales
		for (int i = 0; i < threads.size(); i++) {

			if (threads.get(i).isAlive())
				threads.get(i).reanudar();

		}

		// Reanuda las explosiones vivas.
		for (int i = 0; i < explosiones.size(); i++) {

			if (explosiones.get(i).isAlive())
				explosiones.get(i).reanudar();

		}

		// Reanuda los power ups disponibles.
		for (int i = 0; i < enemigos.size(); i++) {

			if (enemigos.get(i).getPowerUp() != null) {
				if (enemigos.get(i).getPowerUp().isDisponible()) {
					enemigos.get(i).getPowerUp().reanudar();
				}

			}

		}

	}

	/**
	 * Retorna la lista de procesos del {@code Juego}.
	 * 
	 * @return La lista de procesos del {@code Juego}.
	 */
	public LinkedList<HiloAbstract> getThreads() {
		return threads;
	}

	/**
	 * Modifica la lista de procesos del {@code Juego}.
	 * 
	 * @param threads La nueva lista de procesos del {@code Juego}.
	 */
	public void setThreads(LinkedList<HiloAbstract> threads) {
		this.threads = threads;
	}

	/**
	 * Retorna la lista de explosiones del {@code Juego}.
	 * 
	 * @return
	 */
	public LinkedList<Explosion> getExplosiones() {
		return explosiones;
	}

	/**
	 * Modifica la lista de explosiones del {@code Juego}.
	 * 
	 * @param explosiones La nueva lista de explosiones del {@code Juego}.
	 */
	public void setExplosiones(LinkedList<Explosion> explosiones) {
		this.explosiones = explosiones;
	}

	/**
	 * Retorna si el {@code Juego} se encuentra pausado.
	 * 
	 * @return {@code true} si el {@code Juego} se encuentra pausado, {@code false}
	 *         en caso contrario.
	 */
	public boolean isPausa() {
		return pausa;
	}

	/**
	 * Modificar el estado de pausa del {@code Juego}.
	 * 
	 * @param pausa El nuevo estado de pausa del {@code Juego}, {@code true} pausa
	 *              el {@code Juego}, {@code false} en caso contrario.
	 */
	public void setPausa(boolean pausa) {
		this.pausa = pausa;
	}

	/**
	 * Retorna el {@code Jugador} del {@code Juego}.
	 * 
	 * @return El {@code Jugador} del {@code Juego}.
	 */
	public Jugador getJugador() {
		return jugador;
	}

	/**
	 * Modifica el {@code Jugador} del {@code Juego}.
	 * 
	 * @param jugador El nuevo {@code Jugador} del {@code Juego}.
	 */
	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	/**
	 * Retorna los enemigos del {@code Juego} en una lista.
	 * 
	 * @return Una lista de enemigos.
	 */
	public LinkedList<Enemigo> getEnemigos() {
		return enemigos;
	}

	/**
	 * Modifica la lista de enemigos del {@code Juego}.
	 * 
	 * @param enemigos La nueva lista de enemigos del {@code Juego}.
	 */
	public void setEnemigos(LinkedList<Enemigo> enemigos) {
		this.enemigos = enemigos;
	}

	/**
	 * Crea y agrega un enemigo de tipo aleatorio a la lista de enemigos del
	 * {@code Juego}.
	 */
	public void agregarEnemigo() {

		Random r = new Random();

		enemigos.add(new Enemigo(r.nextInt(2) + 1, this));

		enemigosRestantes++;

	}

	/**
	 * Retorna la cantidad de enemigos restantes del {@code Juego}.
	 * 
	 * @return La cantidad de enemigos restantes del {@code Juego}.
	 */
	public int getEnemigosRestantes() {
		return enemigosRestantes;
	}

	/**
	 * Modifica la cantidad de enemigos restantes del {@code Juego}.
	 * 
	 * @param enemigosRestantes La nueva cantidad de enemigos restantes del
	 *                          {@code Juego}.
	 */
	public void setEnemigosRestantes(int enemigosRestantes) {
		this.enemigosRestantes = enemigosRestantes;
	}
}
