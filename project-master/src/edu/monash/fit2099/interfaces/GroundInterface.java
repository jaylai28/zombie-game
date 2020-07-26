package edu.monash.fit2099.interfaces;

/**
 * This interface provides the ability to add methods to Ground, without modifying code in the engine,
 * or downcasting references in the game.   
 */

public interface GroundInterface {
	default public boolean isDirt() {
		return false;
	}
	
	
	default public boolean isTree() {
		return false;
	}
	
	default public boolean isFence() {
		return false;
	}
	
	public int getage();
	
	public void fertilise(int age);
	

}
