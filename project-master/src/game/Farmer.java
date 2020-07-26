package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;

/**
 * Class representing a Farmer as a type of Human. It allows the farmer to perform a lists of actions with the behaviours it has.
 * 
 * @author Amil
 *
 */
public class Farmer extends Human {
	
	private Behaviour[] behaviours = {
			new HarvestBehaviour(),
			new FarmingBehaviour(),
			new WanderBehaviour()
	};
	
	/**
     * Constructor that creates a new Farmer with the input parameter of the farmer's name.
     *
     * @param name        a String the name of the farmer
     */
	public Farmer(String name) {
		super(name, 'F', 50);
        addBehaviour(new FarmingBehaviour());
        addBehaviour(new HarvestBehaviour());
		  }
	
	
	/**
     * Returns an Action to be performed during its turn.
     * It will be able to harvest the crop if it is standing on a ripe crop. Else, it will call farming behaviour to sow or fertilise
     * the crop. If the farmer could not do any of it above, it will wander around.
     *
     * @param actions collection of possible actions for farmer in the turn
     * @param map     the map containing the Actor
     * @param display the object that performs the console I/O
     * @return the Action to be performed, e.g. sowing, fertilising or harvesting a crop
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
	 * return the instance of farmer. It will return true if its a farmer. This is to allow to check from other classes to see if
	 * the actor is a farmer
	 */
	public boolean isFarmer() {
		return true;
	}
	
	@Override
	public boolean isHuman() {
		return false;
	}
	
	@Override
	public boolean isZombie() {
		return false;
	}
	
	

}
