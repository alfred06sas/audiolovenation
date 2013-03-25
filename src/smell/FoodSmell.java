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

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": FoodSmell.removeMyself()");

		s.stack.add(14);
		Field field = s.fields.get(13);

		field.removeSmell(s.foodSmells.get(id));

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": FoodSmell.removeMyself()");
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
	 * A cs�pnak adja meg a �tel szag�nak er�ss�g�t.
	 * 
	 * @param tentacle
	 *            a cs�p, aminak a szag er�ss�g�t �t kell adni
	 * 
	 */
	@Override
	public void smellIt(Tentacle tentacle) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": FoodSmell.smellIt("
				+ s.tentacles.indexOf(tentacle) + ")");

		s.stack.add(s.tentacles.indexOf(tentacle));
		tentacle.increaseFoodSmell(4);
		s.stack.remove(s.stack.size() - 1);

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": FoodSmell.smellIt("
				+ s.tentacles.indexOf(tentacle) + ")");
		s.depth--;
	}
}