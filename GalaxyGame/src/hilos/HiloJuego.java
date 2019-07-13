package hilos;

import modelo.Juego;
import vista.Game;

public class HiloJuego extends Thread{

	
	private Juego juego;

	
	
	public HiloJuego(Juego juego) {
		
		this.juego = juego;
		
		
		
	}
	@Override
	public void run() {
	
		while(true) {
		try {
			Thread.sleep(25);
			
			juego.getJugador().disparar();
			
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	}
}
