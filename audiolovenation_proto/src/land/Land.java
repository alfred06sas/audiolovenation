package land;

import item.Item;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

	}

	/**
	 * Elemek elhelyez�se a mez�k�n. Ezek az elemek lehetnek akad�lyok, hangy�k,
	 * szagok, hangy�szs�n �s hangyales�.
	 * 
	 */
	public void putItems() {
		Singleton s = Singleton.Instance();

		Item item = new Item();

		Field field = new Field();

		field.addItem(item);

	}

	/**
	 * A Land k�szteti mozg�sra minden k�rben az arra k�pes elemeket. A move
	 * hat�s�ra megh�v�dik mind a hangy�ban mind a hangy�szs�nben a step()
	 * f�ggv�ny, ami a l�p�st val�s�tja meg.
	 * 
	 */
	public void move() {
		Singleton s = Singleton.Instance();

		SingletonContainer sc = new SingletonContainer().getInstance();

		sc.getMovables();

		// Hangya l�ptet�se
		Ant ant = new Ant();
		ant.step();

		// Hangy�szs�n l�ptet�se
		Echidna echinda = new Echidna();
		echinda.step();
		sc.getVolatiles();
		Spray spray = new Spray();
		spray.decrease();
	}

	/**
	 * 
	 * @return
	 */
	public void buildLand() {
		Singleton s = Singleton.Instance();

		// Field.addNeighbour h�v�sok
		new Field().addNeighbour(Dir.DOWN, new Field());

	}

	/**
	 * 
	 * @return
	 */
	public void init() {
		Singleton s = Singleton.Instance();

		// palya bet�lt�se
		loadLand();
		// palya fel�p�t�se
		buildLand();
		// elemek palyara helyezese
		putItems();
		// leptetes
		move();
	}

	public void loadTestCase(String inputFileName, String outputFileName) throws FileNotFoundException {
		BufferedReader br = new BufferedReader(new FileReader(inputFileName));

		try {
			while (true) {
				String line = br.readLine();
				if (line == null)
					break;
				String[] sline = line.split(" ");

				if (sline[0].equals("create_land") && sline[1].equals("-r")
						&& sline[3].equals("-c") && sline.length == 5) {
					System.out.println(line);
				} else if (sline[0].equals("put_item") && sline[1].equals("-t")
						&& sline[3].equals("-iid") && sline[5].equals("-fid")
						&& sline.length == 7) {
					System.out.println(line);
				} else if (sline[0].equals("put_smell")
						&& sline[1].equals("-t") && sline[3].equals("-sid")
						&& sline[5].equals("-s") && sline[7].equals("-fid")
						&& sline.length == 9) {
					System.out.println(line);
				} else if (sline[0].equals("set") && sline[1].equals("-t")
						&& sline[3].equals("-mid") && sline[5].equals("-d") && sline[7].equals("-s")
						&& sline.length == 9) {
					System.out.println(line);
				} else if (sline[0].equals("use_spray")
						&& sline[1].equals("-t") && sline[3].equals("-fid")
						&& sline.length == 5) {
					System.out.println(line);
				} else if (sline[0].equals("step_round")
						&& sline[1].equals("-rn") && sline.length == 3) {
					System.out.println(line);
				} else {
					System.err.println("Undefined command: " + line);
					return;
				}
			}
		} catch (IOException e) {

		}
	}
}
