package movable;

import item.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import land.Dir;
import land.Field;
import view.EchidnaView;

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
	private EchidnaView echidnaView;
	private int stepNr;

	public void setDir(Dir d) {
		dir = d;
	}

	public Dir getDir() {
		return dir;
	}

	public Echidna() {
		isActive = false;
		wait = (new Random()).nextInt(40);
		hunger = 10;
		dir = Dir.LEFT_TOP;
		stepNr = 0;
	}

	public void setView() {
		echidnaView = new EchidnaView();
		echidnaView.setPaintable(this);
	}

	public Echidna(String ID) {
		super(ID);
		id = "e" + ID;
		isActive = false;
		wait = new Random().nextInt(40);
		hunger = 2;
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
		if (!isActive) {
			--wait;
			if (wait == 0)
				setAlive();
			return;
		}
		int gravelNr = 0;

		if (stepNr % 3 == 0) {
			dir = Dir.fromInteger((new Random()).nextInt(6));
		}

		stepNr++;

		/* A szomszedok lekerdezese */
		Map<Dir, Field> neighbours = actualField.getNeighbours();

		Field nextField = neighbours.get(dir);
		List<Item> nextFieldItems = nextField.getItems();

		for (Item item : nextFieldItems) {
			gravelNr = item.collisionWithEchidna(this, false, dir);
		}

		if (gravelNr > 2) {
			ReverseDir();
			notifyView();
		}
		/* A hangyaszsun torlese a mezorol */
		else {
			actualField.removeItem(this);

			/* Hangyaszsun atlep a kovetkezo mezore */

			nextField.addItem(this);
			setActualField(nextField);

			for (Item item : nextFieldItems) {
				item.collisionWithEchidna(this, true, dir);
			}
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

		/* Lepes utan */
		if (b == true) {
			/* Hangya megolese */
			ant.kill();

			/* Ehseg csokkentese */
			this.decreaseHunger();
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
		states.put("DIR", dir.toString());

		return states;
	}

	public void setWait(int i) {
		wait = i;
	}

	public void setHungry(boolean b) {
		wait = 0;
		isHunger = b;
	}

	@Override
	public void notifyView() {
		echidnaView.onDraw();
	}
}
