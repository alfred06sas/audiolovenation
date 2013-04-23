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

	public FoodSmell(){
	}
	public FoodSmell(int i){
		strength=i;
	}
	
	/**
	 * Szag eltavolítasa.
	 * 
	 */
	public void removeMyself() {
		Singleton s = Singleton.Instance();
		actualField.removeSmell(this);
		s.printFoodSmellDisappeard(actualField);
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
		tentacle.increaseFoodSmell(strength);		
	}
}