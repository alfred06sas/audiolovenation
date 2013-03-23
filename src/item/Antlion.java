package item;

import land.Field;
import movable.Ant;
import movable.Echidna;
import program.Singleton;

public class Antlion extends Item {
	@Override
	public void collisionWithAnt(Ant ant, boolean b) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Antlion.collisionWithAnt("
				+ s.ants.indexOf(ant) + ": Ant, " + String.valueOf(b) + ": boolean)");
		if (b == true) {
			s.stack.add(s.ants.indexOf(ant));
			ant.kill();
			s.stack.remove(s.stack.size()-1);
		}

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Antlion.collisionWithAnt("
				+ s.ants.indexOf(ant) + ": Ant, " + String.valueOf(b) + ": boolean)");
		s.depth--;
	}
	@Override
	public void collisionWithEchidna(Echidna echidna) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Antlion.collisionWithEchidna("
				+ s.echidnas.indexOf(echidna) + ": Echidna)");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Antlion.collisionWithEchidna("
				+ s.echidnas.indexOf(echidna) + ": Echidna)");
		s.depth--;
	}
	
	@Override
	public void collisionWithSpray(Integer strength) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Antlion.collisionWithSpray("
				+ strength + ": Integer)");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Antlion.collisionWithSpray("
				+ strength + ": Integer)");
		s.depth--;
	}
}