package program;

import item.Antlion;
import item.Food;
import item.Hill;
import item.Item;
import item.Spray;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

public class SingletonLoader {
	private static SingletonLoader instance = null;
	private SingletonContainer sc = SingletonContainer.getInstance();
	private Singleton s = Singleton.Instance();
	private String line = null;
	private Land land = new Land();

	public static SingletonLoader Instance() {
		SingletonContainer sc = SingletonContainer.getInstance();
		if (instance == null) {
			instance = new SingletonLoader();
		}
		return instance;
	}

	public void loadTestCase(File inputFile, String outputFileName)
			throws FileNotFoundException {
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		try {
			while (true) {
				line = br.readLine();
				if (line == null)
					break;
				String[] sline = line.split(" ");
				
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
						&& sline[1].equals("-t") && sline[3].equals("-fid")
						&& sline.length == 5) {
				
				// kor leptetese	
				} else if (sline[0].equals("step_round")
						&& sline[1].equals("-rn") && sline.length == 3) {
					
					int round = Integer.valueOf(sline[2]);
					for (int i = 0; i < round; i++) {
						land.move();
						s.printNextRound();
					}
				
				// a parancsot nem sikerult ertelmezni
				} else {
					System.err.println("Undefined command: " + line);
					return;
				}
			}
			br.close();
		} catch (IOException e) {
 
		}
	}

	public void createLand(String r, String c) {
		land = new Land();
		s.clear();
		sc.clear();
		land.loadLand(Integer.parseInt(r), Integer.parseInt(c));
		land.buildLand(Integer.parseInt(r), Integer.parseInt(c));
	}

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
		} else if (t.equals("antlion")) {
			Spray spray = new Spray(iid);
			s.addItem(spray);
			land.putItems(land.getField(fid), spray);
		} else if (t.equals("food")) {
			Food food = new Food(iid);
			s.addItem(food);
			land.putItems(land.getField(fid), food);
		}
	}

	public void putSmell(String type, String smell_id, String strength,
			String field_id) {
		Smell smell = null;

		if (type.equals("antsmell"))
			smell = new AntSmell();
		else if (type.equals("foodsmell"))
			smell = new FoodSmell();
		else {
			System.out.println("Undefined type: " + type + " at command: "
					+ line);
			return;
		}

		Field field = new Field(field_id);
		field.addSmell(smell);
		smell.setActualField(field);
	}

	private void set(String type, String movable_id, String dir, String state) {
		List<Movable> movables = sc.getMovables();
		if (type.equals("ant")) {
			Ant ant = null;
			loop: for (Movable m : movables) {
				Item i = (Item) m;
				if (i.getId().equals("a"+movable_id)) {
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

		} else if (type.equals("echidna")) {
			Echidna echidna = null;
			loop: for (Movable m : movables) {
				Item i = (Item) m;
				if (i.getId().equals("e"+movable_id)) {
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
		} else {
			System.out
					.println("Only echidna and ant types are allowed at command: "
							+ line);
			return;
		}
	}
}
