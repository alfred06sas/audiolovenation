package movable;

/**
 * 
 * A mozgasra kepes elemek kozos tulajdonsagai vannak itt eltarolva. Az ezt
 * megvalosito elemek kepesek mozogni a mezok kozott, és erzekelni az
 * utkozeseket.
 * 
 * @author audiolovenation
 * 
 */
public interface Movable {

	/**
	 * Leptetes.
	 */
	void step();

	/**
	 * Eletre keltes (wait == 0)
	 */
	void setAlive();

}