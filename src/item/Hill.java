package item;

import program.Singleton;
import land.Dir;
import movable.Ant;
import movable.Echidna;
/**
 * 
 * @author audiolovenation
 * 
 *			A hangy�k a hangyabolyb�l indulnek el �telt keresni, 
 *			majd ha tal�ltak azt ide hozz�k vissza
 */
public class Hill extends Item {

	/**
	 * Hangy�val val� �tk�z�s.
	 * 
	 * @param Ant
	 *            a hangya ami �tk�zik a bollyal
	 * @param b
	 *            �tk�z�s el�tt: false, �tk�z�s ut�n: true
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
	 * Hangy�szs�nnel val� �tk�z�s.
	 * 
	 * @param Echidna
	 *            a hangy�szs�n ami �tk�zik a bollyal
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