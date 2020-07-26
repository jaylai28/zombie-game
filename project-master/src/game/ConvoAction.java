package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.GameMap;

/**
 * Action that allows the Actor to talk by printing a message on the display.
 * @author weijianlai
 *
 */
public class ConvoAction extends Action {
	private String text;
	private Actor actor;

	 /**
     * Constructor to create an Action that takes in the Actor that performs the talk.
     *
     * @param newText a message
     * @param newActor  the actor thats performing the talk
     */
	public ConvoAction(String newText, Actor newActor) {
		text = newText;
		actor = newActor;
	
	}
	
	
	/**
     * Prints out the actor's talk message.
     *
     * @param newActor the actor performing the action
     * @param newMap   the map the actor is on
     * @return a suitable description to display in the UI
     */
	@Override
	public String execute(Actor newActor, GameMap newMap) {
		return actor + ": " + text;
	}
	

	
	/**
     * A string describing the action suitable for displaying in the UI menu.
     *
     * @param actor The actor performing the action.
     * @return null
     */
	@Override
	public String menuDescription(Actor newActor) {
		return null;
	}

	
}
