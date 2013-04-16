package movable;

import java.util.Map;
import java.util.TreeMap;

import land.Dir;
import land.Field;
import program.Singleton;
import program.SingletonContainer;
import smell.AntSmell;
import smell.Smell;
import item.Item;
import item.Tentacle;
import item.Volatile;

/**
 * 
 * A hangyászsün viselkedését valósítja meg. A pálya inicializálásánál elõre
 * meghatározott számút hozunk létre belõle, melyek nem mindig vannak jelen
 * (inaktívak). Megadott idõközönként jelennek meg (aktivizálódnak). Van egy
 * éhség tulajdonsága, aminek függvényében bizonyos mennyiségû, az útjába kerülõ
 * hangyát elfogyaszt, majd eltûnik. Amennyiben más elemmel találkozik, akkor
 * nem történik semmi, halad tovább.
 * 
 * @author audiolovenation
 * 
 */
public class Echidna extends Item implements Movable {

	/**
	 * Aktív/inaktív állapot jelzése
	 */
	private boolean isActive = false;
	/**
	 * Inaktív állapotból hátramaradó körök száma
	 */
	private Integer wait;
	/**
	 * Éhség mértéke. Ha 0, akkor inaktív állapotba kerül meghatározott körre.
	 */
	private Integer hunger;

	/**
	 * Aktuális mezõ beállítása.
	 * 
	 * @param field
	 *            a beállítandó mezõ
	 */
	@Override
	public void setActualField(Field field) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Echidna.setActualField("
				+ s.fields.indexOf(field) + ": Field)");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Echidna.setActualField("
				+ s.fields.indexOf(field) + ": Field)");
		s.depth--;
	}

	/**
	 * Éhség (hunger attribútum) csökkentése meghatározott értékkel.
	 */
	public void decreaseHunger() {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Echidna.decreaseHunger()");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Echidna.decreaseHunger()");
		s.depth--;
	}

	/**
	 * Életre keltés (wait == 0)
	 */
	@Override
	public void setAlive() {

	}

	/**
	 * - Hangyászsünel való ütközés. Nem történik semmmi.
	 * 
	 * @param echidna
	 *            referencia a hangyászsünre, amivel ütközik
	 */
	@Override
	public void collisionWithEchidna(Echidna echidna) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Echidna.collisionWithEchidna("
				+ s.items.indexOf(echidna) + ": Echidna)");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Echidna.collisionWithEchidna("
				+ s.items.indexOf(echidna) + ": Echidna)");
		s.depth--;
	}

	/**
	 * A hangyászsün léptetése.
	 */
	@Override
	public void step() {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Echidna.step()");

		Echidna echidna = (Echidna) s.items.get(id);

		/* A szomszédok lekérdezése */
		s.stack.add(12);
		Field prevField = s.fields.get(11);
		prevField.getNeighbours();
		s.stack.remove(s.stack.size() - 1);

		/* A hangyászsün törlése a mezõrõl */
		s.stack.add(12);
		prevField.removeItem(echidna);
		s.stack.remove(s.stack.size() - 1);

		/* Hangyászsün átlép a kövezkezõ mezõre */
		s.stack.add(11);
		Field nextField = s.fields.get(10);
		nextField.addItem(echidna);
		nextField.getItems();
		s.stack.remove(s.stack.size() - 1);

		/* Az ott levõ elemekkel való ütközés */
		s.stack.add(10);
		Echidna echidna2 = (Echidna) s.items.get(9);
		echidna2.collisionWithEchidna(echidna);
		s.stack.remove(s.stack.size() - 1);

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Echidna.step()");
		s.depth--;

	}

	/**
	 * Hangyával való ütközés. A hangya megölése. Lépés elõtt nem csinál semmit.
	 * 
	 * @param ant
	 *            a hangya referenciája. amivel ütközött
	 * @param b
	 *            ütközés elõtt: false, ütközés után: true -
	 */
	@Override
	public void collisionWithAnt(Ant ant, boolean b) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Echidna.collisionWithAnt("
				+ s.ants.indexOf(ant) + ": Ant, " + String.valueOf(b)
				+ ": boolean)");

		/* Lépés után */
		if (b == true) {
			/* Hangya megölése */
			s.stack.add(s.items.indexOf(ant));
			ant.kill();
			s.stack.remove(s.stack.size() - 1);

			/* Éhség csökkentése */
			s.stack.add(id);
			Echidna echidna = (Echidna) s.items.get(id - 1);
			echidna.decreaseHunger();
			s.stack.remove(s.stack.size() - 1);
		}
		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Echidna.collisionWithAnt("
				+ s.ants.indexOf(ant) + ": Ant, " + String.valueOf(b)
				+ ": boolean)");

		s.depth--;
	}
}