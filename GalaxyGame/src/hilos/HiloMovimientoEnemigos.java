package hilos;

import modelo.Juego;

/**
 * {@code HiloMovimientoEnemigos} se encarga de mover a todos los enemigos de algun {@code Juego}, llamando al metodo {@link modelo.Enemigo#mover()}.
 * @author juanchovelezpro
 *
 */
public class HiloMovimientoEnemigos extends HiloAbstract {

	/**
	 * Crea un {@code HiloMovimientoEnemigos} con un {@code Juego} del proceso.
	 * @param juego El {@code Juego} del proceso.
	 */
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
