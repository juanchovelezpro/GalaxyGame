package hilos;

import modelo.Juego;

public class HiloDesplegarEnemigos extends Thread {

	private Juego juego;
	private boolean desplegados;
	private int cantEnemigos;

	public HiloDesplegarEnemigos(Juego juego, int cantEnemigos) {

		this.juego = juego;
		this.cantEnemigos = cantEnemigos;
		desplegados = false;

	}

	@Override
	public void run() {

		int i = 1;

		while (!desplegados) {

			try {

				if (!juego.isPausa()) {

					if (i <= cantEnemigos) {
						Thread.sleep(500);

						juego.agregarEnemigo();
						i++;
					} else {

						desplegados = true;

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
