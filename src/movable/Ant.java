package movable;

import item.Item;
import item.Tentacle;
import item.Volatile;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import land.Dir;
import land.Field;
import program.Singleton;
import program.SingletonContainer;
import smell.AntSmell;
import smell.Smell;

/**
 * 
 * A hangya feladata mezorol mezore lepni. Mozgasa iranyat az etel, es mas hangyak altal
 * hagyott szagnyom befolyasolja. Vegul, ha etelhez er, vissza
 * kell azt vinnie a bolyba.
 * 
 * @author audiolovenation
 * 
 */
public class Ant extends Item implements Movable {
	/**
	 * Csap objektum. Ez vegzi a tovabbhaladasi iranyok beallitasat.
	 */
	private Tentacle tentacle;
	/**
	 * A hangya meghalt-e.
	 */
	private boolean isKilled = false;
	/**
	 * A hangya aktiv-e (a palyan tartozkodik-e).
	 */
	private boolean isActive = false;
	/**
	 * Inaktiv allapotban a maradek varakozasi korok.
	 */
	private int wait;
	/**
	 * Van-e a hangyanal etel.
	 */
	private boolean haveFood = false;
	/**
	 * Eletero, amelyet a spray, a hangyaleso es a hangyaszsun modosithat. Ha 0,
	 * akkor a hangya atlep inaktiv allapotba es a kor vegen meghal.
	 */
	private int HP;
	/**
	 * A tovabbhaladasi irany. Default: UP.
	 */
	private Dir dir;

	public Ant(){
		HP = 10;
		tentacle = new Tentacle(this);
		isKilled = false;
		isActive = false;
		wait = 10;
		haveFood = false;
		dir = Dir.RIGHT_BOTTOM;
	}
	
	/**
	 * Aktualis mezo beallitasa.
	 * 
	 * @param field
	 *            a beallitando mezo
	 */
	@Override
	public void setActualField(Field field) {
		Singleton s = Singleton.Instance();

	}

	/**
	 * Eletero lecsokkentese a megadott ertekkel.
	 * 
	 * @param hp
	 *            a csokkentes merteke
	 */
	public void looseHP(Integer hp) {
		Singleton s = Singleton.Instance();

		
	}

	/**
	 * A hangya etelt vesz fel. Allapotat atallitja (haveFood = true).
	 */
	public void pickUpFood() {
		Singleton s = Singleton.Instance();

		
	}

	/**
	 * Pihenteti a hangyat egy adott ideig [amig el nem indul (ujra) a
	 * bolybol)].
	 */
	public void rest() {
		Singleton s = Singleton.Instance();

		
	}

	/**
	 * A hangya iranyanak megforditasa.
	 */
	public void reverseDir() {
		Singleton s = Singleton.Instance();

		
	}

	/**
	 * A hangya megolese. Torli magat a mezorol es a SingletonContainer-ben levo
	 * movables listabol is.
	 */
	public void kill() {
		Singleton s = Singleton.Instance();

		
		/* Hangya torlese az ot tartalmazo mezorol */
		Field field = new Field();
		field.removeItem(new Ant());
		
		/* Hangya torlese a SingletonContainer movables listajabol */
		SingletonContainer sc = new SingletonContainer().getInstance();
		sc.removeMovable(new Ant());
		
	}

	/**
	 * Haladasi irany beallitasa.
	 * 
	 * @param dir
	 *            a beallitando haladasi irany
	 */
	public void setDir(Dir dir) {
		Singleton s = Singleton.Instance();
	}
	
	public Dir getDir(){
		return dir;
	}

	/**
	 * A hangya leptetese. Lekerdezi az ot tartalmazo mezo szomszedait, majd
	 * ezekbol a lehetseges tovabbhaladasi iranyban levoket tovabbadja a
	 * csapjanak. A csap ezek utan megvizsgalja, hogy hova lephet, majd ahol
	 * akadaly van, kiszedi a lehetseges mezok listajabol. Ha mindenhol akadaly
	 * van, akkor megfordul (marad ugyanazon a mezon), ha megtalalta a
	 * legmegfelelobbet, akkor ralep, majd utkozik az ottani item-ekkel.
	 */
	@Override
	public void step() {
		Singleton s = Singleton.Instance();

		
		/* A szomszedok lekerdezese. */
		Field prevField = new Field();
		prevField.getNeighbours();
	
		/* A lehetseges szomszedok beallitasa. */
		Tentacle tentacle = new Tentacle();
		Map<Dir, Field> map = new TreeMap<Dir, Field>();
		map.put(Dir.DOWN, prevField);
		tentacle.setPossibleNeighbours(map);
	
		/* A lehetseges szomszedok item-einek egy ciklusban valo lekerdezese. */
		Field nextField = new Field();
		nextField.getItems();
		
		/*
		 * Az elemekkel valo utkoztetes meg lepes elott, hogy kideruljon, hol
		 * talalhato akadaly.
		 */
		Ant ant = new Ant();
		Ant ant2 = new Ant();
		ant2.collisionWithAnt(ant, false);

		/*
		 * A csap scan metodusanak meghivasa, amely kivalasztja a kovetkezo mezot.
		 */
		Map<Dir, Field> map2 = tentacle.scan(true);
	
		/* Ha van olyan mezo, ahol nincs akadaly. */
		if (map2 != null) {
			/* Hangyaszag hagyasa az elozo mezon. */
			Smell smell = new AntSmell();
			prevField.addSmell(smell);
			
			/* Hangyaszag hozzaadasa a SingletonContainer volatiles listajahoz. */
			SingletonContainer sc = new SingletonContainer().getInstance();
			Volatile smell2 = new AntSmell();
			sc.addVolatile(smell2);
			
			/* Elozo mezorol eltavolitja sajat magat. */
			prevField.removeItem(ant);
		
			/* Kovetkezo mezo beallitasa. */
			ant.setActualField(nextField);
		
			/* Kovetkezo mezobe valo beregisztralas. */
			nextField.addItem(ant);
			/* Ha nem tud sehova se menni. */
		} else {
			/* Irany megforditasa. */
			ant.reverseDir();
		}
		/* Lepes utani utkoztetes. */
		Echidna echidna = new Echidna();
		echidna.collisionWithAnt(ant, true);

	}

	/**
	 * Hangya allapotanak inaktivrol aktivra allitasa. (wait == 0)
	 */
	@Override
	public void setAlive() {
		Singleton s = Singleton.Instance();

	}

	/**
	 * Hangyaval valo utkoztetes. Nem csinal semmit, csak visszater.
	 * 
	 * @param ant
	 *            a hangya referenciaja, amivel utkozott
	 * @param b
	 *            utkozes elott: false, utkozes utan: true
	 */
	@Override
	public void collisionWithAnt(Ant ant, boolean b) {
		Singleton s = Singleton.Instance();

	}

	/**
	 * - Hangyaszsunnel valo utkozes. A hangya meghal.
	 * 
	 * @param echidna
	 *            referencia a hangyaszsunre, amivel utkozik
	 */
	@Override
	public void collisionWithEchidna(Echidna echidna) {
		Singleton s = Singleton.Instance();

		/*
		 * A hangya szol a hangyaszsunnek, hogy hangyaval utkozott, amely ennek
		 * hatasara megoli.
		 */
		Ant ant = new Ant();
		echidna.collisionWithAnt(ant, true);
		
	}

	/**
	 * Spray-vel valo utkozes. A hangya vagy meghal, vagy eleterot veszit.
	 * 
	 * @param strength
	 *            a hangya eletereje ennyivel fog csokkenni
	 */
	@Override
	public void collisionWithSpray(Integer strength) {
		Singleton s = Singleton.Instance();

		/* A hangya looseHP metodusanak meghivasa. */
		Ant ant = new Ant();
		ant.looseHP(6);
		
	}

	/**
	 * Akadallyal valo utkozes eseten az akadaly ezzel a metodussal jelzi a
	 * hangyanak, hogy az ot tartalmazo mezore nem lephet. Ezt a mezot a csap
	 * kiveszi a lehetseges szomszedok kozul. Mindig lepes elott hivodik meg,
	 * mivel az nem fordulhat elo, hogy a hangya olyan mezore lep, ahol akadaly
	 * van.
	 * 
	 * @param field
	 *            az a mezo, ahova nem lephet a hangya
	 */
	public void canNotGo(Field field) {
		Singleton s = Singleton.Instance();
	/* A parameterkent megadott mezo kivetele a lehetseges mezok listajabol. */
		Tentacle tentacle = new Tentacle();
		tentacle.removePossibleNeighbour(field);
		
	}
	
	@Override
	public HashMap<String, String> getStates() {
		HashMap<String, String> states = new HashMap<String, String>();
		
		if (isKilled)
			states.put("STATE", "KILLED");
		else if (wait > 0)
			states.put("STATE", "WAIT");
		else {
			if (haveFood)
				states.put("STATE", "HAVE_FOOD");
			else
				states.put("STATE", "DONT_HAVE_FOOD");
		}
		
		return states;
	}
}
