package land;

import item.Item;
import item.Spray;

import java.util.List;
import java.util.Map;

import program.Singleton;
import program.SingletonContainer;
import smell.AntSmell;
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
		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Field.addItem("
				+ s.items.indexOf(item) + ": Item)");

		s.stack.add(s.items.indexOf(item));

		item.setActualField(s.fields.get(id));

		s.stack.remove(s.stack.size() - 1);

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Field.addItem("
				+ s.items.indexOf(item) + ": Item)");
		s.depth--;
	}

	/**
	 * 
	 * @return
	 */
	public List<Item> getItems() {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Field.getItems()");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Field.getItems(): List<Item>");
		s.depth--;
		return null;
	}

	/**
	 * 
	 * @param Item
	 * @return
	 */
	public void removeItem(Item item) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Field.removeItem("
				+ s.items.indexOf(item) + ": Item)");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Field.removeItem("
				+ s.items.indexOf(item) + ": Item)");
		s.depth--;
	}

	/**
	 * 
	 * @return
	 */
	public Map<Dir, Field> getNeighbours() {

		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Field.getNeighbours()");

		s.depth--;
		s.makeSpace("<< RETURN: " + id
				+ ": Field.getNeighbours(): Map<Dir, Field>");
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

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Field.addNeighbour(" + dir
				+ ": Dir, " + s.fields.indexOf(field) + ": Field" + ")");
		s.depth--;

		s.makeSpace("<< RETURN: " + id + ": Field.addNeighbour(" + dir
				+ ": Dir, " + s.fields.indexOf(field) + ": Field" + ")");
		s.depth--;
	}

	/**
	 * 
	 * @return
	 */
	public List<Smell> getSmells() {
		Singleton s = Singleton.Instance();
		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Field.getSmells()");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Field.getSmells(): List<Smell>");
		s.depth--;

		return null;
	}

	/**
	 * 
	 * @param Smell
	 * @return
	 */
	public void addSmell(Smell smell) {
		Singleton s = Singleton.Instance();
		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Field.addSmell("
				+ s.smells.indexOf(smell) + ": Smell)");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Field.addSmell("
				+ s.smells.indexOf(smell) + ": Smell)");
		s.depth--;
	}

	/**
	 * 
	 * @param Smell
	 * @return
	 */
	public void removeSmell(Smell smell) {
		Singleton s = Singleton.Instance();
		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Field.removeSmell("
				+ s.smells.indexOf(smell) + ": Smell)");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Field.removeSmell("
				+ s.smells.indexOf(smell) + ": Smell)");
		s.depth--;
	}

	/**
	 * 
	 * @param Smell
	 * @return
	 */
	public void removeAntSmells() {
		Singleton s = Singleton.Instance();
		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Field.removeAntSmells()");

		s.stack.add(1);
		SingletonContainer sc = s.singletonContainer.get(0).getInstance();
		s.singletonContainer.get(0).decreaseAntSmellSpray(5);
		s.stack.remove(s.stack.size() - 1);

		s.stack.add(3);
		AntSmell antSmell = s.antSmells.get(2);
		antSmell.removeMyself();
		s.stack.remove(s.stack.size() - 1);

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Field.removeAntSmells()");
		s.depth--;
	}

	public void onClick() {
		Singleton s = Singleton.Instance();
		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Field.onClick()");

		s.stack.add(1);
		SingletonContainer sc = s.singletonContainer.get(0).getInstance();
		s.singletonContainer.get(0).getVolatiles();
		s.stack.remove(s.stack.size() - 1);

		s.stack.add(id);
		Field field = s.fields.get(id - 1);
		Spray spray = (Spray) s.items.get(16);
		field.addItem(spray);
		s.stack.remove(s.stack.size() - 1);

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Field.onClick()");
		s.depth--;
	}

}