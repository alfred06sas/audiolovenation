package item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import land.Dir;
import land.Field;
import movable.Ant;
import smell.AntSmell;
import smell.FoodSmell;
import smell.Smell;
import blockage.Blockage;

/**
 * 
 * @author audiolovenation
 * 
 *         A hangya csap tulajdonsagait valositja meg. A szomszedos mezok
 *         szagnyomai alapjan eldonti, hogy a hangya merre lepjen.
 */
public class Tentacle {

	private Map<Dir, Field> possibleFields;
	
	private Ant ant;
	
//	private FoodSmell foodSmell;
//	
//	private AntSmell antSmell;
	
	Tentacle(Ant ant){
		this.ant=ant;
		possibleFields=new HashMap<Dir, Field>();
//		foodSmell=new FoodSmell();
//		antSmell=new AntSmell();
	}
	/**
	 * Azon szomszedokat keressuk, amelyekre lephetunk a hangyaval
	 * 
	 * @param Map
	 *            az aktualis mezo osszes szomszedja
	 */
	public void setPossibleNeighbours(Map<Dir, Field> neighbours) {
		Map<Dir, Field> neg=ant.getActualField().getNeighbours();
		Dir dir=ant.getDir();
		switch(dir){
			case UP:
				possibleFields.put(Dir.LEFT_TOP, neg.get(Dir.LEFT_TOP));
				possibleFields.put(Dir.UP, neg.get(Dir.UP));
				possibleFields.put(Dir.RIGHT_TOP, neg.get(Dir.RIGHT_TOP));
				break;
			case DOWN:
				possibleFields.put(Dir.LEFT_BOTTOM, neg.get(Dir.LEFT_BOTTOM));
				possibleFields.put(Dir.DOWN, neg.get(Dir.DOWN));
				possibleFields.put(Dir.RIGHT_BOTTOM, neg.get(Dir.RIGHT_BOTTOM));
				break;
			case LEFT_TOP:
				possibleFields.put(Dir.LEFT_TOP, neg.get(Dir.LEFT_TOP));
				possibleFields.put(Dir.UP, neg.get(Dir.UP));
				possibleFields.put(Dir.LEFT_BOTTOM, neg.get(Dir.LEFT_BOTTOM));
				break;
			case RIGHT_TOP:
				possibleFields.put(Dir.RIGHT_TOP, neg.get(Dir.RIGHT_TOP));
				possibleFields.put(Dir.UP, neg.get(Dir.UP));
				possibleFields.put(Dir.RIGHT_BOTTOM, neg.get(Dir.RIGHT_BOTTOM));
				break;
			case RIGHT_BOTTOM:
				possibleFields.put(Dir.RIGHT_TOP, neg.get(Dir.RIGHT_TOP));
				possibleFields.put(Dir.DOWN, neg.get(Dir.DOWN));
				possibleFields.put(Dir.RIGHT_BOTTOM, neg.get(Dir.RIGHT_BOTTOM));
				break;
			case LEFT_BOTTOM:
				possibleFields.put(Dir.LEFT_TOP, neg.get(Dir.LEFT_TOP));
				possibleFields.put(Dir.DOWN, neg.get(Dir.DOWN));
				possibleFields.put(Dir.LEFT_BOTTOM, neg.get(Dir.LEFT_BOTTOM));
				break;
		}
	}

	/**
	 * Azon szomszedok kivetele a lehetsegesek kozul, amelyeken akadaly van
	 * 
	 * @param Field
	 *            a vizsgalt szomszedos mezo
	 */
	public void removePossibleNeighbour() {
		List<Dir> dirs=new ArrayList<Dir>();
		for (Dir key : possibleFields.keySet()) {
			List<Item> items = possibleFields.get(key).getItems();
			for (Item item:items){
				try{
					Blockage block=(Blockage)item;
					dirs.add(key);
				}
				catch(ClassCastException e){}
			}
		}
		for (Dir dir : dirs){
			possibleFields.remove(dir);
		}
	}

//	/**
//	 * Eteltelszag novelese
//	 * 
//	 * @param strength
//	 *            a noveles merteke
//	 */
//	public void increaseFoodSmell(Integer strength) {
//		foodSmell.IncreaseSmell(strength);
//	}
//
//	/**
//	 * Hangyaszag novelese
//	 * 
//	 * @param strength
//	 *            a noveles merteke
//	 */
//	public void increaseAntSmell(Integer strength) {
//		antSmell.IncreaseSmell(strength);
//	}

	/**
	 * "Tapogatas"
	 * 
	 * @param haveFood
	 *            van-e a hangyanal etel
	 */
	public Map<Dir, Field> scan(boolean haveFood) {
		Dir dir=ant.getDir();
		
		Smell maxsmell=new Smell();
		for (Dir key : possibleFields.keySet()) {
			Smell mysmell=new Smell();
			List<Smell> smells=possibleFields.get(key).getSmells();
			for (Smell smell:smells){
				mysmell.setStrength(mysmell.getStrength()+smell.getStrength());
			}
			if (maxsmell.getStrength()< mysmell.getStrength()) {
				maxsmell=mysmell;
				dir=key;
			}
		}
		
		Map<Dir, Field> nextmap=new HashMap<Dir, Field>();
		nextmap.put(dir, possibleFields.get(dir));

		return nextmap;
	}

}