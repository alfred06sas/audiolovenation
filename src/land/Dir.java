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
	UP(1), DOWN(4), RIGHT_TOP(2), LEFT_TOP(6), RIGHT_BOTTOM(3), LEFT_BOTTOM(5);
	private int value;

    private Dir(int value) {
            this.value = value;
    }	
    
    public int getValue(){
    	return value;
    }
    
    public static Dir fromInteger(int x) {
        switch(x) {
        case 1:
            return UP;
        case 2:
            return RIGHT_TOP;
        case 3:
            return RIGHT_BOTTOM;
        case 4:
            return DOWN;
        case 5:
            return LEFT_BOTTOM;
        case 6:
            return LEFT_TOP;
            
        }
        return null;
    }
}