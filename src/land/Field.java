package land;

import item.Item;
import item.Spray;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import program.Singleton;
import program.SingletonContainer;
import smell.AntSmell;
import smell.Smell;

/**
 * 
 * @author audiolovenation
 * 
 *         A palya mezokbol epul fel, melyeken elemek (hangya, hangyaszsun,
 *         hangyaleso, akadaly, spray, etel, boly) es szagok helyezkednek el.
 *         Ismeri a szomszedait, es azok iranyat.
 * 
 */
public class Field {

	private String id;
	private List<Item> items;
	private Map<Dir, Field> neighbours;
	private List<Smell> smells;

	public Field(){
		id=new String();
		neighbours=new HashMap<Dir, Field>();
	}
	
	/**
	 * Egy elem hozzarendelese a mezohoz
	 * 
	 * @param Item
	 *            azon elem amelyet el akarunk helyezni a mezon
	 */
	public void addItem(Item item) {
		Singleton s = Singleton.Instance();
		
		item.setActualField(new Field());

	}

	/**
	 * A mezon levo elemek lekerdezesere szolgal.
	 * 
	 * @return
	 */
	public List<Item> getItems() {
		Singleton s = Singleton.Instance();

		
		return null;
	}

	/**
	 * Egy elem eltuntetese a mezorol.
	 * 
	 * @param Item
	 * @return
	 */
	public void removeItem(Item item) {
		Singleton s = Singleton.Instance();

		
	}

	/**
	 * A mezo szomszedainak lekerdezese
	 * 
	 * @return
	 */
	public Map<Dir, Field> getNeighbours() {

		Singleton s = Singleton.Instance();

		
		return neighbours;
	}

	/**
	 * Egy mezo felvetele az aktualis mezo szomszedjanak.
	 * 
	 * @param Dir
	 *            az aktualis mezohoz viszonyitott aranya
	 * @param Field
	 *            az a mezo, amit felveszunk szomszednak
	 */
	public void addNeighbour(Dir dir, Field field) {
		Singleton s = Singleton.Instance();
		
		neighbours.put(dir, field);
	}

	/**
	 * A mezon levo szagok lekerdezese, ez lehet etel, es hangyaszag.
	 * 
	 */
	public List<Smell> getSmells() {
		Singleton s = Singleton.Instance();
	

		return null;
	}

	/**
	 * Egy szag felvetele a mezore, ez lehet etel, vagy hangyaszag.
	 * 
	 * @param Smell
	 *            a mezore felveendo szag
	 */
	public void addSmell(Smell smell) {
		Singleton s = Singleton.Instance();
		
	}

	/**
	 * Szag eltavolitasa mezorol.
	 * 
	 * @param Smell
	 *            az eltavolitando szag
	 * @return
	 */
	public void removeSmell(Smell smell) {
		Singleton s = Singleton.Instance();
	}

	/**
	 * Hangyaszag eltuntetese a mezorol.
	 * 
	 * @param Smell
	 *            az eltuntetendo szag
	 */
	public void removeAntSmells() {
		Singleton s = Singleton.Instance();
		
		SingletonContainer sc = new SingletonContainer().getInstance();
		sc.decreaseAntSmellSpray(5);
	
		//Szag eltuntetese
		AntSmell antSmell = new AntSmell();
		antSmell.removeMyself();
	
	}

	/**
	 * Ha egy mezore kattintunk az egerrel, ez az esemeny hivodik meg, ennek
	 * hatasara egy hangyaszagirto-spray elem kerul a mezore.
	 * 
	 */
	public void onClick() {
		Singleton s = Singleton.Instance();
		
		SingletonContainer sc = new SingletonContainer().getInstance();
		
		Field field = new Field();
		Spray spray = new Spray();
		field.addItem(spray);
		
	}
	
	public void setId(String s){
		id=s;
	}
	
	public String getId(){
		return id;
	}
	
	public String toString(){
		return id;
	}

}