package item;

import program.Singleton;
import land.Field;
import movable.Ant;
import movable.Echidna;

public class Item {

	private Field actualField;

	/**
	 * 
	 * @param Ant
	 * @param Boolean
	 * @return 
	 */
	public void collisionWithAnt(Ant ant, boolean b) {
	}

	/**
	 * 
	 * @param Field
	 * @return 
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
	 * 
	 * @param Echidna
	 * @return 
	 */
	public void collisionWithEchidna(Echidna echidna) {
	}

	/**
	 * 
	 * @param Integer
	 * @return 
	 */
	public void collisionWithSpray(Integer strength) {
	}

}