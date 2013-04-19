package smell;

import item.Tentacle;
import land.Field;

/**
 * 
 * @author audiolovenation
 * 
 *         Különbözõ szagok megvalósítására használjuk (hangyaszag, ételszag
 *         vagy hangyaölõ spray). Ezeket mezõkhöz rendeljük hozzá .
 * 
 */
public class Smell {

	private Field actualField;
	private Integer strength;

	/**
	 * 
	 * Szag csökkentése.
	 * 
	 */
	public void decreaseSmell() {
	}

	/**
	 * 
	 * Szag növelése.
	 * 
	 */
	public void IncreaseSmell() {
	}

	/**
	 * 
	 * Egy szag beállítása egy mezõre.
	 * 
	 * @param field
	 *            a mezõ, ahova be kell álíltani
	 * 
	 */
	public void setActualField(Field field) {
	}

	/**
	 * 
	 * Szag eltávolítása.
	 * 
	 */
	public void removeMyself() {
	}

	/**
	 * A csápnak adja meg a szag erõsségét.
	 * 
	 * @param tentacle
	 *            a csáp, aminak a szag erõsségét át kell adni
	 * 
	 */
	public void smellIt(Tentacle tentacle) {
	}

	/**
	 * 
	 * Hangyaszag-semlegesítõ spray használata.
	 * 
	 */
	public void antSmellSpray() {
	}

}