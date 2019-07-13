package modelo;

import java.util.Iterator;
import java.util.LinkedList;

public class Jugador extends Objeto {
	private String nick;
	private long puntaje;
	private LinkedList<Disparo> disparos;
	private Disparo disparoTemporal;
	public Jugador(int posx, int posy, int ancho, int altura,int velocidad, String nick, long puntaje) {
		super(posx, posy, ancho, altura,velocidad);
		this.nick = nick;
		this.puntaje = puntaje;
		disparos = new LinkedList<>();
		
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
	public void irDerecha() {
		if(super.getPosx()>=770) {
	}else{
		super.setPosx(super.getPosx()+ super.getVelocidad());
	}
	}
	public void irIzquierda() {
		if(super.getPosx()<= 0) {
			
		}else {
		super.setPosx(super.getPosx()- super.getVelocidad());
		}
	}
	public LinkedList<Disparo> getDisparos() {
		return disparos;
	}
	public void setDisparos(LinkedList<Disparo> disparos) {
		this.disparos = disparos;
	}
	public void agregarDisparo() {
		disparos.add(new Disparo(super.getPosx()+40,super.getPosy(),30,70,20,0));
	}
	
	public void eliminarDisparo(Disparo d) {
		disparos.remove(d);
	}
	public void disparar() {
		for (int i = 0; i < disparos.size(); i++) {
			disparoTemporal = disparos.get(i);
			if(disparoTemporal.getPosy() < -70)
				eliminarDisparo(disparoTemporal);
			disparoTemporal.avanzarDisparo();
		}
	}
}
