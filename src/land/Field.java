package land;

import item.Item;
import item.Spray;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import paintable.Paintable;
import program.SingletonContainer;
import program.SingletonWriter;
import smell.Smell;
import view.FieldView;
import view.PaintableView;

/**
 * 
 * @author audiolovenation
 * 
 *         A palya mezokbol epul fel, melyeken elemek (hangya, hangyaszsun,
 *         hangyaleso, akadaly, spray, etel, boly) es szagok helyezkednek el.
 *         Ismeri a szomszedait, es azok iranyat.
 * 
 */
public class Field extends Paintable{

	private String id;
	private CopyOnWriteArrayList<Item> items;
	private Map<Dir, Field> neighbours;
	private List<Smell> smells;
	FieldView fieldView = new FieldView();
	
	public void setView(){
		fieldView=new FieldView();
		fieldView.setPaintable(this);
	}

	public Field() {
		id = new String();
		neighbours = new HashMap<Dir, Field>();
		items = new CopyOnWriteArrayList<Item>();
		smells = new CopyOnWriteArrayList<Smell>();
	}

	public Field(String s) {
		id = s;
		neighbours = new HashMap<Dir, Field>();
		items = new CopyOnWriteArrayList<Item>();
		smells = new CopyOnWriteArrayList<Smell>();
	}

	/**
	 * Egy elem hozzarendelese a mezohoz
	 * 
	 * @param Item
	 *            azon elem amelyet el akarunk helyezni a mezon
	 */
	public void addItem(Item item) {
		items.add(item);
		item.setActualField(this);
	}

	/**
	 * A mezon levo elemek lekerdezesere szolgal.
	 * 
	 * @return
	 */
	public CopyOnWriteArrayList<Item> getItems() {
		return items;
	}

	/**
	 * Egy elem eltuntetese a mezorol.
	 * 
	 * @param Item
	 * @return
	 */
	public void removeItem(Item item) {
		items.remove(item);
	}

	/**
	 * A mezo szomszedainak lekerdezese
	 * 
	 * @return
	 */
	public Map<Dir, Field> getNeighbours() {
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
		neighbours.put(dir, field);
	}

	/**
	 * A mezon levo szagok lekerdezese, ez lehet etel, es hangyaszag.
	 * 
	 */
	public List<Smell> getSmells() {
		return smells;
	}

	/**
	 * Egy szag felvetele a mezore, ez lehet etel, vagy hangyaszag.
	 * 
	 * @param Smell
	 *            a mezore felveendo szag
	 */
	public void addSmell(Smell smell) {
		smells.add(smell);
	}

	/**
	 * Szag eltavolitasa mezorol.
	 * 
	 * @param Smell
	 *            az eltavolitando szag
	 * @return
	 */
	public void removeSmell(Smell smell) {
		smells.remove(smell);
	}

	/**
	 * Hangyaszag eltuntetese a mezorol.
	 * 
	 * @param Smell
	 *            az eltuntetendo szag
	 */
	public void removeAntSmells() {
		SingletonContainer sc = new SingletonContainer().getInstance();
		sc.decreaseAntSmellSpray(5);
		
		for (Smell smell : smells) {
			smell.antSmellSpray();
		}

	}

	/**
	 * Ha egy mezore kattintunk az egerrel, ez az esemeny hivodik meg, ennek
	 * hatasara egy hangyaszagirto-spray elem kerul a mezore.
	 * 
	 */
	public void onClick(String s_id) {
		SingletonWriter s = SingletonWriter.Instance();
		Spray spray = new Spray(s_id);
		s.addItem(spray);		
		SingletonContainer sc = SingletonContainer.getInstance();
		sc.addVolatile(spray);
		CopyOnWriteArrayList<Item> it = getItems();
		for (Item i : it){
			i.collisionWithSpray(spray.getStrength());
			s.printCollision(spray, i, this);
		}
		addItem(spray);
	}

	public void setId(String s) {
		id = s;
	}

	public String getId() {
		return id;
	}

	public String toString() {
		return id;
	}

	@Override
	public void notifyView() {
		fieldView.onDraw();
	}

}
