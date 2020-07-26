package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;


/**
 * Class that generates the SunAction when the player is able to be stunend.
 * @author weijianlai
 *
 */
public class StunBehaviour extends Action implements Behaviour {
	 private Actor target;
	 private Item stun;
	 private Random random = new Random();
	    
	 
	 /**
	     * Constructor to create an Action to stun a target Actor.
	     *
	     * @param target the target for the stun to be performed
	     * @param stun the stun item to stun the target

	     */
	    public StunBehaviour(Actor target, Item stun) {
	        this.target = target;
	        this.stun = stun;
	    }
	    
	 
	/**
	 * Returns the action of stunning if the target is within the range of the Actor.
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		// Is there an Actor next to me to Stunt
		List<Exit> exits = new ArrayList<Exit>(map.locationOf(actor).getExits());
		Collections.shuffle(exits);
		
		for (Exit e: exits) {
			if (!(e.getDestination().containsAnActor()))
				continue;
			if (e.getDestination().getActor().isPlayer() && !e.getDestination().getActor().isStunned()) {
				return this;
				}
			}
		return null;	
		}


	/**
	 * Adds a stun item into the actor's inventory that will stunned the actor for 2 turns. It will have 20% chance of success.
	 * It will not stun the actor if the actor is already stunned.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		if (random.nextDouble() <= 0.2) {
            boolean stunExists = false;
            for (Item item : target.getInventory() ) {
                if (item.equals(stun)) {
                    stunExists = true;
                    break;
                }
            }
            if (!stunExists) {
            	target.addItemToInventory(stun);
                return menuDescription(actor);
            }
            return target + " is already stunned!!";
        }
        return actor + " failed to stun " + target;
	}


	@Override
	public String menuDescription(Actor actor) {
        return actor + " has successfully stunned the " + target + " for 2 turns!!";
	}

}
