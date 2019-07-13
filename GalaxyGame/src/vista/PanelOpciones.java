package vista;

import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelOpciones extends JPanel implements ActionListener {

	public static final Image BACKGROUND = Toolkit.getDefaultToolkit().createImage("./resources/galaxy3.jpg");

	private Game game;
	private JButton butJugar;
	private PanelGame panelGame;

	public PanelOpciones(Game game) {

		this.game = game;

		setLayout(null);

		panelGame = new PanelGame(this);

		componentes();

	}

	public void componentes() {

		Font fuente = new Font("Garamond", 1, 36);
		Color background = new Color(255, 255, 255, 125);

		butJugar = new JButton("JUGAR");
		butJugar.setFont(fuente);
		butJugar.setBackground(background);
		butJugar.setBounds(200, 250, 450, 60);
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
	
		if(e.getSource().equals(butJugar)) {
			
			game.remove(this);
			game.add(panelGame);
			game.refresh();
			
			JOptionPane.showMessageDialog(null, "A Jugar", "", 1);
			
		}
		
		
	}

}
