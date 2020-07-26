package game;

import edu.monash.fit2099.engine.Item;


/**
 * Class that extends from Item class, it will helps to move the player to another map by picking up the key
 * @author weijianlai
 *
 */
public class VehicleKey extends Item {	

	/**
	 * Constructor with no input parameter. Initialise the name of the item as key with the displayChar of 'K' that represents
	 * the vehicle key and make sure it's portable by returning true.
	 */
	public VehicleKey() {
		super("Key",'K',true);
		this.addCapability(ItemCapability.KEY);
	}
	
	
	

}
