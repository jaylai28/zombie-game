package game;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Class representing an ordinary human. Human is allowed to move around the map and eat the food on the ground
 * 
 * 
 * @author weijianlai
 *
 */
public class Human extends ZombieActor {
	
	private Behaviour[] behaviours = {
			new EatBehaviour(),
			new WanderBehaviour()
	};


	/**
	 * The default constructor creates default Humans
	 * 
	 * @param name the human's display name
	 */
	public Human(String name) {
		super(name, 'H', 100, ZombieCapability.ALIVE);
	}
	
	/**
	 * The protected constructor can be used to create subtypes
	 * of Human, such as the Player
	 * 
	 * @param name the human's display name
	 * @param displayChar character that will represent the Human in the map 
	 * @param hitPoints amount of damage that the Human can take before it dies
	 */
	protected Human(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints, ZombieCapability.ALIVE);
	}

	/**
	 * Return an Action to be performed during its turn. It will be able to move around the map or pick up the food item if
	 * it is standing on one.
	 * @param actions list of possible Actions
	 * @param lastAction previous Action, if it was a multiturn action
	 * @param map the map where the current Human is
	 * @param display the Display where the Human utterances will be displayed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {

		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if(action != null) 
				return action;
			
			}
		return new DoNothingAction();
	}
	
	
	
		
	/**
	 * Override the heal method from its parent class to increase the health.
	 */
	@Override
	public void heal(int points) {
		hitPoints += points; 
		hitPoints = Math.min(hitPoints, maxHitPoints);
		System.out.println(Integer.toString(points)+" Health is added to " + name);
	}
	
	
	/**
	 * Override the isConscious method from its parent class to check if the human is still conscious
	 */
	@Override
	public boolean isConscious() {
		if (hitPoints > 0) {
			return true;
		}
		return false;
		
		}
	

	@Override
	public ArrayList<String> getLimbs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String removeLimbs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int health() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void amputatedLegs() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * return the instance of human. It will return true if its a human. This is to allow to check from other classes to see if
	 * the actor is a human
	 */
	public boolean isHuman() {
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
	public boolean isZombie() {
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
