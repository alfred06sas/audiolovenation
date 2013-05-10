package item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import land.Dir;
import land.Field;
import movable.Ant;
import program.SingletonWriter;
import smell.FoodSmell;
import view.FoodView;

/**
 * 
 * @author audiolovenation
 * 
 *         Az egyes mezokon levo eteleket tarolja. Ezeket szeretnek a hangyak a
 *         bolyba juttatni. Van egy szag tulajdonsaga, ami a kornyezo mezokre is
 *         hat. Ez a hangyak mozgasat befolyasolja.
 */
public class Food extends Item {

	FoodView foodView;

	public Food() {
	}
	
	public void setView(){
		foodView = new FoodView();
		foodView.setPaintable(this);
	}

	/**
	 * konstruktor, inicializál
	 * @param ID az elem id-ja
	 */
	public Food(String ID) {
		super(ID);
		id = "f" + ID;
		foodSmells=new ArrayList<FoodSmell>();
	}

	/**
	 * 0. eleme az actualis FoodSmell 1..6ig a kornyezo mezok FoodSmellje
	 * segitsegevel tudjuk eltavolitani a food osszes illatat, ha felveszi egy hangya
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
	 * Etelszag beallitasa egy adott mezore es a szomszedaira
	 * 
	 * @param Field
	 *            a mezo, amin elhelyezkedik az etel
	 */
	public void setActualField(Field field) {
		super.setActualField(field);
		FoodSmell fs = new FoodSmell(5);
		field.addSmell(fs);
		fs.setActualField(field);
//		foodSmells.add(fs);
		Map<Dir, Field> neighs = field.getNeighbours();
	
		for (Dir key : neighs.keySet()){
			FoodSmell fss = new FoodSmell(2);
			neighs.get(key).addSmell(fss);
			fss.setActualField(neighs.get(key));
			//foodSmells.add(fss);
		}
	
	}

	/**
	 * Hangyaszsunnel valo utkozes. Nem csinal semmit, csak visszater.
	 * 
	 * @param Ant
	 *            a hangya amivel utkozik
	 * @param b
	 *            lepes elott: false, lepes kozben: true
	 */
	@Override
	public void collisionWithAnt(Ant ant, boolean b) {
		SingletonWriter s = SingletonWriter.Instance();		
		
		// Ha a hangya oylan mezore lepett ahol etel van akkor felveszi
		if (b == true) {
			ant.pickUpFood();
			s.printCollision(ant, this, actualField);
			// Etelszag kitorlese a mezorol, ahonnan elviszi a hagnya az etelt
			deleteSmell();
			actualField.removeItem(this);			
		}		
	}
	
	@Override
	public void notifyView() {
		foodView.onDraw();
	}
}