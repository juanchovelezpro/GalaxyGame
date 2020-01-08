package hilos;

import modelo.Juego;

public class HiloMovimientoJugador extends HiloAbstract {

	public HiloMovimientoJugador(Juego juego) {

		super(juego);

	}

	@Override
	public void run() {

		while (true) {

			try {

				if (!getJuego().isPausa()) {
					Thread.sleep(10);
					getJuego().getJugador().mover();
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
