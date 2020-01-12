package hilos;

import java.util.Random;
import modelo.Juego;

/**
 * {@code HiloAlternarDisparoEnemigo} se encarga de modificar la decision del enemigo si debe disparar o no.
 * @author juanchovelezpro
 *
 */
public class HiloAlternarDisparoEnemigo extends HiloAbstract {

	/**
	 * Crea un {@code HiloAlternarDisparoEnemigo} 
	 * @param juego El juego del proceso.
	 */
	public HiloAlternarDisparoEnemigo(Juego juego) {

		super(juego);

	}

	@Override
	public void run() {

		Random r = new Random();

		while (true) {

			try {

				if (!getJuego().isPausa()) {
					Thread.sleep(500);

					for (int i = 0; i < getJuego().getEnemigos().size() && !getJuego().isPausa(); i++) {
						int g = r.nextInt(6);
						if (g == 1) {
							getJuego().getEnemigos().get(i).agregarDisparo();

						}
					}
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

}
