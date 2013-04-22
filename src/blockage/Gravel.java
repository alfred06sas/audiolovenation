package blockage;

import item.Item;

import java.util.Map;

import land.Dir;
import land.Field;
import movable.Echidna;
import movable.Movable;

/**
 * 
 * Egy akadaly (kavics). Ha a hangya ilyen mezore akar lepni, kenytelen masik
 * utvonalat valasztani. A hangyaszsun figyelmen kivul hagyja.
 * 
 * @author audiolovenation
 * 
 */
public class Gravel extends Blockage implements Movable {

	public Gravel() {
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
	public Integer collisionWithEchidna(Echidna echidna, boolean b, Dir dir) {
		Map<Dir, Field> neighbours = getActualField().getNeighbours();
		Integer gravelNr = 0;

		for (Item item : neighbours.get(dir).getItems()) {
			gravelNr += item.collisionWithGravel(this, b, dir);
		}
		
		if (b == true) {
			return 0;
		}
		
		return gravelNr;
	}

	@Override
	public int collisionWithGravel(Gravel gravel, boolean b, Dir dir) {
		Map<Dir, Field> neighbours = getActualField().getNeighbours();
		int gravelNr = 0;

		for (Item item : neighbours.get(dir).getItems()) {
			gravelNr += item.collisionWithGravel(this, b, dir);
		}

		if (b == true) {
			actualField.removeItem(this);
			Field nextField = neighbours.get(dir);
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