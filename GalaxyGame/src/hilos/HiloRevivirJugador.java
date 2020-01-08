package hilos;

import modelo.Juego;
import modelo.Jugador;

public class HiloRevivirJugador extends HiloAbstract {

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
						getJuego().getJugador().setSkin(Jugador.SKIN_INVULNERABLE);
						getJuego().getJugador().revivir();
						getJuego().getJugador().setInvulnerable(true);

						Thread.sleep(3000);

						getJuego().getJugador().setSkin(Jugador.SKIN_NORMAL);
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
