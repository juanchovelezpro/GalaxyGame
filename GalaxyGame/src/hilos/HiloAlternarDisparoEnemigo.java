package hilos;

import java.io.File;
import java.util.Random;

import modelo.Juego;
import tools.SoundPlayer;

public class HiloAlternarDisparoEnemigo extends Thread {

	private Juego juego;

	public HiloAlternarDisparoEnemigo(Juego juego) {

		this.juego = juego;

	}

	@Override
	public void run() {

		Random r = new Random();

		while (true) {

			try {

				if (!juego.isPausa()) {
					Thread.sleep(500);

					for (int i = 0; i < juego.getEnemigos().size() && !juego.isPausa(); i++) {
						int g = r.nextInt(6);
						if (g == 1) {
							juego.getEnemigos().get(i).agregarDisparo();

						}
					}
				} else {

					synchronized (this) {
						wait();
					}

				}
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

		}

	}

	public synchronized void reanudar() {

		notify();

	}

}
