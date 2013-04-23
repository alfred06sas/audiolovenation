package movable;

import item.Food;
import item.Item;
import item.Tentacle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import land.Dir;
import land.Field;
import program.Singleton;
import program.SingletonContainer;
import smell.AntSmell;
import blockage.Gravel;

/**
 * 
 * A hangya feladata mezorol mezore lepni. Mozgasa iranyat az etel, es mas hangyak altal
 * hagyott szagnyom befolyasolja. Vegul, ha etelhez er, vissza
 * kell azt vinnie a bolyba.
 * 
 * @author audiolovenation
 * 
 */
public class Ant extends Item implements Movable {
	/**
	 * Csap objektum. Ez vegzi a tovabbhaladasi iranyok beallitasat.
	 */
	private Tentacle tentacle;
	/**
	 * A hangya meghalt-e.
	 */
	private boolean isKilled = false;
	/**
	 * A hangya aktiv-e (a palyan tartozkodik-e).
	 */
	private boolean isActive = false;
	/**
	 * Inaktiv allapotban a maradek varakozasi korok.
	 */
	private int wait;
	/**
	 * Van-e a hangyanal etel.
	 */
	private boolean haveFood = false;
	/**
	 * Eletero, amelyet a spray, a hangyaleso es a hangyaszsun modosithat. Ha 0,
	 * akkor a hangya atlep inaktiv allapotba es a kor vegen meghal.
	 */
	private int HP;
	/**
	 * A tovabbhaladasi irany. Default: UP.
	 */
	private Dir dir;
	
	public Ant(){
		HP = 10;
		tentacle = new Tentacle(this);
		isKilled = false;
		isActive = false;
		wait = 10;
		haveFood = false;
		dir = Dir.RIGHT_BOTTOM;
	}
	
	public Ant(String ID){
		super(ID);
		id="a"+ID;
		HP = 10;
		tentacle = new Tentacle(this);
		isKilled = false;
		isActive = false;
		wait = 10;
		haveFood = false;
		dir = Dir.RIGHT_BOTTOM;
	}

	/**
	 * Eletero lecsokkentese a megadott ertekkel.
	 * 
	 * @param hp
	 *            a csokkentes merteke
	 */
	public void looseHP(Integer hp) {
		HP-=hp;
	}

	/**
	 * A hangya etelt vesz fel. Allapotat atallitja (haveFood = true).
	 */
	public void pickUpFood() {
		haveFood=true;
	}

	/**
	 * Pihenteti a hangyat egy adott ideig [amig el nem indul (ujra) a
	 * bolybol)].
	 */
	public void rest() {
		isKilled = false;
		isActive = false;
		wait = 10;
		haveFood = false;
	}

	/**
	 * A hangya iranyanak megforditasa.
	 */
	public void reverseDir() {
		switch (dir){
		case UP:
			dir = Dir.DOWN;			
			break;
		case DOWN:
			dir = Dir.UP;	
			break;
		case RIGHT_TOP:
			dir = Dir.LEFT_BOTTOM;			
			break;
		case RIGHT_BOTTOM:
			dir = Dir.LEFT_TOP;
			break;
		case LEFT_TOP:
			dir = Dir.RIGHT_BOTTOM;
			break;
		case LEFT_BOTTOM:
			dir = Dir.RIGHT_TOP;
			break;
		}
	}

	/**
	 * A hangya megolese. Torli magat a mezorol es a SingletonContainer-ben levo
	 * movables listabol is.
	 */
	public void kill() {
		
		getActualField().removeItem(this);
		
		/* Hangya torlese a SingletonContainer movables listajabol */
		SingletonContainer sc = SingletonContainer.getInstance();
		sc.removeMovable(this);
		
		/* Hangya torlese az ot tartalmazo mezorol */
		
		isKilled=true;
		
	}

	/**
	 * Haladasi irany beallitasa.
	 * 
	 * @param dir
	 *            a beallitando haladasi irany
	 */
	public void setDir(Dir dir) {
		this.dir=dir;
	}
	
	public Dir getDir(){
		return dir;
	}

	/**
	 * A hangya leptetese. Lekerdezi az ot tartalmazo mezo szomszedait, majd
	 * ezekbol a lehetseges tovabbhaladasi iranyban levoket tovabbadja a
	 * csapjanak. A csap ezek utan megvizsgalja, hogy hova lephet, majd ahol
	 * akadaly van, kiszedi a lehetseges mezok listajabol. Ha mindenhol akadaly
	 * van, akkor megfordul (marad ugyanazon a mezon), ha megtalalta a
	 * legmegfelelobbet, akkor ralep, majd utkozik az ottani item-ekkel.
	 */
	@Override
	public void step() {
		Singleton s = Singleton.Instance();
		Field oldField=actualField;
		
		/* A szomszedok lekerdezese. */
		Map<Dir, Field> neg=getActualField().getNeighbours();
	
		/* A lehetseges szomszedok beallitasa. */
		Map<Dir, Field> possibleNeig = new HashMap<Dir, Field>();
		possibleNeig.put(dir, neg.get(dir));
		possibleNeig.put(Dir.fromInteger((dir.getValue()+1+6)%6), neg.get(Dir.fromInteger((dir.getValue()+1+6)%6)));
		possibleNeig.put(Dir.fromInteger((dir.getValue()+6-1)%6), neg.get(Dir.fromInteger((dir.getValue()+6-1)%6)));
		tentacle.setPossibleNeighbours(possibleNeig);
	
		/* A lehetseges szomszedok item-einek egy ciklusban valo lekerdezese. */
		/*
		 * Az elemekkel valo utkoztetes meg lepes elott, hogy kideruljon, hol
		 * talalhato akadaly.
		 */
//		System.out.println("comment: possibleNeighs: "+possibleNeig+" // Ant.step()");
		Map<Dir, Field> next = new HashMap<Dir, Field>();
		List<Item> items = new ArrayList<Item>();
		for (Dir key : possibleNeig.keySet()) {
			items.addAll(possibleNeig.get(key).getItems());
		}
		for (Item item:items){
			item.collisionWithAnt(this, false);
		}
		//Most mar csak az van bent, ahova tenyleg lepni tudunk		
		next.clear();
		next = tentacle.scan(haveFood);
	
		if (next!=null){
			AntSmell as = new AntSmell();
			actualField.addSmell(as);
			SingletonContainer sc = SingletonContainer.getInstance();
			sc.addVolatile(as);
			actualField.removeItem(this);
			if (next.size()>1){
				System.out.println("Hiba: Nem csak egy lehetséges irány maradt!");
			}
			else{
				for (Dir key : next.keySet()){
					dir=key;
					setActualField(next.get(key));
					actualField.addItem(this);
					s.printStep(this,oldField,actualField);
				}
			}
			
		}
		else{
			reverseDir();
			s.printDirChanged(this);
		}
		
		items=actualField.getItems();
		for (Item item : items)
			item.collisionWithAnt(this, true);
		
		
		s.printDirChanged(this);

	}

	/**
	 * Hangya allapotanak inaktivrol aktivra allitasa. (wait == 0)
	 */
	@Override
	public void setAlive() {
		isActive=true;
	}

	/**
	 * Hangyaval valo utkoztetes. Nem csinal semmit, csak visszater.
	 * 
	 * @param ant
	 *            a hangya referenciaja, amivel utkozott
	 * @param b
	 *            utkozes elott: false, utkozes utan: true
	 */
	@Override
	public void collisionWithAnt(Ant ant, boolean b) {
	}

	/**
	 * - Hangyaszsunnel valo utkozes. A hangya meghal.
	 * 
	 * @param echidna
	 *            referencia a hangyaszsunre, amivel utkozik
	 */
	@Override
	public int collisionWithEchidna(Echidna echidna, boolean b, Dir dir) {
		/*
		 * A hangya szol a hangyaszsunnek, hogy hangyaval utkozott, amely ennek
		 * hatasara megoli.
		 */
		echidna.collisionWithAnt(this, true);
		return 0;
	}

	/**
	 * Spray-vel valo utkozes. A hangya vagy meghal, vagy eleterot veszit.
	 * 
	 * @param strength
	 *            a hangya eletereje ennyivel fog csokkenni
	 */
	@Override
	public void collisionWithSpray(Integer strength) {
		/* A hangya looseHP metodusanak meghivasa. */
		looseHP(6);
	}
	
	@Override
	public int collisionWithGravel(Gravel g, boolean b, Dir d) {
		g.collisionWithAnt(this, true);
		kill();
		
		return HP;
	}

	/**
	 * Akadallyal valo utkozes eseten az akadaly ezzel a metodussal jelzi a
	 * hangyanak, hogy az ot tartalmazo mezore nem lephet. Ezt a mezot a csap
	 * kiveszi a lehetseges szomszedok kozul. Mindig lepes elott hivodik meg,
	 * mivel az nem fordulhat elo, hogy a hangya olyan mezore lep, ahol akadaly
	 * van.
	 * 
	 * @param field
	 *            az a mezo, ahova nem lephet a hangya
	 */
	public void canNotGo(Field field) {
	/* A parameterkent megadott mezo kivetele a lehetseges mezok listajabol. */
		
		tentacle.removePossibleNeighbour(field);
	}
	
	@Override
	public HashMap<String, String> getStates() {
		HashMap<String, String> states = new HashMap<String, String>();
		
		if (isKilled)
			states.put("STATE", "KILLED");
		else if (wait > 0)
			states.put("STATE", "WAIT");
		else {
			if (haveFood==true)
				states.put("STATE", "HAVE_FOOD");
			else
				states.put("STATE", "DONT_HAVE_FOOD");
		}
		
		states.put("DIR", dir.toString());
		return states;
	}

	public void setWait(int i) {
		wait=i;
	}

	public void setHaveFood(boolean b) {
		haveFood=b;
		wait = 0;
	}

	public void setKilled(boolean b) {
		isKilled=b;
		wait = 0;
	}
}
