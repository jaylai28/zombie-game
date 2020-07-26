package game;

import edu.monash.fit2099.engine.Item;

/**
 * Class that represents the Stunt item which can stun the actor.
 * @author weijianlai
 *
 */
public class Stunt extends Item {
	

	
	
	/**
     * Constructor to create stunt with a name.
     *
     * @param name name of the stun powder bomb
     */
	public Stunt(String name) {
		super(name, '?', false);
		
	}
	
	/**
	 * return the instance of stun. It will return true if its a stun. This is to allow to check from other classes to see if
	 * the object is a stun.
	 */
	public boolean isStun() {
		return true;
	}
	

}
