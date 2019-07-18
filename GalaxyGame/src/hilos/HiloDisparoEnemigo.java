package hilos;

import modelo.Juego;

public class HiloDisparoEnemigo extends Thread {

	private Juego juego;

	public HiloDisparoEnemigo(Juego juego) {

		this.juego = juego;

	}

	@Override
	public void run() {

		while (true) {

			try {

				if (!juego.isPausa()) {
					Thread.sleep(25);

					for (int i = 0; i < juego.getEnemigos().size(); i++) {
						juego.getEnemigos().get(i).disparar();
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

	public synchronized void reanudar() {

		notify();

	}

}
