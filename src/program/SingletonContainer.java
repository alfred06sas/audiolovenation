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
	 * Az ill�kony elemek hozz�ad�sa az �ket t�rol� list�ba.
	 * 
	 * @param Volatile
	 *            a hozz�adand� elem
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
	 * Az mozg� elemek hozz�ad�sa az �ket t�rol� list�ba.
	 * 
	 * @param Movable
	 *            a hozz�adand� elem
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
	 * A SingletonContainer referenci�j�nak lek�r�se.
	 * 
	 * @return a SingletonContainerre mutat� referencia
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
	 * Mozg�sra k�pes elemek lek�rdez�se.
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
	 * Ill��kony elemek lek�rdez�se.
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
	 * Egy ill�kony elem kit�rl�se az azokat t�rol� list�b�l.
	 * 
	 * @param Volatile
	 *            a kit�rlend� elem
	 */
	public void removeVolatile(Volatile volatile_) {
	}

	/**
	 * 
	 * Egy mozg� elem kit�rl�se az azokat t�rol� list�b�l.
	 * 
	 * @param Movable
	 *            a kit�rlend� elem
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
	 * A hangya�rt�-spray erej�nek cs�kkents�se.
	 * 
	 * @param Integer
	 *            a cs�kkent�s mr�t�ke
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
	 * A hangyaszag�rt�-spray erej�nek cs�kkents�se.
	 * 
	 * @param Integer
	 *            a cs�kkent�s mr�t�ke
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