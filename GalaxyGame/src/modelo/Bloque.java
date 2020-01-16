package modelo;

import java.awt.Graphics;
import java.awt.Rectangle;

import tools.GameManager;

public class Bloque extends GameObject {

	public enum ID {

		ROCA,BRICK,

	}

	public Bloque(ID id, int x, int y, Juego juego) {
		super(juego);

		crearBloque(id,x,y);

	}

	public void crearBloque(ID id,int x, int y) {

		switch (id) {
		case ROCA:
			setSkin(GameManager.imagenes.get("ROCA"));
			setAltura(getSkin().getHeight(null));
			setAncho(getSkin().getWidth(null));
			setX(x*getAncho());
			setY(y*getAltura());
			break;
			
		case BRICK:
			setSkin(GameManager.imagenes.get("BRICK"));
			setAltura(getSkin().getHeight(null));
			setAncho(getSkin().getWidth(null));
			setX(x*getAncho());
			setY(y*getAltura());

		default:
			break;
		}

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getSkin(),getX(),getY(), null);
		
		if(GameManager.TEST) {
			
			GameManager.renderBounds(g, this);
			
		}
		
	}

	@Override
	public Rectangle getVision() {
		return null;
	}

}
