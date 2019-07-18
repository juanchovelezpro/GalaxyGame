package hilos;

import modelo.Juego;

public class HiloDisparoJugador extends Thread {

	private Juego juego;

	public HiloDisparoJugador(Juego juego) {

		this.juego = juego;

	}

	@Override
	public void run() {

		while (true) {
			try {

				if (!juego.isPausa()) {
					Thread.sleep(25);

					juego.getJugador().disparar();

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

	public synchronized void reanudar() {

		notify();

	}

}
