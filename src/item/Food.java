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
 *			Bizonyos mez�k�n �telek vannak elhelyezve, 
 *			amiknek a szaga befoly�solja a ahngy�k mozg�s�nak az ir�ny�t
 */
public class Food extends Item {

	private List<FoodSmell> foodSmells;

	/**
	 * Az �tel szag�nak elt�ntet�se egy mez�r�l.
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
	 * �telszag hozz�ad�sa egy mez�h�z.
	 * 
	 * @param FoodSmell
	 *            a mez�h�z hozz�adand� �telszag
	 */
	public void addFoodSmell(FoodSmell foodSmell) {

		Singleton s = Singleton.Instance();
		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Item.setActualField("
				+ s.foodSmells.indexOf(foodSmell) + ": FoodSmell)");

		// Ide j�n a k�d

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Item.setActualField("
				+ s.foodSmells.indexOf(foodSmell) + ": FoodSmell)");
		s.depth--;
	}

	/**
	 * �telszag be�ll�t�sa egy mez�h�z.
	 * 
	 * @param Field
	 *            a mez�, amihez be�ll�tjuk az �telszagot.
	 */
	public void setActualField(Field field) {

		Singleton s = Singleton.Instance();
		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Item.setActualField("
				+ s.fields.indexOf(field) + ": Field)");

		// Ide j�n a k�d
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
	 * Hangy�szs�nnel val� �tk�z�s. Nem csin�l semmit, csak visszat�r.
	 * 
	 * @param Echidna
	 *            a hangy�szs�n amivel �tk�zik
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
	 * Hangy�val val� �tk�z�s. Nem csin�l semmit, csak visszat�r.
	 * 
	 * @param Ant
	 *            a hangya amivel �tk�zik
	 * @param b
	 *            �tk�z�s el�tt: false, �tk�z�s ut�n: true
	 */
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