package modelo;

import java.util.LinkedList;

public class Jugador extends Objeto {

	public static final String SKIN_NORMAL = "naves/nave_jugador.png";
	public static final String DISPARO = "disparos/laserJugador.png";
	
	private String nick;
	private long puntaje;
	private LinkedList<Disparo> disparos;
	private Juego juego;
	private int vida;
	private int damage;

	public Jugador(int posx, int posy, int ancho, int altura, int velocidad, String nick, long puntaje, Juego juego) {
		super(posx, posy, ancho, altura, velocidad);

		super.setSkin(SKIN_NORMAL);
		this.juego = juego;
		this.nick = nick;
		this.puntaje = puntaje;
		disparos = new LinkedList<>();
		vida = 3;
		damage = 1;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public long getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(long puntaje) {
		this.puntaje = puntaje;
	}

	public void mover() {

		if (super.getPosx() >= 770) {
			super.setPosx(770);
		} else if (super.getPosx() <= 0) {
			super.setPosx(0);
		}

		super.setPosx(super.getPosx() + super.getVelocidad());

	}

	public LinkedList<Disparo> getDisparos() {
		return disparos;
	}

	public void setDisparos(LinkedList<Disparo> disparos) {
		this.disparos = disparos;
	}

	public void agregarDisparo() {
		disparos.add(new Disparo(DISPARO,super.getPosx() + 25, super.getPosy() - 30, 25, 65, 15));
	}

	public void eliminarDisparo(Disparo d) {
		disparos.remove(d);
	}

	public void disparar() {

		Disparo disparoTemporal = null;

		for (int i = 0; i < disparos.size(); i++) {
			disparoTemporal = disparos.get(i);

			if (Fisica.colision(disparoTemporal, juego.getEnemigos()))
				eliminarDisparo(disparoTemporal);

			if (disparoTemporal.getPosy() < -70)
				eliminarDisparo(disparoTemporal);

			disparoTemporal.avanzarDisparo();
		}
	}

}
