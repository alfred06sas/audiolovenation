package program;

import item.Antlion;
import item.Food;
import item.Hill;
import item.Spray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import land.Field;
import land.Land;
import movable.Ant;
import blockage.Blockage;

public class Main {

	public static void main(String[] args) {

		String[] seqNames = new String[] { "Init", "AddItemsToFields", "AntMove", "ScanAntMove", "AntToAntlion", "AntToEchidna",
				"AnttoFood", "Anttohill", "AnttoBlockage", "AnttoSpray", "EchidnaMove", "EchidnatoAnt", "SpraytoAnt", "UseCaseSpray",
				"MoveLand", "UseCaseSmell" };
		// 1. Init ---------------------------------------------------------------------------------> KÉSZ
		// 2. AddItemsToFields: Land, Field, Item --------------------------------------------------> KÉSZ
		// 3. AntMove: Land, Field, Ant, Smell, item, tentacle, AntSmell, SingletonContainer
		// 4. ScanAntMove: Ant, Field, AntSmell, Smell, Item, Tentacle
		// 5. AntToAntlion: Ant, Antlion, Field, SC ------------------------------------------------> KÉSZ
		// 6. AntToEchidna: Ant, Echidna, SC
		// 7. AnttoFood: Ant, Food, Smell, Field ---------------------------------------------------> KÉSZ
		// 8. AnttoHill: Ant, Hill -----------------------------------------------------------------> KÉSZ
		// 9. AnttoBlockage: Ant, Blockage, tentacle -----------------------------------------------> KÉSZ
		// 10. AnttoSpray: Ant, Spray --------------------------------------------------------------> KÉSZ
		// 11. EchidnaMove: Land, Echidna, Item, Field
		// 12. EchidnatoAnt: Echidna, Ant, Field, SC
		// 13. SpraytoAnt: Spray, Ant --------------------------------------------------------------> KÉSZ
		// 14. useCaseSpray: Actor, Field, Spray, Item, SC -----------------------------------------> KÉSZ
		// 15. MoveLand: Land, Movable, Volatile, SC -----------------------------------------------> KÉSZ
		// 16. UseCaseSmell: Actor, Field, Smell, SC -----------------------------------------------> KÉSZ
		
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
					System.err.println("Adj meg egy számot 1 és 16 között! Próbáld újra!");
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
					s.stack.remove(s.stack.size()-1);
					break;
				case 2:
					System.out.println(seqNames[1]);
					s.stack.add(1);
					land = s.land.get(0);
					land.putItems();
					s.stack.remove(s.stack.size()-1);
					break;
				case 5:
					System.out.println(seqNames[4]);
					s.stack.add(1);
					antlion = s.antlions.get(2);
					antlion.collisionWithAnt((Ant)s.items.get(3), false);
					s.stack.remove(s.stack.size()-1);
					
					System.out.println("----------------------------");
					
					s.stack.add(1);
					antlion = s.antlions.get(2);
					antlion.collisionWithAnt((Ant)s.items.get(3), true);
					s.stack.remove(s.stack.size()-1);
					break;
				case 7:
					System.out.println(seqNames[6]);
					s.stack.add(20);
					food = s.foods.get(19);
					food.collisionWithAnt((Ant)s.items.get(4), false);
					s.stack.remove(s.stack.size()-1);
					
					System.out.println("----------------------------");
					
					s.stack.add(20);
					food = s.foods.get(19);
					food.collisionWithAnt((Ant)s.items.get(4), true);
					s.stack.remove(s.stack.size()-1);
					break;
				case 8:
					System.out.println(seqNames[7]);
					s.stack.add(1);
					hill = s.hill.get(0);
					hill.collisionWithAnt(s.ants.get(3), false);
					s.stack.remove(s.stack.size()-1);
					break;
				case 9:
					System.out.println(seqNames[8]);
					s.stack.add(1);
					blockage = s.blockages.get(2);
					blockage.collisionWithAnt(s.ants.get(15), false);
					s.stack.remove(s.stack.size()-1);
					
					System.out.println("----------------------------");
					
					s.stack.add(1);
					blockage = s.blockages.get(2);
					blockage.collisionWithAnt(s.ants.get(15), true);
					s.stack.remove(s.stack.size()-1);
					break;
				case 10:
					System.out.println(seqNames[9]);
					s.stack.add(10);
					spray = s.sprays.get(9);
					spray.collisionWithAnt(s.ants.get(16), false);
					s.stack.remove(s.stack.size()-1);
					
					System.out.println("----------------------------");
					
					s.stack.add(10);
					spray = s.sprays.get(9);
					spray.collisionWithAnt(s.ants.get(16), true);
					s.stack.remove(s.stack.size()-1);
					break;
				case 14:
					System.out.println(seqNames[13]);
					s.stack.add(11);
					field = s.fields.get(10);
					field.onClick();
					s.stack.remove(s.stack.size()-1);
					break;
				case 15:
					System.out.println(seqNames[14]);
					s.stack.add(1);
					land = s.land.get(0);
					land.move();
					s.stack.remove(s.stack.size()-1);
					break;
				case 16:
					System.out.println(seqNames[15]);
					s.stack.add(11);
					field = s.fields.get(10);
					field.removeAntSmells();
					s.stack.remove(s.stack.size()-1);
					break;
				default:
					System.out.println("Hibas kód!");
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
