package movable;

import item.Item;

public class Echidna extends Item implements Movable {

	private Boolean isActive = false;
	private Integer wait;
	private Integer hunger;

	/**
	 * 
	 * @return 
	 */
	public void decreaseHunger() {
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