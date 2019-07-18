package interfaz;

import javax.swing.*;

import tools.ScreenResolution;
import tools.ToolManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelOpciones extends JPanel implements ActionListener {

	public static final Image BACKGROUND = ToolManager.cargarImagen("fondos/galaxy2.jpg");

	private Game game;
	private JButton butJugar;
	private PanelGame panelGame;

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
		butJugar.setLocation(ScreenResolution.WIDTH / 2 - butJugar.getWidth() / 2, ScreenResolution.HEIGHT / 2);

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

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(butJugar)) {

			panelGame = new PanelGame(this);

			this.removeAll();
			game.add(panelGame);
			game.refresh();

			panelGame.requestFocus();

		}

	}

}
