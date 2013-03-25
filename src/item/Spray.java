package item;

import movable.Ant;
import movable.Echidna;
import program.Singleton;

/**
 * 
 * @author audiolovenation
 * 
 *         Minden, a mez�k�n megjelen� elem ebb�l az oszt�lyb�l sz�ramzik le
 */
public class Spray extends Item implements Volatile {

	private Integer strength;

	/**
	 * Spray er�ss�g�nek a cs�kkent�se
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
	 * Hangy�val val� �tk�z�s. Nem csin�l semmit, csak visszat�r.
	 * 
	 * @param Ant
	 *            a hangya amivel �tk�zik
	 * @param b
	 *            �tk�z�s el�tt: false, �tk�z�s ut�n: true
	 */
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

	/**
	 * Hangy�szs�nnel val� �tk�z�s. Nem csin�l semmit, csak visszat�r.
	 * 
	 * @param Echidna
	 *            a hangy�szs�n amivel �tk�zik
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
	 * Spray-vel val� �tk�z�s. Nem csin�l semmit, csak visszat�r.
	 * 
	 * @param Echidna
	 *            a hangy�szs�n amivel �tk�zik
	 */
	public void collisionWithSpray(Integer strength) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Spray.collisionWithSpray(" + strength
				+ ": Integer)");

		s.stack.add(6);
		s.ants.get(5).looseHP(10);
		s.stack.remove(s.stack.size() - 1);

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Spray.collisionWithSpray("
				+ strength + ": Integer)");
		s.depth--;
	}

}
