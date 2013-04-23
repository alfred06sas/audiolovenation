/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package case_tester;

import java.io.*;



/**
 *
 * @author 
 */
public class CaseTester {

    /**
     * @param args the command line arguments
     */
    public void doTheTest(String par1, String par2) {
        try {
            // referencia-fajl beolvasasa
            FileReader f1 = new FileReader("commands/preDefinedTestCases/results/" + par1);
            BufferedReader in1 = new BufferedReader(f1);

            // vizsgalt fajl beolvasasa
            FileReader f2 = new FileReader("commands/preDefinedTestCases/expOutput/" + par2);
            BufferedReader in2 = new BufferedReader(f2);

            // ebben a ket stringben taroljuk ideiglenesen a sorokat
            String str1, str2;

            Boolean megegyezik = true;
            
            // referencia-fajl sorait megszamoljuk
            // az a sor valid, ha igy kezdodik:
            // <szam> :
            while ( ((str1 = in1.readLine()) != null) ) {
            	str2 = in2.readLine();
            	if(!str1.equals(str2)){
            		megegyezik = false;
            	}
            }

            if (megegyezik == true)
                System.out.println("A ket fajl megegyezik!");
            else
                System.out.println("A ket fajl nem egyezik meg!");

            in1.close();
            in2.close();
        }
        catch (IOException e)
        {
            System.out.println(e.toString());
        }
    }

}