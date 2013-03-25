package item;

import land.Field;
import movable.Ant;
import movable.Echidna;
import program.Singleton;

/**
 * 
 * @author audiolovenation
 * 
 *         A hangy�szs�n n�ha felt�nik �s am�g �hes megeszi az �sszes �tj�ba
 *         ker�l� hangy�t, majd ha j�l lakott tov�bb�ll.
 */
public class Antlion extends Item {
	/**
	 * Hangy�val val� �tk�z�s. Ha �hes megeszi a hangy�t.
	 * 
	 * 
	 * @param ant
	 *            a hangya referenci�ja. amivel �tk�z�tt
	 * @param b
	 *            �tk�z�s el�tt: false, �tk�z�s ut�n: true
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
	 * Hangy�szs�nnel val� �tk�z�s. Nem csin�l semmit, csak visszat�r.
	 * 
	 * @param echidna
	 *            az a hangy�szs�n. amivel �tk�z�tt
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
	 * Hangy�szs�nnel val� �tk�z�s. Nem csin�l semmit, csak visszat�r.
	 * 
	 * @param strength
	 *            a Spray er�ss�ge
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