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
 *         Az egyes mez�k�n l�v� �teleket t�rolja. Ezeket szeretn�k a hangy�k a
 *         bolyba juttatni. Van egy szag tulajdons�ga, ami a k�rnyez� mez�kre is
 *         hat. Ez a hangy�k mozg�s�t befoly�solja.
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
	 *            l�p�s el�tt: false, l�p�s ut�n: true
	 */
	@Override
	public void collisionWithAnt(Ant ant, boolean b) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Food.collisionWithAnt("
				+ s.ants.indexOf(ant) + ": Ant, " + String.valueOf(b)
				+ ": boolean)");

		// Ha a hangya oylan mez�re l�pett ahol �tel van akkor felveszi
		if (b == true) {
			s.stack.add(s.ants.indexOf(ant));
			ant.pickUpFood();
			s.stack.remove(s.stack.size() - 1);
			// �telszag kit�rl�se a mez�r�l, ahonnan elviszi a hagnya az �telt
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