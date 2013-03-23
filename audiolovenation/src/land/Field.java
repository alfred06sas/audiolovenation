package land;

import item.Item;

import java.util.*;

import smell.Smell;

public class Field {

	private Collection<Item> items;
	private Field neighbours;
	private Smell actualField;

	/**
	 * 
	 * @param Item
	 * @return 
	 */
	public void addItem(int Item) {
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
	public void removeItem(int Item) {
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
	public void addNeighbour(int Dir, int Field) {
		throw new UnsupportedOperationException();
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