package hilos;

import modelo.Juego;

public class HiloDisparoJugador extends Thread {

	private Juego juego;

	public HiloDisparoJugador(Juego juego) {

		this.juego = juego;

	}

	@Override
	public void run() {

		while (true) {
			try {
				Thread.sleep(25);

				juego.getJugador().disparar();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
