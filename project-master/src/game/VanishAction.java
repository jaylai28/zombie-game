package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Action that makes an actor to disapppear from the map
 * @author weijianlai
 *
 */
public class VanishAction extends Action {
	
	/**
     * Constructor to create an Action that will make an actor disappear with no input parameter.
     */
	public VanishAction() {
	}

	/**
	 * Make the actor disappear from the map by removing the actor from the map.
	 * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a suitable description to display in the UI
     */
	@Override
	public String execute(Actor actor, GameMap map) {
		actor.disappear(false);
		map.removeActor(actor);
        return menuDescription(actor);
	}

	
	/**
     * A string describing the action suitable for displaying in the UI menu.
     *
     * @param actor The actor performing the action.
     * @return a string
     */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " vanished into the air";
	}

}
