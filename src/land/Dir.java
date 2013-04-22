package land;

/**
 * 
 * @author audiolovenation
 * 
 *         Egy enumeracio, amivel a mezok egymashoz valo viszonyait kezeljuk.
 *         Fent, lent, jobb fent, bal fent, jobb lent, bal lent, lent ertekekkel
 *         rendelkezik.
 */
public enum Dir {
	UP(0), DOWN(3), RIGHT_TOP(1), LEFT_TOP(5), RIGHT_BOTTOM(2), LEFT_BOTTOM(4);
	private int value;

    private Dir(int value) {
            this.value = value;
    }	
    
    public int getValue(){
    	return value;
    }
    
    public static Dir fromInteger(int x) {
        switch(x) {
        case 0:
            return UP;
        case 1:
            return RIGHT_TOP;
        case 2:
            return RIGHT_BOTTOM;
        case 3:
            return DOWN;
        case 4:
            return LEFT_BOTTOM;
        case 5:
            return LEFT_TOP;
            
        }
        return null;
    }
}