package interfaz;

import javax.swing.*;

import tools.ScreenResolution;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelPausa extends JPanel implements ActionListener {

	private PanelGame panelGame;

	private JButton butReanudar;
	private JButton butGuardar;
	private JButton butGuardarSalir;

	public PanelPausa(PanelGame panelGame) {

		this.panelGame = panelGame;

		configPanel();

	}

	public void configPanel() {

		setBounds(0, -1, ScreenResolution.WIDTH_GAME, ScreenResolution.HEIGHT_GAME);
		setBackground(new Color(0, 0, 0, 125));

		Font font = new Font("Broadway", 1, 36);
		Color color = new Color(255, 255, 255, 125);

		butReanudar = new JButton("Reanudar");
		butReanudar.setBackground(color);
		butReanudar.setFont(font);
		butReanudar.setFocusable(false);
		butReanudar.setSize(450, 40);
		butReanudar.setLocation(ScreenResolution.WIDTH_GAME / 2 - butReanudar.getWidth() / 2,
				ScreenResolution.HEIGHT_GAME / 4 + ScreenResolution.HEIGHT_GAME / 8);
		butReanudar.addActionListener(this);

		butGuardar = new JButton("Guardar");
		butGuardar.setBackground(color);
		butGuardar.setFont(font);
		butGuardar.setFocusable(false);
		butGuardar.setSize(450, 40);
		butGuardar.setLocation(ScreenResolution.WIDTH_GAME / 2 - butReanudar.getWidth() / 2,
				butReanudar.getY() + ScreenResolution.HEIGHT_GAME / 16);
		butGuardar.addActionListener(this);

		butGuardarSalir = new JButton("Guardar y salir");
		butGuardarSalir.setBackground(color);
		butGuardarSalir.setFont(font);
		butGuardarSalir.setFocusable(false);
		butGuardarSalir.setSize(450, 40);
		butGuardarSalir.setLocation(ScreenResolution.WIDTH_GAME / 2 - butReanudar.getWidth() / 2,
				butGuardar.getY() + ScreenResolution.HEIGHT_GAME / 16);
		butGuardarSalir.addActionListener(this);

		add(butReanudar);
		add(butGuardar);
		add(butGuardarSalir);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(butReanudar)) {

			panelGame.getPanelOpciones().getGame().getJuego().setPausa(false);
			panelGame.reanudar();
			panelGame.remove(this);

		}

		if (e.getSource().equals(butGuardar)) {

		}

		if (e.getSource().equals(butGuardarSalir)) {

		}

	}

}
