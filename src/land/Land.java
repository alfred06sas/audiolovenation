package land;

import item.Item;
import item.Spray;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import movable.Ant;
import movable.Echidna;
import program.Singleton;
import program.SingletonContainer;

/**
 * 
 * @author audiolovenation
 * 
 *         Az egesz jatek alapja, mezokbol epul fel. Feladata a program
 *         inicializalasa es a lepesek megvalositasa. A palyat felepiti,
 *         letrehoz meghatarozott szamu hangyat, hangyaszsunt, hangyalesot,
 *         akadalyt, bolyt. A hangyakat es a hangyaszsunoket egyelore nem jeleniti 
 *         meg (inaktiv allapotba allitja).
 *         
 */
public class Land {
	
	int columnNumber;
	int rowNumber;

	private List<Field> fields=new ArrayList<Field>();

	public Land(){
		 fields=new ArrayList<Field>();
	}
	

	/**
	 * A palya osszeallitasa.
	 * 
	 */
	public void loadLand(int row, int column) {
		Singleton s = Singleton.Instance();
		
		rowNumber = row;
		columnNumber = column;		
		
		for(int k=0;k<rowNumber;k++){
			for (int j=0;j<columnNumber;j++){
				fields.add(new Field());
				if (((k % 2 == 0) && (j % 2 == 0)) || ((k % 2 == 1) && (j % 2 == 1)))
					try{
						
						try{
							fields.get(k*columnNumber+j).setId(k+"_"+j);
						}
						catch(IndexOutOfBoundsException e){
							System.out.println(fields.size()+": "+k+": "+j);
						}
					}
					catch(NullPointerException e){
					System.out.println(k+" "+j);
				}
			}
		}
	}

	/**
	 * Elemek elhelyezese a mezokon. Ezek az elemek lehetnek akadalyok, hangyak,
	 * szagok, hangyaszsunok es hangyalesok.
	 * 
	 */
	public void putItems() {
		Singleton s = Singleton.Instance();

		Item item = new Item();

		Field field = new Field();

		field.addItem(item);

	}

	/**
	 * A Land keszteti mozgasra minden korben az arra kepes elemeket. A move
	 * hatasara meghivodik mind a hangyaban mind a hangyaszsunben a step()
	 * fuggveny, ami a lepest valositja meg.
	 * 
	 */
	public void move() {
		Singleton s = Singleton.Instance();

		SingletonContainer sc = new SingletonContainer().getInstance();

		sc.getMovables();

		// Hangya leptetese
		Ant ant = new Ant();
		ant.step();

		// Hangyaszsun leptetese
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
	public void buildLand(int row, int column) {
		Singleton s = Singleton.Instance();
		// Field.addNeighbour hivasok

		rowNumber = row;
		columnNumber = column;		
		
		new Field().addNeighbour(Dir.DOWN, new Field());
		
		for(int k=0;k<rowNumber;k++){
			for (int j=0;j<columnNumber;j++){
				if (((k % 2 == 0) && (j % 2 == 0)) || ((k % 2 == 1) && (j % 2 == 1))){
					
					//UP neighbour
					fields.get(k*columnNumber+j).addNeighbour(Dir.DOWN, fields.get(((k+2) % rowNumber)*columnNumber+j));
					
					//DOWN neighbour
					fields.get(k*columnNumber+j).addNeighbour(Dir.UP, fields.get(((rowNumber+k-2) % rowNumber)*columnNumber+j));
					
					//Right_bottom neighbour
					fields.get(k*columnNumber+j).addNeighbour(Dir.RIGHT_BOTTOM, fields.get((((k+1) % rowNumber)*columnNumber)+((j+1) % columnNumber)));
					
					//Right_top neighbour
					fields.get(k*columnNumber+j).addNeighbour(Dir.LEFT_BOTTOM, fields.get((((k+1) % rowNumber)*columnNumber)+((columnNumber+j-1) % columnNumber)));
					
					//Left_bottom neighbour
					fields.get(k*columnNumber+j).addNeighbour(Dir.RIGHT_TOP, fields.get((((rowNumber+k-1) % rowNumber)*columnNumber)+((j+1) % columnNumber)));
					
					//Left_top neighbour
					fields.get(k*columnNumber+j).addNeighbour(Dir.LEFT_TOP, fields.get((((rowNumber+k-1) % rowNumber)*columnNumber)+((columnNumber+j-1) % columnNumber)));
					
					Map<Dir, Field> neighbours=fields.get(k*columnNumber+j).getNeighbours();
					
					
					System.out.println(fields.get(k*columnNumber+j).getId()+": "+neighbours.toString());
				}
			}
		}
		
	}

	/**
	 * 
	 * @return
	 */
	public void init(int row, int column) {
		Singleton s = Singleton.Instance();

		// palya betoltese
		loadLand(row,column);
		// palya felepitese
		buildLand(row,column);
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
					loadLand(Integer.parseInt(sline[2]),Integer.parseInt(sline[4]));
					buildLand(Integer.parseInt(sline[2]),Integer.parseInt(sline[4]));
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
