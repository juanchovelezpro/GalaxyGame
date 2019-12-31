package hilos;

import modelo.Juego;
import modelo.Jugador;

public class HiloRevivirJugador extends Thread {

	private Juego juego;

	public HiloRevivirJugador(Juego juego) {

		this.juego = juego;

	}

	@Override
	public void run() {

		while (true) {
			try {
				if (!juego.isPausa()) {
					Thread.sleep(10);
					if (!juego.getJugador().isVivo()) {

						Thread.sleep(2000);
						juego.getJugador().setSkin(Jugador.SKIN_INVULNERABLE);
						juego.getJugador().revivir();
						juego.getJugador().setInvulnerable(true);

						Thread.sleep(3000);

						juego.getJugador().setSkin(Jugador.SKIN_NORMAL);
						juego.getJugador().setInvulnerable(false);

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

	public synchronized void reanudar() {

		notify();

	}

}
