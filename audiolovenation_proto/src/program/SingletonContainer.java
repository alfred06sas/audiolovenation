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
 *         Volatile és a Movable interfészeket megvalósító osztályok tárolására.
 *         A pálya végzi a léptetést és az illékony anyagok csökkentését is.
 *         Erre a megoldásra azért volt szükség, hogy a pálya számára elérhetõek
 *         legyenek ezek a listák, ugyanakkor mind a Volatile és a Movable is
 *         hozzá tudjon adni elemeket a saját listájához. Így nem kellett
 *         mindenki számára láthatóvá tenni az egész pályát (Land osztály).
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
	 * Az illékony elemek hozzáadása az õket tároló listába.
	 * 
	 * @param Volatile
	 *            a hozzáadandó elem
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
	 * Az mozgó elemek hozzáadása az õket tároló listába.
	 * 
	 * @param Movable
	 *            a hozzáadandó elem
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
	 * A SingletonContainer referenciájának lekérése.
	 * 
	 * @return a SingletonContainerre mutató referencia
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
	 * Mozgásra képes elemek lekérdezése.
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
	 * Illéákony elemek lekérdezése.
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
	 * Egy illékony elem kitörlése az azokat tároló listából.
	 * 
	 * @param Volatile
	 *            a kitörlendõ elem
	 */
	public void removeVolatile(Volatile volatile_) {
	}

	/**
	 * 
	 * Egy mozgó elem kitörlése az azokat tároló listából.
	 * 
	 * @param Movable
	 *            a kitörlendõ elem
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
	 * A hangyaírtó-spray erejének csökkentsése.
	 * 
	 * @param Integer
	 *            a csökkentés mrétéke
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
	 * A hangyaszagírtó-spray erejének csökkentsése.
	 * 
	 * @param Integer
	 *            a csökkentés mrétéke
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