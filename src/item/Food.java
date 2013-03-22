package item;


import java.util.List;

import land.Field;
import program.Singleton;
import smell.FoodSmell;

public class Food extends Item {

	private List<FoodSmell> foodSmells;

	/**
	 * 
	 * @return 
	 */
	public void deleteSmell() {
	}

	/**
	 * 
	 * @param FoodSmell
	 * @return 
	 */
	public void addFoodSmell(FoodSmell foodSmell) {
	}
	
	public void setActualField(Field field) {
		
		Singleton s = Singleton.Instance();		
		Integer id = s.stack.get(s.stack.size()-1);
		
		s.makeSpace(">> CALL: " + id + ": Item.setActualField("+ s.fields.indexOf(field)+")");
		
		// Ide jön a kód
		s.stack.add(s.fields.indexOf(field));
		field.addSmell(s.foodSmells.get(9));
		
		
		field.getNeighbours();
		s.stack.remove(s.stack.size()-1);
		
		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Item.setActualField("+s.fields.indexOf(field)+")");
		s.depth--;
	}

}