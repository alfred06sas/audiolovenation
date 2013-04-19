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
 *         A hangya cs�p tulajdons�gait val�s�tja meg. A szomsz�dos mez�k
 *         szagnyomai alapj�n eld�nti, hogy a hangya merre l�pjen.
 */
public class Tentacle {

	private Map<Dir, Field> possibleFields;

	/**
	 * Azon szomsz�dokat keress�k, amelyekre l�phet�nk a hangy�val
	 * 
	 * @param Map
	 *            az aktu�lis mez� �sszes szomsz�dja
	 */
	public void setPossibleNeighbours(Map<Dir, Field> neighbours) {
		Singleton s = Singleton.Instance();

		
	}

	/**
	 * Azon szomsz�dok kiv�tele a lehets�gesek k�z�l, amelyeken akad�ly van
	 * 
	 * @param Field
	 *            a vizsg�lt szomsz�dos mez�
	 */
	public void removePossibleNeighbour(Field neighbour) {
		Singleton s = Singleton.Instance();

		
	}

	/**
	 * �teltelszag n�vel�se
	 * 
	 * @param strength
	 *            a n�vel�s m�rt�ke
	 */
	public void increaseFoodSmell(Integer strength) {
		Singleton s = Singleton.Instance();

		
	}

	/**
	 * Hangyaszag n�vel�se
	 * 
	 * @param strength
	 *            a n�vel�s m�rt�ke
	 */
	public void increaseAntSmell(Integer strength) {
		Singleton s = Singleton.Instance();

		
	}

	/**
	 * "Tapogat�s"
	 * 
	 * @param haveFood
	 *            van-e a hangy�n�l �tel
	 */
	public Map<Dir, Field> scan(boolean haveFood) {
		Singleton s = Singleton.Instance();

		
		Field field = null;
		field.getSmells();

		//Hangyaszag lekérése
		AntSmell antSmell = null;
		Tentacle tentacle = null;
		antSmell.smellIt(tentacle);
		
		//Ételszag lekérése
		FoodSmell foodSmell = null;
		foodSmell.smellIt(tentacle);

		return new TreeMap<Dir, Field>();
	}

}