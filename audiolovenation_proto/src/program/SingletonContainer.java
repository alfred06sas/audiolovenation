package program;

import item.Volatile;

import java.util.List;

import land.Land;
import movable.Movable;
import smell.Smell;

/**
 * 
 * @author audiolovenation
 * 
 *         A getIstance() metodusa ugyan azt az objektumot adja vissza. A
 *         Volatile �s a Movable interf�szeket megval�s�t� oszt�lyok t�rol�s�ra.
 *         A p�lya v�gzi a l�ptet�st �s az ill�kony anyagok cs�kkent�s�t is.
 *         Erre a megold�sra az�rt volt sz�ks�g, hogy a p�lya sz�m�ra el�rhet�ek
 *         legyenek ezek a list�k, ugyanakkor mind a Volatile �s a Movable is
 *         hozz� tudjon adni elemeket a saj�t list�j�hoz. �gy nem kellett
 *         mindenki sz�m�ra l�that�v� tenni az eg�sz p�ly�t (Land oszt�ly).
 */
public class SingletonContainer {

	private Land container;
	private List<Movable> movable;
	private List<Volatile> volatiles;
	private static SingletonContainer instance;
	private Integer antKillerSpray;
	private Integer antSnellSpray;

	/**
	 * 
	 * Az ill�kony elemek hozz�ad�sa az �ket t�rol� list�ba.
	 * 
	 * @param Volatile
	 *            a hozz�adand� elem
	 */
	public void addVolatile(Volatile volatile_) {
		Singleton s = Singleton.Instance();

	
	}

	/**
	 * 
	 * Az mozg� elemek hozz�ad�sa az �ket t�rol� list�ba.
	 * 
	 * @param Movable
	 *            a hozz�adand� elem
	 */
	public void addMovable(Movable movable) {
		Singleton s = Singleton.Instance();

		
	}

	/**
	 * 
	 * A SingletonContainer referenci�j�nak lek�r�se.
	 * 
	 * @return a SingletonContainerre mutat� referencia
	 */
	public static SingletonContainer getInstance() {
		Singleton s = Singleton.Instance();

	
		return new SingletonContainer();
	}

	/**
	 * 
	 * Mozg�sra k�pes elemek lek�rdez�se.
	 * 
	 * @return
	 */
	public List<Movable> getMovables() {
		Singleton s = Singleton.Instance();

	

		return null;
	}

	/**
	 * 
	 * Ill��kony elemek lek�rdez�se.
	 * 
	 * @return
	 */
	public List<Volatile> getVolatiles() {
		Singleton s = Singleton.Instance();


		return null;
	}

	/**
	 * 
	 * Egy ill�kony elem kit�rl�se az azokat t�rol� list�b�l.
	 * 
	 * @param Volatile
	 *            a kit�rlend� elem
	 */
	public void removeVolatile(Volatile volatile_) {
	}

	/**
	 * 
	 * Egy mozg� elem kit�rl�se az azokat t�rol� list�b�l.
	 * 
	 * @param Movable
	 *            a kit�rlend� elem
	 */
	public void removeMovable(Movable movable) {
		Singleton s = Singleton.Instance();

		
	}

	/**
	 * 
	 * A hangya�rt�-spray erej�nek cs�kkents�se.
	 * 
	 * @param Integer
	 *            a cs�kkent�s mr�t�ke
	 */
	public void decreaseAntKillerSpray(Integer strength) {
		Singleton s = Singleton.Instance();

		
	}

	/**
	 * 
	 * A hangyaszag�rt�-spray erej�nek cs�kkents�se.
	 * 
	 * @param Integer
	 *            a cs�kkent�s mr�t�ke
	 */
	public void decreaseAntSmellSpray(Integer strength) {
		Singleton s = Singleton.Instance();

		
	}

}