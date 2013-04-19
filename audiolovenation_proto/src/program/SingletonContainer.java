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
 *         Volatile es a Movable interfeszeket megvalosito osztalyok tarolasara.
 *         A palya vegzi a leptetest es az illekony anyagok csokkenteset is.
 *         Erre a megoldasra azert volt szukseg, hogy a palya szamara elerhetoek
 *         legyenek ezek a listak, ugyanakkor mind a Volatile es a Movable is
 *         hozzïa tudjon adni elemeket a sajat listajahoz. Igy nem kellett
 *         mindenki szamara lathatova tenni az egesz palyat (Land osztaly).
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
	 * Az illekony elemek hozzaadasa az oket taroa listaba.
	 * 
	 * @param Volatile
	 *            a hozzaadanda elem
	 */
	public void addVolatile(Volatile volatile_) {
		Singleton s = Singleton.Instance();

	
	}

	/**
	 * 
	 * Az mozgo elemek hozzaadasa az oket tarolo listaba.
	 * 
	 * @param Movable
	 *            a hozzaadando elem
	 */
	public void addMovable(Movable movable) {
		Singleton s = Singleton.Instance();

		
	}

	/**
	 * 
	 * A SingletonContainer referenciajunak lekerdezese.
	 * 
	 * @return a SingletonContainerre mutato referencia
	 */
	public static SingletonContainer getInstance() {
		Singleton s = Singleton.Instance();

	
		return new SingletonContainer();
	}

	/**
	 * 
	 * Mozgasra kepes elemek lekerdezese.
	 * 
	 * @return
	 */
	public List<Movable> getMovables() {
		Singleton s = Singleton.Instance();

	

		return null;
	}

	/**
	 * 
	 * Illekony elemek lekerdezese.
	 * 
	 * @return
	 */
	public List<Volatile> getVolatiles() {
		Singleton s = Singleton.Instance();


		return null;
	}

	/**
	 * 
	 * Egy illekony elem kitorlese az azokat tarolo listabol.
	 * 
	 * @param Volatile
	 *            a kitï¿½rlendï¿½ elem
	 */
	public void removeVolatile(Volatile volatile_) {
	}

	/**
	 * 
	 * Egy mozgo elem kitorlese az azokat tarola listabol.
	 * 
	 * @param Movable
	 *            a kiterlendo elem
	 */
	public void removeMovable(Movable movable) {
		Singleton s = Singleton.Instance();

		
	}

	/**
	 * 
	 * A hangyairo-spray erejenek csokkentese.
	 * 
	 * @param Integer
	 *            a csokkentes merteke
	 */
	public void decreaseAntKillerSpray(Integer strength) {
		Singleton s = Singleton.Instance();

		
	}

	/**
	 * 
	 * A hangyaszagirto-spray erejenek csokkentese.
	 * 
	 * @param Integer
	 *            a csokkentes merteke
	 */
	public void decreaseAntSmellSpray(Integer strength) {
		Singleton s = Singleton.Instance();

		
	}

}