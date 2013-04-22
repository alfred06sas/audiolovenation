package blockage;


/**
 * 
 * Egy akadaly (tocsa), ha a hangya egy ilyen mezore akar lepni, akkor kenytelen
 * masik utvonalat valasztani.
 * 
 * @author audiolovenation
 * 
 */
public class Puddle extends Blockage {
	
	public Puddle(){
	}
	
	public Puddle(String ID){
		super(ID);
		id="p"+ID;
	}

}