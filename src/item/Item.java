package item;

import java.util.HashMap;

import land.Dir;
import land.Field;
import movable.Ant;
import movable.Echidna;

/**
 * 
 * @author audiolovenation
 * 
 *         A palya egyes mezoin megtalalhato objektumok (hangya, hangyaszsun,
 *         hangyaleso, akadaly, spray, etel, boly).
 */
public class Item {

	protected String id;

	private Field actualField;
	
	public Item(){
		id = new String();
		actualField = new Field();
	}
	
	public Item(String ID){
		id = ID;
		actualField = new Field();
	}
	
	/**
	 * Hangyaval valo utkozes.
	 * 
	 * @param Ant
	 *            a hangya ami utkozik a bollyal
	 * @param b
	 *            van-e etel a hangyanal vagy nincs
	 */
	public void collisionWithAnt(Ant ant, boolean b) {
	}

	/**
	 * Item beallitasa egy mezohoz.
	 * 
	 * @param Field
	 *            a mezo, amihez beallitjuk az etelszagot.
	 */
	public void setActualField(Field field) {
		actualField=field;
	}

	/**
	 * Hangyaszsunnel valo utkozes.
	 * 
	 * @param Echidna
	 *            a hangyaszsun ami utkozik a bollyal
	 */
	
	public Field getActualField(){
		return actualField;
	}
	
	public void collisionWithEchidna(Echidna echidna, boolean b, Dir dir) {
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