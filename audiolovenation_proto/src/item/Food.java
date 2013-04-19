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
		
		Smell foodsmell = new FoodSmell();
		foodsmell.removeMyself();
	}

	/**
	 * �telszag hozz�ad�sa egy mez�h�z.
	 * 
	 * @param FoodSmell
	 *            a mez�h�z hozz�adand� �telszag
	 */
	public void addFoodSmell(FoodSmell foodSmell) {
		Singleton s = Singleton.Instance();
	}

	/**
	 * �telszag be�ll�t�sa egy mez�h�z.
	 * 
	 * @param Field
	 *            a mez�, amihez be�ll�tjuk az �telszagot.
	 */
	public void setActualField(Field field) {

		Singleton s = Singleton.Instance();
		
		field.addSmell(new FoodSmell());

		field.getNeighbours();
		
		addFoodSmell(new FoodSmell());

		field.addSmell(new FoodSmell());
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

		// Ha a hangya oylan mez�re l�pett ahol �tel van akkor felveszi
		if (b == true) {
			ant.pickUpFood();
			// �telszag kit�rl�se a mez�r�l, ahonnan elviszi a hagnya az �telt
			deleteSmell();
		}
	}
}