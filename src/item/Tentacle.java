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
 *         A hangya csap tulajdonsagait valositja meg. A szomszedos mezok
 *         szagnyomai alapjan eldonti, hogy a hangya merre lepjen.
 */
public class Tentacle {

	private Map<Dir, Field> possibleFields;

	/**
	 * Azon szomszedokat keressuk, amelyekre lephetunk a hangyaval
	 * 
	 * @param Map
	 *            az aktualis mezo osszes szomszedja
	 */
	public void setPossibleNeighbours(Map<Dir, Field> neighbours) {
		Singleton s = Singleton.Instance();

		
	}

	/**
	 * Azon szomszedok kivetele a lehetsegesek kozul, amelyeken akadaly van
	 * 
	 * @param Field
	 *            a vizsgalt szomszedos mezo
	 */
	public void removePossibleNeighbour(Field neighbour) {
		Singleton s = Singleton.Instance();

		
	}

	/**
	 * Eteltelszag novelese
	 * 
	 * @param strength
	 *            a noveles merteke
	 */
	public void increaseFoodSmell(Integer strength) {
		Singleton s = Singleton.Instance();

		
	}

	/**
	 * Hangyaszag novelese
	 * 
	 * @param strength
	 *            a noveles merteke
	 */
	public void increaseAntSmell(Integer strength) {
		Singleton s = Singleton.Instance();

		
	}

	/**
	 * "Tapogatas"
	 * 
	 * @param haveFood
	 *            van-e a hangyanal etel
	 */
	public Map<Dir, Field> scan(boolean haveFood) {
		Singleton s = Singleton.Instance();

		
		Field field = null;
		field.getSmells();

		//Hangyaszag lekerese
		AntSmell antSmell = null;
		Tentacle tentacle = null;
		antSmell.smellIt(tentacle);
		
		//Etelszag lekerese
		FoodSmell foodSmell = null;
		foodSmell.smellIt(tentacle);

		return new TreeMap<Dir, Field>();
	}

}