package hilos;

import modelo.Juego;

public class HiloDisparoEnemigo extends Thread {

	private Juego juego;

	public HiloDisparoEnemigo(Juego juego) {

		this.juego = juego;

	}

	@Override
	public void run() {

		while (true) {

			try {
				Thread.sleep(25);

				for (int i = 0; i < juego.getEnemigos().size(); i++) {
					juego.getEnemigos().get(i).disparar();
				}

			} catch (Exception e) {

				e.printStackTrace();
			}

		}

	}

}
