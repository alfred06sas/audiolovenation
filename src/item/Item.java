package item;

import program.Singleton;
import land.Field;
import movable.Ant;
import movable.Echidna;
/**
 * 
 * @author audiolovenation
 * 
 *			Minden, a mezõkön megjelenõ elem ebbõl az osztályból száramzik le
 */
public class Item {

	private Field actualField;

	/**
	 * Hangyával való ütközés.
	 * 
	 * @param Ant
	 *            a hangya ami ütközik a bollyal
	 * @param b
	 *            van-e étel a hangyánál vagy nincs
	 */
	public void collisionWithAnt(Ant ant, boolean b) {
		Singleton s = Singleton.Instance();		
		Integer id = s.stack.get(s.stack.size()-1);
		
		s.makeSpace(">> CALL: " + id + ": Item.collisionWithAnt("+s.ants.indexOf(ant)+": Ant"+ b +": boolean)");
		
		// Ide jön a kód

		
		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Item.collisionWithAnt("+s.ants.indexOf(ant)+": Ant"+ b +": boolean)");
		s.depth--;
	}

	/**
	 * Item beállítása egy mezõhöz.
	 * 
	 * @param Field
	 *            a mezõ, amihez beállítjuk az ételszagot.
	 */
	public void setActualField(Field field) {
		
		Singleton s = Singleton.Instance();		
		Integer id = s.stack.get(s.stack.size()-1);
		
		s.makeSpace(">> CALL: " + id + ": Item.setActualField("+ s.fields.indexOf(field)+": Field)");
		
		// Ide jön a kód

		
		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Item.setActualField("+s.fields.indexOf(field)+": Field)");
		s.depth--;
	}

	/**
	 * Hangyászsünnel való ütközés.
	 * 
	 * @param Echidna
	 *            a hangyászsün ami ütközik a bollyal
	 */
	public void collisionWithEchidna(Echidna echidna) {
		Singleton s = Singleton.Instance();		
		Integer id = s.stack.get(s.stack.size()-1);
		
		s.makeSpace(">> CALL: " + id + ": Item.collisionWithEchidna()");
		
		// Ide jön a kód

		
		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Item.collisionWithEchidna()");
		s.depth--;
	}

	/**
	 * Spray-vel való ütközés.
	 * 
	 * @param strength
	 *            a spray erõssége
	 */
	public void collisionWithSpray(Integer strength) {
	}

}