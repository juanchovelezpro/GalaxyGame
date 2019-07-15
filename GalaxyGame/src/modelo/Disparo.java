package modelo;

public class Disparo extends Objeto {

	public Disparo(int posx, int posy, int ancho, int altura, int velocidad) {
		super(posx, posy, ancho, altura, velocidad);
	}

	public void avanzarDisparo() {
		super.setPosy(super.getPosy() - super.getVelocidad());
	}

	public void avanzarDisparoEnemigo() {

		super.setPosy(super.getPosy() + super.getVelocidad());
	}
}
