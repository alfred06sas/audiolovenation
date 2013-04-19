package land;

import item.Item;

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
 *         A p�lya mez�kb�l �p�l fel, melyeken elemek (hangya, hangy�szs�n,
 *         hangyales�, akad�ly, spray, �tel, boly) �s szagok helyezkednek el.
 *         Ismeri a szomsz�dait, �s azok ir�ny�t.
 * 
 */
public class Field {

	private String id;
	private List<Item> items;
	private Map<Dir, Field> neighbours;
	private List<Smell> smells;

	/**
	 * Egy elem hozz�rendell�se a mez�h�z
	 * 
	 * @param Item
	 *            azon elem amelyet el akarunk helyezni a mez�n
	 */
	public void addItem(Item item) {
		Singleton s = Singleton.Instance();
		
		item.setActualField(new Field());

	}

	/**
	 * A mez�n l�v� elemek lek�rdez�s�re szolg�l.
	 * 
	 * @return
	 */
	public List<Item> getItems() {
		Singleton s = Singleton.Instance();

		
		return null;
	}

	/**
	 * Egy elem elt�ntet�se a mez�r�l.
	 * 
	 * @param Item
	 * @return
	 */
	public void removeItem(Item item) {
		Singleton s = Singleton.Instance();

		
	}

	/**
	 * A mez� szomsz�dainak a legk�rdez�se
	 * 
	 * @return
	 */
	public Map<Dir, Field> getNeighbours() {

		Singleton s = Singleton.Instance();

		
		return null;
	}

	/**
	 * Egy mez� felv�tele az aktu�lis mez� szomsz�dj�nak.
	 * 
	 * @param Dir
	 *            az aktu�lis mez�h�z viszny�tott ir�nya
	 * @param Field
	 *            az a mez� amit felvesz�nk szomsz�dnak
	 */
	public void addNeighbour(Dir dir, Field field) {
		Singleton s = Singleton.Instance();

	
	}

	/**
	 * A mez�n l�v� szagok lek�rdez�se, ez lehet �tel �s hangyaszag.
	 * 
	 */
	public List<Smell> getSmells() {
		Singleton s = Singleton.Instance();
	

		return null;
	}

	/**
	 * Egy szag felv�tele a mez�re, ez lehet �tel vagy hangyaszag.
	 * 
	 * @param Smell
	 *            a mez�re felveend� szag
	 */
	public void addSmell(Smell smell) {
		Singleton s = Singleton.Instance();
		
	}

	/**
	 * Szag elt�vol�t�sa mez�r�l.
	 * 
	 * @param Smell
	 *            az elt�vol�tand� szag
	 * @return
	 */
	public void removeSmell(Smell smell) {
		Singleton s = Singleton.Instance();
	}

	/**
	 * Hangyaszag elt�ntet�se a mez�r�l.
	 * 
	 * @param Smell
	 *            az elt�ntetend� szag
	 */
	public void removeAntSmells() {
		Singleton s = Singleton.Instance();
		
		SingletonContainer sc = new SingletonContainer().getInstance();
		sc.decreaseAntSmellSpray(5);
	
		//Szag elt�ntet�se
		AntSmell antSmell = new AntSmell();
		antSmell.removeMyself();
	
	}

	/**
	 * Ha egy mez�re kattintunk az eg�rrel, ez az esem�ny h�v�dik meg, ennek
	 * hat�s�ra egy hangyaszag�rt�-spray elem ker�l a mez�re.
	 * 
	 */
	public void onClick() {
		Singleton s = Singleton.Instance();
		
		SingletonContainer sc = new SingletonContainer().getInstance();
		
		Field field = new Field();
		Spray spray = new Spray();
		field.addItem(spray);
		
	}
	
	public String getId(){
		return id;
	}

}