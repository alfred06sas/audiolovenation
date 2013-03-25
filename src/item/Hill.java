package item;

import program.Singleton;
import land.Dir;
import movable.Ant;
import movable.Echidna;
/**
 * 
 * @author audiolovenation
 * 
 *			A hangyák a hangyabolyból indulnek el ételt keresni, 
 *			majd ha találtak azt ide hozzák vissza
 */
public class Hill extends Item {

	/**
	 * Hangyával való ütközés.
	 * 
	 * @param Ant
	 *            a hangya ami ütközik a bollyal
	 * @param b
	 *            ütközés elõtt: false, ütközés után: true
	 */
	@Override
	public void collisionWithAnt(Ant ant, boolean b) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Ant.collisionWithAnt("
				+ s.ants.indexOf(ant) + ": Ant, " + String.valueOf(b) + ")");

		s.stack.add(6);
		Ant ant1 = s.ants.get(5);
		ant1.rest();
		s.stack.remove(s.stack.size() - 1);

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Ant.collisionWithAnt("
				+ s.ants.indexOf(ant) + ": Ant, " + String.valueOf(b) + ")");
		s.depth--;
	}

	/**
	 * Hangyászsünnel való ütközés.
	 * 
	 * @param Echidna
	 *            a hangyászsün ami ütközik a bollyal
	 */
	@Override
	public void collisionWithEchidna(Echidna echidna) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Hill.collisionWithEchidna("
				+ s.echidnas.indexOf(echidna) + ": Echidna)");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Hill.collisionWithEchidna("
				+ s.echidnas.indexOf(echidna) + ": Echidna)");
		s.depth--;
	}
}