package hilos;

import modelo.Juego;

/**
 * {@code HiloDisparoJugador} se encarga de ejecutar constantemente el metodo
 * {@link modelo.Jugador#disparar()}. De esta forma se logra el movimiento de
 * los disparos y la verificacion de interacciones especificadas en dicho metodo.
 * @author juanchovelezpro
 *
 */
public class HiloDisparoJugador extends HiloAbstract {

	/**
	 * Crea un {@code HiloDisparoJugador} con un {@code Juego} del proceso
	 * @param juego El {@code Juego} del proceso.
	 */
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
