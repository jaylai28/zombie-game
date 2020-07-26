package game;

import edu.monash.fit2099.engine.Item;

/**
 * A food item that extends from Item class. It will be added when the crop is harvested. Humans and player will then be able to eat
 * it to heal its health
 * @author Amil
 *
 */
public class Food extends Item{

	/**
	 * Constructor with no input parameter. Initialise the name of the item as food with the displayChar of '$' that represents food
	 * and make sure it's portable by returning true.
	 */
	public Food() {
		super("food ",'$',true);
		this.addCapability(ItemCapability.FOOD);
		this.addCapability(ItemCapability.EAT);
	}
	
	
	

}
