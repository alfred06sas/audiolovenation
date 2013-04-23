package item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import land.Dir;
import land.Field;
import movable.Ant;
import smell.AntSmell;
import smell.FoodSmell;
import smell.Smell;
import blockage.Blockage;

/**
 * 
 * @author audiolovenation
 * 
 *         A hangya csap tulajdonsagait valositja meg. A szomszedos mezok
 *         szagnyomai alapjan eldonti, hogy a hangya merre lepjen.
 */
public class Tentacle {

	private Map<Dir, Field> possibleFields;

	private Ant ant;

	private int foodSmell;

	private int antSmell;

	public Tentacle(Ant ant) {
		this.ant = ant;
		possibleFields = new HashMap<Dir, Field>();
	}

	/**
	 * Azon szomszedokat keressuk, amelyekre lephetunk a hangyaval
	 * 
	 * @param Map
	 *            az iranyba eso harom szomszed
	 */
	public void setPossibleNeighbours(Map<Dir, Field> neighbours) {
		possibleFields = neighbours;
	}

	/**
	 * visszaadja az aktualisan meg lehetseges szomszedokat
	 * 
	 * @return lehetseges szomszedok
	 */
	public Map<Dir, Field> getPossibleNeighbours() {
		return possibleFields;
	}

	/**
	 * egy mezo kivetele a lehetsegesek kozul, tipikusan olyan mezo amelyen
	 * akadaly van
	 * 
	 * @param Field
	 *            a vizsgalt szomszedos mezo
	 */
	public void removePossibleNeighbour(Field field) {
		Dir d = null;
		for (Dir key : possibleFields.keySet()) {
			if (possibleFields.get(key).equals(field))
				d = key;
		}
		if (d != null) {
			possibleFields.remove(d);
		}

	}

	/**
	 * Eteltelszag novelese
	 * 
	 * @param strength
	 *            a noveles merteke
	 */
	public void increaseFoodSmell(Integer strength) {
		foodSmell += strength;
	}

	/**
	 * Hangyaszag novelese
	 * 
	 * @param strength
	 *            a noveles merteke
	 */
	public void increaseAntSmell(Integer strength) {
		antSmell += strength;
	}

	/**
	 * "Tapogatas"
	 * 
	 * @param haveFood
	 *            van-e a hangyanal etel
	 */
	public Map<Dir, Field> scan(boolean haveFood) {
		Map<Dir, Field> choice = new HashMap<Dir, Field>();
		int prev = -1;
		List<Smell> smells;
		if (possibleFields.get(ant.getDir()) != null) {
			smells = possibleFields.get(ant.getDir()).getSmells();
			foodSmell = 0;
			antSmell = 0;
			for (Smell smell : smells) {
				smell.smellIt(this);
			}
			if (haveFood == true) {
				foodSmell = 0;
			}
			if (foodSmell + antSmell > prev) {
				choice.clear();
				choice.put(ant.getDir(), possibleFields.get(ant.getDir()));
				prev = foodSmell + antSmell;
			}
		}
		for (Dir key : possibleFields.keySet()) {
			smells = possibleFields.get(key).getSmells();

			foodSmell = 0;
			antSmell = 0;
			for (Smell smell : smells) {
				smell.smellIt(this);
				// System.out.println("comment: smellek: "+key+" :"+smell.getStrength());
			}
			if (haveFood == true) {
				foodSmell = 0;
			}
			if (foodSmell + antSmell > prev) {
				choice.clear();
				choice.put(key, possibleFields.get(key));
				prev = foodSmell + antSmell;
			}
		}
		// System.out.println("comment: choice: "+choice+" // scan()");
		return choice;
	}

}