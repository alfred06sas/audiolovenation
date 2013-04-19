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
import land.Spray;
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
 *         Mindent eltárolunk listákban, hogy a teszteléskor hozzájuk tudjunk
 *         férni.
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
			
			// típusok inicializálása, HashMap az id-k kezdőbetűire
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
	// Az elemek egyből létrehozásuk után meghívják ezt a metódust
	// hozzáadva ezzel Id-ját és állapotait a az osztályhoz
	void addItem(Item i){
		if(states.get(i.getId())==null){			// TODO Elemeknek Id és fieldnek
			printError("Id duplikáció!");
		}
		else{
			states.put(i.getId(), i.getStates());			// TODO getId, getStates elemeknek
		}
	}
	
	/*
	 *   Az ütközések kiirására szolgáló metódus a kimenet a következő formában jelenik meg:
	 *   <1. ELEM TÍPUS (ID)> TO <2. ELEM TÍPUS (ID)> ON FIELD(<ID>)
	 *						[<1. ELEM TÍPUS(ID)> DIR CHANGED: 	FROM <IRÁNY X> TO <IRÁNY Y>]
	 *						[<1. ELEM TÍPUS(ID)> STATE CHANGED: FROM <ÁLLAPOT X> TO <ÁLLAPOT Y>]
   	 * 						[<2. ELEM TÍPUS(ID)> DIR CHANGED:	FROM <IRÁNY X> TO <IRÁNY Y>]
	 *						[<2. ELEM TÍPUS(ID)> STATE CHANGED: FROM <ÁLLAPOT X> TO <ÁLLAPOT Y>],
     *
	 */
	
	void printCollosion(Item what, Item with, Field field){
		// két típus kiválasztása a types HashMap-ből
		String whatType = types.get(what.getId().charAt(0));
		String withType = types.get(with.getId().charAt(0));
		HashMap<String, String> oldStateItemWhat = states.get(what.getId());
		HashMap<String, String> newStateItemWhat = what.getStates();
		HashMap<String, String> oldStateItemWith = states.get(with.getId());
		HashMap<String, String> newStateItemWith = with.getStates();
		
		
		// A kiírás része
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
	 *   KÖR LÉPTETÉSE következő a formája:
	 *   -------- X. ROUND --------
	 */
	void printNextRound(){
		System.out.println("-------- X. ROUND --------");
	}

	/*
	 *  IRÁNY-, ÁLLAPOTVÁLTOZTATÁS a következő formában:
	 *  [<1. ELEM TÍPUS(ID)> DIR CHANGED: 	FROM <IRÁNY X> TO <IRÁNY Y>]
	 */
	void printDirChanged(Item what){
		String whatType = types.get(what.getId().charAt(0));
		HashMap<String, String> oldStateItemWhat = states.get(what.getId());
		HashMap<String, String> newStateItemWhat = what.getStates();
		
		if(oldStateItemWhat.get("DIR")==null){
			printError("Az elemnek nincs iránya, így nem változhatott!");
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
	 * ÁTLÉPÉS SZOMSZÉDOS MEZŐRE következő a formája:
	 * [<ELEM TÍPUS (ID)> STEPPED FROM <1. FIELD (ID)> TO <2. FIELD(<ID>)>]
	 */
	void printStep(Item what, Field from, Field to){
		String whatType = types.get(what.getId().charAt(0));
		System.out.println(whatType+" ("+what.getId()+") "+" STEPPED FROM FIELD ("+ from.getId()+") TO FIELD ("+to.getId()+")");
	}
	
	/*
	 * HANGYASZAG CSÖKKENÉS következő a formája:
	 * ANT SMELL DECREASED FROM <X> TO <Y> ON FIELD (<ID>)
	 */
	void printAntSmellDecreased(int from, int to, Field field){
		System.out.println("ANT SMELL DECREASED FROM "+from+" TO "+to+" ON FIELD ("+field.getId()+")");
	}
	
	/*
	 * ÉTELSZAG ELTŰNÉSE következő a formája:
	 * FOOD SMELL DISAPPEARED ON FIELD (<ID>)
	 */
	void printFoodSmellDisappeard(Field field){
		System.out.println("FOOD SMELL DISAPPEARED ON FIELD ("+field.getId()+")");
	}
	
	/* A paraméterül kapott stringet irja a kimenetre, hiba kiírásra használjuk
	 * ez magunknak visszajelzés így ez magyar
	 */
	void printError(String s){
		System.out.println("Hiba: "+s);
	}
}
