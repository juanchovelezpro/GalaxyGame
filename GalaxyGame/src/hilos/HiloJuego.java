package hilos;

public class HiloJuego extends Thread{

	public HiloJuego() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public void run() {
		try {
			Thread.sleep(25);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
