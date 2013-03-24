package movable;

import java.util.Map;
import java.util.TreeMap;

import land.Dir;
import land.Field;
import program.Singleton;
import program.SingletonContainer;
import smell.AntSmell;
import smell.Smell;
import item.Item;
import item.Tentacle;
import item.Volatile;

public class Echidna extends Item implements Movable {

	private boolean isActive = false;
	private Integer wait;
	private Integer hunger;

	@Override
	public void setActualField(Field field) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Echidna.setActualField(" + s.fields.indexOf(field) + ": Field)");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Echidna.setActualField(" + s.fields.indexOf(field) + ": Field)");
		s.depth--;
	}

	/**
	 * 
	 * @return
	 */
	public void decreaseHunger() {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Echidna.decreaseHunger()");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Echidna.decreaseHunger()");
		s.depth--;
	}

	@Override
	public void collisionWithEchidna(Echidna echidna) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Echidna.collisionWithEchidna(" + s.items.indexOf(echidna) + ": Echidna)");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Echidna.collisionWithEchidna(" + s.items.indexOf(echidna) + ": Echidna)");
		s.depth--;
	}

	@Override
	public void step() {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Echidna.step()");

		Echidna echidna = (Echidna) s.items.get(id);

		s.stack.add(12);
		Field prevField = s.fields.get(11);
		prevField.getNeighbours();
		s.stack.remove(s.stack.size() - 1);

		s.stack.add(12);
		prevField.removeItem(echidna);
		s.stack.remove(s.stack.size() - 1);

		s.stack.add(11);
		Field nextField = s.fields.get(10);
		nextField.addItem(echidna);
		nextField.getItems();
		s.stack.remove(s.stack.size() - 1);

		s.stack.add(10);
		Echidna echidna2 = (Echidna) s.items.get(9);
		echidna2.collisionWithEchidna(echidna);
		s.stack.remove(s.stack.size() - 1);

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Echidna.step()");
		s.depth--;

	}

	@Override
	public void setAlive() {

	}

	/**
	 * @param ant
	 *            -
	 * @param b
	 *            -
	 */
	@Override
	public void collisionWithAnt(Ant ant, boolean b) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Echidna.collisionWithAnt("
				+ s.ants.indexOf(ant) + ": Ant, " + String.valueOf(b)
				+ ": boolean)");

		if (b == true) {
			s.stack.add(s.items.indexOf(ant));
			ant.kill();
			s.stack.remove(s.stack.size() - 1);

			s.stack.add(id);
			Echidna echidna = (Echidna) s.items.get(id - 1);
			echidna.decreaseHunger();
			s.stack.remove(s.stack.size() - 1);
		}
		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Echidna.collisionWithAnt("
				+ s.ants.indexOf(ant) + ": Ant, " + String.valueOf(b)
				+ ": boolean)");

		s.depth--;
	}

}