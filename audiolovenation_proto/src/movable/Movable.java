package movable;

/**
 * 
 * A mozgásra képes elemek közös tulajdonságai vannak itt eltárolva. Az ezt
 * megvalósító elemek képesek mozogni a mezõk között, és érzékelni az
 * ütközéseket.
 * 
 * @author audiolovenation
 * 
 */
public interface Movable {

	/**
	 * Léptetés.
	 */
	void step();

	/**
	 * Életre keltés (wait == 0)
	 */
	void setAlive();

}