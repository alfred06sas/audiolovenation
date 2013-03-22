package item;

import java.util.Map;

import land.Dir;
import land.Field;

public class Tentacle {

	private Map<Dir, Field> possibleFields;

	/**
	 * 
	 * @param Map
	 * @return 
	 */
	public void setPossibleNeighbours(Map<Dir, Field> neighbours) {
	}

	/**
	 * 
	 * @param Field
	 * @return 
	 */
	public void removePossibleNeighbour(Field neighbour) {
	}

	/**
	 * 
	 * @param Integer
	 * @return 
	 */
	public void increaseFoodSmell(Integer strength) {
	}

	/**
	 * 
	 * @param Integer
	 * @return 
	 */
	public void increaseAntSmell(Integer strength) {
	}

	/**
	 * 
	 * @param haveFood
	 * @return 
	 */
	public Field scan(boolean haveFood) {
		return null;
	}

}