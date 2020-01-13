package hilos;

import modelo.Juego;

/**
 * {@code HiloMovimientoPotenciadores} se encarga de realizar los movimientos de
 * los potenciadores.
 * 
 * @author juanchovelezpro
 *
 */
public class HiloMovimientoPotenciadores extends HiloAbstract {

	/**
	 * Construye un {@code HiloMovimientoPotenciadores} con el {@code Juego} del
	 * proceso.
	 * 
	 * @param juego
	 */
	public HiloMovimientoPotenciadores(Juego juego) {
		super(juego);

	}

	@Override
	public void run() {

		while (true) {
			try {
				if (!getJuego().isPausa()) {

					Thread.sleep(3);

					for (int i = 0; i < getJuego().getEnemigos().size(); i++) {

						if (getJuego().getEnemigos().get(i).getPowerUp() != null) {

							if (getJuego().getEnemigos().get(i).getPowerUp().isDisponible())
								getJuego().getEnemigos().get(i).getPowerUp().mover();

						}

					}

				} else {

					synchronized (this) {
						wait();
					}

				}

			} catch (Exception ex) {

				ex.printStackTrace();

			}
		}

	}

}
