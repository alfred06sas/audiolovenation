package blockage;

import item.Item;
import land.Field;
import movable.Ant;
import program.Singleton;

public class Blockage extends Item {
	public void collisionWithAnt(Ant ant, boolean b) 
	{
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Blockage.collisionWithAnt(" + s.ants.indexOf(ant) + ": Ant, " + String.valueOf(b) + ")");

		s.stack.add(s.ants.indexOf(ant));
		Field field = s.fields.get(14);
		ant.canNotGo(field);
		s.stack.remove(s.stack.size() - 1);

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Blockage.collisionWithAnt(" + s.ants.indexOf(ant) + ": Ant, " + String.valueOf(b) + ")");
		s.depth--;
	}
}