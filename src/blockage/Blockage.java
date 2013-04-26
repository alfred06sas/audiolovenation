package blockage;

import item.Item;
import land.Field;
import movable.Ant;
import movable.Echidna;
import program.SingletonWriter;

/**
 * Az egyes mezokon lehetnek akadalyok (tocsa, kavics), az ilyen mezokre a
 * hangyak nem tudnak lepni, azaz masik iranyt kell valasztaniuk a
 * tovabbhaladasra.
 * 
 * @author audiolovenation
 * 
 */
public class Blockage extends Item {

	public Blockage(){
	}
	
	public Blockage(String ID){
		super(ID);
		id="b"+ID;
	}
	
	public void collisionWithAnt(Ant ant, boolean b) {
		SingletonWriter s = SingletonWriter.Instance();
		s.printCollision(ant, this, actualField);
		if (b == false) {
			ant.canNotGo(actualField);
		}
	}
}