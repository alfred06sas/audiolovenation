package program;

import item.Item;

import java.util.HashMap;

import land.Field;

/**
 * 
 * @author audiolovenation
 * 
 *         Mindent eltarolunk listakban, hogy a teszteleskor hozzajuk tudjunk
 *         ferni.
 * 
 */
public class Singleton {
	private static HashMap<String, HashMap<String, String>> states;
	private static Singleton instance = null;
	private static SingletonLoader sl = null;
	private static HashMap<String, String> types;
	private static int roundNumber;

	public Singleton() {
		states = new HashMap<String, HashMap<String, String>>();
		types = new HashMap<String, String>();
	}

	/**
	 * az osztaly ugyanazon peldanyaval ter vissza minden esetben
	 * 
	 * @return a peldany
	 */
	public static Singleton Instance() {
		if (instance == null) {
			instance = new Singleton();
			sl = SingletonLoader.Instance();
			states = new HashMap<String, HashMap<String, String>>();
			types = new HashMap<String, String>();
			roundNumber = 0;

			// tipusok inicializalasa, HashMap az id-k kezdobeture
			types.put("a", "Ant");
			types.put("f", "Food");
			types.put("s", "Spray");
			types.put("e", "Echidna");
			types.put("l", "Antlion");
			types.put("p", "Puddle");
			types.put("g", "Gravel");
			types.put("h", "Hill");
			
		}
		return instance;
	}

	/**
	 * kitorli a jelenlegi peldanyt, ahhoz kell hogy uj futast tudjunk kezdeni
	 */
	public void clear() {
		instance = null;
	}

	/**
	 * Az elemek egybol letrehozasuk utan meghivjak ezt a metodust hozzaadva
	 * ezzel Id-jet es allapotait a az osztalyhoz
	 * 
	 * @param i a hozzaadando Item
	 */
	public void addItem(Item i) {
		if (states.containsKey(i.getId()) == true) {
			printError("Id duplikacio!");
		} else {
			states.put(i.getId(), i.getStates());
		}
	}

	/**
	 * allapot beallitasa a hasonlo nevu commandhoz
	 * @param i beallitando Item
	 */
	public void setState(Item i) {
		states.remove(i.getId());
		states.put(i.getId(), i.getStates());
	}

	/**
	 * Az utkozesek kiirasara szolgalo metodus a kimenet a kovetkezo foraban
	 * jelenik meg: 
	 * <1. ELEM TIPUS (ID)> TO <2. ELEM TIPUS (ID)> ON FIELD(<ID>)
	 * 			[<1. ELEM TIPUS(ID)> DIR CHANGED: FROM <IRANY X> TO <IRANY Y>]
	 * 			[<1. ELEM TIPUS(ID)> STATE CHANGED: FROM <ALLAPOT X> TO <ALLAPOT Y>]
	 * 			[<2. ELEM TIPUS(ID)> DIR CHANGED: FROM <IRANY X> TO <IRANY Y>]
	 * 			[<2. ELEM TIPUS(ID)> STATE CHANGED: FROM <ALLAPOT X> TO <ALLAPOT Y>],
	 * @param what mi
	 * @param with mivel
	 * @param field hol
	 */
	public void printCollision(Item what, Item with, Field field) {
		// ket tipus kivalasztasa a types HashMap-bol

		String whatType = types.get(String.valueOf(what.getId().charAt(0)));
		String withType = types.get(String.valueOf(with.getId().charAt(0)));

		HashMap<String, String> oldStateItemWhat = states.get(what.getId());
		HashMap<String, String> newStateItemWhat = what.getStates();
		HashMap<String, String> oldStateItemWith = states.get(with.getId());
		HashMap<String, String> newStateItemWith = with.getStates();

		// A kiiras resze
//		System.out.println(whatType + " (" + what.getId().substring(1)
//				+ ") TO " + withType + " (" + with.getId().substring(1)
//				+ ") ON FIELD (" + field.getId() + ")");
		sl.concatToResult(whatType + " (" + what.getId().substring(1) + ") TO "
				+ withType + " (" + with.getId().substring(1) + ") ON FIELD ("
				+ field.getId() + ")");
		if (oldStateItemWhat != null) {
			for (String key : oldStateItemWhat.keySet()) {
				if (!oldStateItemWhat.get(key)
						.equals(newStateItemWhat.get(key))) {
//					System.out.println("                   " + whatType + " ("
//							+ what.getId().substring(1) + ") " + key
//							+ " CHANGED:    FROM " + oldStateItemWhat.get(key)
//							+ " TO " + newStateItemWhat.get(key));

					sl.concatToResult("                   " + whatType + " ("
							+ what.getId().substring(1) + ") " + key
							+ " CHANGED:    FROM " + oldStateItemWhat.get(key)
							+ " TO " + newStateItemWhat.get(key));
					states.remove(what.getId());
					states.put(what.getId(), newStateItemWhat);
				}
			}
		}
		if (oldStateItemWith != null) {
			for (String key : oldStateItemWith.keySet()) {
				if (!oldStateItemWith.get(key)
						.equals(newStateItemWith.get(key))) {
//					System.out.println("                   " + withType + " ("
//							+ with.getId().substring(1) + ") " + key
//							+ " CHANGED:    FROM " + oldStateItemWith.get(key)
//							+ " TO " + newStateItemWith.get(key));
					sl.concatToResult("                   " + withType + " ("
							+ with.getId().substring(1) + ") " + key
							+ " CHANGED:    FROM " + oldStateItemWith.get(key)
							+ " TO " + newStateItemWith.get(key));
					states.remove(with.getId());
					states.put(with.getId(), newStateItemWith);
				}
			}
		}
	}

	/**
	 * KOR LEPTETESE kovetkezo a formaja: -------- X. ROUND --------
	 */
	public void printNextRound() {
		roundNumber++;
//		System.out.println("-------- " + roundNumber + " . ROUND --------");
		sl.concatToResult("-------- " + roundNumber + " . ROUND --------");
	}

	/**
	 * IRANY-, ALLAPOTVALTOZTATAS a kovetkezo formaban: [<1. ELEM TIPUS(ID)> DIR
	 * CHANGED: FROM <IRANY X> TO <IRANY Y>]
	 * @param minek az iranya
	 */
	public void printDirChanged(Item what) {
		String whatType = types.get(String.valueOf(what.getId().charAt(0)));
		HashMap<String, String> oldStateItemWhat = states.get(what.getId());
		HashMap<String, String> newStateItemWhat = what.getStates();

		if (oldStateItemWhat.get("DIR") == null) {
			printError("Az elemnek nincs iranya, igy nem valtozhatott!");
		} else {
			if (!oldStateItemWhat.get("DIR")
					.equals(newStateItemWhat.get("DIR"))) {
//				System.out.println(whatType + " (" + what.getId().substring(1)
//						+ ") " + " DIR CHANGED:    FROM "
//						+ oldStateItemWhat.get("DIR") + " TO "
//						+ newStateItemWhat.get("DIR"));
				sl.concatToResult(whatType + " (" + what.getId().substring(1)
						+ ") " + " DIR CHANGED:    FROM "
						+ oldStateItemWhat.get("DIR") + " TO "
						+ newStateItemWhat.get("DIR"));
				states.remove(what.getId());
				states.put(what.getId(), newStateItemWhat);
			}
		}

	}

	/**
	 * ATLEPES SZOMSZEDOS MEZORE kovetkezo a formaja: [<ELEM TIPUS (ID)> STEPPED
	 * FROM <1. FIELD (ID)> TO <2. FIELD(<ID>)>]
	 * @param what mi 
	 * @param from honnan
	 * @param to hova
	 */
	public void printStep(Item what, Field from, Field to) {
		String whatType = types.get(String.valueOf(what.getId().charAt(0)));
//		System.out.println(whatType + " (" + what.getId().substring(1) + ")"
//				+ " STEPPED FROM FIELD (" + from.getId() + ") TO FIELD ("
//				+ to.getId() + ")");
		sl.concatToResult(whatType + " (" + what.getId().substring(1) + ")"
				+ " STEPPED FROM FIELD (" + from.getId() + ") TO FIELD ("
				+ to.getId() + ")");
	}


	/**
	  * HANGYASZAG CSOKKENES kovetkezo a formaja: ANT SMELL DECREASED FROM <X> TO
	 * <Y> ON FIELD (<ID>)
	 * @param from mirol
	 * @param to mire
	 * @param field hol
	 */
	public void printAntSmellDecreased(int from, int to, Field field) {
//		System.out.println("ANT SMELL DECREASED FROM " + from + " TO " + to
//				+ " ON FIELD (" + field.getId() + ")");
		sl.concatToResult("ANT SMELL DECREASED FROM " + from + " TO " + to
				+ " ON FIELD (" + field.getId() + ")");
	}


	/**
	 * ETELSZAG ELTUNESE kovetkezo a foraja: FOOD SMELL DISAPPEARED ON FIELD
	 * (<ID>)
	 * @param field hol
	 */
	public void printFoodSmellDisappeard(Field field) {
//		System.out.println("FOOD SMELL DISAPPEARED ON FIELD (" + field.getId()
//				+ ")");
		sl.concatToResult("FOOD SMELL DISAPPEARED ON FIELD (" + field.getId()
				+ ")");
	}

	/**
	 * A paramaterben kapott stringet irja a kimenetre, hiba kiirasra hasznaljuk
	 * ez magunknak visszajelzes �gy ez magyar
	 * @param s hiba uzenet
	 */
	public void printError(String s) {
		System.out.println("Hiba: " + s);
	}

	/**
	 * komment kiirasa magunknak, hibakereses kozben hasznaljuk
	 * @param s uzenet
	 */
	public void printComment(String s) {
		System.out.println("komment: " + s);
	}
}
