package interfaz;

import javax.swing.*;

import modelo.Juego;
import modelo.Juego.MODO;
import tools.GameManager;
import tools.SoundPlayer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelOpciones extends JPanel implements ActionListener {

	public static final Image BACKGROUND = GameManager.imagenes.get("galaxy2");

	private Game game;
	private JButton butJugar;
	private PanelGame panelGame;
	private PanelGameHistoria panelHistoria;

	private JButton butJugarMundos;
	private JButton butJugarOriginal;
	private JButton butJugarInvasion;
	private JButton butRegresar;

	private JLabel labEnemigosRestantes;
	private JLabel labFPS;

	public PanelOpciones(Game game) {

		this.game = game;

		setLayout(null);

		componentes();

		add(butJugar);

	}

	public void componentes() {

		Font fuente = new Font("Broadway", 1, 50);
		Color background = new Color(255, 255, 255, 125);

		butJugar = new JButton("JUGAR");
		butJugar.setFont(fuente);
		butJugar.setBackground(background);
		butJugar.setFocusable(false);
		butJugar.setSize(450, 60);
		butJugar.setLocation(GameManager.WIDTH / 2 - butJugar.getWidth() / 2, GameManager.HEIGHT / 2);
		butJugar.addActionListener(this);

		butJugarMundos = new JButton("Mundos");
		butJugarMundos.setFont(fuente);
		butJugarMundos.setBackground(background);
		butJugarMundos.setFocusable(false);
		butJugarMundos.setSize(450, 60);
		butJugarMundos.setLocation(butJugar.getX(), butJugar.getY() - 100);
		butJugarMundos.addActionListener(this);

		butJugarOriginal = new JButton("Original");
		butJugarOriginal.setFont(fuente);
		butJugarOriginal.setBackground(background);
		butJugarOriginal.setFocusable(false);
		butJugarOriginal.setSize(450, 60);
		butJugarOriginal.setLocation(GameManager.WIDTH / 2 - butJugar.getWidth() / 2, GameManager.HEIGHT / 2);
		butJugarOriginal.addActionListener(this);

		butJugarInvasion = new JButton("Invasion");
		butJugarInvasion.setFont(fuente);
		butJugarInvasion.setBackground(background);
		butJugarInvasion.setFocusable(false);
		butJugarInvasion.setSize(450, 60);
		butJugarInvasion.setLocation(butJugar.getX(), butJugar.getY() + 100);
		butJugarInvasion.addActionListener(this);

		butRegresar = new JButton("BACK");
		butRegresar.setFont(new Font("Broadway", 1, 36));
		butRegresar.setBackground(background);
		butRegresar.setFocusable(false);
		butRegresar.setSize(150, 75);
		butRegresar.setLocation(10, 10);
		butRegresar.addActionListener(this);

	}

	public Game getGame() {

		return game;

	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.drawImage(BACKGROUND, 0, 0, null);

		repaint();

	}

	public void HUD() {

		Font font = new Font("Garamond", 1, 36);

		labEnemigosRestantes = new JLabel("Enemigos restantes:");
		labEnemigosRestantes.setForeground(Color.WHITE);
		labEnemigosRestantes.setFont(font);
		labEnemigosRestantes.setSize(400, 60);
		labEnemigosRestantes.setLocation(0, 0);

		labFPS = new JLabel("FPS");
		labFPS.setForeground(Color.WHITE);
		labFPS.setFont(font);
		labFPS.setSize(300, 60);
		labFPS.setLocation(0, 62);

		game.add(labEnemigosRestantes);
		game.add(labFPS);

	}

	public JLabel getLabFPS() {
		return labFPS;
	}

	public void setLabFPS(JLabel labFPS) {
		this.labFPS = labFPS;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(butJugar)) {

			this.removeAll();

			add(butJugarMundos);
			add(butJugarOriginal);
			add(butJugarInvasion);
			add(butRegresar);

			game.refresh();

		}

		if (e.getSource().equals(butJugarMundos)) {

			game.setJuego(new Juego(MODO.HISTORIA));
			
			panelHistoria = new PanelGameHistoria(this);
			this.removeAll();
			game.add(panelHistoria);
			panelHistoria.requestFocus();

			SoundPlayer.play("/sounds/backgroundMusic.wav");

		}

		if (e.getSource().equals(butJugarOriginal)) {

			game.setJuego(new Juego(MODO.ORIGINAL));
			
			panelGame = new PanelGame(this);

			this.removeAll();

			game.add(panelGame);
			HUD();

			panelGame.requestFocus();

			SoundPlayer.play("/sounds/backgroundMusic.wav");

		}

		if (e.getSource().equals(butJugarInvasion)) {

		}

		if (e.getSource().equals(butRegresar)) {

			this.removeAll();

			add(butJugar);

			game.refresh();

		}

	}

	public JLabel getLabEnemigosRestantes() {
		return labEnemigosRestantes;
	}

	public void setLabEnemigosRestantes(JLabel labEnemigosRestantes) {
		this.labEnemigosRestantes = labEnemigosRestantes;
	}

}
