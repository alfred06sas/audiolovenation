package program;

import item.Volatile;

import java.util.List;
import java.util.Map;

import land.Land;
import movable.Movable;

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
	public void addVolatile(int Volatile) {
	}

	/**
	 * 
	 * @param Movable
	 * @return 
	 */
	public void addMovable(Movable movable) {
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
		s.makeSpace("<< RETURN: " + id + ": SingletonContainer.getInstance(): SingletonContainer");
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
		s.makeSpace("<< RETURN: " + id + ": SingletonContainer.getMovables(): List<Movable>");
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
		s.makeSpace("<< RETURN: " + id + ": SingletonContainer.getVolatiles(): List<Volatile>");
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

		s.makeSpace(">> CALL: " + id + ": SingletonContainer.removeMovable(" + s.items.indexOf(movable)
				+ ": Movable)");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": SingletonContainer.removeMovable(" + s.items.indexOf(movable)
				+ ": Movable)");
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

		s.makeSpace(">> CALL: " + id + ": SingletonContainer.decreaseAntKillerSpray(" + strength + ")");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": SingletonContainer.decreaseAntKillerSpray(" + strength + ")");
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

		s.makeSpace(">> CALL: " + id + ": SingletonContainer.decreaseAntSmellSpray(" + strength + ")");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": SingletonContainer.decreaseAntSmellSpray(" + strength + ")");
		s.depth--;
	}

}