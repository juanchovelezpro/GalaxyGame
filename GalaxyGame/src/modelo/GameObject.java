package modelo;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public abstract class GameObject {

	private Image skin;
	private int x;
	private int y;
	private int ancho;
	private int altura;
	private int velX;
	private int velY;

	public GameObject(Image skin, int x, int y, int ancho, int altura, int velX, int velY) {

		this.skin = skin;
		this.x = x;
		this.y = y;
		this.altura = altura;
		this.ancho = ancho;
		this.velX = velX;
		this.velY = velY;
	}

	public GameObject(int x, int y, int ancho, int altura, int velX) {

		skin = null;
		velY = 0;
		this.x = x;
		this.y = y;
		this.altura = altura;
		this.ancho = ancho;
		this.velX = velX;
	}

	public GameObject(int ancho, int altura) {

		this.altura = altura;
		this.ancho = ancho;
		skin = null;
		x = 0;
		y = 0;
		velX = 0;
		velY = 0;

	}
	
	public abstract void render(Graphics g);
	

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public Image getSkin() {
		return skin;
	}

	public void setSkin(Image skin) {
		this.skin = skin;
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

		return new Rectangle(x + ancho / 8, y + altura / 8, ancho - ancho / 4, altura - altura / 4);

	}

}
