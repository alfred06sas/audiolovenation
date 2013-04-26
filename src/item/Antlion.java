package item;

import program.SingletonWriter;
import movable.Ant;

/**
 * 
 * @author audiolovenation
 * 
 *         Az erre az elemre lepo hangya detektalja, hogy mivel talalkozott, es
 *         elpusztul. A hangyaszsunnel nem foglalkozik.
 */
public class Antlion extends Item {

	public Antlion(){
	}
	public Antlion(String ID){
		super(ID);
		id="l"+ID;
	}
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
		SingletonWriter s = SingletonWriter.Instance();
		// ha mar lepett a hangya
		if (b == true) {
			ant.kill();
			s.printCollision(ant, this, actualField);
		}
	}
}