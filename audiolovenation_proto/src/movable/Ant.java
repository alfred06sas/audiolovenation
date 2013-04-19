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
 * A hangya feladata mez�r�l mez�re l�pni. Mozg�sa ir�ny�t az �tel, �s m�s
 * hangy�k �ltal hagyott szagnyom befoly�solja. V�g�l, ha �telhez �r, vissza
 * kell azt vinnie a bolyba.
 * 
 * @author audiolovenation
 * 
 */
public class Ant extends Item implements Movable {
	/**
	 * Cs�p objektum. Ez v�gzi a tov�bbhalad�si ir�nyok be�ll�t�s�t.
	 */
	private Tentacle tentacle;
	/**
	 * A hangya meghalt-e.
	 */
	private boolean isKilled = false;
	/**
	 * A hangya akt�v-e (a p�ly�n tart�zkodik-e).
	 */
	private boolean isActive = false;
	/**
	 * Inakt�v �llapotban a marad�k v�rakoz�si k�r�k.
	 */
	private Integer wait;
	/**
	 * Van-e a hangy�n�l �tel.
	 */
	private boolean haveFood = false;
	/**
	 * �leter�, amelyet a spray, a hangyales� �s a hangy�szs�n m�dos�that. Ha 0,
	 * akkor a hangya �tl�p inakt�v �llapotba �s a k�r v�g�n meghal.
	 */
	private Integer HP;
	/**
	 * A tov�bbhalad�si ir�ny. Default: UP.
	 */
	private Dir dir;

	/**
	 * Aktuális mez� be�ll�t�sa.
	 * 
	 * @param field
	 *            a be�ll�tand� mez�
	 */
	@Override
	public void setActualField(Field field) {
		Singleton s = Singleton.Instance();

	}

	/**
	 * �leter� lecs�kkent�se a megadott �rt�kkel.
	 * 
	 * @param hp
	 *            a cs�kkent�s m�rt�ke
	 */
	public void looseHP(Integer hp) {
		Singleton s = Singleton.Instance();

		
	}

	/**
	 * A hangya �telt vesz fel. �llapot�t �t�ll�tja (haveFood = true).
	 */
	public void pickUpFood() {
		Singleton s = Singleton.Instance();

		
	}

	/**
	 * Pihenteti a hangy�t egy adott ideig [am�g el nem indul (�jra) a
	 * bolyb�l)].
	 */
	public void rest() {
		Singleton s = Singleton.Instance();

		
	}

	/**
	 * A hangya ir�ny�nak megford�t�sa.
	 */
	public void reverseDir() {
		Singleton s = Singleton.Instance();

		
	}

	/**
	 * A hangya meg�l�se. T�rli mag�t a mez�r�l �s a SingletonContainer-ben lev�
	 * movables list�b�l is.
	 */
	public void kill() {
		Singleton s = Singleton.Instance();

		
		/* Hangya t�rl�se az �t tartalmaz� mez�r�l */
		Field field = new Field();
		field.removeItem(new Ant());
		
		/* Hangya t�rl�se a SingletonContainer movables list�j�b�l */
		SingletonContainer sc = new SingletonContainer().getInstance();
		sc.removeMovable(new Ant());
		
	}

	/**
	 * Halad�si ir�ny be�ll�t�sa.
	 * 
	 * @param dir
	 *            a be�ll�tand� halad�si ir�ny
	 */
	public void setDir(Dir dir) {
		Singleton s = Singleton.Instance();

	
	}

	/**
	 * A hangya l�ptet�se. Lek�rdezi az �t tartalmaz� mez� szomsz�dait, majd
	 * ezekb�l a lehets�ges tov�bbhalad�si ir�nyban lev�ket tov�bbadja a
	 * cs�pj�nak. A cs�p ezek ut�n megvizsg�lja, hogy hova l�phet, majd ahol
	 * akad�ly van, kiszedi a lehets�ges mez�k list�j�b�l. Ha mindenhol akad�ly
	 * van, akkor megfordul (marad ugyanazon a mez�n), ha megtal�lta a
	 * legmegfelel�bbet, akkor r�l�p, majd �tk�zik az ottani item-ekkel.
	 */
	@Override
	public void step() {
		Singleton s = Singleton.Instance();

		
		/* A szomsz�dok lek�rdez�se */
		Field prevField = new Field();
		prevField.getNeighbours();
	
		/* A lehets�ges szomsz�dok be�ll�t�sa */
		Tentacle tentacle = new Tentacle();
		Map<Dir, Field> map = new TreeMap<Dir, Field>();
		map.put(Dir.DOWN, prevField);
		tentacle.setPossibleNeighbours(map);
	
		/* A lehets�ges szomsz�dok item-einek egy ciklusban val� lek�rdez�se */
		Field nextField = new Field();
		nextField.getItems();
		
		/*
		 * Az elemekkel val� �tk�ztet�s m�g l�p�s el�tt, hogy kider�lj�n, hol
		 * tal�lhat� akad�ly
		 */
		Ant ant = new Ant();
		Ant ant2 = new Ant();
		ant2.collisionWithAnt(ant, false);

		/*
		 * A cs�p scan met�dus�nak megh�v�sa, amely kiv�lasztja a k�vetkez�
		 * mez�t.
		 */
		Map<Dir, Field> map2 = tentacle.scan(true);
	
		/* Ha van olyan mez�, ahol nincs akad�ly */
		if (map2 != null) {
			/* Hangyaszag hagy�sa az el�z� mez�n */
			Smell smell = new AntSmell();
			prevField.addSmell(smell);
			
			/* Hangyaszag hozz�ad�sa a SingletonContainer volatiles list�j�hoz */
			SingletonContainer sc = new SingletonContainer().getInstance();
			Volatile smell2 = new AntSmell();
			sc.addVolatile(smell2);
			
			/* El�z� mez�r�l elt�vol�tja saj�t mag�t */
			prevField.removeItem(ant);
		
			/* K�vetkez� mez� be�ll�t�sa */
			ant.setActualField(nextField);
		
			/* K�vetkez� mez�be val� beregisztr�l�s */
			nextField.addItem(ant);
			/* Ha nem tud sehova se menni */
		} else {
			/* Ir�ny megford�t�sa */
			ant.reverseDir();
		}
		/* l�p�s ut�ni �tk�ztet�s */
		Echidna echidna = new Echidna();
		echidna.collisionWithAnt(ant, true);

	}

	/**
	 * Hangya �llapot�nak inakt�vr�l akt�vra �ll�t�sa (wait == 0)
	 */
	@Override
	public void setAlive() {
		Singleton s = Singleton.Instance();

	}

	/**
	 * Hangy�val val� �tk�z�s. Nem csin�l semmit, csak visszat�r.
	 * 
	 * @param ant
	 *            a hangya referenci�ja. amivel �tk�z�tt
	 * @param b
	 *            �tk�z�s el�tt: false, �tk�z�s ut�n: true -
	 */
	@Override
	public void collisionWithAnt(Ant ant, boolean b) {
		Singleton s = Singleton.Instance();

	}

	/**
	 * - Hangy�szs�nel val� �tk�z�s. A hangya meghal.
	 * 
	 * @param echidna
	 *            referencia a hangy�szs�nre, amivel �tk�zik
	 */
	@Override
	public void collisionWithEchidna(Echidna echidna) {
		Singleton s = Singleton.Instance();

		/*
		 * A hangya sz�l a hangy�szs�nnek, hogy hangy�val �tk�z�tt, amely ennek
		 * hat�s�ra meg�li
		 */
		Ant ant = new Ant();
		echidna.collisionWithAnt(ant, true);
		
	}

	/**
	 * Spray-vel val� �tk�z�s. A hangya vagy meghal, vagy �leter�t vesz�t
	 * 
	 * @param strength
	 *            a hangya �letereje ennyivel fog cs�kkenni
	 */
	@Override
	public void collisionWithSpray(Integer strength) {
		Singleton s = Singleton.Instance();

		/* A hangya looseHP met�dus�nak megh�v�sa */
		Ant ant = new Ant();
		ant.looseHP(6);
		
	}

	/**
	 * Akad�llyal val� �tk�z�s eset�n az akad�ly ezzel a met�dussal jelzi a
	 * hangy�nak, hogy az �t tartalmaz� mez�re nem l�phet. Ezt a mez�t a cs�p
	 * kiveszi a lehets�ges szomsz�dok k�z�l. Mindig l�p�s el�tt h�v�dik meg,
	 * mivel az nem fordulhat el�. hogy a hangya olyan mez�re l�p, ahol akad�ly
	 * van.
	 * 
	 * @param field
	 *            az a mez�, ahova nem l�phet a hangya
	 */
	public void canNotGo(Field field) {
		Singleton s = Singleton.Instance();
	/* A param�terk�nt megadott mez� kiv�tele a lehets�ges mez�k list�j�b�l */
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
