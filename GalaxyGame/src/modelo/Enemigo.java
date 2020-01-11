package modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.LinkedList;
import java.util.Random;

import hilos.HiloDisparoEnemigo;
import hilos.HiloDisparoJugador;
import hilos.HiloMovimientoEnemigos;
import hilos.HiloMovimientoJugador;
import tools.ScreenResolution;
import tools.ImageLoader;

/**
 * Representa un enemigo en el juego.
 * 
 * @author juanchovelezpro
 *
 */
public class Enemigo extends GameObject {

	/**
	 * El ancho del {@code Enemigo}.
	 * <p>
	 * Valor: {@value #WIDTH}
	 * </p>
	 */
	public static final int WIDTH = 80;

	/**
	 * La altura del {@code Enemigo}.
	 * <p>
	 * Valor: {@value #HEIGHT}
	 * </p>
	 */
	public static final int HEIGHT = 80;

	/**
	 * Velocidad inicial de un {@code Enemigo}.
	 */
	public static final int SPEED = 1;

	/**
	 * Velocidad rapida de un {@code Enemigo}.
	 */
	public static final int SPEED_FAST = 3;

	/**
	 * El limite en el eje Y al que un {@code Enemigo} puede llegar.
	 */
	public static final int Y_LIMIT = ScreenResolution.HEIGHT_GAME + HEIGHT + 50;

	/**
	 * El limite en el eje X en el debe aparece el {@code Enemigo}.
	 */
	public static final int X_BOUND = ScreenResolution.WIDTH_GAME - WIDTH;

	/**
	 * Valor maximo en el eje Y en el que el {@code Enemigo} puede Spawnear.
	 */
	public static final int Y_MAX = -100;

	/**
	 * Valor minimo en el eje Y en el que el {@code Enemigo} puede Spawnear.
	 */
	public static final int Y_MIN = -600;

	/**
	 * El limite que un {@code Disparo} del {@code Enemigo} puede alcanzar.
	 */
	public static final int SHOT_LIMIT = ScreenResolution.HEIGHT_GAME + 50;

	/**
	 * Diferencia en el eje X para el lugar de un {@code Disparo} del
	 * {@code Enemigo}.
	 */
	public static final int SHOT_OFFSET_X = 25;

	/**
	 * Diferencia en el eje Y para el lugar de un {@code Disparo} del
	 * {@code Enemigo}.
	 */
	public static final int SHOT_OFFSET_Y = 40;

	/**
	 * Coordenada x a la que se traslada el {@code Enemigo} cuando "muere".
	 * <p>
	 * Valor: {@value #X_DEATH}
	 * </p>
	 */
	public static final int X_DEATH = -500;

	public static final Image SKIN_NORMAL = ImageLoader.cargarImagen("naves/nave_enemigo.png");
	public static final Image SKIN_FAST = ImageLoader.cargarImagen("naves/fastEnemy.png");
	public static final Image DISPARO_NORMAL = ImageLoader.cargarImagen("disparos/laserEnemigo.png");
	public static final Image DISPARO_FAST = ImageLoader.cargarImagen("disparos/laserGama.png");

	/**
	 * Para diferenciar un {@code Enemigo} de otro. Cada tipo de {@code Enemigo}
	 * tiene diferentes caracteristicas.
	 */
	private int tipo;

	/**
	 * La vida del {@code Enemigo}.
	 */
	private int vida;

	/**
	 * Para consultar si el {@code Enemigo} se encuentra vivo.
	 */
	private boolean vivo;

	/**
	 * Los disparos del {@code Enemigo}.
	 */
	private LinkedList<Disparo> disparos;

	/**
	 * El {@code Juego} al que pertenece el {@code Enemigo}.
	 */
	private Juego juego;

	/**
	 * Para distinguir los tipos de movimientos que el {@code Enemigo} puede
	 * realizar.
	 */
	private int movimiento;

	/**
	 * Auxiliar para los movimientos especiales del {@code Enemigo}.
	 */
	private double auxMovs;

	/**
	 * Objeto {@code Random} que permite obtener numeros al azar.
	 */
	private Random r;

	/**
	 * Constructor que crea un {@code Enemigo} segun el tipo y el {@code Juego} al
	 * que pertenece. Las coordenadas (x,y) en las que se crea el enemigo son
	 * aleatorias definidas por los limites establecidos.
	 * 
	 * @param tipo  El tipo de {@code Enemigo} a crear.
	 * @param juego El {@code Juego} al que pertenece.
	 */
	public Enemigo(int tipo, Juego juego) {

		super(WIDTH, HEIGHT);
		super.setSkin(SKIN_NORMAL);
		super.setVelY(SPEED);

		r = new Random();

		super.setX(r.nextInt(X_BOUND));
		super.setY(r.nextInt(Y_MAX + 1 - Y_MIN) + Y_MIN);

		this.tipo = tipo;
		vivo = true;

		movimiento = 0;
		auxMovs = 0.0;

		crearPorTipo(tipo);

		disparos = new LinkedList<>();
		this.juego = juego;

	}

	/**
	 * Establece las caracteristica al {@code Enemigo} segun el numero.
	 * 
	 * @param tipo
	 */
	public void crearPorTipo(int tipo) {

		switch (tipo) {
		case 1:
			vida = 1;
			movimiento = 1;
			setSkin(SKIN_NORMAL);
			break;
		case 2:
			vida = 2;
			movimiento = 1;
			setSkin(SKIN_FAST);
			setVelY(SPEED_FAST);
			break;
		case 3:
			vida = 3;
			break;
		case 4:
			vida = 4;
			break;
		default:
			vida = 5;
			break;
		}

	}

	/**
	 * Retorna el tipo del {@code Enemigo}.
	 * 
	 * @return El tipo del {@code Enemigo}.
	 */
	public int getTipo() {
		return tipo;
	}

	/**
	 * Modifica el tipo del {@code Enemigo}.
	 * 
	 * @param tipo El nuevo tipo del {@code Enemigo}.
	 */
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	/**
	 * Retorna la vida del {@code Enemigo}.
	 * 
	 * @return La vida del {@code Enemigo}.
	 */
	public int getVida() {
		return vida;
	}

	/**
	 * Modifica la vida del {@code Enemigo}.
	 * 
	 * @param vida La nueva vida del {@code Enemigo}.
	 */
	public void setVida(int vida) {
		this.vida = vida;
	}

	/**
	 * Se encarga de actualizar las coordenadas (x,y) del {@code Enemigo} para su
	 * movimiento. Para realizar el movimiento se usa el {@code Thread}
	 * {@link HiloMovimientoEnemigos} que se encarga de realizar constantemente la
	 * actualizacion de las coordenadas (x,y) del {@code Enemigo}.
	 */
	public void mover() {

		if (vivo) {
			if (super.getY() >= Y_LIMIT) {
				super.setY(r.nextInt(Y_MAX + 1 - Y_MIN) + Y_MIN);
				super.setX(r.nextInt(X_BOUND));
			}

			if (vida <= 0 && vivo)
				morir();

			if (Fisica.colision(this, juego.getJugador())) {

				vida--;

				juego.getExplosiones().add(new Explosion(this.getX() + WIDTH / 2, this.getY() + HEIGHT, juego));
				juego.getExplosiones().getLast().start();

				if (!juego.getJugador().isInvulnerable())
					juego.getJugador().morir();

			}

			switch (movimiento) {

			case 1:
				movimientoNormal();
				break;

			case 2:
				movimientoZigZag();
				break;

			case 3:
				movimientoCircular();
				break;

			}
		}
	}

	/**
	 * Realiza el movimiento normal del {@code Enemigo}. Un movimiento normal de un
	 * {@code Enemigo} es solo desplazarse verticalmente.
	 */
	public void movimientoNormal() {

		super.setY(super.getY() + super.getVelY());

	}

	/**
	 * Realiza el movimiento zig zag de un {@code Enemigo}. Un movimiento zig zag de
	 * un {@code Enemigo}, modifica la coordenada X mientras se desplaza en la eje
	 * Y.
	 */
	public void movimientoZigZag() {

		if (auxMovs >= Math.PI * 2) {

			auxMovs = 0.0;

		}

		if (auxMovs < Math.PI * 2) {

			int posX = (int) (Math.sin(auxMovs) * 15);
			int posY = (int) (Math.asin(auxMovs) * 2);

			super.setX(super.getX() + posX);
			super.setY(super.getY() + posY + 4);

			auxMovs += 0.1;

		}

	}

	/**
	 * Realiza el movimiento circular del {@code Enemigo}. Un movimiento circula
	 * modifica las coordenadas (x,y) mientras se desplaza en el eje Y.
	 */
	public void movimientoCircular() {

		if (auxMovs >= Math.PI * 2) {

			auxMovs = 0.0;

		}

		if (auxMovs < Math.PI * 2) {

			int posX = (int) (Math.sin(auxMovs) * 15);
			int posY = (int) (Math.cos(auxMovs) * 15);

			super.setX(super.getX() + posX);
			super.setY(super.getY() + posY + 2);

			auxMovs += 0.1;

		}

	}

	/**
	 * Retorna si el {@code Enemigo} se encuentra vivo.
	 * 
	 * @return {@code true} se encuentra vivo, {@code false} en caso contrario.
	 */
	public boolean isVivo() {
		return vivo;
	}

	/**
	 * Modifica el estado de vida del {@code Enemigo}.
	 * 
	 * @param vivo El nuevo estado de vida del {@code Enemigo}. {@code true} estar
	 *             vivo, {@code false} en caso contrario.
	 */
	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}

	/**
	 * * Se encarga de hacer "morir" al {@code Jugador} trasladandolo a la
	 * coordenada {@code X_DEATH}.
	 * <p>
	 * Le establece la velocidad en el eje X a -> 0 y le quita una vida al
	 * {@code Jugador}.
	 * </p>
	 * 
	 * @see #X_DEATH
	 * @see #setVivo(boolean)
	 * @see #setVidas(int)
	 */
	public void morir() {

		setX(X_DEATH);
		setVelY(0);
		vivo = false;
		juego.setEnemigosRestantes(juego.getEnemigosRestantes() - 1);

	}

	/**
	 * Retorna la lista de disparos del {@code Enemigo}.
	 * @return La lista de disparos del {@code Enemigo}.
	 */
	public LinkedList<Disparo> getDisparos() {
		return disparos;
	}

	/**
	 * Modifica la lista de disparos del {@code Enemigo}.
	 * @param disparos La nueva lista de disparos del {@code Enemigo}.
	 */
	public void setDisparos(LinkedList<Disparo> disparos) {
		this.disparos = disparos;
	}

	/**
	 * Agrega un disparo a la lista de disparos del {@code Enemigo}.
	 */
	public void agregarDisparo() {

		if (tipo == 1) {
			disparos.add(new Disparo(DISPARO_FAST, super.getX() + SHOT_OFFSET_X - 3, super.getY() + SHOT_OFFSET_Y, 30,
					70, 0, 15));
		}

		if (tipo == 2) {
			disparos.add(new Disparo(DISPARO_NORMAL, super.getX() + SHOT_OFFSET_X, super.getY() + SHOT_OFFSET_Y, 35, 85,
					0, 20));
		}
	}

	/**
	 * Elimina el {@code Disparo} <b> d </b> de la lista de disparos del {@code Enemigo}.
	 * @param d
	 */
	public void eliminarDisparo(Disparo d) {
		disparos.remove(d);
	}

	/**
	 * Se encarga de efectuar las acciones de cada uno de los disparos del
	 * {@code Enemigo}.
	 * 
	 * <p>
	 * Cada vez que se agregue un disparo a la lista de disparos del {@code Enemigo}
	 * es porque el {@code Enemigo} ha disparado, entonces este metodo se encarga de
	 * cada uno de los disparos que se encuentren en la lista se muevan.
	 * </p>
	 * 
	 * <p>
	 * Este metodo se llama constantemente en el {@code Thread}
	 * {@link HiloDisparoEnemigo} de forma que las acciones de un {@code Disparo} se
	 * actualicen, tales como colisiones, movimiento y limites.
	 * </p>
	 */
	public void disparar() {

		Disparo disparoTemporal = null;

		for (int i = 0; i < disparos.size(); i++) {
			disparoTemporal = disparos.get(i);

			disparoTemporal.avanzarDisparoEnemigo();

			if (Fisica.colision(disparoTemporal, juego.getJugador())) {

				juego.getExplosiones().add(
						new Explosion(disparoTemporal.getX(), disparoTemporal.getY() + disparoTemporal.getAltura(), juego));
				juego.getExplosiones().getLast().start();

				eliminarDisparo(disparoTemporal);

				if (!juego.getJugador().isInvulnerable())
					juego.getJugador().morir();

			}
			if (disparoTemporal.getY() > SHOT_LIMIT)
				eliminarDisparo(disparoTemporal);

		}

	}

	@Override
	public void render(Graphics g) {

		g.drawImage(getSkin(), getX(), getY(), null);

//		g.setColor(Color.RED.brighter());
//		g.drawRect((int)getBounds().getX(), (int)getBounds().getY(), (int)getBounds().getWidth(), (int)getBounds().getHeight());

		for (int i = 0; i < disparos.size(); i++) {

			Disparo temp = disparos.get(i);

			temp.render(g);

		}

	}

}
