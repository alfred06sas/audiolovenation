package movable;

import item.Item;

import java.util.HashMap;

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
	private Integer wait;
	/**
	 * Ehseg merteke. Ha 0, akkor inaktiv allapotba kerul meghatarozott idore.
	 */
	private Integer hunger;

	/**
	 * Aktualis mezo beallitasa.
	 * 
	 * @param field
	 *            a beallatando mezo
	 */
	@Override
	public void setActualField(Field field) {
		Singleton s = Singleton.Instance();

		
	}

	/**
	 * Ehseg (hunger attributum) csokkentese meghatarozott ertekkel.
	 */
	public void decreaseHunger() {
		Singleton s = Singleton.Instance();

		
	}

	/**
	 * Eletre keltes (wait == 0)
	 */
	@Override
	public void setAlive() {

	}

	/**
	 * - Hangyaszsunel valo utkozes. Nem tortenik semmmi.
	 * 
	 * @param echidna
	 *            referencia a hangyaszsunre, amivel utkozik
	 */
	@Override
	public void collisionWithEchidna(Echidna echidna) {
		Singleton s = Singleton.Instance();

		
	}

	/**
	 * A hangyaszsun leptetese.
	 */
	@Override
	public void step() {
		Singleton s = Singleton.Instance();

		
		Echidna echidna = new Echidna();

		/* A szomszedok lekerdezese */
		Field prevField = new Field();
		prevField.getNeighbours();
	
		/* A hangyaszsun torese a mezorol */
		prevField.removeItem(echidna);
	
		/* Hangyaszsun atlep a kovetkezo mezore */
		Field nextField = new Field();
		nextField.addItem(echidna);
		nextField.getItems();
		
		/* Az ott levo elemekkel valo utkozes */
		Echidna echidna2 = new Echidna();
		echidna2.collisionWithEchidna(echidna);
		
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
