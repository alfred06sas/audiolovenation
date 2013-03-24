package smell;

import land.Field;
import program.Singleton;

public class FoodSmell extends Smell {
	
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
	
}