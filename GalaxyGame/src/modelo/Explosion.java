package modelo;

import java.awt.image.BufferedImage;

import animation.Animation;
import tools.ToolManager;

public class Explosion extends Thread {

	public static final BufferedImage BOOM = ToolManager.cargarSprites("sprites/explosion.png");

	private Animation animation;
	private int x;
	private int y;

	public Explosion(int x, int y) {

		this.x = x;
		this.y = y;

		animation = new Animation(BOOM, 81, 1, 9, 9);

	}

	public Animation getAnimation() {
		return animation;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

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

	@Override
	public void run() {

		while (animation.isAlive()) {

			try {

				Thread.sleep(3);
				animation.runAnimation();

			} catch (Exception ex) {

				ex.printStackTrace();

			}

		}

	}

}
