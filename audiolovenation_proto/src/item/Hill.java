package item;

import program.Singleton;
import land.Dir;
import movable.Ant;
import movable.Echidna;

/**
 * 
 * @author audiolovenation
 * 
 *         A l�trej�tt hangy�k kiindul�si pontja (boly), ahonnan a hangy�k
 *         indulnak �telt szerezni. Ha egy hangya tal�lt �telt, akkor ezt ide
 *         pr�b�lja visszajuttatni. � Ha siker�l, a hangya vissza megy a bolyba(inakt�v �llapotba ker�l).
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
		//A hangyabolyba vissza�r� hangya inakt�v �llapotba ker�l
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