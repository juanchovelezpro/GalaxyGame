package modelo;

import tools.GameManager;

public class Camara {

	private int x;
	private int y;

	public Camara(int x, int y) {

		this.x = x;
		this.y = y;

	}

	public void mover(GameObject objeto) {

		x += ((objeto.getX() - x) - GameManager.WIDTH / 2) * 0.05f;
		y += ((objeto.getY() - y) - GameManager.HEIGHT / 2) * 0.05f;

	}

	public void transicionAObjeto(GameObject objeto) {

		if (x > objeto.getX() - GameManager.WIDTH/2) {

			x--;

		} else {

			x++;

		}

		if (y > objeto.getY()- GameManager.HEIGHT/2) {

			y--;

		} else {

			y++;
		}

	}

	public float getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
