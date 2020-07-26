package game;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;

/**
 * A class that generates SowingAction or FertiliseAction by checking the ItemCapability and type of the crop at that location.
 * If the crop is unripe, then it will call fertiliseAction to increase the age of the crop by 10 turns. If the location of where
 * the actor standing is a dirt, then it will have 33% chance of calling the SowAction.
 * @author Amil
 *
 */
public class  FarmingBehaviour implements Behaviour {
	
	private Random rand = new Random();
	
	/**
     * Constructor for FarmingBehaviour with no input parameters
     */
	public FarmingBehaviour() {
	}

	/**
	 * returns either fertilise action or sowAction depending on the location of where the actor, farmer is standing. If the
	 * actor is standing on the unrip crop, then it will calls fertilise action. Else, it will go through the list of exits the 
	 * actor can access to and check if the location contains any actor and if the ground type is a dirt. If it meets the
	 * condition, it will call sowAction at that exit.
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		if(map.locationOf(actor).getGround().hasCapability(ItemCapability.UNRIPE)) {
			Action fertilise= new FertiliseAction();
			return fertilise;
			}
		
	
		List<Exit> exits = new ArrayList<Exit>(map.locationOf(actor).getExits());
		Collections.shuffle(exits);
		
		for (Exit e: exits) {
			if (!(e.getDestination().containsAnActor()) && e.getDestination().getGround().isDirt()) {
				if (rand.nextDouble() <= 0.33) {
					Crop crop= new Crop();
					e.getDestination().setGround(crop);
					return new SowAction();

				}}

			}
		
		return null;
		}
}
	


	



