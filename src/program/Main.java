package program;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import land.Land;
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
		final JPanel gamePanel = PaintableView.panel;

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
				land.init(6, 6);
			}
		});

		mediumMapButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				land.init(12, 12);
			}
		});

		bigMapButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				land.init(14, 14);
			}
		});

		JPanel mainPanel = new JPanel();
		JPanel controllerPanel = new JPanel();

		controllerPanel.setLayout(new BoxLayout(controllerPanel,
				BoxLayout.PAGE_AXIS));

		ImageIcon antSmellIcon = new ImageIcon("images/spray_black.png");
		JButton antSmellButton = new JButton(antSmellIcon);
		ImageIcon antKillerIcon = new ImageIcon("images/spray_piros.png");
		JButton antKillerButton = new JButton(antKillerIcon);

		controllerPanel.add(new JLabel("New Game:"));
		controllerPanel.add(littleMapButton);
		controllerPanel.add(mediumMapButton);
		controllerPanel.add(bigMapButton);
		controllerPanel.add(new JLabel("Ant smell spray:"));
		controllerPanel.add(antSmellButton);
		controllerPanel.add(new JLabel("Ant killer spray:"));
		controllerPanel.add(antKillerButton);
		controllerPanel.add(exitButton);

		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(gamePanel, BorderLayout.CENTER);
		mainPanel.add(controllerPanel, BorderLayout.EAST);

		frame.add(mainPanel);
		frame.setSize(1000, 700);
		frame.setTitle("AntGame");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}