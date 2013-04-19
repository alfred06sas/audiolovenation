package smell;

import land.Field;
import program.Singleton;
import item.Tentacle;
import item.Volatile;

/**
 * 
 * @author audiolovenation
 * 
 *         A hangyaszag tulajdons�gait val�s�tja meg. A hangya ezt hagyja maga
 *         ut�n. Id�vel elillan.
 * 
 */
public class AntSmell extends Smell implements Volatile {

	/**
	 * Szag elt�vol�t�sa.
	 * 
	 */
	@Override
	public void removeMyself() {
		Singleton s = Singleton.Instance();

		Field field = new Field();

		field.removeSmell(new Smell());

		
	}

	/**
	 * Szag cs�kkent�se.
	 * 
	 */
	@Override
	public void decrease() {
		Singleton s = Singleton.Instance();

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
	 * A cs�pnak adja meg a hangyaszag er�ss�g�t.
	 * 
	 * @param tentacle
	 *            a cs�p, aminak a szag er�ss�g�t �t kell adni
	 * 
	 */
	@Override
	public void smellIt(Tentacle tentacle) {
		Singleton s = Singleton.Instance();

		tentacle.increaseAntSmell(14);
		
	}
}