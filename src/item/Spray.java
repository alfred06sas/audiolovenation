package item;

import land.Dir;
import movable.Ant;
import movable.Echidna;

/**
 * 
 * @author audiolovenation
 * 
 *         A hangya meg�l�s�re szolg�l� sprayt val�s�tja meg. Haszn�latakor a
 *         haszn�lt mez�n �s annak a szomsz�djain (itt m�r kisebb m�rt�kben hat
 *         csak) hat.
 */
public class Spray extends Item implements Volatile {

	private int strength;

	public Spray(){
		strength = 10;
	}
	
	public Spray(String ID){
		super(ID);
		id="s"+ID;
		strength = 0;
	}
	
	public int getStrength(){
		return strength;
	}
	
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
			ant.looseHP(strength);
		}
	}
}
