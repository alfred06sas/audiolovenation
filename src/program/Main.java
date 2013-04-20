package program;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import land.Land;

/**
 * 
 * @author audiolovenation
 * 
 *         Main
 * 
 */
public class Main {

	public static void main(String[] args) {

		Singleton s = Singleton.Instance();
		Land land = new Land();

		String[] testNames = new String[] { "test", "exit" };

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			while (true) {
				System.out
						.println("Type 1 if you want to load commands from file, or type 2 if you want to load predefined test cases!");
				Integer nr = null;

				try {
					nr = Integer.valueOf(br.readLine());
				} catch (NumberFormatException nfe) {
					System.err.println("Error");
					continue;
				}

				if (nr == null)
					break;
				System.out
						.println("---------------------------------------------");
				switch (nr) {
				case 1:
					System.out.print("Input file: ");
					String inputFileName = null;

					try {
						inputFileName = br.readLine();
					} catch (Exception e) {
						e.printStackTrace();
						break;
					}

					//System.out.println();
					System.out.print("Output file: ");
					String outputFileName = null;
					try {
						outputFileName = br.readLine();
					} catch (Exception e) {
						e.printStackTrace();
						break;
					}

					File file = new File(outputFileName);
					if (file.exists()) {
						System.out
								.println("\nOutput file already exists! Try again!");
						break;
					}

					try {
						s.loadTestCase(inputFileName, outputFileName);
					} catch (FileNotFoundException e) {
						System.out.println("No such a file! Try again!");
						break;
					}
					break;
				case 2:
					loop: while (true) {
						for (int i = 0; i < testNames.length; i++) {
							System.out.println(i + 1 + ". " + testNames[i]);
						}
						System.out.println();
						System.out
								.println("Type the number of the test which you want to run!");
						try {
							nr = Integer.valueOf(br.readLine());
						} catch (NumberFormatException nfe) {
							System.err.println("Error");
							continue;
						}
						if (nr == null)
							break;

						switch (nr) {
						case 1:

							break;
						case 2:
							break loop;
						default:
							/** Hibas bemenet lekezelese */
							System.out.println("Hibas kod!");
							break;
						}
					}
					break;
				default:
					/** Hibas bemenet lekezelese */
					System.out.println("Hibas kod!");
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
//		land.loadLand();
//		land.buildLand();		
	}

}
