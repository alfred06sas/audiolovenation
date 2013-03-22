package program;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Main {

	public static void main(String[] args) {

		String[] seqNames = new String[] { "AnttoAnt", "Init" };
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
					System.out.println(seqNames[0] + ":");
					s.stack.add(5);
					s.ants.get(5).collisionWithAnt(s.ants.get(4), true);
					
					break;
				case 2:
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
