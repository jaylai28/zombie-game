package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.PickUpItemAction;
import edu.monash.fit2099.engine.Item;

/**
 *  A class that generates PickUpItemAction when the Actor is standing on a weapon item.
 * @author weijianlai
 *
 */
public class CheckWeaponBehaviour implements Behaviour {

	/**
     * Constructor for CheckWeaponBehaviour with no input parameters
     */
	public CheckWeaponBehaviour() {
	}
	
	
	/**
	 * Accessor:Create a list to store all the item at that location, and checks if zombie actor has the abiltiy to pickup
	 * the weapon.
	 * 
	 * @return An Action to allow items to be picked up.
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		List<Item> items = new ArrayList<Item>(map.locationOf(actor).getItems());
		if (actor.isZombie()) {
			for (Item i: items) {
				if (i.asWeapon() != null) {
					System.out.println("Zombie has picked up the weapon!");
					return new PickUpItemAction(i);
				}
			}		
		
	}
	return null;
	}
}
