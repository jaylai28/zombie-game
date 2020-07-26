package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Action that allows the Actor to craft an item into a weapon that can deal damage.
 * @author weijianlai
 *
 */
public class CraftAction extends Action {
	protected Item items;

	/**
	 * Constructor that takes the item as the parameter

	 * @param i item
	 */
	public CraftAction(Item i) {
		items = i;
	}

	/**
	 * Crafts the item into selected weapon such as craft the Zombie Arms into Zombie Clubs and Zombie Legs into Zombie Mace.
	 * It will remove the item from the actor's inventory and create the new weapon and add it into the actor's inventory.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		if (items.isZombieArms()) {
			actor.removeItemFromInventory(items);

			System.out.println("Zombie Arms are crafted into Zombie Clubs");
			Item weapon_arms = new ZombieClub("WeaponClubs", '!', 15, "HURT");
			actor.addItemToInventory(weapon_arms);
			return actor + " crafts " + weapon_arms;

		}
		else if (items.isZombieLegs()) {
			actor.removeItemFromInventory(items);

			System.out.println("Zombie Legs are crafted into Zombie Mace");
			Item weapon_legs = new ZombieClub("WeaponMace", '?', 15, "SNAP");
			actor.addItemToInventory(weapon_legs);
			return actor + " crafts " + weapon_legs;
		}

		return actor + "can't crafts " + items;
	}

	
	/**
     * A string suitable for describing the action in the UI display.
     *
     * @param actor The actor crafting the item
     * @return a string 
     */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " crafts " + items;
	}

}
