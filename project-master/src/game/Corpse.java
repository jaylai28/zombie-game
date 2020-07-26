package game;


import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


/**
 * An Item object that extend from Item class which will be added when a Human is dead. It will rise from the dead as zombie after
 * 5 to 10 turns.
 * @author weijianlai
 *
 */
public class Corpse extends Item {
	private int Age = 0;
	private int ResurrectAge = ThreadLocalRandom.current().nextInt(5,11);

	/**
	 * Constructor that takes the corpse name as the parameter
	 * @param name the corpse name
	 */
	protected Corpse(String name) {
		super(name, '%', true);
	}
	
	
	
	
	/**
	 * This method is override from its parent class, Item class. It will be called once per turn when the Item is in the player's
	 * inventory i.e carried by the Actor, player. It will increase the tick and counter by 1 each turn by adding the age of the 
	 * corpse. If the corpse is the same as the ResurrectAge, which is the age determined by ThreadLocalRandom(). Then, it will 
	 * create a new zombie object with the corpse name and remove the corpse item from the actor's inventory and place the new zombie
	 * object at the location taht does not contain any actor. It will call the summonZombies method to summon the zombies.
	 * 
	 */
	@Override
	public void tick(Location location, Actor actor) {
		super.tick(location);
		Age++;	
		if (Age >= ResurrectAge) {
			summonZombies(location);
			actor.removeItemFromInventory(this);

		}
}
	
	
	/**
	 * This method is override from its parent class, Item class. It will be called once per turn when the Item is at the location
	 * in the map. It will increase the tick and counter by 1 each turn by adding the age of the corpse. If the corpse is the same 
	 * as the ResurrectAge, which is the age determined by ThreadLocalRandom(). It will call the summonZombies method to summon the zombies.
	 * 
	 */
	@Override
	public void tick(Location location) {
		super.tick(location);
		Age++;	
		if (Age >= ResurrectAge) {
			summonZombies(location);
		}
	}

			

	
	
	/**
	 * Get the lists of exits that does not contain any actor and the actor is allowed to enter. Then, it will  create a new zombie
	 * object with the corpse name and remove the corpse item at the current location and place the new zombie object at that location.
	 * This will then be used to spawn the zombie at the location. 
	 * @param location Location of the Item. 
	 */
	public void summonZombies(Location location) {
		String newName = (name+="Z");
		Actor zombie = new Zombie(newName);
		
		if (!(location.containsAnActor()) && location.canActorEnter(zombie)) {
			location.removeItem(this);
			location.addActor(zombie);
			System.out.println("New Zombie" + this.name + " is spawned!");
		}
		else {
		List<Exit> exits = new ArrayList<Exit>(location.getExits());
		Collections.shuffle(exits);
		for (Exit e: exits) {
			if (!e.getDestination().containsAnActor() && e.getDestination().canActorEnter(zombie)) {
				e.getDestination().addActor(zombie);
				location.removeItem(this);
				break;
				}
			}
	}
	}
	
	
}
