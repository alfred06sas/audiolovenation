package view;

import item.Item;
import item.Spray;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JPanel;

import land.Field;
import land.Land;
import program.SingletonContainer;
import program.SprayPanel;

public class GamePanel extends JPanel implements MouseListener {

	Land land;

	public GamePanel() {
		addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x, y;

		x = (int) ((e.getX() + 12.5) / 75);

		if (x % 2 == 0) {
			y = e.getY() / 100;
			y *= 2;
		} else {
			y = (e.getY() + 50) / 100;
			y = (y * 2) - 1;
		}

		Field field = land
				.getField(String.valueOf(y) + "_" + String.valueOf(x));

		if (SprayPanel.getType() != null
				&& SprayPanel.getType().equals("killer")
				&& SprayPanel.antKillerCapacity > 0) {
			SprayPanel.antKillerCapacity--;
			antKillerSpray(field);
			SprayPanel.refreshCapacity();
		} else if (SprayPanel.getType() != null
				&& SprayPanel.getType().equals("smell")
				&& SprayPanel.antSmellCapacity > 0) {
			SprayPanel.antSmellCapacity--;
			antSmellSpray(field);
			SprayPanel.refreshCapacity();
		}

	}

	private void antKillerSpray(Field field) {
		Spray spray = new Spray();
		SingletonContainer sc = SingletonContainer.getInstance();
		sc.addVolatile(spray);

		spray.setView();
		field.addItem(spray);

		CopyOnWriteArrayList<Item> items = field.getItems();

		for (Item i : items) {
			i.collisionWithSpray(spray.getStrength());
		}

	}

	private void antSmellSpray(Field field) {

	}

	public void addLand(Land land) {
		this.land = land;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
