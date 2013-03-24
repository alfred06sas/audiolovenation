package movable;

import item.Item;
import item.Tentacle;
import item.Volatile;

import java.util.Map;
import java.util.TreeMap;

import land.Dir;
import land.Field;
import program.Singleton;
import program.SingletonContainer;
import smell.AntSmell;
import smell.Smell;

/**
 * 
 * A hangya feladata mezõrõl mezõre lépni. Mozgása irányát az étel, és más
 * hangyák által hagyott szagnyom befolyásolja. Végül, ha ételhez ér, vissza
 * kell azt vinnie a bolyba.
 * 
 * @author audiolovenation
 * 
 */
public class Ant extends Item implements Movable {
	/**
	 * Csáp objektum. Ez végzi a továbbhaladási irányok beállítását.
	 */
	private Tentacle tentacle;
	/**
	 * A hangya meghalt-e.
	 */
	private boolean isKilled = false;
	/**
	 * A hangya aktív-e (a pályán tartózkodik-e).
	 */
	private boolean isActive = false;
	/**
	 * Inaktív állapotban a maradék várakozási körök.
	 */
	private Integer wait;
	/**
	 * Van-e a hangyánál étel.
	 */
	private boolean haveFood = false;
	/**
	 * Életerõ, amelyet a spray, a hangyalesõ és a hangyászsün módosíthat. Ha 0,
	 * akkor a hangya átlép inaktív állapotba és a kör végén meghal.
	 */
	private Integer HP;
	/**
	 * A továbbhaladási irány. Default: UP.
	 */
	private Dir dir;

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

		s.makeSpace(">> CALL: " + id + ": Ant.setActualField("
				+ s.fields.indexOf(field) + ": Field)");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Ant.setActualField("
				+ s.fields.indexOf(field) + ": Field)");
		s.depth--;
	}

	/**
	 * Életerõ lecsökkentése a megadott értékkel.
	 * 
	 * @param hp
	 *            a csökkentés mértéke
	 */
	public void looseHP(Integer hp) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Ant.looseHP(" + hp + ": Integer)");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Ant.looseHP(" + hp + ": Integer)");
		s.depth--;
	}

	/**
	 * A hangya ételt vesz fel. Állapotát átállítja (haveFood = true).
	 */
	public void pickUpFood() {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Ant.pickupFood()");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Ant.pickUpFood()");
		s.depth--;
	}

	/**
	 * Pihenteti a hangyát egy adott ideig [amíg el nem indul (újra) a
	 * bolyból)].
	 */
	public void rest() {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Ant.rest()");
		s.depth--;

		s.makeSpace("<< RETURN: " + id + ": Ant.rest()");
		s.depth--;
	}

	/**
	 * A hangya irányának megfordítása.
	 */
	public void reverseDir() {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Ant.reverseDir()");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Ant.reverseDir()");
		s.depth--;
	}

	/**
	 * A hangya megölése. Törli magát a mezõrõl és a SingletonContainer-ben levõ
	 * movables listából is.
	 */
	public void kill() {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Ant.kill()");

		/* Hangya törlése az õt tartalmazó mezõrõl */
		s.stack.add(17);
		Field field = s.fields.get(16);
		field.removeItem(s.ants.get(id));
		s.stack.remove(s.stack.size() - 1);

		/* Hangya törlése a SingletonContainer movables listájából */
		s.stack.add(1);
		SingletonContainer sc = s.singletonContainer.get(0).getInstance();
		sc.removeMovable(s.ants.get(id));
		s.stack.remove(s.stack.size() - 1);

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Ant.kill()");
		s.depth--;
	}

	/**
	 * Haladási irány beállítása.
	 * 
	 * @param dir
	 *            a beállítandó haladási irány
	 */
	public void setDir(Dir dir) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Ant.setDir(" + dir.toString()
				+ ": Dir)");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Ant.setDir(" + dir.toString()
				+ ": Dir)");
		s.depth--;
	}

	/**
	 * A hangya léptetése. Lekérdezi az õt tartalmazó mezõ szomszédait, majd
	 * ezekbõl a lehetséges továbbhaladási irányban levõket továbbadja a
	 * csápjának. A csáp ezek után megvizsgálja, hogy hova léphet, majd ahol
	 * akadály van, kiszedi a lehetséges mezõk listájából. Ha mindenhol akadály
	 * van, akkor megfordul (marad ugyanazon a mezõn), ha megtalálta a
	 * legmegfelelõbbet, akkor rálép, majd ütközik az ottani item-ekkel.
	 */
	@Override
	public void step() {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Ant.step()");

		/* A szomszédok lekérdezése */
		s.stack.add(12);
		Field prevField = s.fields.get(11);
		prevField.getNeighbours();
		s.stack.remove(s.stack.size() - 1);

		/* A lehetséges szomszédok beállítása */
		s.stack.add(9);
		Tentacle tentacle = s.tentacles.get(8);
		Map<Dir, Field> map = new TreeMap<Dir, Field>();
		map.put(Dir.DOWN, prevField);
		tentacle.setPossibleNeighbours(map);
		s.stack.remove(s.stack.size() - 1);

		/* A lehetséges szomszédok item-einek egy ciklusban való lekérdezése */
		s.stack.add(11);
		Field nextField = s.fields.get(10);
		nextField.getItems();
		s.stack.remove(s.stack.size() - 1);

		/*
		 * Az elemekkel való ütköztetés még lépés elõtt, hogy kiderüljön, hol
		 * található akadály
		 */
		s.stack.add(5);
		Ant ant = (Ant) s.items.get(id);
		Ant ant2 = (Ant) s.items.get(4);
		ant2.collisionWithAnt(ant, false);
		s.stack.remove(s.stack.size() - 1);

		/*
		 * A csáp scan metódusának meghívása, amely kiválasztja a következõ
		 * mezõt.
		 */
		s.stack.add(9);
		Map<Dir, Field> map2 = tentacle.scan(true);
		s.stack.remove(s.stack.size() - 1);

		/* Ha van olyan mezõ, ahol nincs akadály */
		if (map2 != null) {
			/* Hangyaszag hagyása az elõzõ mezõn */
			s.stack.add(12);
			Smell smell = (AntSmell) s.smells.get(4);
			prevField.addSmell(smell);
			s.stack.remove(s.stack.size() - 1);

			/* Hangyaszag hozzáadása a SingletonContainer volatiles listájához */
			s.stack.add(1);
			SingletonContainer sc = s.singletonContainer.get(0);
			Volatile smell2 = (AntSmell) s.smells.get(4);
			sc.addVolatile(smell2);
			s.stack.remove(s.stack.size() - 1);

			/* Elõzõ mezõrõl eltávolítja saját magát */
			s.stack.add(12);
			prevField.removeItem(ant);
			s.stack.remove(s.stack.size() - 1);

			/* Következõ mezõ beállítása */
			s.stack.add(2);
			ant.setActualField(nextField);
			s.stack.remove(s.stack.size() - 1);

			/* Következõ mezõbe való beregisztrálás */
			s.stack.add(11);
			nextField.addItem(ant);
			s.stack.remove(s.stack.size() - 1);
			/* Ha nem tud sehova se menni */
		} else {
			/* Irány megfordítása */
			s.stack.add(5);
			ant.reverseDir();
			s.stack.remove(s.stack.size() - 1);
		}
		/* lépés utáni ütköztetés */
		s.stack.add(8);
		Echidna echidna = (Echidna) s.items.get(7);
		echidna.collisionWithAnt(ant, true);
		s.stack.remove(s.stack.size() - 1);

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Ant.step()");
		s.depth--;

	}

	/**
	 * Hangya állapotának inaktívról aktívra állítása (wait == 0)
	 */
	@Override
	public void setAlive() {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Ant.setAlive()");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Ant.setAlive()");
		s.depth--;
	}

	/**
	 * Hangyával való ütközés. Nem csinál semmit, csak visszatér.
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

		s.makeSpace(">> CALL: " + id + ": Ant.collisionWithAnt("
				+ s.ants.indexOf(ant) + ": Ant, " + String.valueOf(b) + ")");
		s.depth--;

		s.makeSpace("<< RETURN: " + id + ": Ant.collisionWithAnt("
				+ s.ants.indexOf(ant) + ": Ant, " + String.valueOf(b) + ")");
		s.depth--;
	}

	/**
	 * - Hangyászsünel való ütközés. A hangya meghal.
	 * 
	 * @param echidna
	 *            referencia a hangyászsünre, amivel ütközik
	 */
	@Override
	public void collisionWithEchidna(Echidna echidna) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Ant.collisionWithEchidna("
				+ s.echidnas.indexOf(echidna) + ": Echidna)");

		/*
		 * A hangya szól a hangyászsünnek, hogy hangyával ütközött, amely ennek
		 * hatására megöli
		 */
		s.stack.add(s.items.indexOf(echidna));
		Ant ant = (Ant) s.items.get(id - 1);
		echidna.collisionWithAnt(ant, true);
		s.stack.remove(s.stack.size() - 1);

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Ant.collisionWithEchidna("
				+ s.echidnas.indexOf(echidna) + ": Echidna)");
		s.depth--;
	}

	/**
	 * Spray-vel való ütközés. A hangya vagy meghal, vagy életerõt veszít
	 * 
	 * @param strength
	 *            a hangya életereje ennyivel fog csökkenni
	 */
	@Override
	public void collisionWithSpray(Integer strength) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Ant.collisionWithSpray(" + strength
				+ ": Integer)");

		/* A hangya looseHP metódusának meghívása */
		s.stack.add(id);
		Ant ant = s.ants.get(id);
		ant.looseHP(6);
		s.stack.remove(s.stack.size() - 1);

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Ant.collisionWithSpray(" + strength
				+ ": Integer)");
		s.depth--;
	}

	/**
	 * Akadállyal való ütközés esetén az akadály ezzel a metódussal jelzi a
	 * hangyának, hogy az õt tartalmazó mezõre nem léphet. Ezt a mezõt a csáp
	 * kiveszi a lehetséges szomszédok közül. Mindig lépés elõtt hívódik meg,
	 * mivel az nem fordulhat elõ. hogy a hangya olyan mezõre lép, ahol akadály
	 * van.
	 * 
	 * @param field
	 *            az a mezõ, ahova nem léphet a hangya
	 */
	public void canNotGo(Field field) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Ant.canNotGo("
				+ s.fields.indexOf(field) + ": Field)");

		/* A paraméterként megadott mezõ kivétele a lehetséges mezõk listájából */
		s.stack.add(17);
		Tentacle tentacle = s.tentacles.get(16);
		tentacle.removePossibleNeighbour(field);
		s.stack.remove(s.stack.size() - 1);

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Ant.canNotGo("
				+ s.fields.indexOf(field) + ": Field)");
		s.depth--;
	}
}
