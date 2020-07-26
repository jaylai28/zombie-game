package game;

import java.util.ArrayList;

import edu.monash.fit2099.engine.*;
import game.GameWorld;


/**
 * Class representing Witch as a form of an enemy that extends from zombieActor class. It has an ability of stun a player.
 * @author weijianlai
 */
public class Witch extends ZombieActor {

    public static final char WitchChar = 'W';
    private boolean stunts = false;
    private int count=0;
    private Item stun = new Stunt("Stunt");
    private Behaviour[] behaviours = {
    		new StunBehaviour(GameWorld.getPlayer(), stun),
			new HuntBehaviour(Human.class, 10),
			new WanderBehaviour()
	};

    /**
     * Constructor to create an enemy with the witch name as input parameter
     *
     * @param name name of the witch
     */
    public Witch(String name) {
        super(name, WitchChar, 50, ZombieCapability.UNDEAD);
    }

    /**
     * Return the action to be performed during its turn.
     * It stuns the player and moves one step in a random direction in the following turn.
     * It will also moves closer to the Human, player but it will only stun the player.
     *
     * @param actions collection of possible Actions for this Actor
     * @param map     the map containing the Actor
     * @param display the I/O object to which messages may be written
     * @return the Action to be performed, e.g. stunning a player
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {

        if (!GameWorld.getPlayer().isStunned()) {
    		System.out.println("PLAYER IS  NOT STUNNED!!!");
            stunts = false;
        }
        

        for (Behaviour behaviour : behaviours) {
        	if (stunts && count<3) {
    			Action action = behaviours[2].getAction(this, map);			
    			if (action != null) {
    				count++;
    	            stunts = true;
    	            
    	            if (count >2) {
    	            	stunts = false;
    	            	count=0;
    	            }
    	            return action;
    	        }
        	}
			Action action = behaviour.getAction(this, map);
	        if (action != null) {
	        	count++;
	            stunts = true;
	            return action;
	        }
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

