package item;

import program.Singleton;
import land.Field;
import movable.Ant;
import movable.Echidna;
/**
 * 
 * @author audiolovenation
 * 
 *			Minden, a mez�k�n megjelen� elem ebb�l az oszt�lyb�l sz�ramzik le
 */
public class Item {

	private Field actualField;

	/**
	 * Hangy�val val� �tk�z�s.
	 * 
	 * @param Ant
	 *            a hangya ami �tk�zik a bollyal
	 * @param b
	 *            van-e �tel a hangy�n�l vagy nincs
	 */
	public void collisionWithAnt(Ant ant, boolean b) {
		Singleton s = Singleton.Instance();		
		Integer id = s.stack.get(s.stack.size()-1);
		
		s.makeSpace(">> CALL: " + id + ": Item.collisionWithAnt("+s.ants.indexOf(ant)+": Ant"+ b +": boolean)");
		
		// Ide j�n a k�d

		
		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Item.collisionWithAnt("+s.ants.indexOf(ant)+": Ant"+ b +": boolean)");
		s.depth--;
	}

	/**
	 * Item be�ll�t�sa egy mez�h�z.
	 * 
	 * @param Field
	 *            a mez�, amihez be�ll�tjuk az �telszagot.
	 */
	public void setActualField(Field field) {
		
		Singleton s = Singleton.Instance();		
		Integer id = s.stack.get(s.stack.size()-1);
		
		s.makeSpace(">> CALL: " + id + ": Item.setActualField("+ s.fields.indexOf(field)+": Field)");
		
		// Ide j�n a k�d

		
		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Item.setActualField("+s.fields.indexOf(field)+": Field)");
		s.depth--;
	}

	/**
	 * Hangy�szs�nnel val� �tk�z�s.
	 * 
	 * @param Echidna
	 *            a hangy�szs�n ami �tk�zik a bollyal
	 */
	public void collisionWithEchidna(Echidna echidna) {
		Singleton s = Singleton.Instance();		
		Integer id = s.stack.get(s.stack.size()-1);
		
		s.makeSpace(">> CALL: " + id + ": Item.collisionWithEchidna()");
		
		// Ide j�n a k�d

		
		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Item.collisionWithEchidna()");
		s.depth--;
	}

	/**
	 * Spray-vel val� �tk�z�s.
	 * 
	 * @param strength
	 *            a spray er�ss�ge
	 */
	public void collisionWithSpray(Integer strength) {
	}

}