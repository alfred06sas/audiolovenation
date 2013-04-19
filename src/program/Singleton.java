package program;

import item.Antlion;
import item.Food;
import item.Hill;
import item.Item;
import item.Tentacle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import land.Field;
import land.Land;
import item.Spray;
import movable.Ant;
import movable.Echidna;
import smell.AntSmell;
import smell.FoodSmell;
import smell.Smell;
import blockage.Blockage;
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
	

	public static Singleton Instance() {
		if (instance == null){
			instance = new Singleton();
			types=new HashMap<String,  String>();
			
			// tipusok inicializalasa, HashMap az id-k kezdobeture
			types.put("a", "Ant");
			types.put("f", "Food");
			types.put("s", "Spray");
			types.put("e", "Echidna");
			types.put("l", "Antlion");
			types.put("p", "Puddle");
			types.put("g", "Gravel");
		}
		return instance;
	}

	void init(){
		
	}
	// Az elemek egybol letrehozasuk utan meghivjak ezt a metodust
	// hozzaadva ezzel Id-jet es allapotait a az osztalyhoz
	void addItem(Item i){
		if(states.get(i.getId())==null){			// TODO Elemeknek Id es fieldnek
			printError("Id duplikÃ¡ciÃ³!");
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
	void printNextRound(){
		System.out.println("-------- X. ROUND --------");
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
