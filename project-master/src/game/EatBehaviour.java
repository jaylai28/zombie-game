package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;


/**
 * A class that generates EatAction when it goes through the list of item the actor is in and the ItemCapability of the item is a
 * food. This will allows the actor to calls EatAction.
 * @author Amil
 *
 */
public class EatBehaviour implements Behaviour {
	/**
     * Constructor for EatBehaviour with no input parameters
     */
	public EatBehaviour() {
	}
	
	
	/**
	 * Returns EatAction when the ItemCapability of the item is a food at the location where the actor is standing.
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		List<Item> items = new ArrayList<Item>(map.locationOf(actor).getItems());
		
		for (Item i: items) {
			if(i.hasCapability(ItemCapability.FOOD)) {
				System.out.println("FOOD IS AVAILABLE AT THAT LOCATION!");
				return new EatAction(i);
			}

		}
		
		return null;
	}

}

	
