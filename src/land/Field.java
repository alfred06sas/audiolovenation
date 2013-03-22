package land;

import item.Item;

import java.util.List;
import java.util.Map;

import program.Singleton;
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
		throw new UnsupportedOperationException();
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
		throw new UnsupportedOperationException();
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
	public void addSmell(int Smell) {
		throw new UnsupportedOperationException();
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