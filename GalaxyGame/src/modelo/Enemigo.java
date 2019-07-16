package modelo;

import java.util.LinkedList;
import java.util.Random;

public class Enemigo extends Objeto {

	public static final String SKIN_NORMAL = "naves/nave_enemigo.png";
	public static final String DISPARO_NORMAL = "disparos/laserEnemigo.png";
	
	private int tipo;
	private int vida;
	private LinkedList<Disparo> disparos;
	private Juego juego;

	public Enemigo(int posx, int posy, int ancho, int altura, int velocidad, int tipo, Juego juego) {
		super(posx, posy, ancho, altura, velocidad);
		
		super.setSkin(SKIN_NORMAL);
		this.tipo = tipo;
		
		switch (tipo) {
		case 1:
			vida = 2;
			break;
		case 2:
			vida = 3;
			break;
		case 3:
			vida = 4;
			break;
		case 4:
			vida = 4;
			break;
		default:
			vida = 5;
			break;
		}
		disparos = new LinkedList<>();
		this.juego = juego;

	}

	public void mover() {
		Random r = new Random();
		if (super.getPosy() >= 880) {
			super.setPosy(r.nextInt(Juego.YMAXIMO + 1 - Juego.YMINIMO) + Juego.YMINIMO);
			super.setPosx(r.nextInt(745));
		}

		if (Fisica.colision(this, juego.getJugador())) {

			juego.getJugador().setVida(juego.getJugador().getVida() - 1);

			System.out.println("Colision!");

		}

		super.setPosy(super.getPosy() + super.getVelocidad());
	}

	public LinkedList<Disparo> getDisparos() {
		return disparos;
	}

	public void setDisparos(LinkedList<Disparo> disparos) {
		this.disparos = disparos;
	}

	public void agregarDisparo() {
		disparos.add(new Disparo(DISPARO_NORMAL,super.getPosx() + 25, super.getPosy() + 40, 25, 65, 15));
	}

	public void eliminarDisparo(Disparo d) {
		disparos.remove(d);
	}

	public void disparar() {

		Disparo disparoTemporal = null;

		for (int i = 0; i < disparos.size(); i++) {
			disparoTemporal = disparos.get(i);

			if (Fisica.colision(disparoTemporal, juego.getJugador()))
				eliminarDisparo(disparoTemporal);

			if (disparoTemporal.getPosy() > 870)
				eliminarDisparo(disparoTemporal);

			disparoTemporal.avanzarDisparoEnemigo();
		}

	}

}
