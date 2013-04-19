package item;

import java.util.HashMap;

import land.Field;
import movable.Ant;
import movable.Echidna;
import program.Singleton;

/**
 * 
 * @author audiolovenation
 * 
 *         A pAlya egyes mezoin megtalalhato objektumok (hangya, hangyaszsun,
 *         hangyaleso, akadaly, spray, etel, boly).
 */
public class Item {

	private String id;

	private Field actualField;

	/**
	 * Hangyaval valo utkozes.
	 * 
	 * @param Ant
	 *            a hangya ami utkozik a bollyal
	 * @param b
	 *            van-e etel a hangyanal vagy nincs
	 */
	public void collisionWithAnt(Ant ant, boolean b) {
		Singleton s = Singleton.Instance();
		
	}

	/**
	 * Item beallitasa egy mezohoz.
	 * 
	 * @param Field
	 *            a mezo, amihez beallitjuk az etelszagot.
	 */
	public void setActualField(Field field) {

		Singleton s = Singleton.Instance();
		
	}

	/**
	 * Hangyaszsunnel valo utkozes.
	 * 
	 * @param Echidna
	 *            a hangyaszsun ami utkozik a bollyal
	 */
	public void collisionWithEchidna(Echidna echidna) {
		Singleton s = Singleton.Instance();
		
	}

	/**
	 * Spray-vel valo utkozes.
	 * 
	 * @param strength
	 *            a spray erossege
	 */
	public void collisionWithSpray(Integer strength) {
	}

	public String getId() {
		return id;
	}
	
	public HashMap<String, String> getStates()
	{
		return null;
	}
}