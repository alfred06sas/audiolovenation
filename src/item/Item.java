package item;

import java.util.HashMap;

import paintable.Paintable;
import program.SingletonWriter;

import land.Dir;
import land.Field;
import movable.Ant;
import movable.Echidna;
import blockage.Gravel;

/**
 * 
 * @author audiolovenation
 * 
 *         A palya egyes mezoin megtalalhato objektumok (hangya, hangyaszsun,
 *         hangyaleso, akadaly, spray, etel, boly).
 */
public class Item extends Paintable{

	protected String id;

	protected Field actualField;
	
	public Item(){
		id = new String();
		actualField = new Field();
	}
	
	public Item(String ID){
		id = ID;
		actualField = new Field();
	}
	
	/**
	 * Hangyaval valo utkozes.
	 * 
	 * @param Ant
	 *            a hangya ami utkozik a bollyal
	 * @param b
	 *            van-e etel a hangyanal vagy nincs
	 */
	public void collisionWithAnt(Ant ant, boolean b) {
		SingletonWriter s = SingletonWriter.Instance();
		s.printCollision(ant, this, actualField);
	}

	/**
	 * Item beallitasa egy mezohoz.
	 * 
	 * @param Field
	 *            a mezo, amihez beallitjuk az etelszagot.
	 */
	public void setActualField(Field field) {
		actualField=field;
	}

	/**
	 * Hangyaszsunnel valo utkozes.
	 * 
	 * @param Echidna
	 *            a hangyaszsun ami utkozik a bollyal
	 */
	
	public Field getActualField(){
		return actualField;
	}
	
	public int collisionWithEchidna(Echidna echidna, boolean b, Dir dir) {
		return 0;
	}

	/**
	 * Spray-vel valo utkozes.
	 * 
	 * @param strength
	 *            a spray erossege
	 */
	public void collisionWithSpray(Integer strength) {
	}
	
	public int collisionWithGravel(Gravel gravel, boolean b, Dir dir) {
		return 0;
	}

	public String getId() {
		return id;
	}
	
	public HashMap<String, String> getStates()
	{
		return null;
	}

	@Override
	public void setView() {
		// TODO Auto-generated method stub
		
	}

}
