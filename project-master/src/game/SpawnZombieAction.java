package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * An action that spawns the zombie at the edge of the map randomly. 
 * @author weijianlai
 *
 */
public class SpawnZombieAction extends Action {

	/**
     * Constructor with no input parameter.
     */
	public SpawnZombieAction() {
	}

	
	/**
	 * creates the zombie in the map randomly. It will create 5 zombies in the map randomly inside the map as long as it doesn't
	 * contain any actors and the zombie can enter.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		List<Location> l = new ArrayList<Location>();
		for (int x=0; x<(map.getXRange().max()); x++) {
			for (int y=0; y<(map.getYRange().max()); y++) {
				if(!map.at(x, y).containsAnActor()) {
					l.add(map.at(x, y));
				}
				}	
				}
		Collections.shuffle(l);
		
		for (int i=0; i<5; i++) {
			boolean check = false;
			for ( Location location:l) {
				if (location.canActorEnter(actor) && check == false) {
					Zombie zombie = new Zombie("MarieArmyZ");
					map.addActor(zombie, location);
					check=true;
		}
		}
		}
        return menuDescription(actor);
	}

	
	/**
     * A string describing the action suitable for displaying in the UI menu.
     *
     * @param actor The actor performing the action.
     * @return a string,e.g "spawned 5 zombies"
     */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " spawned 5 zombies";
	}

}
