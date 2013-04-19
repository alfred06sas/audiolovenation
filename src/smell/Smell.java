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
	private int strength;

	public Smell(){
		actualField = new Field();
		strength = 10;
	}
	
	public void setStrength(int s){
		strength=s;
	}
	
	public int getStrength(){
		return strength;
	}
	
	/**
	 * 
	 * Szag csokkentese.
	 * 
	 */
	public void decreaseSmell(int s) {
		strength-=s;
	}

	/**
	 * 
	 * Szag növelése.
	 * 
	 */
	public void IncreaseSmell(int s) {
		strength+=s;
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
	public void removeMyself(Field field) {
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