package hilos;

import modelo.Enemigo;
import modelo.Explosion;
import modelo.Juego;

public class HiloGarbageSystem extends Thread {

	private Juego juego;

	public HiloGarbageSystem(Juego juego) {

		this.juego = juego;

	}

	@Override
	public void run() {

		while (true) {

			try {

				Thread.sleep(1500);

				for (int i = 0; i < juego.getExplosiones().size(); i++) {

					Explosion temp = juego.getExplosiones().get(i);

					if (!temp.isAlive()) {

						juego.getExplosiones().remove(temp);

					}

				}

				for (int i = 0; i < juego.getEnemigos().size(); i++) {

					Enemigo temp = juego.getEnemigos().get(i);

					if (temp.getVida() <= 0) {

						juego.getEnemigos().remove(temp);

					}

				}

			} catch (Exception ex) {

				ex.printStackTrace();

			}

		}

	}

}
