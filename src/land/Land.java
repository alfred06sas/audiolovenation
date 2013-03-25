package land;

import item.Item;
import item.Spray;

import java.util.List;

import movable.Ant;
import movable.Echidna;
import program.Singleton;
import program.SingletonContainer;

/**
 * 
 * @author audiolovenation
 * 
 *         Az egész játék alapja, mezõkbõl épül fel. Feladata a program
 *         inicializálása és a lépések megvalósítása. A pályát felépíti,
 *         létrehoz meghatározott számú hangyát, hangyászsünt, hangyalesõt,
 *         akadályt, bolyt. A hangyákat és a hangyászsünöket egyelõre nem
 *         jelenít meg (inaktív állapotba állítja).
 */
public class Land {

	private List<Field> fields;

	/**
	 * A pálya összeállítása.
	 * 
	 */
	public void loadLand() {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Land.loadLand()");

		// Ide jön a kód

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Land.loadLand()");
		s.depth--;

	}

	/**
	 * Elemek elhelyezése a mezõkön. Ezek az elemek lehetnek akadályok, hangyák,
	 * szagok, hangyászsün és hangyalesõ.
	 * 
	 */
	public void putItems() {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Land.putItems()");

		// Ide jön a kód
		Item item = s.items.get(15);

		s.stack.add(6);
		Field field = s.fields.get(5);

		field.addItem(item);

		s.stack.remove(s.stack.size() - 1);

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Land.putItems()");
		s.depth--;
	}

	/**
	 * A Land készteti mozgásra minden körben az arra képes elemeket. A move
	 * hatására meghívódik mind a hangyában mind a hangyászsünben a step()
	 * függvény, ami a lépést valósítja meg.
	 * 
	 */
	public void move() {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Land.move()");

		s.stack.add(1);
		SingletonContainer sc = s.singletonContainer.get(0).getInstance();

		sc.getMovables();
		s.stack.remove(s.stack.size() - 1);

		s.stack.add(2);
		Ant ant = (Ant) s.items.get(1);
		ant.step();
		s.stack.remove(s.stack.size() - 1);

		s.stack.add(6);
		Echidna echinda = (Echidna) s.items.get(5);
		echinda.step();
		s.stack.remove(s.stack.size() - 1);

		s.stack.add(1);
		sc.getVolatiles();
		s.stack.remove(s.stack.size() - 1);

		s.stack.add(2);
		Spray spray = s.sprays.get(1);
		spray.decrease();
		s.stack.remove(s.stack.size() - 1);

		// Ide jön a kód
		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Land.move()");
		s.depth--;
	}

	/**
	 * 
	 * @return
	 */
	public void buildLand() {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Land.buildLand()");

		// Field.addNeighbour hívások
		s.stack.add(3);
		s.fields.get(2).addNeighbour(Dir.DOWN, s.fields.get(8));
		s.stack.remove(s.stack.size() - 1);

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Land.buildLand()");
		s.depth--;
	}

	/**
	 * 
	 * @return
	 */
	public void init() {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Land.init()");

		/* ide jön a kód */
		// palya betöltése
		loadLand();
		// palya felépítése
		buildLand();
		// elemek palyara helyezese
		putItems();
		// leptetes
		move();

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Land.init()");
		s.depth--;
	}

}
