package program;

import item.Volatile;

import java.util.ArrayList;
import java.util.List;

import land.Land;
import movable.Movable;

/**
 * 
 * @author audiolovenation
 * 
 *         A getIstance() metodusa ugyan azt az objektumot adja vissza. A
 *         Volatile es a Movable interfeszeket megvalosito osztalyok tarolasara.
 *         A palya vegzi a leptetest es az illekony anyagok csokkenteset is.
 *         Erre a megoldasra azert volt szukseg, hogy a palya szamara elerhetoek
 *         legyenek ezek a listak, ugyanakkor mind a Volatile es a Movable is
 *         hozzïa tudjon adni elemeket a sajat listajahoz. Igy nem kellett
 *         mindenki szamara lathatova tenni az egesz palyat (Land osztaly).
 */
public class SingletonContainer {

	private Land container;
	private List<Movable> movables;
	private List<Volatile> volatiles;
	private static SingletonContainer instance;
	private int antKillerSpray;
	private int antSmellSpray;
	private int numberOfRound;

	public SingletonContainer(){
		container = new Land();
		movables = new ArrayList<Movable>();
		volatiles = new ArrayList<Volatile>();
		instance = null;
		antKillerSpray = 10;
		antSmellSpray = 10;
		numberOfRound = 0;
	}
	/**
	 * 
	 * Az illekony elemek hozzaadasa az oket taroa listaba.
	 * 
	 * @param Volatile
	 *            a hozzaadanda elem
	 */
	public void addVolatile(Volatile volatile_) {
		volatiles.add(volatile_);
	}

	/**
	 * 
	 * Az mozgo elemek hozzaadasa az oket tarolo listaba.
	 * 
	 * @param Movable
	 *            a hozzaadando elem
	 */
	public void addMovable(Movable movable) {
		movables.add(movable);
	}

	/**
	 * 
	 * A SingletonContainer referenciajunak lekerdezese.
	 * 
	 * @return a SingletonContainerre mutato referencia
	 */
	public static SingletonContainer getInstance() {
		if (instance == null)
			instance = new SingletonContainer();
		return instance;
	}

	/**
	 * 
	 * Mozgasra kepes elemek lekerdezese.
	 * 
	 * @return
	 */
	public List<Movable> getMovables() {
		return movables;
	}

	/**
	 * 
	 * Illekony elemek lekerdezese.
	 * 
	 * @return
	 */
	public List<Volatile> getVolatiles() {
		return volatiles;
	}

	/**
	 * 
	 * Egy illekony elem kitorlese az azokat tarolo listabol.
	 * 
	 * @param Volatile
	 *            a kitorlendo elem
	 */
	public void removeVolatile(Volatile volatile_) {
		volatiles.remove(volatile_);
	}

	/**
	 * 
	 * Egy mozgo elem kitorlese az azokat tarola listabol.
	 * 
	 * @param Movable
	 *            a kiterlendo elem
	 */
	public void removeMovable(Movable movable) {
		movables.remove(movable);
	}

	/**
	 * 
	 * A hangyairo-spray erejenek csokkentese.
	 * 
	 * @param Integer
	 *            a csokkentes merteke
	 */
	public void decreaseAntKillerSpray(Integer strength) {
		antKillerSpray-=strength;
	}

	/**
	 * 
	 * A hangyaszagirto-spray erejenek csokkentese.
	 * 
	 * @param Integer
	 *            a csokkentes merteke
	 */
	public void decreaseAntSmellSpray(Integer strength) {
		antSmellSpray-=strength;		
	}

	public int getNumberOfRound(){
		return numberOfRound;
	}
	public void increaseOfRoundNumber() {
		numberOfRound++;
		
	}
}