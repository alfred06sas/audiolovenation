package program;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Logger {

	public static void main(String[] args) {

		int depth;
		String[] seqNames = new String[] { "fos" };

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			while (br.readLine() != null) {
				for (int i = 0; i < seqNames.length; i++)
					System.out.println(i + 1 + ". " + seqNames[i]);

				System.out.println("Which sequence do you want to run?");
			//	System.in;

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}