package movable;

/**
 * 
 * A mozg�sra k�pes elemek k�z�s tulajdons�gai vannak itt elt�rolva. Az ezt
 * megval�s�t� elemek k�pesek mozogni a mez�k k�z�tt, �s �rz�kelni az
 * �tk�z�seket.
 * 
 * @author audiolovenation
 * 
 */
public interface Movable {

	/**
	 * L�ptet�s.
	 */
	void step();

	/**
	 * �letre kelt�s (wait == 0)
	 */
	void setAlive();

}