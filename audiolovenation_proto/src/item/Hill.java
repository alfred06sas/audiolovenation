package item;

import program.Singleton;
import land.Dir;
import movable.Ant;
import movable.Echidna;

/**
 * 
 * @author audiolovenation
 * 
 *         A letrejott hangyak kiindulasi pontja (boly), ahonnan a hangyak
 *         indulnak etelt szerezni. Ha egy hangya talalt etelt, akkor ezt ide
 *         probalja visszajuttatni. Ha sikerul, a hangya vissza megy a bolyba(inaktiv allapotba kerul).
 */
public class Hill extends Item {

	/**
	 * Hangyaval valo utkozes.
	 * 
	 * @param Ant
	 *            a hangya ami utkozik a bollyal
	 * @param b
	 *            utkozes elott: false, utkozes utan: true
	 */
	@Override
	public void collisionWithAnt(Ant ant, boolean b) {
		Singleton s = Singleton.Instance();

		Ant ant1 = new Ant();
		//A hangyabolyba visszaero hangya inaktiv allapotba kerul
		ant1.rest();
		
	}

	/**
	 * Hangyaszsunnel valo utkozes.
	 * 
	 * @param Echidna
	 *            a hangyaszun ami utkozik a bollyal
	 */
	@Override
	public void collisionWithEchidna(Echidna echidna) {
		Singleton s = Singleton.Instance();

		
	}
}