package hilos;

import java.util.Random;

import modelo.Juego;

/**
 * {@code HiloAlternarEsquivarEnemigo} modifica segun el tiempo determinado, la
 * variable {@code ladoEsquivar} de la clase {@code Enemigo}.
 * 
 * @author juanchovelezpro
 *
 */
public class HiloAlternarEsquivarEnemigo extends HiloAbstract {

	/**
	 * Crea un {@code HiloAlternarEsquivarEnemigo} con el {@code Juego} del proceso.
	 * 
	 * @param juego El {@code Juego} del proceso.
	 */
	public HiloAlternarEsquivarEnemigo(Juego juego) {

		super(juego);

	}

	@Override
	public void run() {

		Random r = new Random();

		while (true) {

			try {

				if (!getJuego().isPausa()) {

					Thread.sleep(3000);

					for (int i = 0; i < getJuego().getEnemigos().size(); i++) {

						getJuego().getEnemigos().get(i).setLadoEsquivar(r.nextBoolean());

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
