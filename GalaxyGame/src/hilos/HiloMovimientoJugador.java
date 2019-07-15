package hilos;

import modelo.Juego;

public class HiloMovimientoJugador extends Thread {

	
	private Juego juego;
	
	public HiloMovimientoJugador(Juego juego) {
		
		this.juego = juego;
		
	}

	@Override
	public void run() {
		
		while(true) {
			
			try {
				Thread.sleep(20);
				juego.getJugador().mover();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
			
			
		}
		
		
	}
	
	
	
	
}
