package land;

import item.Item;
import item.Volatile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sound.midi.SysexMessage;

import movable.Movable;
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
		 columnNumber = 10;
		 rowNumber = 10;
	}
	

	/**
	 * A palya osszeallitasa.
	 * 
	 */
	public void loadLand(int row, int column) {
		System.out.println(row+" "+column);
		if (!(((row % 2 == 0) && (column % 2 == 0)) || ((row % 2 == 1) && (column % 2 == 1)))){
			System.out.println("Hiba: az oszlop és sorszám legyen ugyanolyan paritású!");
			System.exit(0);
		}
		rowNumber = row;
		columnNumber = column;		
		
		
		for(int k=1;k<=rowNumber;k++){
			for (int j=1;j<=columnNumber;j++){
				fields.add(new Field(k+"_"+j));
			}
		}
	}
	

	/**
	 * Elemek elhelyezese a mezokon. Ezek az elemek lehetnek akadalyok, hangyak,
	 * szagok, hangyaszsunok es hangyalesok.
	 * 
	 */
	public void putItems(Field field, Item item) {
		field.addItem(item);
	}

	/**
	 * A Land keszteti mozgasra minden korben az arra kepes elemeket. A move
	 * hatasara meghivodik mind a hangyaban mind a hangyaszsunben a step()
	 * fuggveny, ami a lepest valositja meg.
	 * 
	 */
	public void move() {
		SingletonContainer sc = SingletonContainer.getInstance();
		List<Movable> movables = new ArrayList<Movable>();
		List<Volatile> volatiles = new ArrayList<Volatile>();

		// Hangya leptetese
		movables = sc.getMovables();
		try{
			for (Movable movable :movables)
					movable.step();
		}catch(NullPointerException e){
			System.out.println("Ures Movable lista.");
		}

		// Illekonyok csokkentese
		volatiles = sc.getVolatiles();
		try{
			for (Volatile volatile_ :volatiles)
				volatile_.decrease();
		}catch(NullPointerException e){
			System.out.println("Ures Volatile lista.");
		}
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
					
					
					//System.out.println(fields.get(k*columnNumber+j).getId()+": "+neighbours.toString());
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
//		putItems(); Egyelore kezzel adjuk hozza az Itemeket
		// leptetes
		move();
	}

	public Field getField(String id){
		System.out.println("getField-ben");
		System.out.println("ami kell:"+id);
		for (Field field: fields){
			System.out.println("ami van: "+ field.getId());
			if (field != null)
				if (field.getId().equals(id))
					return field;
		}
		System.out.println("Nincs ilyen id-ju Field.");
		return null;
	}
}
