package modelo;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import hilos.HiloDisparoEnemigo;
import hilos.HiloMovimientoEnemigos;
import modelo.Potenciador.PODER;
import tools.GameManager;

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
	public static final int Y_LIMIT = GameManager.HEIGHT_GAME + HEIGHT + 50;

	/**
	 * El limite en el eje X en el debe aparece el {@code Enemigo}.
	 */
	public static final int X_BOUND = GameManager.WIDTH_GAME - WIDTH;

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
	public static final int SHOT_LIMIT = GameManager.HEIGHT_GAME + 50;

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
	 * La cantidad de vida que tiene el {@code Enemigo}.
	 */
	private int salud;

	/**
	 * Para consultar si el {@code Enemigo} se encuentra vivo.
	 */
	private boolean vivo;

	/**
	 * Los disparos del {@code Enemigo}.
	 */
	private LinkedList<Disparo> disparos;

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
	 * Determina si el {@code Enemigo} puede esquivar o no.
	 */
	private boolean esquivar;

	/**
	 * Determina a que lado el {@code Enemigo} va a esquivar.
	 * <p>
	 * {@code true} = derecha, {@code false} = izquierda.
	 * </p>
	 */
	private boolean ladoEsquivar;

	/**
	 * La cantidad de daño que puede infligir un {@code Enemigo}.
	 */
	private int damage;

	/**
	 * El {@code Potenciador} que el {@code Enemigo} puede dropear.
	 */
	private Potenciador powerUp;

	/**
	 * El controlador del {@code Potenciador} para su duracion en el {@code Juego}.
	 * Dicha duracion es mientras el {@code Jugador} no haya capturado el
	 * {@code Potenciador}.
	 */
	private Thread controlPowerUp;

	/**
	 * Constructor que crea un {@code Enemigo} segun el tipo y el {@code Juego} al
	 * que pertenece. Las coordenadas (x,y) en las que se crea el enemigo son
	 * aleatorias definidas por los limites establecidos.
	 * 
	 * @param tipo  El tipo de {@code Enemigo} a crear.
	 * @param juego El {@code Juego} al que pertenece.
	 */
	public Enemigo(int tipo, Juego juego) {

		super(juego);

		setX(GameManager.random.nextInt(X_BOUND));
		setY(GameManager.random.nextInt(Y_MAX + 1 - Y_MIN) + Y_MIN);
		vivo = true;

		this.tipo = tipo;

		movimiento = 0;
		auxMovs = 0.0;
		esquivar = false;
		ladoEsquivar = false;
		damage = 0;
		powerUp = null;
		controlPowerUp = null;

		crearPorTipo(tipo);

		disparos = new LinkedList<>();

	}

	/**
	 * Establece las caracteristicas al {@code Enemigo} segun el numero.
	 * 
	 * @param tipo
	 */
	public void crearPorTipo(int tipo) {

		switch (tipo) {
		case 1:

			crearEnemigo("nave2", 1, 1, 1, true, 33, null);
			break;
		case 2:
			crearEnemigo("nave6", 2, 3, 1, false, 40, new Potenciador(PODER.CONGELAR, getJuego()));
			break;
		case 3:

			break;
		case 4:

			break;
		default:

			break;
		}

	}

	/**
	 * Modifica la skin, vida, velocidad, habilidad de esquivar y movimiento de un
	 * {@code Enemigo}.
	 * 
	 * @param skin       La nueva skin del {@code Enemigo}.
	 * @param vida       La nueva vida del {@code Enemigo}.
	 * @param velocidad  La nueva velocidad del {@code Enemigo}.
	 * @param movimiento El nuevo movimiento del {@code Enemigo}.
	 * @param esquivar   El nuevo esquivar del {@code Enemigo}, {@code true}.
	 *                   esquiva, {@code false} no esquiva.
	 * @param damage     La cantidad de daño que va a infligir el {@code Enemigo}.
	 * @param powerUp    El {@code Potenciador} que va a tener el {@code Enemigo}.
	 */
	private void crearEnemigo(String skin, int vida, int velocidad, int movimiento, boolean esquivar, int damage,
			Potenciador powerUp) {

		this.damage = damage;
		this.esquivar = esquivar;
		this.vida = vida;
		this.powerUp = powerUp;
		salud = vida;
		this.movimiento = movimiento;
		setSkin(GameManager.imagenes.get(skin));
		setAltura(getSkin().getHeight(null));
		setAncho(getSkin().getWidth(null));
		setVelY(velocidad);
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

		// Limites en X y Y
		if (vivo) {

			if (super.getY() >= Y_LIMIT) {
				super.setY(GameManager.random.nextInt(Y_MAX + 1 - Y_MIN) + Y_MIN);
				super.setX(GameManager.random.nextInt(X_BOUND));
			}

			// Si la salud es menor a cero se muere.
			if (salud <= 0) {
				morir();
			}

			// Si colisiona con el jugador.
			if (Fisica.colision(this, getJuego().getJugador())) {

				if (!getJuego().getJugador().isInvulnerable()) {
					getJuego().getJugador().morir();

				}

				getJuego().getExplosiones().add(new Explosion(this.getX() + WIDTH / 2, this.getY() + HEIGHT, getJuego()));
				getJuego().getExplosiones().getLast().start();

				morir();

			}

			// Si detecta un disparo del Jugador
			if (Fisica.detect(getVision(), getJuego().getJugador().getDisparos())) {

				if (esquivar)
					esquivar();

			}

			// Si detecta otro Enemigo
			if (Fisica.detect(this, getJuego().getEnemigos())) {

				esquivar();

			}

			// Si el controlador del power up es != de null y no está disponible entonces
			// poner el power Up en null.
			if (powerUp != null && !powerUp.isDisponible())
				powerUp = null;

			// Realizar el debido movimiento.
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
		} else {

			// Si el controlador del power up es != de null y no está disponible entonces
			// poner el power Up en null.
			if (powerUp != null && !powerUp.isDisponible())
				powerUp = null;

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
	 * Permite que el enemigo pueda esquivar hacia un lado, dependiendo de la
	 * variable {@link #ladoEsquivar}
	 */
	public void esquivar() {

		if (ladoEsquivar) {

			setX(getX() + 3);

		} else {

			setX(getX() - 3);

		}

	}

	/**
	 * Retorna el lado al que va esquivar el {@code Enemigo}
	 * 
	 * @return {@code true} si el {@code Enemigo} va a esquivar hacia la derecha o
	 *         {@code false} en caso contrario.
	 */
	public boolean getLadoEsquivar() {
		return ladoEsquivar;
	}

	/**
	 * Modifica el lado a esquivar del {@code Enemigo}.
	 * 
	 * @param ladoEsquivar {@code true} esquiva hacia la derecha, {@code false}
	 *                     hacia la izquierda.
	 */
	public void setLadoEsquivar(boolean ladoEsquivar) {
		this.ladoEsquivar = ladoEsquivar;
	}

	/**
	 * Retorna la cantidad de vida que tiene el {@code Enemigo}.
	 * 
	 * @return
	 */
	public int getSalud() {
		return salud;
	}

	/**
	 * Modifica la cantidad de vida del {@code Enemigo}.
	 * 
	 * @param salud La nueva cantidad de vida del {@code Enemigo}.
	 */
	public void setSalud(int salud) {
		this.salud = salud;
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

		if (powerUp != null) {

			powerUp.setX(getX());
			powerUp.setY(getY());
			controlPowerUp = new Thread(powerUp);
			controlPowerUp.start();

		}

		setX(X_DEATH);
		setVelY(0);
		vivo = false;
		getJuego().setEnemigosRestantes(getJuego().getEnemigosRestantes() - 1);

	}

	/**
	 * Retorna la lista de disparos del {@code Enemigo}.
	 * 
	 * @return La lista de disparos del {@code Enemigo}.
	 */
	public LinkedList<Disparo> getDisparos() {
		return disparos;
	}

	/**
	 * Modifica la lista de disparos del {@code Enemigo}.
	 * 
	 * @param disparos La nueva lista de disparos del {@code Enemigo}.
	 */
	public void setDisparos(LinkedList<Disparo> disparos) {
		this.disparos = disparos;
	}

	/**
	 * Retorna el controlador del {@code Potenciador} que tiene el {@code Enemigo}.
	 * 
	 * @return El controlador del {@code Potenciador} del {@code Enemigo}.
	 */
	public Thread getControlPowerUp() {
		return controlPowerUp;
	}

	/**
	 * Modifica el controlador del {@code Potenciador} del {@code Enemigo}.
	 * 
	 * @param controlPowerUp EL nuevo controlador del {@code Potenciador} del
	 *                       {@code Enemigo}.
	 */
	public void setControlPowerUp(Thread controlPowerUp) {
		this.controlPowerUp = controlPowerUp;
	}

	/**
	 * Agrega un disparo a la lista de disparos del {@code Enemigo}.
	 */
	public void agregarDisparo() {

		if (tipo == 1) {
			disparos.add(new Disparo(1, super.getX() + SHOT_OFFSET_X, super.getY() + SHOT_OFFSET_Y, 0, 15, getJuego()));
		}

		if (tipo == 2) {
			disparos.add(new Disparo(1, super.getX() + SHOT_OFFSET_X, super.getY() + SHOT_OFFSET_Y, 0, 20, getJuego()));
		}
	}

	/**
	 * Elimina el {@code Disparo} <b> d </b> de la lista de disparos del
	 * {@code Enemigo}.
	 * 
	 * @param d
	 */
	public void eliminarDisparo(Disparo d) {
		disparos.remove(d);
	}

	/**
	 * Retorna el {@code Potenciador} que puede dropear el {@code Enemigo}. Puede
	 * ser null si no tiene algun {@code Potenciador}
	 * 
	 * @return El {@code Potenciador} del {@code Enemigo}.
	 */
	public Potenciador getPowerUp() {
		return powerUp;
	}

	/**
	 * Modifica el {@code Potenciador} del {@code Enemigo}.
	 * 
	 * @param powerUp El nuevo {@code Potenciador} del {@code Enemigo}
	 */
	public void setPowerUp(Potenciador powerUp) {
		this.powerUp = powerUp;
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

			if (Fisica.colision(disparoTemporal, getJuego().getJugador())) {

				getJuego().getExplosiones().add(new Explosion(disparoTemporal.getX(),
						disparoTemporal.getY() + disparoTemporal.getAltura(), getJuego()));
				getJuego().getExplosiones().getLast().start();

				eliminarDisparo(disparoTemporal);

				if (!getJuego().getJugador().isInvulnerable()) {
					getJuego().getJugador().setSalud(getJuego().getJugador().getSalud() - damage);
				}

			}
			if (disparoTemporal.getY() > SHOT_LIMIT)
				eliminarDisparo(disparoTemporal);

		}

	}

	@Override
	public void render(Graphics g) {

		g.drawImage(getSkin(), getX(), getY(), null);

		if (powerUp != null && powerUp.isDisponible())
			powerUp.render(g);

//		g.setColor(new Color(128, 128, 128));
//		g.fillRect(getX(), getY() - 10, getAncho(), 2);
//		g.setColor(new Color(0, 255, 0));
//
//		if (vivo) {
//
//			g.fillRect(getX(), getY() - 10, (getAncho() / vida) * salud, 2);
//
//		}

		if (GameManager.TEST) {

			GameManager.renderBounds(g, this);

		}

		for (int i = 0; i < disparos.size(); i++) {

			Disparo temp = disparos.get(i);

			temp.render(g);

		}

	}

	@Override
	public Rectangle getVision() {

		return new Rectangle(getX(), getY() + getAltura(), getAncho(), getAltura() * 2);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(getX() + getAncho()/ 8, getY() + getAltura()/ 8, getAncho()- getAncho()/ 4, getAltura()- getAltura()/ 4);
	}

}
