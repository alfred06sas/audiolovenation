package program;

import item.Antlion;
import item.Food;
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
	public List<Puddle> puddles;
	public List<Gravel> gravels;
	public List<Food> foods;
	public List<AntSmell> antSmells;
	public List<FoodSmell> foodSmells;

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
		puddles = new ArrayList<Puddle>();
		gravels = new ArrayList<Gravel>();
		foods = new ArrayList<Food>();
		antSmells = new ArrayList<AntSmell>();
		foodSmells = new ArrayList<FoodSmell>();

		land.add(new Land());
		
		for (int i = 0; i < 20; i++) {
			ants.add(new Ant());
			fields.add(new Field());
			tentacles.add(new Tentacle());
			echidnas.add(new Echidna());
			antlions.add(new Antlion());
			sprays.add(new Spray());
			puddles.add(new Puddle());
			gravels.add(new Gravel());
			foods.add(new Food());
			antSmells.add(new AntSmell());
			foodSmells.add(new FoodSmell());
		}
	}
}
