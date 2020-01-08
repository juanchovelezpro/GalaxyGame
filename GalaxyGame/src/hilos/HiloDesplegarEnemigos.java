package hilos;

import modelo.Juego;

public class HiloDesplegarEnemigos extends HiloAbstract {

	private boolean desplegados;
	private int cantEnemigos;

	public HiloDesplegarEnemigos(Juego juego, int cantEnemigos) {

		super(juego);
		this.cantEnemigos = cantEnemigos;
		desplegados = false;

	}

	@Override
	public void run() {

		int i = 1;

		while (!desplegados) {

			try {

				if (!getJuego().isPausa()) {

					if (i <= cantEnemigos) {
						Thread.sleep(500);

						getJuego().agregarEnemigo();
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



}
