package smell;

import item.Tentacle;
import item.Volatile;
import land.Field;
import program.Singleton;
import program.SingletonContainer;

/**
 * 
 * @author audiolovenation
 * 
 *         A hangyaszag tulajdonsagait valositja meg. A hangya ezt hagyja maga
 *         utan. Idovel elillan.
 * 
 */
public class AntSmell extends Smell implements Volatile {

	public AntSmell(){
	}
	
	/**
	 * Szag eltavolitasa.
	 * 
	 */
	@Override
	public void removeMyself(Field field) {
		SingletonContainer sc=SingletonContainer.getInstance();
		sc.removeVolatile(this);
		field.removeSmell(this);

		
	}

	/**
	 * Szag csokkentese.
	 * 
	 */
	@Override
	public void decrease() {
		strength--;
		
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

		tentacle.increaseAntSmell(strength);
		
	}
}