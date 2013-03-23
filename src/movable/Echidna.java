package movable;

import program.Singleton;
import item.Item;

public class Echidna extends Item implements Movable {

	private boolean isActive = false;
	private Integer wait;
	private Integer hunger;

	/**
	 * 
	 * @return 
	 */
	public void decreaseHunger() {
	}

	@Override
	public void step() {
		
	}

	@Override
	public void setAlive() {
		
	}

	/**
	 * @param ant
	 *            -
	 * @param b
	 *            -
	 */
	@Override
	public void collisionWithAnt(Ant ant, boolean b) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Echidna.collisionWithAnt("
				+ s.ants.indexOf(ant) + ": Ant, " + String.valueOf(b) + ")");
		s.depth--;

		ant.kill();
		
		s.makeSpace("<< RETURN: " + id + ": Echidna.collisionWithAnt("
				+ s.ants.indexOf(ant) + ": Ant, " + String.valueOf(b) + ")");
		s.depth--;
	}
	
}