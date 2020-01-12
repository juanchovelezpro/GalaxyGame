package hilos;

import modelo.Juego;

/**
 * {@code HiloDesplegarEnemigos} se encarga de crear enemigos cada medio segundo.
 * @author juanchovelezpro
 *
 */
public class HiloDesplegarEnemigos extends HiloAbstract {

	/**
	 * Variable que indica si todos los enemigos ya han sido desplegados.
	 */
	private boolean desplegados;
	
	/**
	 * La cantidad de enemigos a desplegar.
	 */
	private int cantEnemigos;

	/**
	 * Crea un {@code HiloDesplegarEnemigos} con el juego del proceso y la cantidad de enemigos a desplegar.
	 * @param juego El {@code Juego} del proceso.
	 * @param cantEnemigos La cantidad de enemigos a desplegar.
	 */
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
