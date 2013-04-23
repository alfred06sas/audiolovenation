package program;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import case_tester.CaseTester;

import land.Land;

/**
 * 
 * @author audiolovenation
 * 
 *         Main
 * 
 */
public class Main {

	/**
	 * main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// SingletonLoader objektum a parancsok kezelesehez
		SingletonLoader sl = SingletonLoader.Instance();
		
		// tesztesetek nevei
		String[] testNames = new String[14];

		testNames[0] = "Tracking ant's smell";
		testNames[1] = "Tracking food's smell";
		testNames[2] = "Moving ant on an empty land";
		testNames[3] = "Ant's collision with an echidna";
		testNames[4] = "Ant's collision with an antlion";
		testNames[5] = "Ant's collision with a puddle or gravel";
		testNames[6] = "Ant's collision with food, afterwards with a hill";
		testNames[7] = "Ant killer spray's collision with an ant";
		testNames[8] = "Echidna's collision with an ant";
		testNames[9] = "Echidna's collision with a gravel";
		testNames[10] = "Echidna's collision with two gravels";
		testNames[11] = "Echidna's collision with three gravels";
		testNames[12] = "Using ant smell neutralizer spray";
		testNames[13] = "EXIT";

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String path = null;
		try {
			while (true) {
				// a felhasznalo altal keszitett parancsok eleresenek helye
				// csak egyszer fut le: bekeri az eleresi utat, majd ezzel dolgozik tovabb
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
			e.printStackTrace();
		}
		// megfelelo tesztesetek megnyitasa
		try {
			while (true) {
				// elso kerdesre valasz:
				//    1 - felhasznalo altal definialt esetek
				//    2 - elore megirt teszesetek
				Integer nr = null;
				
				// input es output file-ok
				File inputFile = null;
				File outputFile = null;

				// input es output file-ok nevei
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

				if (nr == null)
					break;
				System.out
						.println("---------------------------------------------");
				switch (nr) {
				case 1: // felhasznalo altal definialt tesztek
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

					outputFile = new File(
							"commands\\customTestCases\\results\\"
									+ outputFileName);
					inputFile = new File("commands\\customTestCases\\"
							+ inputFileName);
					// inputFile = new File(path + "customTestCases\\"
					// + inputFileName);

					try { // a parancsok ertelmezese a kovetkezo metodusban
						sl.loadTestCase(inputFile, outputFile);
					} catch (FileNotFoundException e) {
						System.out.println("No such a file! Try again!");
						break;
					}
					break;
				case 2: // elore definialt tesztesetek
					// a valaszthato tesztek listajanak kiirasa konzolra 
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

						// String predefinedPath = path +
						// "preDefinedTestCases\\";
						// String predefinedResultsPath = path
						// + "preDefinedTestCases\\results\\";
						String predefinedPath = "commands\\preDefinedTestCases\\";
						String predefinedResultsPath = "commands\\preDefinedTestCases\\results\\";

						// ki es bemenet ellenorzesere szolgalo osztaly
						CaseTester ct = new CaseTester();

						if (nr == null)
							break;
						
						switch (nr) {
						case 1: // Hangyaszag kovetese
							sl.loadTestCase(new File(predefinedPath
									+ "input1.dat"), new File(
									predefinedResultsPath + "output1.dat"));
							ct.doTheTest("output1.dat", "expoutput1.dat");
							break;
						case 2: // etelszag kovetes
							sl.loadTestCase(new File(predefinedPath
									+ "input2.dat"), new File(
									predefinedResultsPath + "output2.dat"));
							ct.doTheTest("output2.dat", "expoutput2.dat");
							break;
						case 3: // hangya akadalytalan mozgasa
							sl.loadTestCase(new File(predefinedPath
									+ "input3.dat"), new File(
									predefinedResultsPath + "output3.dat"));
							ct.doTheTest("output3.dat", "expoutput3.dat");
							break;
						case 4: // hangya utkozese hangyaszsunnel
							sl.loadTestCase(new File(predefinedPath
									+ "input4.dat"), new File(
									predefinedResultsPath + "output4.dat"));
							ct.doTheTest("output4.dat", "expoutput4.dat");
							break;
						case 5: // hangya utkozese hangyalesovel
							sl.loadTestCase(new File(predefinedPath
									+ "input5.dat"), new File(
									predefinedResultsPath + "output5.dat"));
							ct.doTheTest("output5.dat", "expoutput5.dat");
							break;
						case 6: // hangya utkozese kaviccsal/tocsaval
							sl.loadTestCase(new File(predefinedPath
									+ "input6.dat"), new File(
									predefinedResultsPath + "output6.dat"));
							ct.doTheTest("output6.dat", "expoutput6.dat");
							break;
						case 7: // hangya utkozese etellel/bollyal
							sl.loadTestCase(new File(predefinedPath
									+ "input7.dat"), new File(
									predefinedResultsPath + "output7.dat"));
							ct.doTheTest("output7.dat", "expoutput7.dat");
							break;
						case 8: // spray utkozese hangyaval
							sl.loadTestCase(new File(predefinedPath
									+ "input8.dat"), new File(
									predefinedResultsPath + "output8.dat"));
							ct.doTheTest("output8.dat", "expoutput8.dat");
							break;
						case 9: // hangyasz utkozese hangyaval
							sl.loadTestCase(new File(predefinedPath
									+ "input9.dat"), new File(
									predefinedResultsPath + "output9.dat"));
							ct.doTheTest("output9.dat", "expoutput9.dat");
							break;
						case 10: // hangyasz utkozese 1 kaviccsal
							sl.loadTestCase(new File(predefinedPath
									+ "input10.dat"), new File(
									predefinedResultsPath + "output10.dat"));
							ct.doTheTest("output10.dat", "expoutput10.dat");
							break;
						case 11: // hangyasz utkozese 2 kaviccsal
							sl.loadTestCase(new File(predefinedPath
									+ "input11.dat"), new File(
									predefinedResultsPath + "output11.dat"));
							ct.doTheTest("output11.dat", "expoutput11.dat");
							break;
						case 12: // hangyasz utkozese kettonel tobb kaviccsal
							sl.loadTestCase(new File(predefinedPath
									+ "input12.dat"), new File(
									predefinedResultsPath + "output12.dat"));
							ct.doTheTest("output12.dat", "expoutput12.dat");
							break;
						case 13: // hangyaszag semlegesito spray hasznalata
							sl.loadTestCase(new File(predefinedPath
									+ "input13.dat"), new File(
									predefinedResultsPath + "output13.dat"));
							ct.doTheTest("output13.dat", "expoutput13.dat");
							break;
						case 14: // kilepes a programbol
							System.exit(1);
							break;
						default: // hibas bemenet
							System.out.println("Invalid input");
							break;
						}
					}
					break;
				default:
					System.out.println("Invalid input!");
					break;
				}

				System.out
						.println("---------------------------------------------");
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
