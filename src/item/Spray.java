package item;

import item.Item;
import item.Volatile;
import movable.Ant;
import movable.Echidna;
import program.Singleton;

/**
 * 
 * @author audiolovenation
 * 
 *         A hangya meg�l�s�re szolg�l� sprayt val�s�tja meg. Haszn�latakor a
 *         haszn�lt mez�n �s annak a szomsz�djain (itt m�r kisebb m�rt�kben hat
 *         csak) hat.
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

		if (b == true) {
			//Hangya �leterej�nek cs�kkent�se
			ant.looseHP(5);
		}

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
	}

	/**
	 * Spray-vel val� �tk�z�s. Nem csin�l semmit, csak visszat�r.
	 * 
	 * @param strength
	 *            erősség
	 */
	public void collisionWithSpray(Integer strength) {
		Singleton s = Singleton.Instance();
		
	}

}
