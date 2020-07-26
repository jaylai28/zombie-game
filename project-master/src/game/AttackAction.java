package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;






/**
 *  Special Action that allows the Actor to attack other Actors.

 * @author weijianlai
 *
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;
	// probability of attacking starting from 0.50
	private static double prob = 0.50;
	// counter to count the number of zombie arms
	private static int arm_counter = 0;

	
	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor to create an Action to damage an Actor
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target) {
		this.target = target;
	}

	
	/**
	 * Check if the Actor is able to perform the attack by doing the probability check. It will call the punch attack method
	 *  if it meets the probability check. This allows any actor that has the ability to attack to perform the attack action check here
	 *  which means that either Player or Zombie. It will also calls the AmputatedArms, AmputatedLegs and DropZombieParts methods
	 *  when the attack is successful that hits the Zombie.	 
	 *  */
	@Override
	public String execute(Actor actor, GameMap map) {
		Weapon weapon = actor.getWeapon();
		
		int weapon_damage = weapon.damage();
		int damage = 10;
		
		
		if (actor.isZombie()) {
			if (rand.nextDouble() <= 0.50) {
				if (rand.nextDouble() <= prob) {
					return punchAttack(actor,map,weapon,weapon_damage);				
				}
				
			}
			else {
				if (rand.nextDouble() <= 0.3) {
					int bitedamage = (damage)*2;
					actor.heal(5);
					return biteAttack(actor,map,bitedamage);
				}
		}	
	}
		
		else if (actor.isPlayer()) {
			if (rand.nextDouble() <= 0.5) {
				if (target.isZombie()) {
					if (rand.nextDouble() <= 0.5) {
					if (target.getLimbs().size()!=0) {
						String parts = target.removeLimbs();
						if (parts == "arm") {
							amputatedArms(map);
							Item arms = new ZombieArms("ZombieArms",'A');
							System.out.println("Arms are dropped");
							drop_zombie_parts(map,arms);
						}
						else {
							Item legs = new ZombieLegs("ZombieLegs",'L');
							System.out.println("Legs are dropped");
							target.amputatedLegs();
							drop_zombie_parts(map,legs);
							
						}	
					}
					else {
						System.out.println("Zombie only has 2 ARMS and 2 LEGS!!");
					}
				}
				}
				return punchAttack(actor,map,weapon,weapon_damage);				
				}
			}
		return actor + " missed the " + target;
	}
	

	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target;
	}
	
	
	/**
	 * Damage the target Actor and check if the subject is still conscious. If the subject is no longer conscious, it will then create an Item % to represent 
	 * the dead body of the subject and drops all Items in the subjects inventory. Then, it passes all Skills from the Actor to the
     * dead. It will also check if the unconcscious target is a Human, if it's Human, it will create a corpse instead that will turn
     * into a zombie after 5-10 turns.
	 * @param actor The attacking Actor
	 * @param map The game map in which the actor is in
	 * @param weapon the weapon the actor is using
	 * @param damage the total damage dealt in Integer
	 * @return A string displaying the damage dealt or the target is killed.
	 */
	public String punchAttack(Actor actor, GameMap map, Weapon weapon, int damage) {
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";

		target.hurt(damage);

		if (!target.isConscious()) {
			System.out.println(target + " HUMANNNNNNNNNNNNNN" + target.isHuman() + " " + target.isFarmer());
			if (target.isHuman()) {
			String name = map.locationOf(target).getActor().toString();
			Corpse dead_corpse = new Corpse(name);
			map.locationOf(target).addItem(dead_corpse);
			}
			else {
				Item corpse = new PortableItem("dead " + target, '%');
				map.locationOf(target).addItem(corpse);
			}
	
			Actions dropActions = new Actions();
			for (Item item : target.getInventory()) {
				dropActions.add(item.getDropAction()); }
			for (Action drop : dropActions)		{
				drop.execute(target, map);
				}
			map.removeActor(target);	
			
		
			result += System.lineSeparator() + target + " is killed.";
			}	
			
		return result;

	}
	
	
	/**
	* Damage the target Actor with bite attack and check if the subject is still conscious. If the subject is no longer conscious, it will then create an Item % to represent 
	 * the dead body of the subject and drops all Items in the subjects inventory. Then, it passes all Skills from the Actor to the
    * dead. It will also check if the unconcscious target is a Human, if it's Human, it will create a corpse instead that will turn
    * into a zombie after 5-10 turns.
	 * @param actor The attacking Actor
	 * @param map The game map in which the actor is in
	 * @param damage the total damage dealt in Integer
	 * @return A string displaying the damage dealt or the target is killed.
	 */
	public String biteAttack(Actor actor, GameMap map, int damage) {
		String result = actor + " " + "bite" + " " + target + " for " + damage + " damage.";

		target.hurt(damage);
		return isKilled(map, result);
		

	}
	
	public String isKilled(GameMap map, String result) {
		if (!target.isConscious()) {
			System.out.println(target + " HUMANNNNNNNNNNNNNN" + target.isHuman() + " " + target.isFarmer());
			if (target.isHuman()) {
			String name = map.locationOf(target).getActor().toString();
			Corpse dead_corpse = new Corpse(name);
			map.locationOf(target).addItem(dead_corpse);
			}
			else {
				Item corpse = new PortableItem("dead " + target, '%');
				map.locationOf(target).addItem(corpse);
			}
	
			Actions dropActions = new Actions();
			for (Item item : target.getInventory()) {
				dropActions.add(item.getDropAction()); }
			for (Action drop : dropActions)		{
				drop.execute(target, map);
				}
			map.removeActor(target);	
			
		
			result += System.lineSeparator() + target + " is killed.";
			}
		return result;

	}
	
	
	/**
	 * Decrease the chance of zombie's normal punch attack by half by cutting off one of the zombie arms. It will drop the zombie arm
	 * at the player's location so that player will have the options to pick the zombie arms up. It will also check if all the two
	 * zombie arms are cut, then it will lose the ability to attack and drop every weapon it's holding. 
	 * @param map The gamemap in which the actor in
	 * @return String showing the Zombie's Arms are dropped
	 */
	public String amputatedArms(GameMap map) {
		prob = prob/2;
		arm_counter++;
		if (rand.nextDouble() <= 0.50) {
			if (target.getWeapon() != null) {
				for (Item items : target.getInventory()) {
					System.out.println("Arms are dropped");
					Action dropitem= new DropItemAction(items);
					return dropitem.execute(target, map);

				}
			}
		}
		if (arm_counter == 2) {
			prob = 0;
			for (Item items : target.getInventory()) {
				Action dropitem= new DropItemAction(items);
				return dropitem.execute(target, map);
			
		}
		}
		return null;
	}
	
	
	
	

	/**
	 * A function that checks for the lists of exit where the zombie parts can be dropped. It will check if the location does not
	 * contain an actor and is a Dirt before dropping the parts.
	 * @param map the map in which the actor is in
	 * @param item the item to be dropped in the map
	 */
	public void drop_zombie_parts(GameMap map, Item item) {
		List<Exit> exits = new ArrayList<Exit>(map.locationOf(target).getExits());
		Collections.shuffle(exits);
		for (Exit e: exits) {
			if (!(e.getDestination().containsAnActor() && e.getDestination().getGround().isDirt())) {
				e.getDestination().addItem(item);
				break;
				}}
	}


}
	
	
	

	
