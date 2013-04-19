package movable;

import item.Item;

import java.util.HashMap;
import java.util.Map;

import land.Dir;
import land.Field;
import program.Singleton;

/**
 * 
 * A hangyaszsun viselkedusut valositja meg. A palya inicializalasanal elore
 * meghatarozott szamot hozunk letre belole, melyek nem mindig vannak jelen
 * (inaktavak). Megadott idokozonkent jelennek meg (aktivizalodnak). Van egy
 * ehseg tulajdonsaga, aminek fuggvenyeben bizonyos mennyisegu, az utjaba kerulu
 * hangyat elfogyaszt, majd eltunik. Amennyiben mas elemmel talalkozik, akkor
 * nem tortenik semmi, halad tovabb.
 * 
 * @author audiolovenation
 * 
 */
public class Echidna extends Item implements Movable {

	/**
	 * Aktiv/inakiv allapot jelzese
	 */
	private boolean isActive = false;
	/**
	 * Inaktiv allapotbol hatramarado ido
	 */
	private int wait;
	/**
	 * Ehseg merteke. Ha 0, akkor inaktiv allapotba kerul meghatarozott idore.
	 */
	private int hunger;
	/**
	 * Hangyaszsun iranya.
	 */
	private Dir dir;

	public Echidna(){
		isActive = false;
		wait = 50;
		hunger = 10;
		dir = Dir.LEFT_TOP;
	}
	
	/**
	 * Aktualis mezo beallitasa.
	 * 
	 * @param field
	 *            a beallatando mezo
	 */
	@Override
	public void setActualField(Field field) {
		super.setActualField(field);
		
	}

	/**
	 * Ehseg (hunger attributum) csokkentese meghatarozott ertekkel.
	 */
	public void decreaseHunger() {
		hunger--;

		
	}

	/**
	 * Eletre keltes (wait == 0)
	 */
	@Override
	public void setAlive() {
		isActive=true;
	}

	/**
	 * - Hangyaszsunel valo utkozes. Nem tortenik semmmi.
	 * 
	 * @param echidna
	 *            referencia a hangyaszsunre, amivel utkozik
	 */
	@Override
	public void collisionWithEchidna(Echidna echidna) {
		
		
	}

	/**
	 * A hangyaszsun leptetese.
	 */
	@Override
	public void step() {

		
		Echidna echidna = new Echidna();

		/* A szomszedok lekerdezese */
		Field prevField = getActualField();
		Map<Dir, Field> map = prevField.getNeighbours();
	
		/* A hangyaszsun torese a mezorol */
		prevField.removeItem(echidna);
	
		/* Hangyaszsun atlep a kovetkezo mezore */
		Field nextField = map.get(dir);
		nextField.addItem(echidna);
		nextField.getItems();
		
		collisionWithEchidna(echidna);
		
	}

	/**
	 * Hangyaval valo utkozes. A hangya megolese. Lepes elott nem csinal semmit.
	 * 
	 * @param ant
	 *            a hangya referenciaja, amivel utkozott
	 * @param b
	 *            utkozes elott: false, utkozes utan: true -
	 */
	@Override
	public void collisionWithAnt(Ant ant, boolean b) {
		Singleton s = Singleton.Instance();

		/* Lepïes utan */
		if (b == true) {
			/* Hangya megolese */
			ant.kill();
		
			/* Ehseg csokkentese */
			Echidna echidna = new Echidna();
			echidna.decreaseHunger();
		}
	
	}
	
	@Override
	public HashMap<String, String> getStates() {
		HashMap<String, String> states = new HashMap<String, String>();
		
		if (wait > 0)
			states.put("STATE", "WAIT");
		else {
			if (hunger > 0)
				states.put("STATE", "HUNGRY");
			else
				states.put("STATE", "NOT_HUNGRY");
		}
		
		return states;
	}
}
