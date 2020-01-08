package hilos;

import modelo.Juego;

public abstract class HiloAbstract extends Thread {

	private Juego juego;
	
	public HiloAbstract(Juego juego) {
		
		this.juego = juego;
		
	}
	
	
	public synchronized void reanudar() {
		
		notify();
		
	}


	public Juego getJuego() {
		return juego;
	}


	public void setJuego(Juego juego) {
		this.juego = juego;
	}
	
}
