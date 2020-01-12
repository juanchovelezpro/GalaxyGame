package hilos;

import java.util.Random;
import modelo.Juego;

public class HiloAlternarDisparoEnemigo extends HiloAbstract {

	public HiloAlternarDisparoEnemigo(Juego juego) {

		super(juego);

	}

	@Override
	public void run() {

		Random r = new Random();

		while (true) {

			try {

				if (!getJuego().isPausa()) {
					Thread.sleep(500);

					for (int i = 0; i < getJuego().getEnemigos().size() && !getJuego().isPausa(); i++) {
						int g = r.nextInt(6);
						if (g == 1) {
							getJuego().getEnemigos().get(i).agregarDisparo();

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

}
