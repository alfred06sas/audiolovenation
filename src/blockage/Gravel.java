package blockage;

import land.Field;
import movable.Ant;
import movable.Echidna;
import program.Singleton;
/**
 * 
 * @author audiolovenation
 * 
 * Egy akadály (kavics). Ha a hangya ilyen mezõre akar lépni, kénytelen másik útvonalat választani. 
 * A hangyászsün figyelmen kivül hagyja.
 *
 */
public class Gravel extends Blockage {
	/**
	 * Hangyával való ütközés. Lépés után (b == true) nem csinál semmit, mivel ez az eset nem lehetséges: a hangya nem léphet akadályra.
	 * Lépés elõtt (b == false) jelzi a hangyának h az õt tartalmazó mezõre nem léphet.
	 * 
	 * @param ant a hangya referenciája. amivel ütközött
	 * @param b lépés elõtt: false, lépés után: true
	 */
	@Override
	public void collisionWithAnt(Ant ant, boolean b) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Gravel.collisionWithAnt("
				+ s.ants.indexOf(ant) + ": Ant, " + String.valueOf(b) + ": boolean)");
		if (b == false) {
			s.stack.add(s.ants.indexOf(ant));
			Field field = s.fields.get(14);
			ant.canNotGo(field);
			s.stack.remove(s.stack.size() - 1);
		}

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Gravel.collisionWithAnt("
				+ s.ants.indexOf(ant) + ": Ant, " + String.valueOf(b) + ": boolean)");
		s.depth--;
	}
	
	/**
	 * Hangyászsünnel való ütközés. Nem csinál semmit, csak visszatér.
	 * 
	 * @param echidna az a hangyászsün. amivel ütközött
	 */
	@Override
	public void collisionWithEchidna(Echidna echidna) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);
		
		s.makeSpace(">> CALL: " + id + ": Gravel.collisionWithEchidna("
				+ s.echidnas.indexOf(echidna) + ": Echidna)");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Gravel.collisionWithEchidna("
				+ s.echidnas.indexOf(echidna) + ": Echidna)");
		s.depth--;
	}
	
}