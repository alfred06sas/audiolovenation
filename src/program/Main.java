package program;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import land.Land;
import view.GamePanel;
import view.PaintableView;

/**
 * 
 * @author audiolovenation
 * 
 *         Main
 * 
 */
public class Main {

	/**
	 * main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		final Land land = new Land();

		final JFrame frame = new JFrame();
		final GamePanel gamePanel = PaintableView.panel;
		gamePanel.addLand(land);

		JButton exitButton = new JButton("exit");
		exitButton.setMaximumSize(new Dimension(135, 50));
		exitButton.setMinimumSize(new Dimension(135, 50));
		JButton littleMapButton = new JButton("Little map");
		littleMapButton.setMaximumSize(new Dimension(135, 50));
		littleMapButton.setMinimumSize(new Dimension(135, 50));
		JButton mediumMapButton = new JButton("Medium map");
		mediumMapButton.setMaximumSize(new Dimension(135, 50));
		mediumMapButton.setMinimumSize(new Dimension(135, 50));
		JButton bigMapButton = new JButton("Big map");
		bigMapButton.setMaximumSize(new Dimension(135, 50));
		bigMapButton.setMinimumSize(new Dimension(135, 50));

		// listenerek beallitasa
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		littleMapButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				land.init(8, 10);
			}
		});

		mediumMapButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				land.init(10, 12);
			}
		});

		bigMapButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				land.init(12, 14);
			}
		});

		JPanel mainPanel = new JPanel();
		JPanel controllerPanel = new JPanel();
		SprayPanel sprayPanel = new SprayPanel();

		controllerPanel.setLayout(new BoxLayout(controllerPanel,
				BoxLayout.PAGE_AXIS));

		controllerPanel.add(new JLabel("New Game:"));
		controllerPanel.add(littleMapButton);
		controllerPanel.add(mediumMapButton);
		controllerPanel.add(bigMapButton);
		controllerPanel.add(sprayPanel);
		controllerPanel.add(exitButton);

		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(gamePanel, BorderLayout.CENTER);
		mainPanel.add(controllerPanel, BorderLayout.EAST);

		frame.add(mainPanel);
		frame.setSize(1250, 700);
		frame.setTitle("AntGame");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
	}

}