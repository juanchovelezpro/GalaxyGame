package hilos;

import modelo.Juego;

/**
 * {@code HiloAbstract} representa la clase padre de los procesos del juego. Hereda de {@code Thread} por lo que clases que hereden de {@code HiloAbstract} tambien son {@code Thread.}
 * 
 * <p> Presenta sincronizacion con su metodo reanudar, pero cabe destacar que clases que hereden de {@code HiloAbstract} deben implementar en su {@code public void run()} la instruccion
 * {@code synchronized} para garantizar dicha sincronizacion. </p>
 * 
 * @author juanchovelezpro
 *
 */
public abstract class HiloAbstract extends Thread {

	/**
	 * El {@code Juego} del proceso.
	 */
	private Juego juego;
	
	/**
	 * Crea un {@code HiloAbstract} con un juego.
	 * @param juego El {@code Juego}.
	 */
	public HiloAbstract(Juego juego) {
		
		this.juego = juego;
		
	}
	
	/**
	 * Se encarga de notificar al objeto instanciado por esta clase que reanude su metodo {@code run()}.
	 */
	public synchronized void reanudar() {
		
		notify();
		
	}


	/**
	 * Retorna el {@code Juego} del proceso.
	 * @return El {@code Juego} del proceso.
	 */
	public Juego getJuego() {
		return juego;
	}


	/**
	 * Modifica el {@code Juego} del proceso.
	 * @param juego El nuevo {@code Juego} del proceso.
	 */
	public void setJuego(Juego juego) {
		this.juego = juego;
	}
	
}
