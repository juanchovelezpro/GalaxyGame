package modelo;

import tools.GameManager;

public class Camara {

	private float x;
	private float y;
	
	public Camara(float x, float y) {
		
		this.x = x;
		this.y = y;
		
	}
	
	public void mover(GameObject objeto) {
		
		x+= ((objeto.getX()- x)- GameManager.WIDTH/2) * 0.05f;
		y+= ((objeto.getY()- y)- GameManager.HEIGHT/2) * 0.05f;
		
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
}
