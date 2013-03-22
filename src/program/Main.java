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
		// AddItemsToFields: Land, Field, Item
		// AntMove: Land, Field, Ant, Smell, item, tentacle, AntSmell, SingletonContainer
		// ScanAntMove: Ant, Field, AntSmell, Smell, Item, Tentacle
		// AntToAntlion: Ant, Antlion, Field, SC
		// AntToEchidna: Ant, Echidna, SC
		// AnttoFood: Ant, Food, Smell, Field
		// AnttoHill: Ant, Hill
		// AnttoBlockage: Ant, Blockage, tentacle
		// AnttoSpray: Ant, Spray
		// EchidnaMove: Land, Echidna, Item, Field
		// EchidnatoAnt: Echidna, Ant, Field, SC
		// SpraytoAnt: Spray, Ant
		// useCaseSpray: Actor, Field, Spray, Item, SC
		// MoveLand: Land, Movable, Volatile, SC
		// UseCaseSmell: Actor, Field, Smell, SC
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
					System.out.println("Adj meg egy számot!");
					System.out.println("-----------------------------");
					continue;
				}
				if (nr == null)
					break;

				switch (nr) {
				case 1:
					System.out.println(seqNames[1]);
					s.stack.add(1);
					s.land.get(0).init();
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
