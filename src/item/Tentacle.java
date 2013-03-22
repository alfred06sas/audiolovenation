package item;

import java.util.Map;

import program.Singleton;

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
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Tentacle.removePossibleNeighbour(" + 
				s.fields.indexOf(neighbour) + ": Field)");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Tentacle.removePossibleNeighbour(" + 
				s.fields.indexOf(neighbour) + ": Field)");
		s.depth--;
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