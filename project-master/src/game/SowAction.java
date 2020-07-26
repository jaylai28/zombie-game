package game;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Action that allows Actor to plant the crop
 * @author Amil
 *
 */
public class SowAction extends Action{
	
	 /**
     * Constructor to create an Action that will sow the crop with no input parameter.
     */
	public SowAction() {
	}

	
	/**
	 * return the string that the actor has plant the crop
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		return actor + " plants the crop!";
		}

	
	/**
     * A string suitable for describing the action in the UI display.
     *
     * @param actor The actor performing the action.
     * @return a string
     */
	@Override
	public String menuDescription(Actor actor) {
		return null;
	}
}
