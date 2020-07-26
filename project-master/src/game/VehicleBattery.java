package game;

import edu.monash.fit2099.engine.Item;

/**
 * Class that extends from Item class, it will helps to move the player to another map by picking up the battery
 * @author weijianlai
 *
 */
public class VehicleBattery extends Item {

	/**
	 * Constructor with no input parameter. Initialise the name of the item as battery with the displayChar of 'B' that represents
	 * the vehicle battery and make sure it's portable by returning true.
	 */
	public VehicleBattery() {
		super("Battery",'B',true);		
		this.addCapability(ItemCapability.BATTERY);

	}

}
