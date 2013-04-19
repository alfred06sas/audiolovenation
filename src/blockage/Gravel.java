package blockage;

import land.Field;
import movable.Ant;
import movable.Echidna;
import program.Singleton;

/**
 * 
 * Egy akadaly (kavics). Ha a hangya ilyen mezore akar lepni, kenytelen masik
 * utvonalat valasztani. A hangyaszsun figyelmen kivul hagyja.
 * 
 * @author audiolovenation
 * 
 */
public class Gravel extends Blockage {

	public Gravel(){
	}
	
	/**
	 * Hangyaval valo utkozes. Lepes utan (b == true) nem csinal semmit, mivel
	 * ez az eset nem lehetseges: a hangya nem lephet akadalyra. Lepes elott (b
	 * == false) jelzi a hangyanak h az ot tartalmazo mezore nem lephet.
	 * 
	 * @param ant
	 *            a hangya referenciája. amivel utkozott
	 * @param b
	 *            lepes elott: false, lepes utan: true
	 */
	@Override
	public void collisionWithAnt(Ant ant, boolean b) {
		Singleton s = Singleton.Instance();

		if (b == false) {
			
			Field field = new Field();
			ant.canNotGo(field);
			
		}

	}

	/**
	 * Hangyaszsunnel valo utkozes. Nem csinal semmit, csak visszater.
	 * 
	 * @param echidna
	 *            az a hangyaszsun. amivel utkozott
	 */
	@Override
	public void collisionWithEchidna(Echidna echidna) {
		Singleton s = Singleton.Instance();

		
	}

}