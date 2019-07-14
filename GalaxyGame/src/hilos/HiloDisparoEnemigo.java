package hilos;

import java.util.Random;

import modelo.Juego;

public class HiloDisparoEnemigo extends Thread {

	private Juego juego;

	public HiloDisparoEnemigo(Juego juego) {

		this.juego = juego;

	}

	@Override
	public void run() {

		Random r = new Random();
		boolean[] activar = { false, true };

		while (true) {

			try {
				Thread.sleep(2000);

				for (int i = 0; i < juego.getEnemigos().size(); i++) {

					juego.getEnemigos().get(i).setActivarDisparo(activar[r.nextInt(1)]);

				}

			} catch (InterruptedException e) {

				e.printStackTrace();
			}

		}

	}

}
