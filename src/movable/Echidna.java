package movable;

import item.Item;

import java.util.HashMap;

import land.Field;
import program.Singleton;

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

		
	}

	/**
	 * �hs�g (hunger attrib�tum) cs�kkent�se meghat�rozott �rt�kkel.
	 */
	public void decreaseHunger() {
		Singleton s = Singleton.Instance();

		
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

		
	}

	/**
	 * A hangy�szs�n l�ptet�se.
	 */
	@Override
	public void step() {
		Singleton s = Singleton.Instance();

		
		Echidna echidna = new Echidna();

		/* A szomsz�dok lek�rdez�se */
		Field prevField = new Field();
		prevField.getNeighbours();
	
		/* A hangy�szs�n t�rl�se a mez�r�l */
		prevField.removeItem(echidna);
	
		/* Hangy�szs�n �tl�p a k�vezkez� mez�re */
		Field nextField = new Field();
		nextField.addItem(echidna);
		nextField.getItems();
		
		/* Az ott lev� elemekkel val� �tk�z�s */
		Echidna echidna2 = new Echidna();
		echidna2.collisionWithEchidna(echidna);
		
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

		/* L�p�s ut�n */
		if (b == true) {
			/* Hangya meg�l�se */
			ant.kill();
		
			/* �hs�g cs�kkent�se */
			Echidna echidna = new Echidna();
			echidna.decreaseHunger();
		}
	
	}
	
	@Override
	public HashMap<String, String> getStates() {
		HashMap<String, String> states = new HashMap<String, String>();
		
		if (wait > 0)
			states.put("STATE", "WAIT");
		else {
			if (hunger > 0)
				states.put("STATE", "HUNGRY");
			else
				states.put("STATE", "NOT_HUNGRY");
		}
		
		return states;
	}
}