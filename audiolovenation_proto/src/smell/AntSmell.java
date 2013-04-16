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

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": AntSmell.removeMyself()");

		s.stack.add(11);
		Field field = s.fields.get(10);

		field.removeSmell(s.smells.get(id));

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": AntSmell.removeMyself()");
		s.depth--;
	}

	/**
	 * Szag cs�kkent�se.
	 * 
	 */
	@Override
	public void decrease() {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": AntSmell.decrease()");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": AntSmell.decrease()");
		s.depth--;
	}

	/**
	 * Szag cs�kkent�se.
	 * 
	 */
	@Override
	public void decreaseSmell() {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": AntSmell.decreaseSmell()");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": AntSmell.decreaseSmell()");
		s.depth--;
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

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": AntSmell.smellIt("
				+ s.tentacles.indexOf(tentacle) + ": Tentacle)");

		s.stack.add(s.tentacles.indexOf(tentacle));
		tentacle.increaseAntSmell(14);
		s.stack.remove(s.stack.size() - 1);

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": AntSmell.smellIt("
				+ s.tentacles.indexOf(tentacle) + ": Tentacle)");
		s.depth--;
	}
}