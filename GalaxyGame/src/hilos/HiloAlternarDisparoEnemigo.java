package hilos;

import java.util.Random;

import modelo.Juego;

public class HiloAlternarDisparoEnemigo extends Thread {

	private Juego juego;

	public HiloAlternarDisparoEnemigo(Juego juego) {

		this.juego = juego;

	}

	@Override
	public void run() {

		Random r = new Random();
		boolean[] activar = { false, true, false , false , false, false};

		while (true) {

			try {
				Thread.sleep(500);

				for (int i = 0; i < juego.getEnemigos().size(); i++) {
					int g =r.nextInt(6);
					if(activar[g])	
					juego.getEnemigos().get(i).agregarDisparo();
				}

			} catch (InterruptedException e) {

				e.printStackTrace();
			}

		}

	}

}
