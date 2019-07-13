package modelo;

public class Disparo extends Objeto{

	private int damage;
	public Disparo(String imagen, int posx, int posy, int ancho, int altura, int velocidad,int damage) {
		super(imagen, posx, posy, ancho, altura, velocidad);
		this.damage = damage;
	}
	
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}


}
