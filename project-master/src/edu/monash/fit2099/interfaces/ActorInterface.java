package edu.monash.fit2099.interfaces;

import java.util.ArrayList;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.ItemCapability;

/**
 * This interface provides the ability to add methods to Actor, without modifying code in the engine,
 * or downcasting references in the game.   
 */

public interface ActorInterface {
	public ArrayList<String> getLimbs();
	
	public String removeLimbs();
	
	public void amputatedLegs();
	
	public int health();
	
	public boolean maxhealth();
	
	public boolean hasBattery();
    
	public void removeBattery();

	public void removeKey();

	
    public boolean hasKey();
    
    public default boolean disappear(boolean value) {
    	return true;
    }

	
	default public boolean isHuman() {
		return false;
	}
	default public boolean isPlayer() {
		return false;
	}
	default public boolean isFarmer() {
		return false;
	}
	default public boolean isZombie() {
		return false;
	}
	default public boolean isMambo() {
		return false;
	}
	default public boolean isAlive() {
		return false;
	}
	default public boolean isStunned() {
        return false;    
    }
	
	
	
}