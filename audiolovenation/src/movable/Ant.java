package movable;

import item.Item;
import item.Tentacle;
import land.Dir;

public class Ant extends Item implements Movable {

	private Tentacle tentacle;
	private Boolean isKilled = false;
	private Boolean isActive = false;
	private Integer wait;
	private Boolean haveFood = false;
	private Integer HP;
	private Dir dir;

	/**
	 * 
	 * @param Integer
	 * @return 
	 */
	public void lostHP(int Integer) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return 
	 */
	public void pickUpFood() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return 
	 */
	public void rest() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return 
	 */
	public void reverseDir() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @return 
	 */
	public void kill() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param Dir
	 * @return 
	 */
	public void setDir(int Dir) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAlive() {
		// TODO Auto-generated method stub
		
	}

}