package program;

import item.Volatile;

import java.util.List;

import land.Land;
import movable.Movable;
import smell.Smell;

/**
 * 
 * @author audiolovenation
 * 
 *         A getIstance() metodusa ugyan azt az objektumot adja vissza. A
 *         Volatile �s a Movable interf�szeket megval�s�t� oszt�lyok t�rol�s�ra.
 *         A p�lya v�gzi a l�ptet�st �s az ill�kony anyagok cs�kkent�s�t is.
 *         Erre a megold�sra az�rt volt sz�ks�g, hogy a p�lya sz�m�ra el�rhet�ek
 *         legyenek ezek a list�k, ugyanakkor mind a Volatile �s a Movable is
 *         hozz� tudjon adni elemeket a saj�t list�j�hoz. �gy nem kellett
 *         mindenki sz�m�ra l�that�v� tenni az eg�sz p�ly�t (Land oszt�ly).
 */
public class SingletonContainer {

	private Land container;
	private List<Movable> movable;
	private List<Volatile> volatiles;
	private static SingletonContainer instance;
	private Integer antKillerSpray;
	private Integer antSnellSpray;

	/**
	 * 
	 * @param Volatile
	 * @return
	 */
	public void addVolatile(Volatile volatile_) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": SingletonContainer.addVolatile("
				+ s.smells.indexOf((Smell) volatile_) + ": Volatile)");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": SingletonContainer.addVolatile("
				+ s.smells.indexOf((Smell) volatile_) + ": Volatile)");
		s.depth--;
	}

	/**
	 * 
	 * @param Movable
	 * @return
	 */
	public void addMovable(Movable movable) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": SingletonContainer.addMovable()");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": SingletonContainer.addMovable()");
		s.depth--;
	}

	/**
	 * 
	 * @return
	 */
	public static SingletonContainer getInstance() {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": SingletonContainer.getInstance()");

		s.depth--;
		s.makeSpace("<< RETURN: " + id
				+ ": SingletonContainer.getInstance(): SingletonContainer");
		s.depth--;
		return new SingletonContainer();
	}

	/**
	 * 
	 * @return
	 */
	public List<Movable> getMovables() {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": SingletonContainer.getMovables()");

		s.depth--;
		s.makeSpace("<< RETURN: " + id
				+ ": SingletonContainer.getMovables(): List<Movable>");
		s.depth--;

		return null;
	}

	/**
	 * 
	 * @return
	 */
	public List<Volatile> getVolatiles() {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": SingletonContainer.getVolatiles()");

		s.depth--;
		s.makeSpace("<< RETURN: " + id
				+ ": SingletonContainer.getVolatiles(): List<Volatile>");
		s.depth--;

		return null;
	}

	/**
	 * 
	 * @param Volatile
	 * @return
	 */
	public void removeVolatile(Volatile volatile_) {
	}

	/**
	 * 
	 * @param Movable
	 * @return
	 */
	public void removeMovable(Movable movable) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": SingletonContainer.removeMovable("
				+ s.items.indexOf(movable) + ": Movable)");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": SingletonContainer.removeMovable("
				+ s.items.indexOf(movable) + ": Movable)");
		s.depth--;
	}

	/**
	 * 
	 * @param Integer
	 * @return
	 */
	public void decreaseAntKillerSpray(Integer strength) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id
				+ ": SingletonContainer.decreaseAntKillerSpray(" + strength
				+ ": Integer)");

		s.depth--;
		s.makeSpace("<< RETURN: " + id
				+ ": SingletonContainer.decreaseAntKillerSpray(" + strength
				+ ": Integer)");
		s.depth--;
	}

	/**
	 * 
	 * @param Integer
	 * @return
	 */
	public void decreaseAntSmellSpray(Integer strength) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id
				+ ": SingletonContainer.decreaseAntSmellSpray(" + strength
				+ ": Integer)");

		s.depth--;
		s.makeSpace("<< RETURN: " + id
				+ ": SingletonContainer.decreaseAntSmellSpray(" + strength
				+ ": Integer)");
		s.depth--;
	}

}