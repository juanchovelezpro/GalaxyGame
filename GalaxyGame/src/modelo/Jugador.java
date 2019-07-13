package modelo;

public class Jugador extends Objeto {
	private String nick;
	private long puntaje;
	public Jugador(int posx, int posy, int ancho, int altura,int velocidad, String nick, long puntaje) {
		super(posx, posy, ancho, altura,velocidad);
		this.nick = nick;
		this.puntaje = puntaje;
		
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
		if(super.getPosx()>=810) {
	}else{
		super.setPosx(super.getPosx()+ super.getVelocidad());
	}
	}
	public void irIzquierda() {
		if(super.getPosx()<= 40) {
			
		}else {
		super.setPosx(super.getPosx()- super.getVelocidad());
		}
	}
	
}
