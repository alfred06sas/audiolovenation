package land;

import item.Item;
import item.Spray;

import java.util.List;

import movable.Ant;
import movable.Echidna;
import program.Singleton;
import program.SingletonContainer;

/**
 * 
 * @author audiolovenation
 * 
 *         Az eg�sz j�t�k alapja, mez�kb�l �p�l fel. Feladata a program
 *         inicializ�l�sa �s a l�p�sek megval�s�t�sa. A p�ly�t fel�p�ti,
 *         l�trehoz meghat�rozott sz�m� hangy�t, hangy�szs�nt, hangyales�t,
 *         akad�lyt, bolyt. A hangy�kat �s a hangy�szs�n�ket egyel�re nem
 *         jelen�t meg (inakt�v �llapotba �ll�tja).
 */
public class Land {

	private List<Field> fields;

	/**
	 * A p�lya �ssze�ll�t�sa.
	 * 
	 */
	public void loadLand() {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Land.loadLand()");

		// Ide j�n a k�d

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Land.loadLand()");
		s.depth--;

	}

	/**
	 * Elemek elhelyez�se a mez�k�n. Ezek az elemek lehetnek akad�lyok, hangy�k,
	 * szagok, hangy�szs�n �s hangyales�.
	 * 
	 */
	public void putItems() {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Land.putItems()");

		// Ide j�n a k�d
		Item item = s.items.get(15);

		s.stack.add(6);
		Field field = s.fields.get(5);

		field.addItem(item);

		s.stack.remove(s.stack.size() - 1);

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Land.putItems()");
		s.depth--;
	}

	/**
	 * A Land k�szteti mozg�sra minden k�rben az arra k�pes elemeket. A move
	 * hat�s�ra megh�v�dik mind a hangy�ban mind a hangy�szs�nben a step()
	 * f�ggv�ny, ami a l�p�st val�s�tja meg.
	 * 
	 */
	public void move() {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Land.move()");

		s.stack.add(1);
		SingletonContainer sc = s.singletonContainer.get(0).getInstance();

		sc.getMovables();
		s.stack.remove(s.stack.size() - 1);

		s.stack.add(2);
		Ant ant = (Ant) s.items.get(1);
		ant.step();
		s.stack.remove(s.stack.size() - 1);

		s.stack.add(6);
		Echidna echinda = (Echidna) s.items.get(5);
		echinda.step();
		s.stack.remove(s.stack.size() - 1);

		s.stack.add(1);
		sc.getVolatiles();
		s.stack.remove(s.stack.size() - 1);

		s.stack.add(2);
		Spray spray = s.sprays.get(1);
		spray.decrease();
		s.stack.remove(s.stack.size() - 1);

		// Ide j�n a k�d
		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Land.move()");
		s.depth--;
	}

	/**
	 * 
	 * @return
	 */
	public void buildLand() {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Land.buildLand()");

		// Field.addNeighbour h�v�sok
		s.stack.add(3);
		s.fields.get(2).addNeighbour(Dir.DOWN, s.fields.get(8));
		s.stack.remove(s.stack.size() - 1);

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Land.buildLand()");
		s.depth--;
	}

	/**
	 * 
	 * @return
	 */
	public void init() {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Land.init()");

		/* ide j�n a k�d */
		// palya bet�lt�se
		loadLand();
		// palya fel�p�t�se
		buildLand();
		// elemek palyara helyezese
		putItems();
		// leptetes
		move();

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Land.init()");
		s.depth--;
	}

}
