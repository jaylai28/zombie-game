package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;
import edu.monash.fit2099.engine.WeaponItem;

import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

/**
 * A Zombie.
 * 
 * This Zombie is pretty boring.  It needs to be made more interesting. Class representing Zombie as a type of Enemy that attacks
 * the Human and player
 * 
 * @author weijianlai
 *
 */
public class Zombie extends ZombieActor {
	private Random rand = new Random();
	protected int leg_counter = 0;

	private static final int base_damage = 10;
	private static final char zombie_display = 'Z';
	private ArrayList<String> zombie_limbs = new ArrayList<>();
	static boolean firstlegone = false;
	private static ArrayList<String> zombie_convo = new ArrayList<>();
	private Behaviour[] behaviours = {
			new CheckWeaponBehaviour(),
			new AttackBehaviour(ZombieCapability.ALIVE), 
			new HuntBehaviour(Human.class, 10),
			new WanderBehaviour()
	};

	public Zombie(String name) {
		super(name, zombie_display, 100, ZombieCapability.UNDEAD);
		this.addBehaviour(new AttackBehaviour(ZombieCapability.ALIVE));
		this.addBehaviour(new CheckWeaponBehaviour());
		addConvo();
		zombie_limbs.add("arm");
		zombie_limbs.add("arm");
		zombie_limbs.add("leg");
		zombie_limbs.add("leg");
	}
	
	

	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(base_damage, "punches");
	}
	
	@Override
	public void heal(int points) {
		if (!(hitPoints==maxHitPoints)) {
			hitPoints += points;
			hitPoints = Math.min(hitPoints, maxHitPoints);
		System.out.println("5 Health is added to " + name);

		}
		else {
			System.out.println("The health is full!!");
		}
	}
	
	
	
	private void addConvo() {
		zombie_convo.add("BRAINS!");
		zombie_convo.add("TASTY!");
		zombie_convo.add("I'M COMING FOR YOU!");
		zombie_convo.add("YOU BETTER RUN!");
		zombie_convo.add("HUNGRY GRHHH!");
	}
	
	
	
	/**
	 * Returns an Action to be performed during its turn. It has 10% chance of shouting an insult and it will check if there's any
	 * weapon that the Zombie can pick up at its location. If a Zombie can attack, it will.  If not, it will chase any human 
	 * within 10 spaces. If no humans are close enough it will wander randomly. It also checks the zombie's legs in this play turn
	 * so that if zombie loses one of its legs or both, the wander behaviour will be affected and cause it to only perform checkweapon
	 * and attack behaviour.
	 * 
	 * @param actions list of possible Actions
	 * @param lastAction previous Action, if it was a multiturn action
	 * @param map the map where the current Zombie is
	 * @param display the Display where the Zombie's utterances will be displayed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
	if(rand.nextDouble() <= 0.1) {
		Action text = new ConvoAction(zombie_convo.get(rand.nextInt(zombie_convo.size())), this);
		return text;
	}
	
	
	if(get_amputatedLegs() ==2) {
		for (int i=0; i < 2; i++) {
			Action action = behaviours[i].getAction(this, map);
			firstlegone=true;
			if(action != null)
				return action;
	}}

	else if (get_amputatedLegs() ==0 || firstlegone == true) {
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if(get_amputatedLegs() >0) {
				firstlegone=false;
			}
			
			if(action != null)
				return action;
			}}
	
	else if(get_amputatedLegs() ==1 || firstlegone == false) {
		for (int i=0; i < 2; i++) {
			Action action = behaviours[i].getAction(this, map);
			firstlegone=true;
			if(action != null)
				return action;
	}}

	
	return new DoNothingAction();		

	
	}
	
	
	
	/**
	 * create instance of zombie in attack action to make sure its the correct zombie object.
	 */
	  public ArrayList<String> getLimbs() {
	     return this.zombie_limbs;
	  }
		  
		  
	
	
	  /**
	   * create instance of zombie in attack action to make sure its the correct zombie object.
	   */
	  public String removeLimbs() {
		 Collections.shuffle(zombie_limbs);
	     return zombie_limbs.remove(0);
	  }

	  
	  /**
	   * increase the number of zombie leg counter by 1
	   */
	  public void amputatedLegs() {
		   this.leg_counter++;
				
			}
	  
	  /**
	   * get the number of zombie legs for the zombie
	   * @return the number of legs of the zombie
	   */
	  public int get_amputatedLegs() {
			return this.leg_counter;
				
			}



	@Override
	public int health() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * return the instance of zombie. It will return true if its a zombie. This is to allow to check from other classes to see if
	 * the actor is a zombie
	 */
	public boolean isZombie() {
		return true;
	}



	@Override
	public boolean maxhealth() {
		if (hitPoints==maxHitPoints) {
			return true;
	}
		return false;
	}
	
	@Override
	public boolean isHuman() {
		return false;
	}



	@Override
	public boolean hasBattery() {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean hasKey() {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public void removeBattery() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void removeKey() {
		// TODO Auto-generated method stub
		
	}



	


	
}
