package hilos;

import modelo.GameObject;
import modelo.Juego;

public class HiloTransicionCamara extends HiloAbstract {

	private GameObject objeto;

	public HiloTransicionCamara(Juego juego, GameObject objeto) {
		super(juego);

		this.objeto = objeto;

	}

	@Override
	public void run() {

		while (true) {
			try {
				if (!getJuego().isPausa()) {

					Thread.sleep(1);
					getJuego().getCamara().transicionAObjeto(objeto);

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
