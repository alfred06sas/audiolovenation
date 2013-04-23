package item;

import movable.Ant;


/**
 * 
 * @author audiolovenation
 * 
 *         A hangya megolesere szolgalo sprayt valositja meg. Hasznalatakor a
 *         hasznalt mezon hat.
 */
public class Spray extends Item implements Volatile {

	private int strength;

	/**
	 * konstruktor, inicializalasra
	 */
	public Spray() {
		strength = 10;
	}

	/**
	 * konstruktor
	 * 
	 * @param ID
	 *            id-ja
	 */
	public Spray(String ID) {
		super(ID);
		id = "s" + ID;
		strength = 10;
	}

	/**
	 * a spray hatoerejet adja vissza
	 * 
	 * @return hatoero
	 */
	public int getStrength() {
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
			// Hangya eleterejenek csokkentese
			ant.looseHP(strength);
		}
	}
}
