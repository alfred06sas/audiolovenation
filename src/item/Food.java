package item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import program.Singleton;

import land.Dir;
import land.Field;
import movable.Ant;
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

	public Food() {
	}

	public Food(String ID) {
		super(ID);
		id = "f" + ID;
		foodSmells=new ArrayList<FoodSmell>();
	}

	/**
	 * 0. eleme az actualis FoodSmell 1..6ig a kornyezo mezok FoodSmellje
	 * 
	 */
	private List<FoodSmell> foodSmells;

	/**
	 * Az etel szaganak eltuntetese egy mezorol.
	 */
	public void deleteSmell() {
		
		for (FoodSmell f : foodSmells)
			f.removeMyself();
	}

	/**
	 * Etelszag hozzaadasa a mezohoz, es szomszedaihoz.
	 * 
	 * @param FoodSmell
	 *            a mezohoz hozzaadando etelszag
	 */
/*	public void addFoodSmell(FoodSmell foodSmell) {
		getActualField().addSmell(new FoodSmell(5));
		Map<Dir, Field> neighbours = getActualField().getNeighbours();
		for (int i = 1; i < neighbours.size(); i++) {
			neighbours.get(i).addSmell(new FoodSmell(2));
		}
		foodSmells.add(foodSmell);
	} */

	/**
	 * Etelszag beallitasa egy adott mezore.
	 * 
	 * @param Field
	 *            a mezo, amihez beallitjuk az etelszagot.
	 */
	public void setActualField(Field field) {
		super.setActualField(field);
		FoodSmell fs = new FoodSmell(5);
		field.addSmell(fs);
		fs.setActualField(field);
		foodSmells.add(fs);
		Map<Dir, Field> neighs = field.getNeighbours();
	
		for (Dir key : neighs.keySet()){
			FoodSmell fss = new FoodSmell(2);
			neighs.get(key).addSmell(fss);
			fss.setActualField(neighs.get(key));
			foodSmells.add(fss);
		}
	
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
			s.printCollision(ant, this, actualField);
			// Etelszag kitorlese a mezorol, ahonnan elviszi a hagnya az etelt
			deleteSmell();
			actualField.removeItem(this);			
		}		
	}
}