package hilos;

import modelo.Juego;
import vista.Game;

public class HiloJuego extends Thread{

	
	private Juego juego;
	private Game game;
	
	
	public HiloJuego(Juego juego, Game game) {
		
		this.juego = juego;
		this.game = game;
		
		
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
