package movable;

import item.Item;
import item.Tentacle;
import item.Volatile;

import java.util.Map;
import java.util.TreeMap;

import land.Dir;
import land.Field;
import program.Singleton;
import program.SingletonContainer;
import smell.AntSmell;
import smell.Smell;

public class Ant extends Item implements Movable {

	private Tentacle tentacle;
	private boolean isKilled = false;
	private boolean isActive = false;
	private Integer wait;
	private boolean haveFood = false;
	private Integer HP;
	private Dir dir;

	@Override
	public void setActualField(Field field) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Ant.setActualField("
				+ s.fields.indexOf(field) + ": Field)");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Ant.setActualField("
				+ s.fields.indexOf(field) + ": Field)");
		s.depth--;
	}

	/**
	 * 
	 * @param Integer
	 * @return
	 */
	public void looseHP(Integer hp) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Ant.looseHP(" + hp + ": Integer)");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Ant.looseHP(" + hp + ": Integer)");
		s.depth--;
	}

	/**
	 * 
	 * @return
	 */
	public void pickUpFood() {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Ant.pickupFood()");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Ant.pickUpFood()");
		s.depth--;
	}

	/**
	 * 
	 * @return
	 */
	public void rest() {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Ant.rest()");
		s.depth--;

		s.makeSpace("<< RETURN: " + id + ": Ant.rest()");
		s.depth--;
	}

	/**
	 * 
	 * @return
	 */
	public void reverseDir() {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Ant.reverseDir()");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Ant.reverseDir()");
		s.depth--;
	}

	/**
	 * 
	 * @return
	 */
	public void kill() {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Ant.kill()");

		s.stack.add(17);
		Field field = s.fields.get(16);
		field.removeItem(s.ants.get(id));
		s.stack.remove(s.stack.size() - 1);

		s.stack.add(1);
		SingletonContainer sc = s.singletonContainer.get(0).getInstance();
		sc.removeMovable(s.ants.get(id));
		s.stack.remove(s.stack.size() - 1);

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Ant.kill()");
		s.depth--;
	}

	/**
	 * 
	 * @param Dir
	 * @return
	 */
	public void setDir(Dir dir) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Ant.setDir(" + dir.toString()
				+ ": Dir)");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Ant.setDir(" + dir.toString()
				+ ": Dir)");
		s.depth--;
	}

	@Override
	public void step() {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Ant.step()");

		s.stack.add(12);
		Field prevField = s.fields.get(11);
		prevField.getNeighbours();
		s.stack.remove(s.stack.size() - 1);

		s.stack.add(9);
		Tentacle tentacle = s.tentacles.get(8);
		Map<Dir, Field> map = new TreeMap<Dir, Field>();
		map.put(Dir.DOWN, prevField);
		tentacle.setPossibleNeighbours(map);
		s.stack.remove(s.stack.size() - 1);

		s.stack.add(11);
		Field nextField = s.fields.get(10);
		nextField.getItems();
		s.stack.remove(s.stack.size() - 1);

		s.stack.add(5);
		Ant ant = (Ant) s.items.get(id);
		Ant ant2 = (Ant) s.items.get(4);
		ant2.collisionWithAnt(ant, false);
		s.stack.remove(s.stack.size() - 1);

		s.stack.add(9);
		Map<Dir, Field> map2 = tentacle.scan(true);
		s.stack.remove(s.stack.size() - 1);

		if (map2 != null) {
			s.stack.add(12);
			Smell smell = (AntSmell) s.smells.get(4);
			prevField.addSmell(smell);
			s.stack.remove(s.stack.size() - 1);

			s.stack.add(1);
			SingletonContainer sc = s.singletonContainer.get(0);
			Volatile smell2 = (AntSmell) s.smells.get(4);
			sc.addVolatile(smell2);
			s.stack.remove(s.stack.size() - 1);

			s.stack.add(12);
			prevField.removeItem(ant);
			s.stack.remove(s.stack.size() - 1);

			s.stack.add(2);
			ant.setActualField(nextField);
			s.stack.remove(s.stack.size() - 1);

			s.stack.add(11);
			nextField.addItem(ant);
			s.stack.remove(s.stack.size() - 1);
		} else {
			s.stack.add(5);
			ant.reverseDir();
			s.stack.remove(s.stack.size() - 1);
		}

		s.stack.add(8);
		Echidna echidna = (Echidna) s.items.get(7);
		echidna.collisionWithAnt(ant, true);
		s.stack.remove(s.stack.size() - 1);

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Ant.step()");
		s.depth--;

	}

	@Override
	public void setAlive() {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Ant.setAlive()");

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Ant.setAlive()");
		s.depth--;
	}

	/**
	 * @param ant
	 *            -
	 * @param b
	 *            -
	 */
	@Override
	public void collisionWithAnt(Ant ant, boolean b) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Ant.collisionWithAnt("
				+ s.ants.indexOf(ant) + ": Ant, " + String.valueOf(b) + ")");
		s.depth--;

		s.makeSpace("<< RETURN: " + id + ": Ant.collisionWithAnt("
				+ s.ants.indexOf(ant) + ": Ant, " + String.valueOf(b) + ")");
		s.depth--;
	}

	@Override
	public void collisionWithEchidna(Echidna echidna) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Ant.collisionWithEchidna("
				+ s.echidnas.indexOf(echidna) + ": Echidna)");

		s.stack.add(s.items.indexOf(echidna));
		Ant ant = (Ant) s.items.get(id - 1);
		echidna.collisionWithAnt(ant, true);
		s.stack.remove(s.stack.size() - 1);

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Ant.collisionWithEchidna("
				+ s.echidnas.indexOf(echidna) + ": Echidna)");
		s.depth--;
	}

	@Override
	public void collisionWithSpray(Integer strength) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Ant.collisionWithSpray(" + strength
				+ ": Integer)");

		s.stack.add(id);
		Ant ant = s.ants.get(id);
		ant.looseHP(6);
		s.stack.remove(s.stack.size() - 1);

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Ant.collisionWithSpray(" + strength
				+ ": Integer)");
		s.depth--;
	}

	public void canNotGo(Field field) {
		Singleton s = Singleton.Instance();

		Integer id = s.stack.get(s.stack.size() - 1);

		s.makeSpace(">> CALL: " + id + ": Ant.canNotGo("
				+ s.fields.indexOf(field) + ": Field)");

		s.stack.add(17);
		Tentacle tentacle = s.tentacles.get(16);
		tentacle.removePossibleNeighbour(field);
		s.stack.remove(s.stack.size() - 1);

		s.depth--;
		s.makeSpace("<< RETURN: " + id + ": Ant.canNotGo("
				+ s.fields.indexOf(field) + ": Field)");
		s.depth--;
	}
}
