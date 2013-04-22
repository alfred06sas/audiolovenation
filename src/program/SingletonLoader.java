package program;

import item.Antlion;
import item.Food;
import item.Hill;
import item.Spray;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import land.Field;
import land.Land;
import movable.Ant;
import movable.Echidna;
import smell.AntSmell;
import smell.FoodSmell;
import smell.Smell;
import blockage.Gravel;
import blockage.Puddle;

public class SingletonLoader {
	private static SingletonLoader instance = null;

	public static SingletonLoader Instance() {
		SingletonContainer sc = SingletonContainer.getInstance();
		if (instance == null) {
			instance = new SingletonLoader();
		}
		return instance;
	}

	public void loadTestCase(String inputFileName, String outputFileName)
			throws FileNotFoundException {
		SingletonContainer sc = SingletonContainer.getInstance();
		Singleton s = Singleton.Instance();
		Land land = new Land();
		BufferedReader br = new BufferedReader(new FileReader(inputFileName));
		try {
			while (true) {
				String line = br.readLine();
				if (line == null)
					break;
				String[] sline = line.split(" ");

				if (sline[0].equals("create_land") && sline[1].equals("-r")
						&& sline[3].equals("-c") && sline.length == 5) {
					land.loadLand(Integer.parseInt(sline[2]),
							Integer.parseInt(sline[4]));
					land.buildLand(Integer.parseInt(sline[2]),
							Integer.parseInt(sline[4]));
				} else if (sline[0].equals("put_item") && sline[1].equals("-t")
						&& sline[3].equals("-iid") && sline[5].equals("-fid")
						&& sline.length == 7) {
					String t = sline[2];
					String iid = sline[4];
					String fid = sline[6];

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

				} else if (sline[0].equals("put_smell")
						&& sline[1].equals("-t") && sline[3].equals("-sid")
						&& sline[5].equals("-s") && sline[7].equals("-fid")
						&& sline.length == 9) {
					String type = sline[2];
					String smell_id = sline[4];
					String strength = sline[6];
					String field_id = sline[8];
					
					Smell smell = null;
					
					if (type.equals("antsmell"))
						smell = new AntSmell();
					else if (type.equals("foodsmell"))
						smell = new FoodSmell();
					else {
						System.out.println("Undefined type: " + type + " at command: " + line);
						return;
					}
					
					Field field = new Field(field_id);
					field.addSmell(smell);
					smell.setActualField(field);
					
					
				} else if (sline[0].equals("set") && sline[1].equals("-t")
						&& sline[3].equals("-mid") && sline[5].equals("-d")
						&& sline[7].equals("-s") && sline.length == 9) {
					System.out.println(line);
				} else if (sline[0].equals("use_spray")
						&& sline[1].equals("-t") && sline[3].equals("-fid")
						&& sline.length == 5) {
					System.out.println(line);
				} else if (sline[0].equals("step_round")
						&& sline[1].equals("-rn") && sline.length == 3) {
					s.printNextRound();
					land.move();
				} else {
					System.err.println("Undefined command: " + line);
					return;
				}
			}
		} catch (IOException e) {

		}
	}
}
