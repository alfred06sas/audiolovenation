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
 *         Mindent elt�rolunk list�kban, hogy a tesztel�skor hozz�juk tudjunk
 *         f�rni.
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
			
			// t�pusok inicializ�l�sa, HashMap az id-k kezd�bet�ire
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
	// Az elemek egyb�l l�trehoz�suk ut�n megh�vj�k ezt a met�dust
	// hozz�adva ezzel Id-j�t �s �llapotait a az oszt�lyhoz
	void addItem(Item i){
		if(states.get(i.getId())==null){			// TODO Elemeknek Id �s fieldnek
			printError("Id duplik�ci�!");
		}
		else{
			states.put(i.getId(), i.getStates());			// TODO getId, getStates elemeknek
		}
	}
	
	/*
	 *   Az �tk�z�sek kiir�s�ra szolg�l� met�dus a kimenet a k�vetkez� form�ban jelenik meg:
	 *   <1. ELEM T�PUS (ID)> TO <2. ELEM T�PUS (ID)> ON FIELD(<ID>)
	 *						[<1. ELEM T�PUS(ID)> DIR CHANGED: 	FROM <IR�NY X> TO <IR�NY Y>]
	 *						[<1. ELEM T�PUS(ID)> STATE CHANGED: FROM <�LLAPOT X> TO <�LLAPOT Y>]
   	 * 						[<2. ELEM T�PUS(ID)> DIR CHANGED:	FROM <IR�NY X> TO <IR�NY Y>]
	 *						[<2. ELEM T�PUS(ID)> STATE CHANGED: FROM <�LLAPOT X> TO <�LLAPOT Y>],
     *
	 */
	
	void printCollosion(Item what, Item with, Field field){
		// k�t t�pus kiv�laszt�sa a types HashMap-b�l
		String whatType = types.get(what.getId().charAt(0));
		String withType = types.get(with.getId().charAt(0));
		HashMap<String, String> oldStateItemWhat = states.get(what.getId());
		HashMap<String, String> newStateItemWhat = what.getStates();
		HashMap<String, String> oldStateItemWith = states.get(with.getId());
		HashMap<String, String> newStateItemWith = with.getStates();
		
		
		// A ki�r�s r�sze
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
	 *   K�R L�PTET�SE k�vetkez� a form�ja:
	 *   -------- X. ROUND --------
	 */
	void printNextRound(){
		System.out.println("-------- X. ROUND --------");
	}

	/*
	 *  IR�NY-, �LLAPOTV�LTOZTAT�S a k�vetkez� form�ban:
	 *  [<1. ELEM T�PUS(ID)> DIR CHANGED: 	FROM <IR�NY X> TO <IR�NY Y>]
	 */
	void printDirChanged(Item what){
		String whatType = types.get(what.getId().charAt(0));
		HashMap<String, String> oldStateItemWhat = states.get(what.getId());
		HashMap<String, String> newStateItemWhat = what.getStates();
		
		if(oldStateItemWhat.get("DIR")==null){
			printError("Az elemnek nincs ir�nya, �gy nem v�ltozhatott!");
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
	 * �TL�P�S SZOMSZ�DOS MEZ�RE k�vetkez� a form�ja:
	 * [<ELEM T�PUS (ID)> STEPPED FROM <1. FIELD (ID)> TO <2. FIELD(<ID>)>]
	 */
	void printStep(Item what, Field from, Field to){
		String whatType = types.get(what.getId().charAt(0));
		System.out.println(whatType+" ("+what.getId()+") "+" STEPPED FROM FIELD ("+ from.getId()+") TO FIELD ("+to.getId()+")");
	}
	
	/*
	 * HANGYASZAG CS�KKEN�S k�vetkez� a form�ja:
	 * ANT SMELL DECREASED FROM <X> TO <Y> ON FIELD (<ID>)
	 */
	void printAntSmellDecreased(int from, int to, Field field){
		System.out.println("ANT SMELL DECREASED FROM "+from+" TO "+to+" ON FIELD ("+field.getId()+")");
	}
	
	/*
	 * �TELSZAG ELT�N�SE k�vetkez� a form�ja:
	 * FOOD SMELL DISAPPEARED ON FIELD (<ID>)
	 */
	void printFoodSmellDisappeard(Field field){
		System.out.println("FOOD SMELL DISAPPEARED ON FIELD ("+field.getId()+")");
	}
	
	/* A param�ter�l kapott stringet irja a kimenetre, hiba ki�r�sra haszn�ljuk
	 * ez magunknak visszajelz�s �gy ez magyar
	 */
	void printError(String s){
		System.out.println("Hiba: "+s);
	}
}
