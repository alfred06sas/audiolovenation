package program;

import item.Antlion;
import item.Food;
import item.Hill;
import item.Item;
import item.Spray;
import item.Tentacle;

import java.util.ArrayList;
import java.util.List;

import land.Field;
import land.Land;
import movable.Ant;
import movable.Echidna;
import smell.AntSmell;
import smell.FoodSmell;
import smell.Smell;
import blockage.Blockage;
import blockage.Gravel;
import blockage.Puddle;

public class Singleton {

	private static Singleton instance = null;
	public List<Integer> stack = new ArrayList<Integer>();
	public int depth = 0;
	public List<Land> land;
	public List<Ant> ants;
	public List<Tentacle> tentacles;
	public List<Field> fields;
	public List<Echidna> echidnas;
	public List<Antlion> antlions;
	public List<Spray> sprays;
	public List<Blockage> blockages;
	public List<Food> foods;
	public List<AntSmell> antSmells;
	public List<FoodSmell> foodSmells;
	public List<Hill> hill;
	public List<Item> items;
	public List<Smell> smells;
	public List<SingletonContainer> singletonContainer;

	public static Singleton Instance() {
		if (instance == null)
			instance = new Singleton();
		return instance;
	}

	public void makeSpace(String s) {
		depth++;
		for (int i = 0; i < depth; i++) {
			System.out.print("     ");
		}
		System.out.println(s);
	}

	public void initItems() {
		land = new ArrayList<Land>();
		ants = new ArrayList<Ant>();
		fields = new ArrayList<Field>();
		tentacles = new ArrayList<Tentacle>();
		echidnas = new ArrayList<Echidna>();
		antlions = new ArrayList<Antlion>();
		sprays = new ArrayList<Spray>();
		blockages = new ArrayList<Blockage>();
		foods = new ArrayList<Food>();
		antSmells = new ArrayList<AntSmell>();
		foodSmells = new ArrayList<FoodSmell>();
		hill = new ArrayList<Hill>();
		items = new ArrayList<Item>();
		smells = new ArrayList<Smell>();
		singletonContainer = new ArrayList<SingletonContainer>();

		land.add(new Land());
		hill.add(new Hill());
		singletonContainer.add(new SingletonContainer());

		for (int i = 0; i < 20; i++) {
			Ant ant = new Ant();
			ants.add(ant);
			fields.add(new Field());
			tentacles.add(new Tentacle());
			Echidna echidna = new Echidna();
			echidnas.add(echidna);
			antlions.add(new Antlion());
			Spray spray = new Spray();
			sprays.add(spray);
			foods.add(new Food());
			AntSmell antSmell = new AntSmell();
			antSmells.add(antSmell);
			FoodSmell foodSmell = new FoodSmell();
			foodSmells.add(foodSmell);
			if (i < 5) {
				items.add(ant);
				blockages.add(new Puddle());
				smells.add(antSmell);
			} else if (i < 10) {
				items.add(echidna);
				blockages.add(new Puddle());
				smells.add(antSmell);
			} else if (i < 15) {
				items.add(new Food());
				blockages.add(new Gravel());
				smells.add(foodSmell);
			} else {
				items.add(spray);
				blockages.add(new Gravel());
				smells.add(foodSmell);
			}

		}
	}
}
