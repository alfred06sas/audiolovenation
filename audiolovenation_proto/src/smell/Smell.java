package smell;

import item.Tentacle;
import land.Field;

/**
 * 
 * @author audiolovenation
 * 
 *         Kulonbozo szagok megvalositasara hasznaljuk (hangyaszag, etelszag
 *         vagy hangyaolo spray). Ezeket mezokhoz rendeljuk hozza.
 * 
 */
public class Smell {

	private Field actualField;
	private Integer strength;

	/**
	 * 
	 * Szag csokkentese.
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
	 * Egy szag beallitasa egy mezore.
	 * 
	 * @param field
	 *            a mezo, ahova be kell aliltani
	 * 
	 */
	public void setActualField(Field field) {
	}

	/**
	 * 
	 * Szag eltavolitasa.
	 * 
	 */
	public void removeMyself() {
	}

	/**
	 * A csapnak adja meg a szag erosseget.
	 * 
	 * @param tentacle
	 *            a csap, aminak a szag erosseget at kell adni
	 * 
	 */
	public void smellIt(Tentacle tentacle) {
	}

	/**
	 * 
	 * Hangyaszag-semlegesito spray hasznalata.
	 * 
	 */
	public void antSmellSpray() {
	}

}