package movable;

import item.Item;

import java.util.HashMap;
import java.util.List;
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
	private boolean isHunger;

	public void setDir(Dir d) {
		dir = d;
	}

	public Dir getDir() {
		return dir;
	}

	public Echidna() {
		isActive = false;
		wait = 50;
		hunger = 10;
		dir = Dir.LEFT_TOP;
	}

	public Echidna(String ID) {
		super(ID);
		id = "e" + ID;
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
		isActive = true;
	}

	/**
	 * A hangyaszsun leptetese.
	 */
	@Override
	public void step() {
		int gravelNr = 0;

		/* A szomszedok lekerdezese */
		Field prevField = getActualField();
		Map<Dir, Field> map = prevField.getNeighbours();

		Field nextField = map.get(dir);
		List<Item> items = nextField.getItems();

		for (Item item : items) {
			gravelNr = item.collisionWithEchidna(this, false, dir);
		}

		if (gravelNr > 2)
			ReverseDir();
		/* A hangyaszsun torlese a mezorol */
		else {
			prevField.removeItem(this);

			/* Hangyaszsun atlep a kovetkezo mezore */
			nextField.addItem(this);
			for (Item item : items)
				item.collisionWithEchidna(this, true, dir);
		}

	}

	public void ReverseDir() {
		switch (dir) {
		case UP:
			dir = Dir.DOWN;
			break;
		case DOWN:
			dir = Dir.UP;
			break;
		case RIGHT_TOP:
			dir = Dir.LEFT_BOTTOM;
			break;
		case RIGHT_BOTTOM:
			dir = Dir.LEFT_TOP;
			break;
		case LEFT_TOP:
			dir = Dir.RIGHT_BOTTOM;
			break;
		case LEFT_BOTTOM:
			dir = Dir.RIGHT_TOP;
			break;
		}
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

		/* Lep�es utan */
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

	public void setWait(int i) {
		wait = i;
	}

	public void setHungry(boolean b) {
		isHunger = b;
	}
}
