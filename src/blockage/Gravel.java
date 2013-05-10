package blockage;

import item.Item;

import java.util.List;
import java.util.Map;

import land.Dir;
import land.Field;
import movable.Echidna;
import movable.Movable;
import program.SingletonWriter;
import view.GravelView;

/**
 * 
 * Egy akadaly (kavics). Ha a hangya ilyen mezore akar lepni, kenytelen masik
 * utvonalat valasztani. A hangyaszsun figyelmen kivul hagyja.
 * 
 * @author audiolovenation
 * 
 */
public class Gravel extends Blockage implements Movable {

	private GravelView gravelView;

	public Gravel() {
	}

	public void setView(){
		gravelView = new GravelView();
		gravelView.setPaintable(this);
	}
	
	public Gravel(String ID) {
		super(ID);
		id = "g" + ID;
	}

	/**
	 * Hangyaszsunnel valo utkozes. Nem csinal semmit, csak visszater.
	 * 
	 * @param echidna
	 *            az a hangyaszsun. amivel utkozott
	 */
	@Override
	public int collisionWithEchidna(Echidna echidna, boolean b, Dir dir) {
		SingletonWriter s = SingletonWriter.Instance();
		int gravelNr = 1;
		Map<Dir, Field> neighbours = actualField.getNeighbours();
		Field nextField = neighbours.get(dir);
		List<Item> nextFieldItems = nextField.getItems();

		if (b == true) {
			s.printCollision(echidna, this, actualField);
			s.printStep(this, actualField, nextField);
			
		}
		
		Item nextGravelRef = null;
		for (Item item : nextFieldItems) {
			gravelNr += item.collisionWithGravel(this, b, dir);
			if (gravelNr == 1 && b == true) {
				nextGravelRef = item;	
			}
		}

		if (b == true) {
			if (nextGravelRef != null)
				nextField.removeItem(nextGravelRef);
			actualField.removeItem(this);
			nextField.addItem(this);
			setActualField(nextField);

			return 0;
		}

		return gravelNr;
	}

	@Override
	public int collisionWithGravel(Gravel gravel, boolean b, Dir dir) {
		SingletonWriter s = SingletonWriter.Instance();
		int gravelNr = 1;
		Map<Dir, Field> neighbours = actualField.getNeighbours();
		Field nextField = neighbours.get(dir);
		List<Item> items = nextField.getItems();

		if (b == true) {
			s.printCollision(gravel, this, actualField);
			s.printStep(this, actualField, nextField);
		}

		for (Item item : items) {
			gravelNr += item.collisionWithGravel(this, b, dir);
		}

		if (b == true) {
			setActualField(nextField);
			nextField.addItem(this);
			
			return 0;
		}

		return gravelNr;
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAlive() {
		// TODO Auto-generated method stub

	}

}