package smell;

import item.Tentacle;
import land.Field;
import program.Singleton;

/**
 * 
 * @author audiolovenation
 * 
 *         Az étel szag tulajdonságait valósítja meg.
 * 
 */
public class FoodSmell extends Smell {

	/**
	 * Szag eltávolítása.
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
	 * Szag csökkentése.
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
	 * A csápnak adja meg a étel szagának erõsségét.
	 * 
	 * @param tentacle
	 *            a csáp, aminak a szag erõsségét át kell adni
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