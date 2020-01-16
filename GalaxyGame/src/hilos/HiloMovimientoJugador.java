package hilos;

import modelo.Juego;

/**
 * {@code HiloMovimientoJugador} se encarga de mover constantemente al
 * {@code Jugador} llamando al metodo {@link modelo.Jugador#mover()}.
 * 
 * @author juanchovelezpro
 *
 */
public class HiloMovimientoJugador extends HiloAbstract {

	private int velSleep;

	/**
	 * Crea un {@code HiloMovimientoJugador} con un {@code Juego} del proceso.
	 * 
	 * @param juego El {@code Juego} del proceso.
	 */
	public HiloMovimientoJugador(Juego juego, int velSleep) {

		super(juego);
		this.velSleep = velSleep;

	}

	@Override
	public void run() {

		while (true) {

			try {

				if (!getJuego().isPausa()) {
					Thread.sleep(velSleep);
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
