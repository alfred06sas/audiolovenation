package item;

import java.util.List;
import java.util.Map;

import land.Dir;
import land.Field;
import movable.Ant;
import movable.Echidna;
import program.Singleton;
import smell.FoodSmell;

/**
 * 
 * @author audiolovenation
 * 
 *         Az egyes mezokon levo eteleket tarolja. Ezeket szeretnek a hangyak a
 *         bolyba juttatni. Van egy szag tulajdonsaga, ami a kornyezo mezokre is
 *         hat. Ez a hangyak mozgasat befolyasolja.
 */
public class Food extends Item {

	public Food(){
	}
	public Food(String ID){
		super(ID);
		id="f"+ID;
	}
	/** 0. eleme az actualis FoodSmell
	 *  1..6ig a kornyezo mezok FoodSmellje
	 * 
	 */
	private List<FoodSmell> foodSmells;

	/**
	 * Az etel szaganak eltuntetese egy mezorol.
	 */
	public void deleteSmell() {
		for(FoodSmell f :foodSmells)
			f.removeMyself(getActualField());
	}

	/**
	 * Etelszag hozzaadasa a mezohoz, es szomszedaihoz.
	 * 
	 * @param FoodSmell
	 *            a mezohoz hozzaadando etelszag
	 */
	public void addFoodSmell(FoodSmell foodSmell) {
		getActualField().addSmell(foodSmells.get(0));
		Map<Dir, Field> neg=getActualField().getNeighbours();
		for (int i=1;i<=6;i++){
			neg.get(i).addSmell(foodSmells.get(i));
		}
	}

	/**
	 * Etelszag beallitasa egy adott mezore.
	 * 
	 * @param Field
	 *            a mezo, amihez beallitjuk az etelszagot.
	 */
	public void setActualField(Field field) {
		super.setActualField(field);
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
			// Ha a hangya oylan mezore lepett ahol etel van akkor felveszi
		if (b == true) {
			ant.pickUpFood();
			// Etelszag kitorlese a mezorol, ahonnan elviszi a hagnya az etelt
			deleteSmell();
		}
	}
}