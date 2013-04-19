package blockage;

import land.Field;
import movable.Ant;
import movable.Echidna;
import program.Singleton;

/**
 * 
 * Egy akad�ly (kavics). Ha a hangya ilyen mez�re akar l�pni, k�nytelen m�sik
 * �tvonalat v�lasztani. A hangy�szs�n figyelmen kiv�l hagyja.
 * 
 * @author audiolovenation
 * 
 */
public class Gravel extends Blockage {
	/**
	 * Hangy�val val� �tk�z�s. L�p�s ut�n (b == true) nem csin�l semmit, mivel
	 * ez az eset nem lehets�ges: a hangya nem l�phet akad�lyra. L�p�s el�tt (b
	 * == false) jelzi a hangy�nak h az �t tartalmaz� mez�re nem l�phet.
	 * 
	 * @param ant
	 *            a hangya referenci�ja. amivel �tk�z�tt
	 * @param b
	 *            l�p�s el�tt: false, l�p�s ut�n: true
	 */
	@Override
	public void collisionWithAnt(Ant ant, boolean b) {
		Singleton s = Singleton.Instance();

		if (b == false) {
			
			Field field = new Field();
			ant.canNotGo(field);
			
		}

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

		
	}

}