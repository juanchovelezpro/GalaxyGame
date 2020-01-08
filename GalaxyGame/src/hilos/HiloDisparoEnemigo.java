package hilos;

import modelo.Juego;

public class HiloDisparoEnemigo extends HiloAbstract {

	public HiloDisparoEnemigo(Juego juego) {

		super(juego);

	}

	@Override
	public void run() {

		while (true) {

			try {

				if (!getJuego().isPausa()) {
					Thread.sleep(25);

					for (int i = 0; i < getJuego().getEnemigos().size(); i++) {
						getJuego().getEnemigos().get(i).disparar();
					}
				} else {

					synchronized (this) {
						wait();
					}

				}
			} catch (Exception e) {

				e.printStackTrace();
			}

		}

	}

}
