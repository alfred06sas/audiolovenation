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
		String path = null;
		try {
			while (true) {
				System.out
						.println("Type the absolute path of the commands directory (ex C:\\Users\\audiolovenation\\commands\\)!");

				try {
					path = String.valueOf(br.readLine());
				} catch (NumberFormatException nfe) {
					System.err.println("Error");
					continue;
				}
				break;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (true) {

				Integer nr = null;
				File inputFile = null;
				File outputFile = null;

				String inputFileName = null;
				String outputFileName = null;

				System.out
						.println("Type 1 if you want to load commands from file, or type 2 if you want to load predefined test cases!");

				try {
					nr = Integer.valueOf(br.readLine());
				} catch (NumberFormatException nfe) {
					System.err.println("Error");
					continue;
				}

				System.out.println(path);
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

					System.out.print("Output file: ");
					try {
						outputFileName = br.readLine();
					} catch (Exception e) {
						e.printStackTrace();
						break;
					}

					outputFile = new File(outputFileName);
					inputFile = new File("commands\\customTestCases\\"
							+ inputFileName);
					// inputFile = new File(path + "customTestCases\\"
					// + inputFileName);

					try {
						sl.loadTestCase(inputFile, outputFile);
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

						//String predefinedPath = path + "preDefinedTestCases\\";
						//String predefinedResultsPath = path
						//		+ "preDefinedTestCases\\results\\";
						String predefinedPath = "commands\\preDefinedTestCases\\";
						String predefinedResultsPath = "commands\\preDefinedTestCases\\results\\";

						if (nr == null)
							break;
						switch (nr) {
						case 1:
							sl.loadTestCase(new File(predefinedPath
									+ "input1.dat"), new File(
									predefinedResultsPath + "output1.dat"));
							break;
						case 2:
							sl.loadTestCase(new File(predefinedPath
									+ "input2.dat"), new File(
									predefinedResultsPath + "output2.dat"));
							break;
						case 3:
							sl.loadTestCase(new File(predefinedPath
									+ "input3.dat"), new File(
									predefinedResultsPath + "output3.dat"));
							break;
						case 4:
							sl.loadTestCase(new File(predefinedPath
									+ "input4.dat"), new File(
									predefinedResultsPath + "output4.dat"));
							break;
						case 5:
							sl.loadTestCase(new File(predefinedPath
									+ "input5.dat"), new File(
									predefinedResultsPath + "output5.dat"));
							break;
						case 6:
							sl.loadTestCase(new File(predefinedPath
									+ "input6.dat"), new File(
									predefinedResultsPath + "output6.dat"));
							break;
						case 7:
							sl.loadTestCase(new File(predefinedPath
									+ "input7.dat"), new File(
									predefinedResultsPath + "output7.dat"));
							break;
						case 8:
							sl.loadTestCase(new File(predefinedPath
									+ "input8.dat"), new File(
									predefinedResultsPath + "output8.dat"));
							break;
						case 9:
							sl.loadTestCase(new File(predefinedPath
									+ "input9.dat"), new File(
									predefinedResultsPath + "output9.dat"));
							break;
						case 10:
							sl.loadTestCase(new File(predefinedPath
									+ "input10.dat"), new File(
									predefinedResultsPath + "output10.dat"));
							break;
						case 11:
							sl.loadTestCase(new File(predefinedPath
									+ "input11.dat"), new File(
									predefinedResultsPath + "output11.dat"));
							break;
						case 12:
							sl.loadTestCase(new File(predefinedPath
									+ "input12.dat"), new File(
									predefinedResultsPath + "output12.dat"));
							break;
						case 13:
							sl.loadTestCase(new File(predefinedPath
									+ "input13.dat"), new File(
									predefinedResultsPath + "output13.dat"));
							break;
						case 14:
							sl.loadTestCase(new File(predefinedPath
									+ "input14.dat"), new File(
									predefinedResultsPath + "output14.dat"));
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
		// land.loadLand();
		// land.buildLand();
	}

}
