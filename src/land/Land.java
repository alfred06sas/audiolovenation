package land;

import item.Item;
import item.Volatile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sound.midi.SysexMessage;

import movable.Movable;
import program.SingletonWriter;
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

	private HashMap<String, Field> fields=new HashMap<String, Field>();

	public Land(){
		 fields=new HashMap<String, Field>();
		 columnNumber = 10;
		 rowNumber = 10;
	}
	

	/**
	 * A palya osszeallitasa.
	 * 
	 */
	public void loadLand(int row, int column) {
		if (!(((row % 2 == 0) && (column % 2 == 0)) || ((row % 2 == 1) && (column % 2 == 1)))){
			System.out.println("Hiba: az oszlop és sorszám legyen ugyanolyan paritású!");
			System.exit(0);
		}
		rowNumber = row;
		columnNumber = column;		
		
		
		for(int k=0;k<rowNumber;k++){
			for (int j=0;j<columnNumber;j++){
				if(((k % 2 == 0) && (j % 2 == 0)) || ((k % 2 == 1) && (j % 2 == 1))){
					fields.put(k+"_"+j,new Field(k+"_"+j));
					fields.get(k+"_"+j).setView();
					fields.get(k+"_"+j).notifyView();
				}
			}
		}
//		System.out.println(fields.toString());
	}
	

	/**
	 * Elemek elhelyezese a mezokon. Ezek az elemek lehetnek akadalyok, hangyak,
	 * szagok, hangyaszsunok es hangyalesok.
	 * 
	 */
	public void putItems(/*Field field, Item item*/) {
		//field.addItem(item);
		
	}

	/**
	 * A Land keszteti mozgasra minden korben az arra kepes elemeket. A move
	 * hatasara meghivodik mind a hangyaban mind a hangyaszsunben a step()
	 * fuggveny, ami a lepest valositja meg.
	 * 
	 */
	public void move() {
		SingletonContainer sc = SingletonContainer.getInstance();
		List<Movable> movables = sc.getMovables();
		
		// Movable-k leptetese
		for (Movable movable :movables)
			movable.step();
		
		List<Volatile> volatiles = sc.getVolatiles();
		
		// Illekony anyagok csokkentese
		for (Volatile volatile_ :volatiles){
			volatile_.decrease();
		}
	}

	/**
	 * 
	 * @return
	 */
	public void buildLand() {
		SingletonWriter s = SingletonWriter.Instance();
		// Field.addNeighbour hivasok		
		
		for(int k=0;k<rowNumber;k++){
			for (int j=0;j<columnNumber;j++){
				if(((k % 2 == 0) && (j % 2 == 0)) || ((k % 2 == 1) && (j % 2 == 1))){
					//DOWN
					fields.get(k+"_"+j).addNeighbour(Dir.DOWN, fields.get(((rowNumber+k+2)%rowNumber)+"_"+j));
					//UP
					fields.get(k+"_"+j).addNeighbour(Dir.UP, fields.get(((rowNumber+k-2)%rowNumber)+"_"+j));
					//RIGHT_BOTTOM
					fields.get(k+"_"+j).addNeighbour(Dir.RIGHT_BOTTOM, fields.get(((rowNumber+k+1)%rowNumber)+"_"+((columnNumber+j+1)%columnNumber)));
					//LEFT_TOP
					fields.get(k+"_"+j).addNeighbour(Dir.LEFT_TOP, fields.get(((rowNumber+k-1)%rowNumber)+"_"+((columnNumber+j-1)%columnNumber)));
					//RIGHT_TOP
					fields.get(k+"_"+j).addNeighbour(Dir.RIGHT_TOP, fields.get(((rowNumber+k-1)%rowNumber)+"_"+((columnNumber+j+1)%columnNumber)));
					//LEFT_BOTTOM
					fields.get(k+"_"+j).addNeighbour(Dir.LEFT_BOTTOM, fields.get(((rowNumber+k+1)%rowNumber)+"_"+((columnNumber+j-1)%columnNumber)));
						
				}
			}
		}
	}

	

	/**
	 * 
	 * @return
	 */
	public void init(int row, int column) {

		// palya betoltese
		loadLand(row,column);
		// palya felepitese
		buildLand();
		// elemek palyara helyezese
//		putItems(); Egyelore kezzel adjuk hozza az Itemeket
		// leptetes
		move();
	}

	public Field getField(String id){
		return fields.get(id);
	}
}
