package hilos;

import modelo.Juego;

public class HiloMovimientoCamara extends HiloAbstract {

	public HiloMovimientoCamara(Juego juego) {
		super(juego);
	}

	@Override
	public void run() {
		
		while(true) {
			try {
			if(!getJuego().isPausa()) {
				
				Thread.sleep(1);
				getJuego().getCamara().mover(getJuego().getJugador());
				
				
			}else {
				
				
				synchronized (this) {
					wait();
				}
				
			}
			
			}catch(Exception ex) {
				
				ex.printStackTrace();
				
			}
			
		}
		
		
	}
	
	
}
