package item;

import java.util.List;

import land.Field;
import movable.Ant;
import movable.Echidna;
import program.Singleton;
import smell.FoodSmell;
import smell.Smell;

public class Food extends Item {

	private List<FoodSmell> foodSmells;

	/**
	 * 
	 * @return
	 */
	public void deleteSmell() {
		Singleton s = Singleton.Instance();
		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Food.deleteSmell()");

		s.stack.add(19);
		Smell foodsmell = (FoodSmell) s.foodSmells.get(18);
		foodsmell.removeMyself();
		s.stack.remove(s.stack.size() - 1);

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Food.deleteSmell()");
		s.depth--;
	}

	/**
	 * 
	 * @param FoodSmell
	 * @return
	 */
	public void addFoodSmell(FoodSmell foodSmell) {

		Singleton s = Singleton.Instance();
		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Item.setActualField("
				+ s.foodSmells.indexOf(foodSmell) + ": FoodSmell)");

		// Ide jön a kód

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Item.setActualField("
				+ s.foodSmells.indexOf(foodSmell) + ": FoodSmell)");
		s.depth--;
	}

	public void setActualField(Field field) {

		Singleton s = Singleton.Instance();
		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Item.setActualField("
				+ s.fields.indexOf(field) + ": Field)");

		// Ide jön a kód
		s.stack.add(s.fields.indexOf(field));
		field.addSmell(s.foodSmells.get(9));

		field.getNeighbours();
		s.stack.remove(s.stack.size() - 1);

		addFoodSmell(s.foodSmells.get(4));

		field.addSmell(s.foodSmells.get(4));

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Item.setActualField("
				+ s.fields.indexOf(field) + ": Field)");
		s.depth--;
	}

	@Override
	public void collisionWithEchidna(Echidna echidna) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Food.collisionWithEchidna("
				+ s.echidnas.indexOf(echidna) + ": Echidna)");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Food.collisionWithEchidna("
				+ s.echidnas.indexOf(echidna) + ": Echidna)");
		s.depth--;
	}

	@Override
	public void collisionWithAnt(Ant ant, boolean b) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Food.collisionWithAnt("
				+ s.ants.indexOf(ant) + ": Ant, " + String.valueOf(b)
				+ ": boolean)");

		if (b == true) {
			s.stack.add(s.ants.indexOf(ant));
			ant.pickUpFood();
			s.stack.remove(s.stack.size() - 1);

			s.stack.add(id);
			deleteSmell();
			s.stack.remove(s.stack.size() - 1);
		}

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Food.collisionWithAnt("
				+ s.ants.indexOf(ant) + ": Ant, " + String.valueOf(b)
				+ ": boolean)");
		s.depth--;
	}
}