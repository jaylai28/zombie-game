package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Action that allows the player or humans to eat the food and heal its health.
 * @author Amil
 *
 */
public class EatAction extends Action {
	protected Item items;

	/**
	 * input the Item as an input parameter to pass the food item into the class.
	 * @param i food item
	 */
	public EatAction(Item i) {
		items = i;

	}
	
	
	/**
	 * Allows the humans or player to eat the food. For the player, it will check if the health of the player is not full first,
	 * before eating the food. The player will then remove the food item from the inventory and heal its health by 10. For the human,
	 * it will remove the food item at the location where its standing and heal 10 health to itself.
	 * 
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		if (!actor.maxhealth()) {
			if (actor.isPlayer()) {
				actor.removeItemFromInventory(items);
				actor.heal(10);
				return actor + " heals from eating!";
				}
			
			}
			else if(actor.isHuman()){
				map.locationOf(actor).removeItem(items);
				actor.heal(10);
				return actor + " heals from eating!";
				}
		
	return actor + " health is full!";

	}
	
	
	/**
     * A string suitable for describing the action in the UI display.
     *
     * @param actor The actor performing the action.
     * @return a string
     */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " eats food";
	}
	
}
