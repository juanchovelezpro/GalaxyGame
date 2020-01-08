package modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class Disparo extends GameObject {

	public Disparo(Image skin, int x, int y, int ancho, int altura, int velX, int velY) {
		super(skin, x, y, ancho, altura, 0, velY);
	}

	public void avanzarDisparo() {

		super.setY(super.getY() - super.getVelY());
	}

	public void avanzarDisparoEnemigo() {

		super.setY(super.getY() + super.getVelY());
	}

	@Override
	public void render(Graphics g) {
		
		g.drawImage(getSkin(), getX(), getY(), null);
		
		g.setColor(Color.RED.brighter());
		g.drawRect((int)getBounds().getX(), (int)getBounds().getY(), (int)getBounds().getWidth(), (int)getBounds().getHeight());
	}
}
