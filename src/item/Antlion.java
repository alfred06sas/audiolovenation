package item;

import land.Field;
import movable.Ant;
import movable.Echidna;
import program.Singleton;

/**
 * 
 * @author audiolovenation
 * 
 *         A hangyászsün néha feltûnik és amíg éhes megeszi az összes útjába
 *         kerülõ hangyát, majd ha jól lakott továbbáll.
 */
public class Antlion extends Item {
	/**
	 * Hangyával való ütközés. Ha éhes megeszi a hangyát.
	 * 
	 * 
	 * @param ant
	 *            a hangya referenciája. amivel ütközött
	 * @param b
	 *            ütközés elõtt: false, ütközés után: true
	 */
	@Override
	public void collisionWithAnt(Ant ant, boolean b) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Antlion.collisionWithAnt("
				+ s.ants.indexOf(ant) + ": Ant, " + String.valueOf(b)
				+ ": boolean)");
		if (b == true) {
			s.stack.add(s.ants.indexOf(ant));
			ant.kill();
			s.stack.remove(s.stack.size() - 1);
		}

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Antlion.collisionWithAnt("
				+ s.ants.indexOf(ant) + ": Ant, " + String.valueOf(b)
				+ ": boolean)");
		s.depth--;
	}

	/**
	 * Hangyászsünnel való ütközés. Nem csinál semmit, csak visszatér.
	 * 
	 * @param echidna
	 *            az a hangyászsün. amivel ütközött
	 */
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

	/**
	 * Hangyászsünnel való ütközés. Nem csinál semmit, csak visszatér.
	 * 
	 * @param strength
	 *            a Spray erõssége
	 */
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