package item;

import movable.Ant;
import view.AntlionView;

/**
 * 
 * @author audiolovenation
 * 
 *         Az erre az elemre lepo hangya detektalja, hogy mivel talalkozott, es
 *         elpusztul. A hangyaszsunnel nem foglalkozik.
 */
public class Antlion extends Item {
	AntlionView antlionView;
	
	public Antlion(){
	}
	
	public void setView(){
		antlionView = new AntlionView();
		antlionView.setPaintable(this);
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
		// ha mar lepett a hangya
		if (b == true) {
			ant.kill();
		}
	}
	
	@Override
	public void notifyView() {
		antlionView.onDraw();
	}
}