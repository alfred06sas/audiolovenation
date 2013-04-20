package program;

import item.Antlion;
import item.Food;
import item.Hill;
import item.Item;
import item.Spray;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import land.Field;
import movable.Ant;
import movable.Echidna;
import blockage.Gravel;
import blockage.Puddle;

/**
 * 
 * @author audiolovenation
 * 
 *         Mindent eltarolunk listakban, hogy a teszteleskor hozzajuk tudjunk
 *         ferni.
 * 
 */
public class Singleton {
	private HashMap<String, HashMap<String, String>> states;
	private static Singleton instance = null;
	private static HashMap<String,  String> types;
	

	public Singleton(){
		states = new HashMap<String, HashMap<String, String>>();
		types = new HashMap<String,  String>();
	}
	
	public static Singleton Instance() {
		SingletonContainer sc = SingletonContainer.getInstance();
		if (instance == null){
			instance = new Singleton();
			types=new HashMap<String,  String>();
			
			// tipusok inicializalasa, HashMap az id-k kezdobeture
//			types.put("a", "Ant");
//			types.put("f", "Food");
//			types.put("s", "Spray");
//			types.put("e", "Echidna");
//			types.put("l", "Antlion");
//			types.put("p", "Puddle");
//			types.put("g", "Gravel");
		}
		return instance;
	}

	void init(){
		
	}
	// Az elemek egybol letrehozasuk utan meghivjak ezt a metodust
	// hozzaadva ezzel Id-jet es allapotait a az osztalyhoz
	void addItem(Item i){
		if(states.get(i.getId())==null){			// TODO Elemeknek Id es fieldnek
			printError("Id duplikacio!");
		}
		else{
			states.put(i.getId(), i.getStates());			// TODO getId, getStates elemeknek
		}
	}
	
	/*
	 *   Az utkozesek kiirasara szolgalo metodus a kimenet a kovetkezo foraban jelenik meg:
	 *   <1. ELEM TIPUS (ID)> TO <2. ELEM TIPUS (ID)> ON FIELD(<ID>)
	 *						[<1. ELEM TIPUS(ID)> DIR CHANGED: 	FROM <IRANY X> TO <IRANY Y>]
	 *						[<1. ELEM TIPUS(ID)> STATE CHANGED: FROM <ALLAPOT X> TO <ALLAPOT Y>]
   	 * 						[<2. ELEM TIPUS(ID)> DIR CHANGED:	FROM <IRANY X> TO <IRANY Y>]
	 *						[<2. ELEM TIPUS(ID)> STATE CHANGED: FROM <ALLAPOT X> TO <ALLAPOT Y>],
     *
	 */
	
	void printCollosion(Item what, Item with, Field field){
		// ket típus kivalasztasa a types HashMap-bol
		String whatType = types.get(what.getId().charAt(0));
		String withType = types.get(with.getId().charAt(0));
		HashMap<String, String> oldStateItemWhat = states.get(what.getId());
		HashMap<String, String> newStateItemWhat = what.getStates();
		HashMap<String, String> oldStateItemWith = states.get(with.getId());
		HashMap<String, String> newStateItemWith = with.getStates();
		
		
		// A kiiras része
		System.out.println(whatType+" ("+what.getId()+ ") TO "+withType+" ("+with.getId()+") ON FIELD ("+ field.getId()+")");
		for (String key : oldStateItemWhat.keySet()) {
		    if(!oldStateItemWhat.get(key).equals(newStateItemWhat.get(key))){
		    	System.out.println("                   "+whatType+" ("+what.getId()+") "+
		    						key+" CHANGED:    FROM "+oldStateItemWhat.get(key)+
		    						" TO "+newStateItemWhat.get(key));
		    }
		}
		for (String key : oldStateItemWith.keySet()) {
		    if(!oldStateItemWith.get(key).equals(newStateItemWith.get(key))){
		    	System.out.println("                   "+withType+" ("+with.getId()+") "+
		    						key+" CHANGED:    FROM "+oldStateItemWith.get(key)+
		    						" TO "+newStateItemWith.get(key));
		    }
		}
	}
	
	/* 
	 *   KOR LEPTETESE kovetkezo a formaja:
	 *   -------- X. ROUND --------
	 */
	public void printNextRound(){
		SingletonContainer sc = SingletonContainer.getInstance();
		sc.increaseOfRoundNumber();
		System.out.println("-------- " + sc.getNumberOfRound() + " . ROUND --------");
	}

	/*
	 *  IRANY-, ALLAPOTVALTOZTATAS a kovetkezo formaban:
	 *  [<1. ELEM TIPUS(ID)> DIR CHANGED: 	FROM <IRANY X> TO <IRANY Y>]
	 */
	void printDirChanged(Item what){
		String whatType = types.get(what.getId().charAt(0));
		HashMap<String, String> oldStateItemWhat = states.get(what.getId());
		HashMap<String, String> newStateItemWhat = what.getStates();
		
		if(oldStateItemWhat.get("DIR")==null){
			printError("Az elemnek nincs iranya, igy nem valtozhatott!");
		}
		else{
			if(!oldStateItemWhat.get("DIR").equals(newStateItemWhat.get("DIR"))){
		    	System.out.println(whatType+" ("+what.getId()+") "+
		    						" DIR CHANGED:    FROM "+oldStateItemWhat.get("DIR")+
		    						" TO "+newStateItemWhat.get("DIR"));
		    }
		}
		
	}
	
	
	public void loadTestCase(String inputFileName, String outputFileName) throws FileNotFoundException {
		SingletonContainer sc = SingletonContainer.getInstance();
		BufferedReader br = new BufferedReader(new FileReader(inputFileName));
		try {
			while (true) {
				String line = br.readLine();
				if (line == null)
					break;
				String[] sline = line.split(" ");

				if (sline[0].equals("create_land") && sline[1].equals("-r")
						&& sline[3].equals("-c") && sline.length == 5) {
					System.out.println(line);
					sc.getContainer().loadLand(Integer.parseInt(sline[2]),Integer.parseInt(sline[4]));
					sc.getContainer().buildLand(Integer.parseInt(sline[2]),Integer.parseInt(sline[4]));
				} else if (sline[0].equals("put_item") && sline[1].equals("-t")
						&& sline[3].equals("-iid") && sline[5].equals("-fid")
						&& sline.length == 7) {
					switch(Integer.parseInt(sline[2])){
						case 1:
							Ant ant = new Ant();
							sc.addMovable(ant);
							types.put(sline[4], "Ant");
							addItem(ant);
							sc.getContainer().putItems(sc.getContainer().getField(sline[6]), ant);
							break;
						case 2:
							Echidna echidna= new Echidna();
							sc.addMovable(echidna);
							types.put(sline[4], "Echidna");
							addItem(echidna);
							sc.getContainer().putItems(sc.getContainer().getField(sline[6]), echidna);
							break;
						case 3:
							Antlion antlion = new Antlion();
							types.put(sline[4], "Antlion");
							addItem(antlion);
							sc.getContainer().putItems(sc.getContainer().getField(sline[6]), antlion);
							break;
						case 4:
							Puddle puddle = new Puddle();
							types.put(sline[4], "Puddle");
							addItem(puddle);
							sc.getContainer().putItems(sc.getContainer().getField(sline[6]), puddle);
							break;
						case 5:
							Gravel gravel = new Gravel();
							types.put(sline[4], "Gravel");
							addItem(gravel);
							sc.getContainer().putItems(sc.getContainer().getField(sline[6]), gravel);
							break;
						case 6:
							Hill hill = new Hill();
							types.put(sline[4], "Hill");
							addItem(hill);
							sc.getContainer().putItems(sc.getContainer().getField(sline[6]), hill);
							break;
						case 7:
							Spray spray = new Spray();
							types.put(sline[4], "Spray");
							addItem(spray);
							sc.getContainer().putItems(sc.getContainer().getField(sline[6]), spray);
							break;
						case 8:
							Food food = new Food();
							types.put(sline[4], "Food");
							addItem(food);
							sc.getContainer().putItems(sc.getContainer().getField(sline[6]), food);
							break;
					}
					System.out.println(line);
				} else if (sline[0].equals("put_smell")
						&& sline[1].equals("-t") && sline[3].equals("-sid")
						&& sline[5].equals("-s") && sline[7].equals("-fid")
						&& sline.length == 9) {
					System.out.println(line);
				} else if (sline[0].equals("set") && sline[1].equals("-t")
						&& sline[3].equals("-mid") && sline[5].equals("-d") && sline[7].equals("-s")
						&& sline.length == 9) {
					System.out.println(line);
				} else if (sline[0].equals("use_spray")
						&& sline[1].equals("-t") && sline[3].equals("-fid")
						&& sline.length == 5) {
					System.out.println(line);
				} else if (sline[0].equals("step_round")
						&& sline[1].equals("-rn") && sline.length == 3) {
					System.out.println(line);					
					printNextRound();
					sc.getContainer().move();
				} else {
					System.err.println("Undefined command: " + line);
					return;
				}
			}
		} catch (IOException e) {

		}
	}
	
	
	/*
	 * ATLEPES SZOMSZEDOS MEZORE kovetkezo a formaja:
	 * [<ELEM TIPUS (ID)> STEPPED FROM <1. FIELD (ID)> TO <2. FIELD(<ID>)>]
	 */
	void printStep(Item what, Field from, Field to){
		String whatType = types.get(what.getId().charAt(0));
		System.out.println(whatType+" ("+what.getId()+") "+" STEPPED FROM FIELD ("+ from.getId()+") TO FIELD ("+to.getId()+")");
	}
	
	/*
	 * HANGYASZAG CSOKKENES kovetkezo a formaja:
	 * ANT SMELL DECREASED FROM <X> TO <Y> ON FIELD (<ID>)
	 */
	void printAntSmellDecreased(int from, int to, Field field){
		System.out.println("ANT SMELL DECREASED FROM "+from+" TO "+to+" ON FIELD ("+field.getId()+")");
	}
	
	/*
	 * ETELSZAG ELTUNESE kovetkezo a foraja:
	 * FOOD SMELL DISAPPEARED ON FIELD (<ID>)
	 */
	void printFoodSmellDisappeard(Field field){
		System.out.println("FOOD SMELL DISAPPEARED ON FIELD ("+field.getId()+")");
	}
	
	/* A paramaterben kapott stringet irja a kimenetre, hiba kiirasra hasznaljuk
	 * ez magunknak visszajelzes így ez magyar
	 */
	void printError(String s){
		System.out.println("Hiba: "+s);
	}
}
