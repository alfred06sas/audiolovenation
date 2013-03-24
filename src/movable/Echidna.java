package movable;

import land.Field;
import program.Singleton;
import item.Item;

public class Echidna extends Item implements Movable {

	private boolean isActive = false;
	private Integer wait;
	private Integer hunger;

	@Override
	public void setActualField(Field field) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Echidna.setActualField()");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Echidna.setActualField()");
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

		s.makeSpace(">> CALL: " + id + ": Echidna.collisionWithEchidna()");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Echidna.collisionWithEchidna()");
		s.depth--;
	}

	@Override
	public void step() {

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
				+ s.ants.indexOf(ant) + ": Ant, " + String.valueOf(b) + ": boolean)");

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
				+ s.ants.indexOf(ant) + ": Ant, " + String.valueOf(b) + ": boolean)");

		s.depth--;
	}

}