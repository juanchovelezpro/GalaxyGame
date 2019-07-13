package modelo;

public class Disparo extends Objeto {

	private int damage;

	public Disparo(int posx, int posy, int ancho, int altura, int velocidad, int damage) {
		super(posx, posy, ancho, altura, velocidad);
		this.damage = damage;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public void avanzarDisparo() {
		super.setPosy(super.getPosy() - super.getVelocidad());
	}
}
