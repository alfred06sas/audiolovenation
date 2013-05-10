package program;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import view.PaintableView;

import land.Land;

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
		JMenuBar menuBar = new JMenuBar();

		JMenu gameMenu = new JMenu("Game");
		JMenu newGameMenu = new JMenu("New Game");
		JMenuItem littleMapMenuItem = new JMenuItem("Little map");
		JMenuItem mediumMapMenuItem = new JMenuItem("Medium map");
		JMenuItem bigMapMenuItem = new JMenuItem("Big map");
		JMenuItem exitMenuItem = new JMenuItem("Exit");

		JMenu helpMenu = new JMenu("Help");
		JMenuItem helpMenuItem = new JMenuItem("About Game");

		// elemek hozzaadasa a menuhoz
		newGameMenu.add(littleMapMenuItem);
		newGameMenu.add(mediumMapMenuItem);
		newGameMenu.add(bigMapMenuItem);

		gameMenu.add(newGameMenu);
		gameMenu.add(exitMenuItem);

		helpMenu.add(helpMenuItem);

		menuBar.add(gameMenu);
		menuBar.add(helpMenu);

		// listenerek beallitasa
		exitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		littleMapMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.revalidate();
				land.init(6, 6);
				
			}
		});
		
		mediumMapMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				land.init(12, 12);
			}
		});
		
		bigMapMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				land.init(14, 14);
			}
		});
		
		frame.add(PaintableView.panel);
		frame.setJMenuBar(menuBar);
		frame.setSize(1000, 700);
		frame.setTitle("AntGame");
		frame.setVisible(true);
	}
}