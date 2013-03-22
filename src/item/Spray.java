package item;

import program.Singleton;
import movable.Ant;

public class Spray extends Item implements Volatile {

	private Integer strength;

	@Override
	public void decrease() {

	}

	@Override
	public void collisionWithAnt(Ant ant, boolean b) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Spray.collisionWithAnt("
				+ s.ants.indexOf(ant) + ": Ant, " + String.valueOf(b) + ")");

		if (b == true) {
			s.stack.add(s.ants.indexOf(ant));
			ant.looseHP(5);
			s.stack.remove(s.stack.size() - 1);
		}

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Spray.collisionWithAnt("
				+ s.ants.indexOf(ant) + ": Ant, " + String.valueOf(b) + ")");
		s.depth--;
	}
}