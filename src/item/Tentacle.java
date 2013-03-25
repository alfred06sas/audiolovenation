package item;

import java.util.Map;
import java.util.TreeMap;

import land.Dir;
import land.Field;
import program.Singleton;
import smell.AntSmell;
import smell.FoodSmell;

/**
 * 
 * @author audiolovenation
 * 
 *         A hangya csáp tulajdonságait valósítja meg. A szomszédos mezõk
 *         szagnyomai alapján eldönti, hogy a hangya merre lépjen.
 */
public class Tentacle {

	private Map<Dir, Field> possibleFields;

	/**
	 * Azon szomszédokat keressük, amelyekre léphetünk a hangyával
	 * 
	 * @param Map
	 *            az aktuális mezõ összes szomszédja
	 */
	public void setPossibleNeighbours(Map<Dir, Field> neighbours) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id
				+ ": Tentacle.setPossibleNeighbours(Map<Dir, Field>)");

		s.depth--;
		s.makeSpace("<< RETURN: " + id
				+ ": Tentacle.setPossibleNeighbours((Map<Dir, Field>)");
		s.depth--;
	}

	/**
	 * Azon szomszédok kivétele a lehetségesek közül, amelyeken akadály van
	 * 
	 * @param Field
	 *            a vizsgált szomszédos mezõ
	 */
	public void removePossibleNeighbour(Field neighbour) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Tentacle.removePossibleNeighbour("
				+ s.fields.indexOf(neighbour) + ": Field)");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Tentacle.removePossibleNeighbour("
				+ s.fields.indexOf(neighbour) + ": Field)");
		s.depth--;
	}

	/**
	 * Ételtelszag növelése
	 * 
	 * @param strength
	 *            a növelés mértéke
	 */
	public void increaseFoodSmell(Integer strength) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Tentacle.increaseFoodSmell("
				+ strength + ": Integer)");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Tentacle.increaseFoodSmell("
				+ strength + ": Integer)");
		s.depth--;
	}

	/**
	 * Hangyaszag növelése
	 * 
	 * @param strength
	 *            a növelés mértéke
	 */
	public void increaseAntSmell(Integer strength) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Tentacle.increaseAntSmell("
				+ strength + ": Integer)");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Tentacle.increaseAntSmell("
				+ strength + ": Integer)");
		s.depth--;
	}

	/**
	 * "Tapogatás"
	 * 
	 * @param haveFood
	 *            van-e a hangyánál étel
	 */
	public Map<Dir, Field> scan(boolean haveFood) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Tentacle.scan("
				+ String.valueOf(haveFood) + ": Boolean)");

		s.stack.add(11);
		Field field = s.fields.get(11);
		field.getSmells();
		s.stack.remove(s.stack.size() - 1);

		s.stack.add(8);
		AntSmell antSmell = (AntSmell) s.smells.get(7);
		Tentacle tentacle = s.tentacles.get(id);
		antSmell.smellIt(tentacle);
		s.stack.remove(s.stack.size() - 1);

		s.stack.add(8);
		FoodSmell foodSmell = (FoodSmell) s.smells.get(16);
		foodSmell.smellIt(tentacle);
		s.stack.remove(s.stack.size() - 1);

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Tentacle.scan("
				+ String.valueOf(haveFood) + ": Boolean): Map<Dir, Field>");
		s.depth--;

		return new TreeMap<Dir, Field>();
	}

}