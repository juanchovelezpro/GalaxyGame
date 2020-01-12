package interfaz;

import javax.swing.*;

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

	private JLabel labEnemigosRestantes;
	private JLabel labFPS;

	public PanelOpciones(Game game) {

		this.game = game;

		setLayout(null);

		componentes();

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

		add(butJugar);

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

			panelGame = new PanelGame(this);

			this.removeAll();
			game.add(panelGame);
			HUD();
			game.refresh();

			panelGame.requestFocus();

			SoundPlayer.play("/sounds/backgroundMusic.wav");

		}
	}

	public JLabel getLabEnemigosRestantes() {
		return labEnemigosRestantes;
	}

	public void setLabEnemigosRestantes(JLabel labEnemigosRestantes) {
		this.labEnemigosRestantes = labEnemigosRestantes;
	}

}
