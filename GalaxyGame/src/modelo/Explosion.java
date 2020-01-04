package modelo;

import java.awt.image.BufferedImage;

import animation.Animation;
import tools.ToolManager;

public class Explosion extends Thread{

	private Animation animation;
	private int x;
	private int y;

	public Explosion(int x, int y) {

		this.x = x;
		this.y = y;

		BufferedImage image = ToolManager.cargarSprites("sprites/explosion.png");

		animation = new Animation(image, 81, 1, 9, 9);

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
		
		while(animation.isAlive()) {
			
			try {
				
				Thread.sleep(5);
				animation.runAnimation();
				
			}catch(Exception ex) {
				
				ex.printStackTrace();
				
			}
			
		}
		
	}

}
