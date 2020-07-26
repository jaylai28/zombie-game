package game;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;


/**
 * Class that represents as a type of Enemy that is allowed to spawn 5 new zombies in every 10 turns and will disappear after 30 turns
 * @author weijianlai
 *
 */
public class MamboMarie extends ZombieActor { 
	protected boolean alive = true;


	private static final char MamboMarie_display = 'M';
	private Behaviour[] behaviours = {
			new ChantBehaviour(),
			new WanderBehaviour()
	};

	/**
	 * Constructor that takes the Mambo Marie name as the parameter
	 * @param name mambo marie name
	 */
	public MamboMarie(String name) {
		super(name, MamboMarie_display, 10, ZombieCapability.UNDEAD);
	}


	/**
	 *  * Returns an Action to be performed during its turn. It will be able to perform chanting which will spawn 5 zombies in every
	 *  10 turns. Else, it will just wanders around the map.
	 *  
	 *  
	 * @param actions list of possible Actions
	 * @param lastAction previous Action, if it was a multiturn action
	 * @param map the map where the current Mambo Marie is
	 * @param display the Display where the Mambo Marie's utterances will be displayed
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
	public void amputatedLegs() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public int health() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public boolean maxhealth() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	/**
	 * return the instance of mambo marie. It will return true if its a mambo marie. This is to allow to check from other 
	 * classes to see if the actor is a mambo marie.
	 */
	public boolean isMambo() {
		return true;
	}
	
	/**
	 * return a boolean value of false when the mambo marie disappeared from the map. This means that mambo is not dead yet, just 
	 * temporarily disappear
	 */
	public boolean isAlive() {
		return this.alive;
	}
	
	/**
	 * return the current boolean value to the set boolean value.
	 */
	public boolean disappear(boolean value) {
		return this.alive=value;
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
