package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;

/**
 * Class that allows the player to move around between different maps
 * @author weijianlai
 *
 */
public class Vehicle extends Item {
	private Location currentLocation;
	private Location townLocation;
	private Actor player;
	private boolean validmove = false;

	/**
	 * Constructor with the input parameters of currentLocation,townLocation and player to move player around.
	 * @param currentLocation the current location of the player
	 * @param townLocation the location to be moved to
	 * @param player the player to be moved
	 */
	public Vehicle(Location currentLocation, Location townLocation, Actor player) {
		super("Vehicle", '^', false);
		this.currentLocation = currentLocation;
		this.townLocation = townLocation;
		this.player = player;
        allowableActions.clear();

    }

	
	
    /**
     * Allows Actor to move to town while it is on the curent map and move to the current map while it is in the town.
     *
     * @return a collection of Action which the actor can perform
     */
    @Override
    public List<Action> getAllowableActions() {
    	if (player.hasBattery() && player.hasKey()) {
    		player.removeBattery();
    		player.removeKey();
    		validmove = true;
    	}
    	
        allowableActions.clear();

        if (currentLocation.containsAnActor() && currentLocation.getActor().isPlayer() && validmove) {
            allowableActions.add(new MoveActorAction(townLocation, "to Town Map!"));
            validmove=false;
        } else if (townLocation.containsAnActor() && townLocation.getActor().isPlayer() && validmove) {
            allowableActions.add(new MoveActorAction(currentLocation, "to Game Map!"));
            validmove=false;

        }
        
        return allowableActions.getUnmodifiableActionList();
    }
		

}
