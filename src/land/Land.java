package land;

import item.Antlion;
import item.Food;
import item.Hill;
import item.Spray;
import item.Volatile;

import java.util.HashMap;
import java.util.List;

import movable.Ant;
import movable.Echidna;
import movable.Movable;
import program.SingletonContainer;
import blockage.Gravel;
import blockage.Puddle;

/**
 * 
 * @author audiolovenation
 * 
 *         Az egesz jatek alapja, mezokbol epul fel. Feladata a program
 *         inicializalasa es a lepesek megvalositasa. A palyat felepiti,
 *         letrehoz meghatarozott szamu hangyat, hangyaszsunt, hangyalesot,
 *         akadalyt, bolyt. A hangyakat es a hangyaszsunoket egyelore nem
 *         jeleniti meg (inaktiv allapotba allitja).
 * 
 */
public class Land {

	int columnNumber;
	int rowNumber;

	private HashMap<String, Field> fields = new HashMap<String, Field>();

	public Land() {
		fields = new HashMap<String, Field>();
		columnNumber = 10;
		rowNumber = 10;
	}

	/**
	 * A palya osszeallitasa.
	 * 
	 */
	public void loadLand(int row, int column) {
		if (!(((row % 2 == 0) && (column % 2 == 0)) || ((row % 2 == 1) && (column % 2 == 1)))) {
			System.out
					.println("Hiba: az oszlop es sorszam legyen ugyanolyan paritasu!");
			System.exit(0);
		}
		rowNumber = row;
		columnNumber = column;

		for (int k = 0; k < rowNumber; k++) {
			for (int j = 0; j < columnNumber; j++) {
				if (((k % 2 == 0) && (j % 2 == 0))
						|| ((k % 2 == 1) && (j % 2 == 1))) {
					fields.put(k + "_" + j, new Field(k + "_" + j));
					fields.get(k + "_" + j).setView();
					fields.get(k + "_" + j).notifyView();
				}
			}
		}
	}

	/**
	 * Elemek elhelyezese a mezokon. Ezek az elemek lehetnek akadalyok, hangyak,
	 * szagok, hangyaszsunok es hangyalesok.
	 * 
	 */
	public void putItems(/* Field field, Item item */) {
		SingletonContainer sc = new SingletonContainer().getInstance();

		// // Antlion(ok) hozzáadása
		// Antlion a = new Antlion();
		// a.setView();
		// fields.get(2 + "_" + 2).addItem(a);
		//
//		 // Boly(ok) hozzáadása
//		 Hill h = new Hill();
//		 h.setView();
//		 fields.get(7 + "_" + 7).addItem(h);

		// Hangyaszsun(ok) hozzáadása
		// Echidna e = new Echidna();
		// sc.addMovable(e);
		// e.setView();
		// fields.get(1 + "_" + 3).addItem(e);

		Echidna e2 = new Echidna();
		sc.addMovable(e2);
		e2.setView();
		fields.get(3 + "_" + 3).addItem(e2);

		// Hangya(k) hozzáadása
		Ant a1 = new Ant();
		sc.addMovable(a1);
		a1.setView();
		a1.setDir(Dir.RIGHT_BOTTOM);
		fields.get(3 + "_" + 7).addItem(a1);

		// // Hangya(k) hozzáadása
		// Ant a2 = new Ant();
		// sc.addMovable(a2);
		// a2.setView();
		// a2.setDir(Dir.DOWN);
		// fields.get(4 + "_" + 4).addItem(a2);

		// Kaja(k) hozzáadása

		Food f = new Food();
		f.setView();
		fields.get(7 + "_" + 3).addItem(f);

//		 // Pocsolya(k) hozzáadása
//		 Puddle p = new Puddle();
//		 p.setView();
//		 fields.get(7 + "_" +7).addItem(p);

		// Kove(k) hozzáadása
		Gravel g = new Gravel();
		g.setView();
		fields.get(1 + "_" + 1).addItem(g);

		// Kove(k) hozzáadása
		Gravel g1 = new Gravel();
		g1.setView();
		fields.get(3 + "_" + 3).addItem(g1);

		// Kove(k) hozzáadása
		Gravel g2 = new Gravel();
		g2.setView();
		fields.get(5 + "_" + 5).addItem(g2);
		//
		// // Kove(k) hozzáadása
		// Gravel g1 = new Gravel();
		// g1.setView();
		// fields.get(2 + "_" + 4).addItem(g1);
		//
		// // Sprey(k) hozzáadása
		// Spray s = new Spray();
		// s.setView();
		// fields.get(2 + "_" + 0).addItem(s);

	}

	/**
	 * A Land keszteti mozgasra minden korben az arra kepes elemeket. A move
	 * hatasara meghivodik mind a hangyaban mind a hangyaszsunben a step()
	 * fuggveny, ami a lepest valositja meg.
	 * 
	 */
	public void move() {
		SingletonContainer sc = SingletonContainer.getInstance();
		List<Movable> movables = sc.getMovables();

		// Movable-k leptetese
		for (Movable movable : movables)
			movable.step();

		List<Volatile> volatiles = sc.getVolatiles();

		// Illekony anyagok csokkentese
		for (Volatile volatile_ : volatiles) {
			volatile_.decrease();
		}
	}

	/**
	 * 
	 * @return
	 */
	public void buildLand() {
		// Field.addNeighbour hivasok

		for (int k = 0; k < rowNumber; k++) {
			for (int j = 0; j < columnNumber; j++) {
				if (((k % 2 == 0) && (j % 2 == 0))
						|| ((k % 2 == 1) && (j % 2 == 1))) {
					// DOWN
					fields.get(k + "_" + j).addNeighbour(
							Dir.DOWN,
							fields.get(((rowNumber + k + 2) % rowNumber) + "_"
									+ j));
					// UP
					fields.get(k + "_" + j).addNeighbour(
							Dir.UP,
							fields.get(((rowNumber + k - 2) % rowNumber) + "_"
									+ j));
					// RIGHT_BOTTOM
					fields.get(k + "_" + j).addNeighbour(
							Dir.RIGHT_BOTTOM,
							fields.get(((rowNumber + k + 1) % rowNumber) + "_"
									+ ((columnNumber + j + 1) % columnNumber)));
					// LEFT_TOP
					fields.get(k + "_" + j).addNeighbour(
							Dir.LEFT_TOP,
							fields.get(((rowNumber + k - 1) % rowNumber) + "_"
									+ ((columnNumber + j - 1) % columnNumber)));
					// RIGHT_TOP
					fields.get(k + "_" + j).addNeighbour(
							Dir.RIGHT_TOP,
							fields.get(((rowNumber + k - 1) % rowNumber) + "_"
									+ ((columnNumber + j + 1) % columnNumber)));
					// LEFT_BOTTOM
					fields.get(k + "_" + j).addNeighbour(
							Dir.LEFT_BOTTOM,
							fields.get(((rowNumber + k + 1) % rowNumber) + "_"
									+ ((columnNumber + j - 1) % columnNumber)));

				}
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	public void init(int row, int column) {

		// palya betoltese
		loadLand(row, column);
		// palya felepitese
		buildLand();
		// elemek palyara helyezese
		putItems();
		// leptetes
		Thread thread = new Thread() {
			@Override
			public void run() {
				while (true) {
					move();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		thread.start();

	}

	public Field getField(String id) {
		return fields.get(id);
	}
}
