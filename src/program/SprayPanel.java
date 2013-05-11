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
	public static Integer antSmellCapacity = 40;
	public static Integer antKillerCapacity = 20;
	private static JLabel antKillerLabel = new JLabel("Capacity: " + antKillerCapacity.toString());
	private static JLabel antSmellLabel = new JLabel("Capacity: " + antSmellCapacity.toString());

	public SprayPanel() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
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
		add(antSmellLabel);
		add(new JLabel("Ant killer spray:"));
		add(antKillerButton);
		add(antKillerLabel);
	}

	public static String getType() {
		return type;
	}
	
	public static void refreshCapacity(){
		antKillerLabel.setText("Capacity: " + antKillerCapacity.toString());
		antSmellLabel.setText("Capacity: " + antSmellCapacity.toString());
	}
}
