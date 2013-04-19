package item;

import java.util.HashMap;

import land.Field;
import movable.Ant;
import movable.Echidna;
import program.Singleton;

/**
 * 
 * @author audiolovenation
 * 
 *         A p�lya egyes mez�in megtal�lhat� objektumok (hangya, hangy�szs�n,
 *         hangyales�, akad�ly, spray, �tel, boly).
 */
public class Item {

	private String id;

	private Field actualField;

	/**
	 * Hangy�val val� �tk�z�s.
	 * 
	 * @param Ant
	 *            a hangya ami �tk�zik a bollyal
	 * @param b
	 *            van-e �tel a hangy�n�l vagy nincs
	 */
	public void collisionWithAnt(Ant ant, boolean b) {
		Singleton s = Singleton.Instance();
		
	}

	/**
	 * Item be�ll�t�sa egy mez�h�z.
	 * 
	 * @param Field
	 *            a mez�, amihez be�ll�tjuk az �telszagot.
	 */
	public void setActualField(Field field) {

		Singleton s = Singleton.Instance();
		
	}

	/**
	 * Hangy�szs�nnel val� �tk�z�s.
	 * 
	 * @param Echidna
	 *            a hangy�szs�n ami �tk�zik a bollyal
	 */
	public void collisionWithEchidna(Echidna echidna) {
		Singleton s = Singleton.Instance();
		
	}

	/**
	 * Spray-vel val� �tk�z�s.
	 * 
	 * @param strength
	 *            a spray er�ss�ge
	 */
	public void collisionWithSpray(Integer strength) {
	}

	public String getId() {
		return id;
	}
	
	public HashMap<String, String> getStates()
	{
		return null;
	}
}