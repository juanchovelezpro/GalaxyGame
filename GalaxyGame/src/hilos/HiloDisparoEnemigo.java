package hilos;

import modelo.Juego;

public class HiloDisparoEnemigo extends Thread {

	private Juego juego;

	public HiloDisparoEnemigo(Juego juego) {

		this.juego = juego;

	}

	@Override
	public void run() {

		while (true) {

			try {
				Thread.sleep(25);

				juego.getEnemigos().get(0).disparar();

			} catch (InterruptedException e) {

				e.printStackTrace();
			}

		}

	}

}
