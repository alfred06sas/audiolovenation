package item;

import land.Dir;
import movable.Ant;
import program.SingletonWriter;
import view.HillView;
import blockage.Gravel;

/**
 * 
 * @author audiolovenation
 * 
 *         A letrejott hangyak kiindulasi pontja (boly), ahonnan a hangyak
 *         indulnak etelt szerezni. Ha egy hangya talalt etelt, akkor ezt ide
 *         probalja visszajuttatni. Ha sikerul, a hangya vissza megy a
 *         bolyba(inaktiv allapotba kerul).
 */
public class Hill extends Item {

	private HillView hillView;

	public Hill() {
	}

	public void setView(){
		hillView = new HillView();
		hillView.setPaintable(this);
	}
	
	public Hill(String ID) {
		super(ID);
		id = "h" + ID;
	}

	/**
	 * Hangyaval valo utkozes.
	 * 
	 * @param Ant
	 *            a hangya ami utkozik a bollyal
	 * @param b
	 *            lepes elott: false, lepes utan: true
	 */
	@Override
	public void collisionWithAnt(Ant ant, boolean b) {
		SingletonWriter s = SingletonWriter.Instance();	
		// A hangyabolyba visszaero hangya inaktiv allapotba kerul
		if(b==true){
			ant.rest();
			s.printCollision(ant, this, actualField);
		}
	}

	@Override
	public int collisionWithGravel(Gravel gravel, boolean b, Dir dir) {
		return 3;
	}
	
	@Override
	public void notifyView() {
		hillView.onDraw();
	}
}