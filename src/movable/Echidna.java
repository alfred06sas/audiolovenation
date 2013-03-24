package movable;

import java.util.Map;
import java.util.TreeMap;

import land.Dir;
import land.Field;
import program.Singleton;
import program.SingletonContainer;
import smell.AntSmell;
import smell.Smell;
import item.Item;
import item.Tentacle;
import item.Volatile;

/**
 * 
 * A hangy�szs�n viselked�s�t val�s�tja meg. A p�lya inicializ�l�s�n�l el�re
 * meghat�rozott sz�m�t hozunk l�tre bel�le, melyek nem mindig vannak jelen
 * (inakt�vak). Megadott id�k�z�nk�nt jelennek meg (aktiviz�l�dnak). Van egy
 * �hs�g tulajdons�ga, aminek f�ggv�ny�ben bizonyos mennyis�g�, az �tj�ba ker�l�
 * hangy�t elfogyaszt, majd elt�nik. Amennyiben m�s elemmel tal�lkozik, akkor
 * nem t�rt�nik semmi, halad tov�bb.
 * 
 * @author audiolovenation
 * 
 */
public class Echidna extends Item implements Movable {

	/**
	 * Akt�v/inakt�v �llapot jelz�se
	 */
	private boolean isActive = false;
	/**
	 * Inakt�v �llapotb�l h�tramarad� k�r�k sz�ma
	 */
	private Integer wait;
	/**
	 * �hs�g m�rt�ke. Ha 0, akkor inakt�v �llapotba ker�l meghat�rozott k�rre.
	 */
	private Integer hunger;

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

		s.makeSpace(">> CALL: " + id + ": Echidna.setActualField("
				+ s.fields.indexOf(field) + ": Field)");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Echidna.setActualField("
				+ s.fields.indexOf(field) + ": Field)");
		s.depth--;
	}

	/**
	 * �hs�g (hunger attrib�tum) cs�kkent�se meghat�rozott �rt�kkel.
	 */
	public void decreaseHunger() {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Echidna.decreaseHunger()");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Echidna.decreaseHunger()");
		s.depth--;
	}

	/**
	 * �letre kelt�s (wait == 0)
	 */
	@Override
	public void setAlive() {

	}

	/**
	 * - Hangy�szs�nel val� �tk�z�s. Nem t�rt�nik semmmi.
	 * 
	 * @param echidna
	 *            referencia a hangy�szs�nre, amivel �tk�zik
	 */
	@Override
	public void collisionWithEchidna(Echidna echidna) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Echidna.collisionWithEchidna("
				+ s.items.indexOf(echidna) + ": Echidna)");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Echidna.collisionWithEchidna("
				+ s.items.indexOf(echidna) + ": Echidna)");
		s.depth--;
	}

	/**
	 * A hangy�szs�n l�ptet�se.
	 */
	@Override
	public void step() {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Echidna.step()");

		Echidna echidna = (Echidna) s.items.get(id);

		/* A szomsz�dok lek�rdez�se */
		s.stack.add(12);
		Field prevField = s.fields.get(11);
		prevField.getNeighbours();
		s.stack.remove(s.stack.size() - 1);

		/* A hangy�szs�n t�rl�se a mez�r�l */
		s.stack.add(12);
		prevField.removeItem(echidna);
		s.stack.remove(s.stack.size() - 1);

		/* Hangy�szs�n �tl�p a k�vezkez� mez�re */
		s.stack.add(11);
		Field nextField = s.fields.get(10);
		nextField.addItem(echidna);
		nextField.getItems();
		s.stack.remove(s.stack.size() - 1);

		/* Az ott lev� elemekkel val� �tk�z�s */
		s.stack.add(10);
		Echidna echidna2 = (Echidna) s.items.get(9);
		echidna2.collisionWithEchidna(echidna);
		s.stack.remove(s.stack.size() - 1);

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Echidna.step()");
		s.depth--;

	}

	/**
	 * Hangy�val val� �tk�z�s. A hangya meg�l�se. L�p�s el�tt nem csin�l semmit.
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

		s.makeSpace(">> CALL: " + id + ": Echidna.collisionWithAnt("
				+ s.ants.indexOf(ant) + ": Ant, " + String.valueOf(b)
				+ ": boolean)");

		/* L�p�s ut�n */
		if (b == true) {
			/* Hangya meg�l�se */
			s.stack.add(s.items.indexOf(ant));
			ant.kill();
			s.stack.remove(s.stack.size() - 1);

			/* �hs�g cs�kkent�se */
			s.stack.add(id);
			Echidna echidna = (Echidna) s.items.get(id - 1);
			echidna.decreaseHunger();
			s.stack.remove(s.stack.size() - 1);
		}
		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Echidna.collisionWithAnt("
				+ s.ants.indexOf(ant) + ": Ant, " + String.valueOf(b)
				+ ": boolean)");

		s.depth--;
	}
}