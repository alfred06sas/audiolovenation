package smell;

import program.Singleton;
import item.Volatile;

public class AntSmell extends Smell implements Volatile {

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