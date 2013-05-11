package program;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SprayPanel extends JPanel {

	private static String type = null;
	
	public SprayPanel() {
		setLayout(new BoxLayout(this,
				BoxLayout.PAGE_AXIS));
		ImageIcon antSmellIcon = new ImageIcon("images/spray_black.png");
		JButton antSmellButton = new JButton(antSmellIcon);
		antSmellButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				type = "smell";
			}
		});
		
		ImageIcon antKillerIcon = new ImageIcon("images/spray_piros.png");
		JButton antKillerButton = new JButton(antKillerIcon);
		antKillerButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				type = "killer";
			}
		});
		
		add(new JLabel("Ant smell spray:"));
		add(antSmellButton);
		add(new JLabel("Ant killer spray:"));
		add(antKillerButton);
	}
	
	public static String getType(){
		return type;
	}
}
