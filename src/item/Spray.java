package item;

import movable.Ant;
import movable.Echidna;
import program.Singleton;

/**
 * 
 * @author audiolovenation
 * 
 *         A hangya megölésére szolgáló sprayt valósítja meg. Használatakor a
 *         használt mezõn és annak a szomszédjain (itt már kisebb mértékben hat
 *         csak) hat.
 */
public class Spray extends Item implements Volatile {

	private Integer strength;

	/**
	 * Spray erõsségének a csökkentése
	 * 
	 */
	@Override
	public void decrease() {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Spray.decrease()");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Spray.decrease()");
		s.depth--;
	}

	/**
	 * Hangyával való ütközés. Nem csinál semmit, csak visszatér.
	 * 
	 * @param Ant
	 *            a hangya amivel ütközik
	 * @param b
	 *            ütközés elõtt: false, ütközés után: true
	 */
	@Override
	public void collisionWithAnt(Ant ant, boolean b) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Spray.collisionWithAnt("
				+ s.ants.indexOf(ant) + ": Ant, " + String.valueOf(b) + ")");

		if (b == true) {
			s.stack.add(s.ants.indexOf(ant));
			//Hangya életerejének csökkentése
			ant.looseHP(5);
			s.stack.remove(s.stack.size() - 1);
		}

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Spray.collisionWithAnt("
				+ s.ants.indexOf(ant) + ": Ant, " + String.valueOf(b) + ")");
		s.depth--;
	}

	/**
	 * Hangyászsünnel való ütközés. Nem csinál semmit, csak visszatér.
	 * 
	 * @param Echidna
	 *            a hangyászsün amivel ütközik
	 */
	@Override
	public void collisionWithEchidna(Echidna echidna) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Spray.collisionWithEchidna("
				+ s.echidnas.indexOf(echidna) + ": Echidna)");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Spray.collisionWithEchidna("
				+ s.echidnas.indexOf(echidna) + ": Echidna)");
		s.depth--;
	}

	/**
	 * Spray-vel való ütközés. Nem csinál semmit, csak visszatér.
	 * 
	 * @param Echidna
	 *            a hangyászsün amivel ütközik
	 */
	public void collisionWithSpray(Integer strength) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Spray.collisionWithSpray(" + strength
				+ ": Integer)");

		s.stack.add(6);
		//Hangya életerejének csökkentése
		s.ants.get(5).looseHP(10);
		s.stack.remove(s.stack.size() - 1);

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Spray.collisionWithSpray("
				+ strength + ": Integer)");
		s.depth--;
	}

}
