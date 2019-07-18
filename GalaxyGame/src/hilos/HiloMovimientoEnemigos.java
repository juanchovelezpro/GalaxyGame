package hilos;

import modelo.Juego;

public class HiloMovimientoEnemigos extends Thread {
	private Juego juego;

	public HiloMovimientoEnemigos(Juego juego) {
		this.juego = juego;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(20);
				for (int i = 0; i < juego.getEnemigos().size(); i++) {

					if (juego.getEnemigos().get(i) != null)
						juego.getEnemigos().get(i).mover();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

}
