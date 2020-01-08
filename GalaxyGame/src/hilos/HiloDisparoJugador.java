package hilos;

import modelo.Juego;

public class HiloDisparoJugador extends HiloAbstract {

	public HiloDisparoJugador(Juego juego) {

		super(juego);

	}

	@Override
	public void run() {

		while (true) {
			try {

				if (!getJuego().isPausa()) {
					Thread.sleep(25);

					getJuego().getJugador().disparar();

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
