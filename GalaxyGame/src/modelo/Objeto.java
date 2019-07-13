package modelo;

import java.awt.Rectangle;

public class Objeto {
	private String imagen;
	private int posx;
	private int posy;
	private Rectangle rectangulo;
	private int ancho;
	private int altura;
	private int velocidad;

	public Objeto(String imagen, int posx, int posy, int ancho, int altura, int velocidad) {
		this.imagen = imagen;
		this.posx = posx;
		this.posy = posy;
		this.altura = altura;
		this.ancho = ancho;
		rectangulo = new Rectangle(posx,posy,ancho,altura);
		this.velocidad = velocidad;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
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
	
}
