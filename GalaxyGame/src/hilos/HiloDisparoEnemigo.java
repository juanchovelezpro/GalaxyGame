package hilos;

import modelo.Juego;

/**
 * {@code HiloDisparoEnemigo} se encarga de ejecutar constantemente el metodo
 * {@link modelo.Enemigo#disparar()}. De esta forma se logra el movimiento de
 * los disparos y la verificacion de interacciones especificadas en dicho metodo.
 * 
 * @author juanchovelezpro
 *
 */
public class HiloDisparoEnemigo extends HiloAbstract {

	/**
	 * Crea un {@code HiloDisparoEnemigo} con el {@code Juego} del proceso.
	 * @param juego El {@code Juego} del proceso.
	 */
	public HiloDisparoEnemigo(Juego juego) {

		super(juego);

	}

	@Override
	public void run() {

		while (true) {

			try {

				if (!getJuego().isPausa()) {
					Thread.sleep(25);

					for (int i = 0; i < getJuego().getEnemigos().size(); i++) {
						getJuego().getEnemigos().get(i).disparar();
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

}
