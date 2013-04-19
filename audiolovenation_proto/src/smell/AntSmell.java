package smell;

import land.Field;
import program.Singleton;
import item.Tentacle;
import item.Volatile;

/**
 * 
 * @author audiolovenation
 * 
 *         A hangyaszag tulajdonsagait valositja meg. A hangya ezt hagyja maga
 *         utan. Idovel elillan.
 * 
 */
public class AntSmell extends Smell implements Volatile {

	/**
	 * Szag eltavolitasa.
	 * 
	 */
	@Override
	public void removeMyself() {
		Singleton s = Singleton.Instance();

		Field field = new Field();

		field.removeSmell(new Smell());

		
	}

	/**
	 * Szag csokkentese.
	 * 
	 */
	@Override
	public void decrease() {
		Singleton s = Singleton.Instance();

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
	 * A csapnak adja meg a hangyaszag erosseget.
	 * 
	 * @param tentacle
	 *            a csap, aminek a szag erosseget et kell adni
	 * 
	 */
	@Override
	public void smellIt(Tentacle tentacle) {
		Singleton s = Singleton.Instance();

		tentacle.increaseAntSmell(14);
		
	}
}