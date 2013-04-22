package blockage;

import item.Item;

import java.util.Map;

import land.Dir;
import land.Field;
import movable.Ant;
import movable.Echidna;

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
	
	public Gravel(String ID){
		super(ID);
		id="g"+ID;
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
//		if (b == false) {
//			ant.canNotGo(getActualField());
//		}

	}

	/**
	 * Hangyaszsunnel valo utkozes. Nem csinal semmit, csak visszater.
	 * 
	 * @param echidna
	 *            az a hangyaszsun. amivel utkozott
	 */
	@Override
	public void collisionWithEchidna(Echidna echidna) {
		boolean ketto=false;
		boolean harom=false;
		
		Dir dir=echidna.getDir();
		Map<Dir, Field> neig=getActualField().getNeighbours();
		
		Gravel gravleKetto=null;
		
		for (Item item : neig.get(dir).getItems()){
			try{
				gravleKetto = (Gravel) item;
				ketto = true;
			}
			catch (ClassCastException e){}
		}
		
		for (Item item : neig.get(dir).getNeighbours().get(dir).getItems()){
			try{
				Gravel gravle = (Gravel) item;
				harom = true;
			}
			catch (ClassCastException e){}
		}
		if (!((ketto)&&(harom))){
			getActualField().removeItem(this);
			setActualField(neig.get(dir));
			getActualField().addItem(this);
		}
		
		if ((ketto)&&(!harom)){
			gravleKetto.getActualField().removeItem(this);
			gravleKetto.setActualField(neig.get(dir));
			gravleKetto.getActualField().addItem(this);
		}
		
	}

}