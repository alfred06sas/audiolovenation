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
 *         Az egyes mezokon levo eteleket tarolja. Ezeket szeretnek a hangyak a
 *         bolyba juttatni. Van egy szag tulajdonsaga, ami a kornyezo mezokre is
 *         hat. Ez a hangyak mozgasat befolyasolja.
 */
public class Food extends Item {

	private List<FoodSmell> foodSmells;

	/**
	 * Az etel szaganak eltuntetese egy mezorol.
	 */
	public void deleteSmell() {
		Singleton s = Singleton.Instance();
		
		Smell foodsmell = new FoodSmell();
		foodsmell.removeMyself();
	}

	/**
	 * Etelszag hozzaadasa egy mezohoz.
	 * 
	 * @param FoodSmell
	 *            a mezohoz hozzaadando etelszag
	 */
	public void addFoodSmell(FoodSmell foodSmell) {
		Singleton s = Singleton.Instance();
	}

	/**
	 * Etelszag beallitasa egy mezohoz.
	 * 
	 * @param Field
	 *            a mezo, amihez beallitjuk az etelszagot.
	 */
	public void setActualField(Field field) {

		Singleton s = Singleton.Instance();
		
		field.addSmell(new FoodSmell());

		field.getNeighbours();
		
		addFoodSmell(new FoodSmell());

		field.addSmell(new FoodSmell());
	}

	/**
	 * Hangyaszsunnel valo utkozes. Nem csinal semmit, csak visszater.
	 * 
	 * @param Echidna
	 *            a hangyaszsun amivel utkozik
	 */
	@Override
	public void collisionWithEchidna(Echidna echidna) {
		Singleton s = Singleton.Instance();
	}

	/**
	 * Hangyaszsunnel valo utkozes. Nem csinal semmit, csak visszater.
	 * 
	 * @param Ant
	 *            a hangya amivel utkozik
	 * @param b
	 *            lepes elott: false, lepes utan: true
	 */
	@Override
	public void collisionWithAnt(Ant ant, boolean b) {
		Singleton s = Singleton.Instance();

		// Ha a hangya oylan mezore lepett ahol etel van akkor felveszi
		if (b == true) {
			ant.pickUpFood();
			// Etelszag kitorlese a mezorol, ahonnan elviszi a hagnya az etelt
			deleteSmell();
		}
	}
}