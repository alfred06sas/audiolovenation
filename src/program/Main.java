package program;

import item.Antlion;
import item.Food;
import item.Hill;
import item.Spray;
import item.Tentacle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import land.Field;
import land.Land;
import movable.Ant;
import movable.Echidna;
import blockage.Blockage;

public class Main {

	public static void main(String[] args) {

		String[] seqNames = new String[] { "Init", "AddItemsToFields",
				"AntMove - EchidnaMove", "ScanAntMove", "AntToAntlion",
				"AntToEchidna", "AnttoFood", "AnttoHill", "AnttoBlockage",
				"AnttoSpray", "EchidnatoAnt", "UseCaseSpray", "MoveLand",
				"UseCaseSmell" };

		Singleton s = Singleton.Instance();
		s.initItems();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Land land = null;
		Hill hill = null;
		Blockage blockage = null;
		Spray spray = null;
		Antlion antlion = null;
		Food food = null;
		Field field = null;
		Echidna echidna = null;
		Ant ant = null;
		Tentacle tentacle = null;

		try {
			while (true) {
				for (int i = 0; i < seqNames.length; i++)
					System.out.println(i + 1 + ". " + seqNames[i]);

				System.out.println();
				System.out.println("Which sequence do you want to run?");
				Integer nr;
				try {
					nr = Integer.valueOf(br.readLine());
				} catch (NumberFormatException nfe) {
					System.err
							.println("Adj meg egy sz�mot 1 �s 16 k�z�tt! Pr�b�ld �jra!");
					continue;
				}
				if (nr == null)
					break;

				switch (nr) {
				case 1:
					System.out.println(seqNames[0]);
					s.stack.add(1);
					land = s.land.get(0);
					land.init();
					s.stack.remove(s.stack.size() - 1);
					break;
				case 2:
					System.out.println(seqNames[1]);
					s.stack.add(1);
					land = s.land.get(0);
					land.putItems();
					s.stack.remove(s.stack.size() - 1);
					break;
				case 3:
					System.out.println(seqNames[2]);
					s.stack.add(1);
					land = s.land.get(0);
					land.move();
					s.stack.remove(s.stack.size() - 1);
					break;
				case 4:
					System.out.println(seqNames[3]);
					s.stack.add(2);
					tentacle = s.tentacles.get(7);
					tentacle.scan(true);
					s.stack.remove(s.stack.size() - 1);
					break;
				case 5:
					System.out.println(seqNames[4]);
					s.stack.add(1);
					antlion = s.antlions.get(2);
					antlion.collisionWithAnt((Ant) s.items.get(3), false);
					s.stack.remove(s.stack.size() - 1);

					System.out.println("----------------------------");

					s.stack.add(1);
					antlion = s.antlions.get(2);
					antlion.collisionWithAnt((Ant) s.items.get(3), true);
					s.stack.remove(s.stack.size() - 1);
					break;
				case 6:
					System.out.println(seqNames[5]);
					s.stack.add(7);
					echidna = (Echidna) s.items.get(6);
					echidna.collisionWithAnt((Ant) s.items.get(3), false);
					s.stack.remove(s.stack.size() - 1);

					System.out.println("----------------------------");

					s.stack.add(7);
					echidna = (Echidna) s.items.get(6);
					echidna.collisionWithAnt((Ant) s.items.get(3), true);
					s.stack.remove(s.stack.size() - 1);
					break;
				case 7:
					System.out.println(seqNames[6]);
					s.stack.add(20);
					food = s.foods.get(19);
					food.collisionWithAnt((Ant) s.items.get(4), false);
					s.stack.remove(s.stack.size() - 1);

					System.out.println("----------------------------");

					s.stack.add(20);
					food = s.foods.get(19);
					food.collisionWithAnt((Ant) s.items.get(4), true);
					s.stack.remove(s.stack.size() - 1);
					break;
				case 8:
					System.out.println(seqNames[7]);
					s.stack.add(1);
					hill = s.hill.get(0);
					hill.collisionWithAnt(s.ants.get(3), false);
					s.stack.remove(s.stack.size() - 1);
					break;
				case 9:
					System.out.println(seqNames[8]);
					s.stack.add(1);
					blockage = s.blockages.get(2);
					blockage.collisionWithAnt(s.ants.get(15), false);
					s.stack.remove(s.stack.size() - 1);

					System.out.println("----------------------------");

					s.stack.add(1);
					blockage = s.blockages.get(2);
					blockage.collisionWithAnt(s.ants.get(15), true);
					s.stack.remove(s.stack.size() - 1);
					break;
				case 10:
					System.out.println(seqNames[9]);
					s.stack.add(10);
					spray = s.sprays.get(9);
					spray.collisionWithAnt(s.ants.get(16), false);
					s.stack.remove(s.stack.size() - 1);

					System.out.println("----------------------------");

					s.stack.add(10);
					spray = s.sprays.get(9);
					spray.collisionWithAnt(s.ants.get(16), true);
					s.stack.remove(s.stack.size() - 1);
					break;
				case 11:
					System.out.println(seqNames[10]);
					s.stack.add(3);
					ant = (Ant) s.items.get(2);
					echidna = (Echidna) s.items.get(8);
					ant.collisionWithEchidna(echidna);
					s.stack.remove(s.stack.size() - 1);
					break;
				case 12:
					System.out.println(seqNames[11]);
					s.stack.add(11);
					field = s.fields.get(10);
					field.onClick();
					s.stack.remove(s.stack.size() - 1);
					break;
				case 13:
					System.out.println(seqNames[12]);
					s.stack.add(1);
					land = s.land.get(0);
					land.move();
					s.stack.remove(s.stack.size() - 1);
					break;
				case 14:
					System.out.println(seqNames[13]);
					s.stack.add(11);
					field = s.fields.get(10);
					field.removeAntSmells();
					s.stack.remove(s.stack.size() - 1);
					break;
				default:
					System.out.println("Hibas k�d!");
					break;
				}
				System.out
						.println("---------------------------------------------");

			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
