package land;

import item.Antlion;
import item.Food;
import item.Hill;
import item.Volatile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import movable.Ant;
import movable.Echidna;
import movable.Movable;
import program.SingletonContainer;
import program.SprayPanel;
import view.PaintableView;
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

	private int columnNumber;
	private int rowNumber;

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

		Set<String> keyset = fields.keySet();

		String[] keys = new String[fields.size()];
		int index = 0;
		for (Map.Entry<String, Field> mapEntry : fields.entrySet()) {
			keys[index] = mapEntry.getKey();
			index++;
		}

		ArrayList<Integer> hillPts = new ArrayList<Integer>();
		int hillNr;
		if (rowNumber == 8)
			hillNr = 1;
		else
			hillNr = 2;
		// Boly(ok) hozzaadasa
		for (int i = 0; i < hillNr; i++) {
			Hill h = new Hill();
			h.setView();
			Integer pt = new Random().nextInt(keys.length);
			hillPts.add(pt);
			String[] xy = keys[pt].split("_");
			fields.get(xy[0] + "_" + xy[1]).addItem(h);
		}

		ArrayList<Integer> antlionPoints = new ArrayList<Integer>();
		int antlionNr;
		if (rowNumber == 8)
			antlionNr = 1;
		else if (rowNumber == 10)
			antlionNr = 2;
		else
			antlionNr = 3;
		// Antlion(ok) hozz�ad�sa
		int ap = 0;
		loop: while (ap < antlionNr) {
			Integer pt = new Random().nextInt(keys.length);
			if (hillPts.contains(pt))
				break loop;
			ap++;
			Antlion a = new Antlion();
			a.setView();

			antlionPoints.add(pt);
			String[] xy = keys[pt].split("_");
			fields.get(xy[0] + "_" + xy[1]).addItem(a);
		}

		int antNr;
		if (rowNumber == 10 || rowNumber == 8)
			antNr = 10;
		else
			antNr = 15;

		for (int i = 0; i < antNr; i++) {
			// Hangya(k) hozzaadasa
			Ant a1 = new Ant();
			sc.addMovable(a1);
			a1.setView();
			a1.setDir(Dir.fromInteger((new Random()).nextInt(6)));
			fields.get(keys[hillPts.get(0)]).addItem(a1);
			if (rowNumber >= 10) {
				Ant a2 = new Ant();
				sc.addMovable(a2);
				a2.setView();
				a2.setDir(Dir.fromInteger((new Random()).nextInt(6)));
				fields.get(keys[hillPts.get(1)]).addItem(a2);
			}
		}

		// hangyaszsunok hozzaadasa
		int echidnaNr;
		if (rowNumber == 8)
			echidnaNr = 1;
		else 
			echidnaNr = 2;
		int ep = 0;
		loop: while (ep < echidnaNr) {
			Integer pt = new Random().nextInt(keys.length);
			if (hillPts.contains(pt))
				break loop;
			ep++;
			Echidna e = new Echidna();
			sc.addMovable(e);
			e.setView();
			
			e.setDir(Dir.fromInteger((new Random()).nextInt(6)));
			String[] xy = keys[pt].split("_");
			fields.get(xy[0] + "_" + xy[1]).addItem(e);
		}
		
		// kaja-k hozzaadasa
		int foodNr;
		if (rowNumber == 8)
			foodNr = 4;
		else if (rowNumber == 10)
			foodNr = 6;
		else
			foodNr = 8;
		ArrayList<Integer> foodPts = new ArrayList<Integer>();
		
		int fp = 0;
		loop: while (fp < foodNr) {
			Integer pt = new Random().nextInt(keys.length);
			if (hillPts.contains(pt) || antlionPoints.contains(pt) || foodPts.contains(pt))
				break loop;
			fp++;
			foodPts.add(pt);
			Food f = new Food();
			f.setView();
			
			String[] xy = keys[pt].split("_");
			fields.get(xy[0] + "_" + xy[1]).addItem(f);
		}

		// Pocsolya(k) hozzaadasa
		int pp = 0;
		loop: while (pp < 1) {
			Integer pt = new Random().nextInt(keys.length);
			if (hillPts.contains(pt) || antlionPoints.contains(pt) || foodPts.contains(pt))
				break loop;
			pp++;
			Puddle p = new Puddle();
			p.setView();
			
			String[] xy = keys[pt].split("_");
			fields.get(xy[0] + "_" + xy[1]).addItem(p);
		}

		// Kove(k) hozz�ad�sa
		
		int gravelNr;
		if (rowNumber == 8)
			gravelNr = 3;
		else if (rowNumber == 10)
			gravelNr = 4;
		else
			gravelNr = 6;
		
		int gp = 0;
		loop: while (gp < gravelNr) {
			Integer pt = new Random().nextInt(keys.length);
			if (hillPts.contains(pt))
				break loop;
			gp++;
			Gravel g = new Gravel();
			g.setView();
			
			String[] xy = keys[pt].split("_");
			fields.get(xy[0] + "_" + xy[1]).addItem(g);
		}
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

	private Thread thread = new Thread();

	/**
	 * 
	 * @return
	 */
	public void init(int row, int column) {
		SingletonContainer sc = SingletonContainer.getInstance();
		sc.getVolatiles().clear();
		sc.getMovables().clear();
		SprayPanel.antSmellCapacity = 40;
		SprayPanel.antKillerCapacity = 20;
		SprayPanel.refreshCapacity();
		fields = new HashMap<String, Field>();
		if (thread.isAlive())
			thread.stop();
		PaintableView.panel.paint(PaintableView.panel.getGraphics());

		// palya betoltese
		loadLand(row, column);
		// palya felepitese
		buildLand();
		// elemek palyara helyezese
		putItems();
		// leptetes
		thread = new Thread() {
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
