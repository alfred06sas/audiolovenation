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
		return null;
	}

	/**
	 * 
	 * @return 
	 */
	public List<Movable> getMovables() {
		return null;
	}

	/**
	 * 
	 * @return 
	 */
	public List<Volatile> getVolatiles() {
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
	}

	/**
	 * 
	 * @param Integer
	 * @return 
	 */
	public void decreaseAntKillerSpray(Integer strength) {
	}

	/**
	 * 
	 * @param Integer
	 * @return 
	 */
	public void decreaseAntSmellSpray(Integer strength) {
	}

}