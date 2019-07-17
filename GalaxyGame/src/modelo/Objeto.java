package modelo;

import java.awt.Rectangle;

public class Objeto {

	private String skin;
	private int posx;
	private int posy;
	private Rectangle rectangulo;
	private int ancho;
	private int altura;
	private int velocidad;

	public Objeto(String skin, int posx, int posy, int ancho, int altura, int velocidad) {

		this.skin = skin;
		this.posx = posx;
		this.posy = posy;
		this.altura = altura;
		this.ancho = ancho;
		rectangulo = new Rectangle(posx, posy, ancho, altura);
		this.velocidad = velocidad;
	}

	public Objeto(int posx, int posy, int ancho, int altura, int velocidad) {

		skin = null;
		this.posx = posx;
		this.posy = posy;
		this.altura = altura;
		this.ancho = ancho;
		rectangulo = new Rectangle(posx, posy, ancho, altura);
		this.velocidad = velocidad;
	}

	public Objeto(int ancho, int altura) {

		this.altura = altura;
		this.ancho = ancho;
		skin = null;
		posx = 0;
		posy = 0;
		rectangulo = new Rectangle(posx, posy, ancho, altura);
		velocidad = 0;

	}

	public String getSkin() {
		return skin;
	}

	public void setSkin(String skin) {
		this.skin = skin;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public int getPosx() {
		return posx;
	}

	public void setPosx(int posx) {
		this.posx = posx;
	}

	public int getPosy() {
		return posy;
	}

	public void setPosy(int posy) {
		this.posy = posy;
	}

	public Rectangle getRectangulo() {
		return rectangulo;
	}

	public void setRectangulo(Rectangle rectangulo) {
		this.rectangulo = rectangulo;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public Rectangle getBounds() {

		return new Rectangle(posx + ancho / 8, posy + altura / 8, ancho - ancho / 4, altura - altura / 4);

	}

}
