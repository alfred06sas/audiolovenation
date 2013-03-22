package movable;

import program.Singleton;
import item.Item;
import item.Tentacle;
import land.Dir;

public class Ant extends Item implements Movable {

	private Tentacle tentacle;
	private boolean isKilled = false;
	private boolean isActive = false;
	private Integer wait;
	private boolean haveFood = false;
	private Integer HP;
	private Dir dir;

	/**
	 * 
	 * @param Integer
	 * @return 
	 */
	public void looseHP(Integer hp) {
	}

	/**
	 * 
	 * @return 
	 */
	public void pickUpFood() {
	}

	/**
	 * 
	 * @return 
	 */
	public void rest() {
	}

	/**
	 * 
	 * @return 
	 */
	public void reverseDir() {
	}

	/**
	 * 
	 * @return 
	 */
	public void kill() {
	}

	/**
	 * 
	 * @param Dir
	 * @return 
	 */
	public void setDir(Dir dir) {
	}

	@Override
	public void step() {
		
	}

	@Override
	public void setAlive() {
		
	}
	/**
	 * @param ant - 
	 * @param b - 
	 */
	@Override
	public void collisionWithAnt(Ant ant, boolean b) {
		Singleton s = Singleton.Instance();
		
		Integer id = s.stack.get(s.stack.size()-1);
		
		s.makeSpace(">> CALL: " + id + ": Ant.collisionWithAnt(" + s.ants.indexOf(ant) + ": Ant, " + String.valueOf(b) + ")");
		s.depth--;
		
		s.makeSpace("<< RETURN: " + id + ": Ant.collisionWithAnt(" + s.ants.indexOf(ant) + ": Ant, " + String.valueOf(b) + ")");
		s.depth--;
	}
}

