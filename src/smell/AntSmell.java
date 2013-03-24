package smell;

import land.Field;
import program.Singleton;
import item.Volatile;

public class AntSmell extends Smell implements Volatile {

	@Override
	public void removeMyself() {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);
		
		s.makeSpace(">> CALL: " + id + ": AntSmell.removeMyself()");

		s.stack.add(11);
		Field field = s.fields.get(10);
		
		field.removeSmell(s.antSmells.get(id));
		
		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": AntSmell.removeMyself()");
		s.depth--;
	}

	@Override
	public void decrease() {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);
		
		s.makeSpace(">> CALL: " + id + ": AntSmell.decrease()");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": AntSmell.decrease()");
		s.depth--;
	}
}