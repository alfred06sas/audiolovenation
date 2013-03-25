package item;

import java.util.List;

import land.Field;
import movable.Ant;
import movable.Echidna;
import program.Singleton;
import smell.FoodSmell;
import smell.Smell;

/**
 * 
 * @author audiolovenation
 * 
 *         Az egyes mezõkön lévõ ételeket tárolja. Ezeket szeretnék a hangyák a
 *         bolyba juttatni. Van egy szag tulajdonsága, ami a környezõ mezõkre is
 *         hat. Ez a hangyák mozgását befolyásolja.
 */
public class Food extends Item {

	private List<FoodSmell> foodSmells;

	/**
	 * Az étel szagának eltüntetése egy mezõrõl.
	 */
	public void deleteSmell() {
		Singleton s = Singleton.Instance();
		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Food.deleteSmell()");

		s.stack.add(19);
		Smell foodsmell = (FoodSmell) s.smells.get(18);
		foodsmell.removeMyself();
		s.stack.remove(s.stack.size() - 1);

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Food.deleteSmell()");
		s.depth--;
	}

	/**
	 * Ételszag hozzáadása egy mezõhöz.
	 * 
	 * @param FoodSmell
	 *            a mezõhöz hozzáadandó ételszag
	 */
	public void addFoodSmell(FoodSmell foodSmell) {

		Singleton s = Singleton.Instance();
		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Item.setActualField("
				+ s.foodSmells.indexOf(foodSmell) + ": FoodSmell)");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Item.setActualField("
				+ s.foodSmells.indexOf(foodSmell) + ": FoodSmell)");
		s.depth--;
	}

	/**
	 * Ételszag beállítása egy mezõhöz.
	 * 
	 * @param Field
	 *            a mezõ, amihez beállítjuk az ételszagot.
	 */
	public void setActualField(Field field) {

		Singleton s = Singleton.Instance();
		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Item.setActualField("
				+ s.fields.indexOf(field) + ": Field)");

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

	/**
	 * Hangyászsünnel való ütközés. Nem csinál semmit, csak visszatér.
	 * 
	 * @param Echidna
	 *            a hangyászsün amivel ütközik
	 */
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

	/**
	 * Hangyával való ütközés. Nem csinál semmit, csak visszatér.
	 * 
	 * @param Ant
	 *            a hangya amivel ütközik
	 * @param b
	 *            lépés elõtt: false, lépés után: true
	 */
	@Override
	public void collisionWithAnt(Ant ant, boolean b) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Food.collisionWithAnt("
				+ s.ants.indexOf(ant) + ": Ant, " + String.valueOf(b)
				+ ": boolean)");

		// Ha a hangya oylan mezõre lépett ahol étel van akkor felveszi
		if (b == true) {
			s.stack.add(s.ants.indexOf(ant));
			ant.pickUpFood();
			s.stack.remove(s.stack.size() - 1);
			// Ételszag kitörlése a mezõrõl, ahonnan elviszi a hagnya az ételt
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