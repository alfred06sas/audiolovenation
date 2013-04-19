package smell;

import item.Tentacle;
import land.Field;
import program.Singleton;

/**
 * 
 * @author audiolovenation
 * 
 *         Az �tel szag tulajdons�gait val�s�tja meg.
 * 
 */
public class FoodSmell extends Smell {

	/**
	 * Szag elt�vol�t�sa.
	 * 
	 */
	@Override
	public void removeMyself() {
		Singleton s = Singleton.Instance();

		
		Field field = new Field();

		field.removeSmell(new FoodSmell());

	}

	/**
	 * Szag cs�kkent�se.
	 * 
	 */
	@Override
	public void decreaseSmell() {
		Singleton s = Singleton.Instance();

	
	}

	/**
	 * A cs�pnak adja meg a �tel szag�nak er�ss�g�t.
	 * 
	 * @param tentacle
	 *            a cs�p, aminak a szag er�ss�g�t �t kell adni
	 * 
	 */
	@Override
	public void smellIt(Tentacle tentacle) {
		Singleton s = Singleton.Instance();

		
		tentacle.increaseFoodSmell(4);
		
	}
}