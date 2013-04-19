package item;

import land.Field;
import movable.Ant;
import movable.Echidna;
import program.Singleton;

/**
 * 
 * @author audiolovenation
 * 
 *         Az erre az elemre lepo hangya detektalja, hogy mivel talalkozott, es
 *         elpusztul. A hangyaszsunnel nem foglalkozik.
 */
public class Antlion extends Item {
	/**
	 * Hangyaval valo utkozes. Ha ehes megeszi a hangyat.
	 * 
	 * 
	 * @param ant
	 *            a hangya referenciaja. amivel utkozott
	 * @param b
	 *            lepes elott: false, lepes utan: true
	 */
	@Override
	public void collisionWithAnt(Ant ant, boolean b) {
		// ha mar lepett a hangya
		if (b == true) {
			ant.kill();
		}
	}

	/**
	 * Hangyaszsunnel valu utkozes. Nem csinïal semmit, csak visszater.
	 * 
	 * @param echidna
	 *            az a hangyaszsun. amivel utkozott
	 */
	@Override
	public void collisionWithEchidna(Echidna echidna) {
	}

	/**
	 * Hangyaszsunnel valu utkozes. Nem csinïal semmit, csak visszater.
	 * 
	 * @param strength
	 *            a Spray erossege
	 */
	@Override
	public void collisionWithSpray(Integer strength) {
	}
}