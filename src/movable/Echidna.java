package movable;

import program.Singleton;
import item.Item;

public class Echidna extends Item implements Movable {

	private boolean isActive = false;
	private Integer wait;
	private Integer hunger;

	/**
	 * 
	 * @return
	 */
	public void decreaseHunger() {
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