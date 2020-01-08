package hilos;

import modelo.Enemigo;
import modelo.Explosion;
import modelo.Juego;

public class HiloGarbageSystem extends HiloAbstract {



	public HiloGarbageSystem(Juego juego) {

		super(juego);

	}

	@Override
	public void run() {

		while (true) {

			try {

				Thread.sleep(1500);

				for (int i = 0; i < getJuego().getExplosiones().size(); i++) {

					Explosion temp = getJuego().getExplosiones().get(i);

					if (!temp.isAlive()) {

						getJuego().getExplosiones().remove(temp);

					}

				}

				for (int i = 0; i < getJuego().getEnemigos().size(); i++) {

					Enemigo temp = getJuego().getEnemigos().get(i);

					if (!temp.isVivo()) {

						getJuego().getEnemigos().remove(temp);

					}

				}

			} catch (Exception ex) {

				ex.printStackTrace();

			}

		}

	}

}
