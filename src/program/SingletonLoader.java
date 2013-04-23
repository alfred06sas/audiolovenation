package program;

import item.Antlion;
import item.Food;
import item.Hill;
import item.Item;
import item.Spray;
import item.Volatile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import land.Dir;
import land.Field;
import land.Land;
import movable.Ant;
import movable.Echidna;
import movable.Movable;
import smell.AntSmell;
import smell.FoodSmell;
import smell.Smell;
import blockage.Gravel;
import blockage.Puddle;

/**
 * 
 * @author audiolovenation
 * 
 *         A fajlok beolvasasara és kimeneti fajlbairas celjara letrehozott
 *         segedosztaly. Ez ertelmezi a parancsokat.
 * 
 */
public class SingletonLoader {
	// singleton peldany sajat magarol
	private static SingletonLoader instance = null;
	// SingletonContainer peldany a volatile/movable stb eleresere
	private SingletonContainer sc = SingletonContainer.getInstance();
	// Singleton peldany a kimenet generalasara
	private Singleton s = Singleton.Instance();
	private String line = null;
	// eredmeny string, amit a parancsok lefuttatasa utan kiirun file-ba
	private String result = new String();
	// palya peldany
	private Land land = new Land();

	/**
	 * Peldany visszakapasa
	 * 
	 * @return singleton peldany
	 */
	public static SingletonLoader Instance() {
		SingletonContainer sc = SingletonContainer.getInstance();
		if (instance == null) {
			instance = new SingletonLoader();
		}
		return instance;
	}

	/**
	 * Egy parancs eredmenyenek hozzafuzese az eredmenystring-hez
	 * 
	 * @param r
	 *            vegrehajtas eredmenye
	 */
	public void concatToResult(String r) {
		this.result += r + "\n";
	}

	/**
	 * Parancsok ertelmezese
	 * 
	 * @param inputFile
	 *            bemeneti file
	 * @param outputFile
	 *            kimeneti file
	 * @throws FileNotFoundException
	 *             ha a kimeneti file nem letezik
	 */
	public void loadTestCase(File inputFile, File outputFile)
			throws FileNotFoundException {
		BufferedReader br = null;
		PrintWriter pw;
		try {
			// a bemeneti file-ban kapott parancsokon megy vegig
			while (true) {
				br = new BufferedReader(new FileReader(inputFile));
				line = br.readLine();
				if (line == null)
					break;
				String[] sline = line.split(" "); // parancsok feldarabolasa
													// whitespace karakter
													// szerint

				// a palya letrehozasa
				if (sline[0].equals("create_land") && sline[1].equals("-r")
						&& sline[3].equals("-c") && sline.length == 5) {
					String row = sline[2];
					String column = sline[4];

					createLand(row, column);

					// item elhelyezese a palyan
				} else if (sline[0].equals("put_item") && sline[1].equals("-t")
						&& sline[3].equals("-iid") && sline[5].equals("-fid")
						&& sline.length == 7) {
					String t = sline[2];
					String iid = sline[4];
					String fid = sline[6];

					putItem(t, iid, fid);

					// szag elhelyezese a palyan
				} else if (sline[0].equals("put_smell")
						&& sline[1].equals("-t") && sline[3].equals("-sid")
						&& sline[5].equals("-s") && sline[7].equals("-fid")
						&& sline.length == 9) {
					String type = sline[2];
					String smell_id = sline[4];
					String strength = sline[6];
					String field_id = sline[8];

					putSmell(type, smell_id, strength, field_id);

					// A hangya/hangyasz kulonbozo allapotainak beallitasa
				} else if (sline[0].equals("set") && sline[1].equals("-t")
						&& sline[3].equals("-mid") && sline[5].equals("-d")
						&& sline[7].equals("-s") && sline.length == 9) {
					String type = sline[2];
					String movable_id = sline[4];
					String dir = sline[6];
					String state = sline[8];

					set(type, movable_id, dir, state);

					// spray hasznalata
				} else if (sline[0].equals("use_spray")
						&& sline[1].equals("-t") && sline[3].equals("-sid")
						&& sline[5].equals("-fid") && sline.length == 7) {

					String type = sline[2];
					String spray_id = sline[4];
					String fid = sline[6];

					useSpray(type, spray_id, fid);

					// kor leptetese
				} else if (sline[0].equals("step_round")
						&& sline[1].equals("-rn") && sline.length == 3) {

					int round = Integer.valueOf(sline[2]);
					for (int i = 0; i < round; i++) {
						s.printNextRound();
						land.move();
					}

					// a parancsot nem sikerult ertelmezni
				} else {
					System.err.println("Undefined command: " + line);
					return;
				}
			}

			// ha a result nem ures, kiirjuk file-ba
			if (result.length() != 0) {
				pw = new PrintWriter(new FileWriter(outputFile));
				pw.write(result);
				pw.close();
			}
			// result tartalmanak torlese
			result = new String();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Palya keszitese
	 * 
	 * @param r
	 *            sorok szama
	 * @param c
	 *            oszlopok szama
	 */
	public void createLand(String r, String c) {
		land = new Land();
		s.clear();
		sc.clear();
		land.loadLand(Integer.parseInt(r), Integer.parseInt(c));
		land.buildLand();
	}

	/**
	 * Elem elhelyezese a palyan
	 * 
	 * @param t
	 *            tipus
	 * @param iid
	 *            elem azonositoja
	 * @param fid
	 *            field azonositoja
	 */
	public void putItem(String t, String iid, String fid) {
		if (t.equals("ant")) {
			Ant ant = new Ant(iid);
			sc.addMovable(ant);
			s.addItem(ant);
			land.putItems(land.getField(fid), ant);
		} else if (t.equals("echidna")) {
			Echidna echidna = new Echidna(iid);
			sc.addMovable(echidna);
			s.addItem(echidna);
			land.putItems(land.getField(fid), echidna);
		} else if (t.equals("antlion")) {
			Antlion antlion = new Antlion(iid);
			s.addItem(antlion);
			land.putItems(land.getField(fid), antlion);
		} else if (t.equals("puddle")) {
			Puddle puddle = new Puddle(iid);
			s.addItem(puddle);
			land.putItems(land.getField(fid), puddle);
		} else if (t.equals("gravel")) {
			Gravel gravel = new Gravel(iid);
			s.addItem(gravel);
			land.putItems(land.getField(fid), gravel);
		} else if (t.equals("hill")) {
			Hill hill = new Hill(iid);
			s.addItem(hill);
			land.putItems(land.getField(fid), hill);
		} else if (t.equals("spray")) {
			Spray al = new Spray(iid);
			s.addItem(al);
			land.putItems(land.getField(fid), al);
		} else if (t.equals("food")) {
			Food food = new Food(iid);
			s.addItem(food);
			land.putItems(land.getField(fid), food);
		}
	}

	/**
	 * Szag elhelyezese a palyan
	 * 
	 * @param type
	 *            szag tipus
	 * @param smell_id
	 *            szag id
	 * @param strength
	 *            erosseg
	 * @param field_id
	 *            field id
	 */
	public void putSmell(String type, String smell_id, String strength,
			String field_id) {
		SingletonContainer sc = SingletonContainer.getInstance();
		Smell smell = null;

		if (type.equals("antsmell")) {
			smell = new AntSmell();
			smell.setId(smell_id);
			sc.addVolatile((Volatile) smell);
		} else if (type.equals("foodsmell")) {
			smell = new FoodSmell();
			smell.setId(smell_id);
		} else {
			System.out.println("Undefined type: " + type + " at command: "
					+ line);
			return;
		}
		Field field = land.getField(field_id);
		field.addSmell(smell);
		smell.setActualField(field);

		smell.setStrength(Integer.valueOf(strength));
		smell.setActualField(field);
	}

	/**
	 * Hangya/hangyaszsun allapotanak/iranyanak beallitasa
	 * 
	 * @param type
	 *            tipus (ant, echidna)
	 * @param movable_id
	 *            azonosito
	 * @param dir
	 *            irany
	 * @param state
	 *            allapot (hangya és hangyasz eseten kulonbozo)
	 */
	private void set(String type, String movable_id, String dir, String state) {
		List<Movable> movables = sc.getMovables();
		// hangya tipus eseten
		if (type.equals("ant")) {
			Ant ant = null;
			// a hangya lejerdezese
			loop: for (Movable m : movables) {
				Item i = (Item) m;
				if (i.getId().equals("a" + movable_id)) {
					ant = (Ant) m;
					break loop;
				}
			}
			if (ant == null) {
				System.out.println("Ant(" + movable_id
						+ ") does not exist at command: " + line);
				return;
			}

			// dir beallitasa
			if (dir.equals("up")) {
				ant.setDir(Dir.UP);
			} else if (dir.equals("down")) {
				ant.setDir(Dir.DOWN);
			} else if (dir.equals("left_bottom")) {
				ant.setDir(Dir.LEFT_BOTTOM);
			} else if (dir.equals("right_bottom")) {
				ant.setDir(Dir.RIGHT_BOTTOM);
			} else if (dir.equals("left_top")) {
				ant.setDir(Dir.LEFT_TOP);
			} else if (dir.equals("right_top")) {
				ant.setDir(Dir.RIGHT_TOP);
			} else {
				System.out.println("Incorrect direction at command: " + line);
				return;
			}

			// state vizsgalata
			if (state.equals("wait")) {
				ant.setWait(10);
			} else if (state.equals("have_food")) {
				ant.setHaveFood(true);
			} else if (state.equals("dont_have_food")) {
				ant.setHaveFood(false);
			} else if (state.equals("killed")) {
				ant.setKilled(true);
			} else {
				System.out.println("Incorrect state at command: " + line);
				return;
			}
			s.setState(ant);
			// hangyasz tipus eseten
		} else if (type.equals("echidna")) {
			Echidna echidna = null;
			// a hangyasz lekerdezese
			loop: for (Movable m : movables) {
				Item i = (Item) m;
				if (i.getId().equals("e" + movable_id)) {
					echidna = (Echidna) m;
					break loop;
				}
			}

			if (echidna == null) {
				System.out.println("Echidna(" + movable_id
						+ ") does not exist at command: " + line);
				return;
			}

			// dir beallitasa
			if (dir.equals("up")) {
				echidna.setDir(Dir.UP);
			} else if (dir.equals("down")) {
				echidna.setDir(Dir.DOWN);
			} else if (dir.equals("left_bottom")) {
				echidna.setDir(Dir.LEFT_BOTTOM);
			} else if (dir.equals("right_bottom")) {
				echidna.setDir(Dir.RIGHT_BOTTOM);
			} else if (dir.equals("left_top")) {
				echidna.setDir(Dir.LEFT_TOP);
			} else if (dir.equals("right_top")) {
				echidna.setDir(Dir.RIGHT_TOP);
			} else {
				System.out.println("Incorrect direction at command: " + line);
				return;
			}

			// state vizsgalata
			if (state.equals("wait")) {
				echidna.setWait(10);
			} else if (state.equals("hungry")) {
				echidna.setHungry(true);
			} else if (state.equals("not_hungry")) {
				echidna.setHungry(false);
			} else {
				System.out.println("Incorrect state at command: " + line);
				return;
			}
			s.setState(echidna);
		} else { // rossz tipus megadasa eseten
			System.out
					.println("Only echidna and ant types are allowed at command: "
							+ line);
			return;
		}
	}

	/**
	 * Spray hasznalata
	 * 
	 * @param type
	 *            spray tipusa (ant_smell, ant_killer)
	 * @param spray_id
	 *            spray azonosito
	 * @param fid
	 *            field azonosito
	 */
	private void useSpray(String type, String spray_id, String fid) {
		Field field = land.getField(fid);

		if (type.equals("ant_smell"))
			field.removeAntSmells();
		else if (type.equals("ant_killer"))
			field.onClick(spray_id);
		else {
			System.out.println("Undefined type at command: " + line);
			return;
		}
	}
}
