package hilos;

import modelo.Juego;
import tools.GameManager;

/**
 * {@code HiloRevivirJugador} se encarga de revivir al {@code Jugador} cuando se encuentre muerto despues de un determinado tiempo.
 * @author juanchovelezpro
 *
 */
public class HiloRevivirJugador extends HiloAbstract {

	/**
	 * Crea un {@code HiloRevivirJugador} con el {@code Juego} del proceso.
	 * @param juego El {@code Juego} del proceso.
	 */
	public HiloRevivirJugador(Juego juego) {

		super(juego);

	}

	@Override
	public void run() {

		while (true) {
			try {
				if (!getJuego().isPausa()) {
					Thread.sleep(10);
					if (!getJuego().getJugador().isVivo()) {

						Thread.sleep(2000);
						getJuego().getJugador().setSkin(GameManager.imagenes.get("nave1invulnerable"));
						getJuego().getJugador().revivir();
						getJuego().getJugador().setInvulnerable(true);

						Thread.sleep(3000);

						getJuego().getJugador().setSkin(GameManager.imagenes.get("nave1"));
						getJuego().getJugador().setInvulnerable(false);

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
