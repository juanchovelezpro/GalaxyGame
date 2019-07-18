package hilos;

import modelo.Juego;

public class HiloMovimientoJugador extends Thread {

	private Juego juego;

	public HiloMovimientoJugador(Juego juego) {

		this.juego = juego;

	}

	@Override
	public void run() {

		while (true) {

			try {

				if (!juego.isPausa()) {
					Thread.sleep(20);
					juego.getJugador().mover();
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

	public synchronized void reanudar() {

		notify();

	}

}
