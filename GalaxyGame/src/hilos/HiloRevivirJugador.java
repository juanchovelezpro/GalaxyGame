package hilos;

import modelo.Juego;
import tools.GameManager;

/**
 * {@code HiloRevivirJugador} se encarga de revivir al {@code Jugador} cuando se
 * encuentre muerto despues de un determinado tiempo.
 * 
 * @author juanchovelezpro
 *
 */
public class HiloRevivirJugador extends HiloAbstract {

	private int auxTimeRevivir;
	private int auxTimeInvulnerabilidad;

	/**
	 * Crea un {@code HiloRevivirJugador} con el {@code Juego} del proceso.
	 * 
	 * @param juego El {@code Juego} del proceso.
	 */
	public HiloRevivirJugador(Juego juego) {

		super(juego);
		auxTimeRevivir = 2;
		auxTimeInvulnerabilidad = 3;
	}

	@Override
	public void run() {

		int revivir = auxTimeRevivir;
		int invulnerabilidad = auxTimeInvulnerabilidad;

		boolean auxRevivir = true;
		boolean auxInvul = false;

		while (true) {
			try {
				if (!getJuego().isPausa()) {
					Thread.sleep(10);
					if (!getJuego().getJugador().isVivo()) {

						if (auxRevivir) {
							Thread.sleep(1000);
							auxTimeRevivir--;
							if (auxTimeRevivir == 0) {
								getJuego().getJugador().setSkin(GameManager.imagenes.get("nave1invulnerable"));
								getJuego().getJugador().revivir();
								getJuego().getJugador().setInvulnerable(true);
								auxTimeRevivir = revivir;
								auxInvul = true;
							}
						}

					} else {

						if (auxInvul) {
							auxRevivir = false;
							Thread.sleep(1000);
							auxTimeInvulnerabilidad--;
							if (auxTimeInvulnerabilidad == 0) {
								getJuego().getJugador().setSkin(GameManager.imagenes.get("nave1"));
								getJuego().getJugador().setInvulnerable(false);
								auxTimeInvulnerabilidad = invulnerabilidad;
								auxInvul = false;
								auxRevivir = true;
							}
						}

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
