package smell;

import item.Tentacle;
import land.Field;

/**
 * 
 * @author audiolovenation
 * 
 *         K�l�nb�z� szagok megval�s�t�s�ra haszn�ljuk (hangyaszag, �telszag
 *         vagy hangya�l� spray). Ezeket mez�kh�z rendelj�k hozz� .
 * 
 */
public class Smell {

	private Field actualField;
	private Integer strength;

	/**
	 * 
	 * Szag cs�kkent�se.
	 * 
	 */
	public void decreaseSmell() {
	}

	/**
	 * 
	 * Szag n�vel�se.
	 * 
	 */
	public void IncreaseSmell() {
	}

	/**
	 * 
	 * Egy szag be�ll�t�sa egy mez�re.
	 * 
	 * @param field
	 *            a mez�, ahova be kell �l�ltani
	 * 
	 */
	public void setActualField(Field field) {
	}

	/**
	 * 
	 * Szag elt�vol�t�sa.
	 * 
	 */
	public void removeMyself() {
	}

	/**
	 * A cs�pnak adja meg a szag er�ss�g�t.
	 * 
	 * @param tentacle
	 *            a cs�p, aminak a szag er�ss�g�t �t kell adni
	 * 
	 */
	public void smellIt(Tentacle tentacle) {
	}

	/**
	 * 
	 * Hangyaszag-semleges�t� spray haszn�lata.
	 * 
	 */
	public void antSmellSpray() {
	}

}