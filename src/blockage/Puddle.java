package blockage;

import land.Field;
import movable.Ant;
import movable.Echidna;
import program.Singleton;
/**
 * 
 * @author audiolovenation
 * 
 * Egy akad�ly (t�csa), ha a hangya egy ilyen mez�re akar l�pni, akkor k�nytelen m�sik �tvonalat v�lasztani.
 * 
 */
public class Puddle extends Blockage {
	/**
	 * Hangy�val val� �tk�z�s. L�p�s ut�n (b == true) nem csin�l semmit, mivel ez az eset nem lehets�ges: a hangya nem l�phet akad�lyra.
	 * L�p�s el�tt (b == false) jelzi a hangy�nak h az �t tartalmaz� mez�re nem l�phet.
	 * 
	 * @param ant a hangya referenci�ja. amivel �tk�z�tt
	 * @param b �tk�z�s el�tt: false, �tk�z�s ut�n: true
	 */
	@Override
	public void collisionWithAnt(Ant ant, boolean b) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Puddle.collisionWithAnt("
				+ s.ants.indexOf(ant) + ": Ant, " + String.valueOf(b) + ": boolean)");
		if (b == false) {
			s.stack.add(s.ants.indexOf(ant));
			Field field = s.fields.get(14);
			ant.canNotGo(field);
			s.stack.remove(s.stack.size() - 1);
		}

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Puddle.collisionWithAnt("
				+ s.ants.indexOf(ant) + ": Ant, " + String.valueOf(b) + ": boolean)");
		s.depth--;
	}
	
	/**
	 * Hangy�szs�nnel val� �tk�z�s. Nem csin�l semmit, csak visszat�r.
	 * 
	 * @param echidna az a hangy�szs�n. amivel �tk�z�tt
	 */
	@Override
	public void collisionWithEchidna(Echidna echidna) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Puddle.collisionWithEchidna("
				+ s.echidnas.indexOf(echidna) + ": Echidna)");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Puddle.collisionWithEchidna("
				+ s.echidnas.indexOf(echidna) + ": Echidna)");
		s.depth--;
	}
}