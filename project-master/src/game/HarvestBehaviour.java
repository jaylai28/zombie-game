package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;

/**
 * Class represents the Behaviour of harvesting a RIPE crop and return an Action that allows the Actor to harvest the crop 
 * when the crop is RIPE. It will harvest the RIPE crop and turn it into a food which is edible by humans and player.
 * @author Amil
 *
 */
public class HarvestBehaviour extends Action implements Behaviour {
	private Food food = new Food();
	private Ground dirt= new Dirt();
	
	/**
	 * Constructor for HarvestBehaviour with no input parameters
	 */
	public HarvestBehaviour() {
	}

	
	/**
	 * Returns the Action of harvesting the crop if the crop is RIPE and create a food. Add the ItemCapability of FOOD and EAT to the
	 * newly created food. Set the location of the crop back to ground and add the food item at that location.
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		
		if(map.locationOf(actor).getGround().hasCapability(ItemCapability.RIPE)) {
			map.locationOf(actor).setGround(dirt);
			map.locationOf(actor).addItem(food);
			return this;
		}

		return null;
	}

	/**
	 * Harvest the crop and turn the location of the crop into dirt and add the food item into player's inventory or just drop 
	 * the food item at the current location of where the actor stands. This will allow the humans or player to pick up the food
	 * to perform eatAction.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		if (actor.isPlayer()) {
			map.locationOf(actor).setGround(dirt);
			actor.addItemToInventory(food);
			return actor + " harvests the crop";
		}
			
		else if (actor.isFarmer()){
			return actor + " harvests the crop";
		}
		
		return actor + " cannot harvest crops!!";
	}

	/**
     * A string suitable for describing the action in the UI display.
     *
     * @param actor The actor harvesting the crop
     * @return a string 
     */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " harvests crops";
	}

}
