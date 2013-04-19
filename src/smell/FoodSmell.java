package smell;

import item.Tentacle;
import land.Field;
import program.Singleton;

/**
 * 
 * @author audiolovenation
 * 
 *         Az etel szag tulajdonsagait valositja meg.
 * 
 */
public class FoodSmell extends Smell {

	/**
	 * Szag eltavolítasa.
	 * 
	 */
	@Override
	public void removeMyself() {
		Singleton s = Singleton.Instance();

		
		Field field = new Field();

		field.removeSmell(new FoodSmell());

	}

	/**
	 * Szag csokkentese.
	 * 
	 */
	@Override
	public void decreaseSmell() {
		Singleton s = Singleton.Instance();

	
	}

	/**
	 * A csapnak adja meg a etel szaganak erosseget.
	 * 
	 * @param tentacle
	 *            a csap, aminek a szag erosseget at kell adni
	 * 
	 */
	@Override
	public void smellIt(Tentacle tentacle) {
		Singleton s = Singleton.Instance();

		
		tentacle.increaseFoodSmell(4);
		
	}
}