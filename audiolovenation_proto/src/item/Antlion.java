package item;

import land.Field;
import movable.Ant;
import movable.Echidna;
import program.Singleton;

/**
 * 
 * @author audiolovenation
 * 
 *         Az erre az elemre l�p� hangya detekt�lja, hogy mivel tal�lkozott, �s
 *         elpusztul. A hangy�szs�nnel nem foglalkozik.
 */
public class Antlion extends Item {
	/**
	 * Hangy�val val� �tk�z�s. Ha �hes megeszi a hangy�t.
	 * 
	 * 
	 * @param ant
	 *            a hangya referenci�ja. amivel �tk�z�tt
	 * @param b
	 *            l�p�s el�tt: false, l�p�s ut�n: true
	 */
	@Override
	public void collisionWithAnt(Ant ant, boolean b) {
		Singleton s = Singleton.Instance();

		// ha m�r l�pett a hangya
		if (b == true) {
			
			ant.kill();
			
		}
	}

	/**
	 * Hangy�szs�nnel val� �tk�z�s. Nem csin�l semmit, csak visszat�r.
	 * 
	 * @param echidna
	 *            az a hangy�szs�n. amivel �tk�z�tt
	 */
	@Override
	public void collisionWithEchidna(Echidna echidna) {
		Singleton s = Singleton.Instance();

	}

	/**
	 * Hangy�szs�nnel val� �tk�z�s. Nem csin�l semmit, csak visszat�r.
	 * 
	 * @param strength
	 *            a Spray er�ss�ge
	 */
	@Override
	public void collisionWithSpray(Integer strength) {
		Singleton s = Singleton.Instance();

	}
}