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
	 * Spray erossegenek a csokkentese
	 * 
	 */
	@Override
	public void decrease() {
		--strength;
	}

	/**
	 * Hangyaval valo utkozes. Nem csinal semmit, csak visszater.
	 * 
	 * @param Ant
	 *            a hangya amivel utkozik
	 * @param b
	 *            utkozes elott: false, utkozes utan: true
	 */
	@Override
	public void collisionWithAnt(Ant ant, boolean b) {
		if (b == true) {
			//Hangya eleterejenek csokkentese
			ant.looseHP(5);
		}
	}

	/**
	 * Hangyaszsunnel valo utkozes. Nem csinil semmit, csak visszater.
	 * 
	 * @param Echidna
	 *            a hangyaszsun amivel utkozik
	 */
	@Override
	public void collisionWithEchidna(Echidna echidna) {
	}

	/**
	 * Spray-vel valo utkozes. Nem csinal semmit, csak visszater.
	 * 
	 * @param strength
	 *            erosseg
	 */
	public void collisionWithSpray(Integer strength) {
	}

}
