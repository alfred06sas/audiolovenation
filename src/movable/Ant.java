package movable;

import item.Item;
import item.Tentacle;
import item.Volatile;

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
	 * Aktu�lis mez� be�ll�t�sa.
	 * 
	 * @param field
	 *            a be�ll�tand� mez�
	 */
	@Override
	public void setActualField(Field field) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Ant.setActualField("
				+ s.fields.indexOf(field) + ": Field)");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Ant.setActualField("
				+ s.fields.indexOf(field) + ": Field)");
		s.depth--;
	}

	/**
	 * �leter� lecs�kkent�se a megadott �rt�kkel.
	 * 
	 * @param hp
	 *            a cs�kkent�s m�rt�ke
	 */
	public void looseHP(Integer hp) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Ant.looseHP(" + hp + ": Integer)");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Ant.looseHP(" + hp + ": Integer)");
		s.depth--;
	}

	/**
	 * A hangya �telt vesz fel. �llapot�t �t�ll�tja (haveFood = true).
	 */
	public void pickUpFood() {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Ant.pickupFood()");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Ant.pickUpFood()");
		s.depth--;
	}

	/**
	 * Pihenteti a hangy�t egy adott ideig [am�g el nem indul (�jra) a
	 * bolyb�l)].
	 */
	public void rest() {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Ant.rest()");
		s.depth--;

		s.makeSpace("<< RETURN: " + id + ": Ant.rest()");
		s.depth--;
	}

	/**
	 * A hangya ir�ny�nak megford�t�sa.
	 */
	public void reverseDir() {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Ant.reverseDir()");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Ant.reverseDir()");
		s.depth--;
	}

	/**
	 * A hangya meg�l�se. T�rli mag�t a mez�r�l �s a SingletonContainer-ben lev�
	 * movables list�b�l is.
	 */
	public void kill() {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Ant.kill()");

		/* Hangya t�rl�se az �t tartalmaz� mez�r�l */
		s.stack.add(17);
		Field field = s.fields.get(16);
		field.removeItem(s.ants.get(id));
		s.stack.remove(s.stack.size() - 1);

		/* Hangya t�rl�se a SingletonContainer movables list�j�b�l */
		s.stack.add(1);
		SingletonContainer sc = s.singletonContainer.get(0).getInstance();
		sc.removeMovable(s.ants.get(id));
		s.stack.remove(s.stack.size() - 1);

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Ant.kill()");
		s.depth--;
	}

	/**
	 * Halad�si ir�ny be�ll�t�sa.
	 * 
	 * @param dir
	 *            a be�ll�tand� halad�si ir�ny
	 */
	public void setDir(Dir dir) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Ant.setDir(" + dir.toString()
				+ ": Dir)");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Ant.setDir(" + dir.toString()
				+ ": Dir)");
		s.depth--;
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

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Ant.step()");

		/* A szomsz�dok lek�rdez�se */
		s.stack.add(12);
		Field prevField = s.fields.get(11);
		prevField.getNeighbours();
		s.stack.remove(s.stack.size() - 1);

		/* A lehets�ges szomsz�dok be�ll�t�sa */
		s.stack.add(9);
		Tentacle tentacle = s.tentacles.get(8);
		Map<Dir, Field> map = new TreeMap<Dir, Field>();
		map.put(Dir.DOWN, prevField);
		tentacle.setPossibleNeighbours(map);
		s.stack.remove(s.stack.size() - 1);

		/* A lehets�ges szomsz�dok item-einek egy ciklusban val� lek�rdez�se */
		s.stack.add(11);
		Field nextField = s.fields.get(10);
		nextField.getItems();
		s.stack.remove(s.stack.size() - 1);

		/*
		 * Az elemekkel val� �tk�ztet�s m�g l�p�s el�tt, hogy kider�lj�n, hol
		 * tal�lhat� akad�ly
		 */
		s.stack.add(5);
		Ant ant = (Ant) s.items.get(id);
		Ant ant2 = (Ant) s.items.get(4);
		ant2.collisionWithAnt(ant, false);
		s.stack.remove(s.stack.size() - 1);

		/*
		 * A cs�p scan met�dus�nak megh�v�sa, amely kiv�lasztja a k�vetkez�
		 * mez�t.
		 */
		s.stack.add(9);
		Map<Dir, Field> map2 = tentacle.scan(true);
		s.stack.remove(s.stack.size() - 1);

		/* Ha van olyan mez�, ahol nincs akad�ly */
		if (map2 != null) {
			/* Hangyaszag hagy�sa az el�z� mez�n */
			s.stack.add(12);
			Smell smell = (AntSmell) s.smells.get(4);
			prevField.addSmell(smell);
			s.stack.remove(s.stack.size() - 1);

			/* Hangyaszag hozz�ad�sa a SingletonContainer volatiles list�j�hoz */
			s.stack.add(1);
			SingletonContainer sc = s.singletonContainer.get(0);
			Volatile smell2 = (AntSmell) s.smells.get(4);
			sc.addVolatile(smell2);
			s.stack.remove(s.stack.size() - 1);

			/* El�z� mez�r�l elt�vol�tja saj�t mag�t */
			s.stack.add(12);
			prevField.removeItem(ant);
			s.stack.remove(s.stack.size() - 1);

			/* K�vetkez� mez� be�ll�t�sa */
			s.stack.add(2);
			ant.setActualField(nextField);
			s.stack.remove(s.stack.size() - 1);

			/* K�vetkez� mez�be val� beregisztr�l�s */
			s.stack.add(11);
			nextField.addItem(ant);
			s.stack.remove(s.stack.size() - 1);
			/* Ha nem tud sehova se menni */
		} else {
			/* Ir�ny megford�t�sa */
			s.stack.add(5);
			ant.reverseDir();
			s.stack.remove(s.stack.size() - 1);
		}
		/* l�p�s ut�ni �tk�ztet�s */
		s.stack.add(8);
		Echidna echidna = (Echidna) s.items.get(7);
		echidna.collisionWithAnt(ant, true);
		s.stack.remove(s.stack.size() - 1);

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Ant.step()");
		s.depth--;

	}

	/**
	 * Hangya �llapot�nak inakt�vr�l akt�vra �ll�t�sa (wait == 0)
	 */
	@Override
	public void setAlive() {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Ant.setAlive()");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Ant.setAlive()");
		s.depth--;
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

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Ant.collisionWithAnt("
				+ s.ants.indexOf(ant) + ": Ant, " + String.valueOf(b) + ")");
		s.depth--;

		s.makeSpace("<< RETURN: " + id + ": Ant.collisionWithAnt("
				+ s.ants.indexOf(ant) + ": Ant, " + String.valueOf(b) + ")");
		s.depth--;
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

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Ant.collisionWithEchidna("
				+ s.echidnas.indexOf(echidna) + ": Echidna)");

		/*
		 * A hangya sz�l a hangy�szs�nnek, hogy hangy�val �tk�z�tt, amely ennek
		 * hat�s�ra meg�li
		 */
		s.stack.add(s.items.indexOf(echidna));
		Ant ant = (Ant) s.items.get(id - 1);
		echidna.collisionWithAnt(ant, true);
		s.stack.remove(s.stack.size() - 1);

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Ant.collisionWithEchidna("
				+ s.echidnas.indexOf(echidna) + ": Echidna)");
		s.depth--;
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

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Ant.collisionWithSpray(" + strength
				+ ": Integer)");

		/* A hangya looseHP met�dus�nak megh�v�sa */
		s.stack.add(id);
		Ant ant = s.ants.get(id);
		ant.looseHP(6);
		s.stack.remove(s.stack.size() - 1);

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Ant.collisionWithSpray(" + strength
				+ ": Integer)");
		s.depth--;
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

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Ant.canNotGo("
				+ s.fields.indexOf(field) + ": Field)");

		/* A param�terk�nt megadott mez� kiv�tele a lehets�ges mez�k list�j�b�l */
		s.stack.add(17);
		Tentacle tentacle = s.tentacles.get(16);
		tentacle.removePossibleNeighbour(field);
		s.stack.remove(s.stack.size() - 1);

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Ant.canNotGo("
				+ s.fields.indexOf(field) + ": Field)");
		s.depth--;
	}
}
