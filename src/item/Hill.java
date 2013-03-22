package item;

import program.Singleton;
import land.Dir;
import movable.Ant;

public class Hill extends Item {
	@Override
	public void collisionWithAnt(Ant ant, boolean b) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Ant.collisionWithAnt(" + s.ants.indexOf(ant) + ": Ant, " + String.valueOf(b) + ")");

		s.stack.add(6);
		s.ants.get(5).rest();
		s.stack.remove(s.stack.size() - 1);

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Ant.collisionWithAnt(" + s.ants.indexOf(ant) + ": Ant, " + String.valueOf(b) + ")");
		s.depth--;
	}
}