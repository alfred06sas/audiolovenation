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

		SingletonLoader sl = SingletonLoader.Instance();
		String[] testNames = new String[15];
		
		testNames[0] = "Tracking ant's smell";
		testNames[1] = "Tracking food's smell";
		testNames[2] = "Moving ant on an empty land";
		testNames[3] = "Ant's collision with an echidna";
		testNames[4] = "Ant's collision with an antlion";
		testNames[5] = "Ant's collision with a puddle or gravel";
		testNames[6] = "Ant's collision with food, afterwards with a hill";
		testNames[7] = "Spraying a field with ant killer spray";
		testNames[8] = "Ant killer spray's collision with an ant";
		testNames[9] = "Echidna's collision with an ant";
		testNames[10] = "Echidna's collision with a gravel";
		testNames[11] = "Echidna's collision with two gravels";
		testNames[12] = "Echidna's collision with three gravels";
		testNames[13] = "Using ant smell neutralizer spray";
		testNames[14] = "EXIT";

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			while (true) {
				System.out
						.println("Type 1 if you want to load commands from file, or type 2 if you want to load predefined test cases!");
				Integer nr = null;
				File inputFile = null;
				String inputFileName = null;
				String outputFileName = null;

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

					try {
						inputFileName = br.readLine();
					} catch (Exception e) {
						e.printStackTrace();
						break;
					}

					//System.out.println();
					System.out.print("Output file: ");
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
					inputFile = new File("commands\\customTestCases\\"+inputFileName);
					try {
						sl.loadTestCase(inputFile, outputFileName);
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
							sl.loadTestCase(new File("commands\\preDefinedTestCases\\input1.dat"), "output1.dat");
							break;
						case 2:
							sl.loadTestCase(new File("commands\\preDefinedTestCases\\input2.dat"), "output2.dat");
							break;
						case 3:
							sl.loadTestCase(new File("commands\\preDefinedTestCases\\input3.dat"), "output3.dat");
							break;
						case 4:
							sl.loadTestCase(new File("commands\\preDefinedTestCases\\input4.dat"), "output4.dat");
							break;
						case 5:
							sl.loadTestCase(new File("commands\\preDefinedTestCases\\input5.dat"), "output5.dat");
							break;
						case 6:
							sl.loadTestCase(new File("commands\\preDefinedTestCases\\input6.dat"), "output6.dat");
							break;
						case 7:
							sl.loadTestCase(new File("commands\\preDefinedTestCases\\input7.dat"), "output7.dat");
							break;
						case 8:
							sl.loadTestCase(new File("commands\\preDefinedTestCases\\input8.dat"), "output8.dat");
							break;
						case 9:
							sl.loadTestCase(new File("commands\\preDefinedTestCases\\input9.dat"), "output9.dat");
							break;
						case 10:
							sl.loadTestCase(new File("commands\\preDefinedTestCases\\input10.dat"), "output10.dat");
							break;
						case 11:
							sl.loadTestCase(new File("commands\\preDefinedTestCases\\input11.dat"), "output11.dat");
							break;
						case 12:
							sl.loadTestCase(new File("commands\\preDefinedTestCases\\input12.dat"), "output12.dat");
							break;
						case 13:
							sl.loadTestCase(new File("commands\\preDefinedTestCases\\input13.dat"), "output13.dat");
							break;
						case 14:
							sl.loadTestCase(new File("commands\\preDefinedTestCases\\input14.dat"), "output14.dat");
							break;
						case 15:
							break;
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
