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

		Ant ant1 = new Ant();
		//A hangyabolyba vissza�r� hangya inakt�v �llapotba ker�l
		ant1.rest();
		
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

		
	}
}