package land;

import item.Item;

import java.util.List;
import java.util.Map;

import program.Singleton;
import smell.FoodSmell;
import smell.Smell;

public class Field {

	private List<Item> items;
	private Map<Dir, Field> neighbours;
	private List<Smell> smells;

	/**
	 * 
	 * @param Item
	 * @return 
	 */
	public void addItem(Item item) {
		Singleton s = Singleton.Instance();
		Integer id = s.stack.get(s.stack.size()-1);
		
		s.makeSpace(">> CALL: " + id + ": Field.addItem("+s.items.indexOf(item)+")");
		
		// Ide jön a kód
		s.stack.add(s.items.indexOf(item));
		
		item.setActualField(s.fields.get(id));
		
		s.stack.remove(s.stack.size()-1);
		
		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Field.addItem("+s.items.indexOf(item)+")");
		s.depth--;
	}

	/**
	 * 
	 * @return 
	 */
	public List<Item> getItems() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param Item
	 * @return 
	 */
	public void removeItem(Item item) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return 
	 */
	public Map<Dir, Field> getNeighbours() {
		
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size()-1);
		
		s.makeSpace(">> CALL: " + id + ": Field.getNeighbour()");
		
		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Field.getNeighbour()");
		s.depth--;
		
		return null;
	}

	/**
	 * 
	 * @param Dir
	 * @param Field
	 * @return 
	 */
	public void addNeighbour(Dir dir, Field field) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size()-1);
		
		s.makeSpace(">> CALL: " + id + ": Field.addNeighbour(" + dir +": Dir, "+ s.fields.indexOf(field) + ": Field" + ")");
		s.depth--;
		
		s.makeSpace("<< RETURN: " + id + ": Field.addNeighbour(" + dir +": Dir, "+ s.fields.indexOf(field) + ": Field" + ")");
		s.depth--;
	}

	/**
	 * 
	 * @return 
	 */
	public List<Smell> getSmell() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param Smell
	 * @return 
	 */
	public void addSmell(Smell smell) {
		Singleton s = Singleton.Instance();		
		Integer id = s.stack.get(s.stack.size()-1);
		
		s.makeSpace(">> CALL: " + id + ": Field.addSmell("+ s.foodSmells.indexOf(smell)+")");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Field.addSmell("+ s.foodSmells.indexOf(smell)+")");
		s.depth--;
	}

	/**
	 * 
	 * @param Smell
	 * @return 
	 */
	public void removeSmell(int Smell) {
		throw new UnsupportedOperationException();
	}

}