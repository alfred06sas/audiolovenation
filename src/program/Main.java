package program;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Main {

	public static void main(String[] args) {

		String[] seqNames = new String[] { "Init", "AddItemsToFields", "AntMove", "ScanAntMove", "AntToAntlion", "AntToEchidna",
				"AnttoFood", "Anttohill", "AnttoBlockage", "AnttoSpray", "EchidnaMove", "EchidnatoAnt", "SpraytoAnt", "UseCaseSpray",
				"MoveLand", "UseCaseSmell" };
		// 1. Init
		// 2. AddItemsToFields: Land, Field, Item
		// 3. AntMove: Land, Field, Ant, Smell, item, tentacle, AntSmell, SingletonContainer
		// 4. ScanAntMove: Ant, Field, AntSmell, Smell, Item, Tentacle
		// 5. AntToAntlion: Ant, Antlion, Field, SC
		// 6. AntToEchidna: Ant, Echidna, SC
		// 7. AnttoFood: Ant, Food, Smell, Field
		// 8. AnttoHill: Ant, Hill -----------> k�sz
		// 9. AnttoBlockage: Ant, Blockage, tentacle
		// 10. AnttoSpray: Ant, Spray
		// 11. EchidnaMove: Land, Echidna, Item, Field
		// 12. EchidnatoAnt: Echidna, Ant, Field, SC
		// 13. SpraytoAnt: Spray, Ant
		// 14. useCaseSpray: Actor, Field, Spray, Item, SC
		// 15. MoveLand: Land, Movable, Volatile, SC
		// 16. UseCaseSmell: Actor, Field, Smell, SC
		Singleton s = Singleton.Instance();
		s.initItems();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

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
					System.out.println("Adj meg egy sz�mot!");
					System.out.println("-----------------------------");
					continue;
				}
				if (nr == null)
					break;

				switch (nr) {
				case 1:
					System.out.println(seqNames[0]);
					s.stack.add(1);
					s.land.get(0).init();
					s.stack.remove(s.stack.size()-1);
					break;
				case 8:
					System.out.println(seqNames[7]);
					s.stack.add(1);
					s.hill.get(0).collisionWithAnt(s.ants.get(3), false);
					s.stack.remove(s.stack.size()-1);
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