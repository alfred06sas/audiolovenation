package blockage;

import view.PuddleView;


/**
 * 
 * Egy akadaly (tocsa), ha a hangya egy ilyen mezore akar lepni, akkor kenytelen
 * masik utvonalat valasztani.
 * 
 * @author audiolovenation
 * 
 */
public class Puddle extends Blockage {
	
	private PuddleView puddleView;

	public Puddle(){
	}
	
	public void setView(){
		puddleView = new PuddleView();
		puddleView.setPaintable(this);
	}
	
	public Puddle(String ID){
		super(ID);
		id="p"+ID;
	}

}