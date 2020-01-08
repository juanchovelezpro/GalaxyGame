package hilos;

import modelo.Juego;

public class HiloMovimientoEnemigos extends HiloAbstract {

	public HiloMovimientoEnemigos(Juego juego) {
		super(juego);
	}

	@Override
	public void run() {
		while (true) {

			try {

				if (!getJuego().isPausa()) {
					Thread.sleep(5);
					for (int i = 0; i < getJuego().getEnemigos().size(); i++) {

						if (getJuego().getEnemigos().get(i) != null)
							getJuego().getEnemigos().get(i).mover();
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
