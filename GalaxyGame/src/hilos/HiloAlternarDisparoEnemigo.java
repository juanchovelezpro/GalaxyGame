package hilos;

import java.util.Random;

import modelo.Juego;

public class HiloAlternarDisparoEnemigo extends Thread {

	private Juego juego;

	public HiloAlternarDisparoEnemigo(Juego juego) {

		this.juego = juego;

	}

	@Override
	public void run() {

		Random r = new Random();
		boolean[] activar = { false, true };

		while (true) {

			try {
				Thread.sleep(500);

//				for (int i = 0; i < juego.getEnemigos().size(); i++) {

//					juego.getEnemigos().get(0).setActivarDisparo(activar[r.nextInt(2)]);
					juego.getEnemigos().get(0).agregarDisparo();

//				}

			} catch (InterruptedException e) {

				e.printStackTrace();
			}

		}

	}

}
