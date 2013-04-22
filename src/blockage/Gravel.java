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
	 * Hangyaszsunnel valo utkozes. Nem csinal semmit, csak visszater.
	 * 
	 * @param echidna
	 *            az a hangyaszsun. amivel utkozott
	 */
	@Override
	public void collisionWithEchidna(Echidna echidna, boolean b, Dir dir) {
		boolean ketto=false;
		boolean harom=false;
		
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