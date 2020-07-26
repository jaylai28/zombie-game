package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * Action that allows the player to end the game at any time.
 * @author Amil
 *
 */
public class QuitGameAction extends Action {

    Location location;

    /**
     * Constructor to create an Action that will end the Game with no input parameter.
     */
    public QuitGameAction() {
    }

    
    /**
     * Remove the player from the map to exit the game
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String End the game
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return "Player ends the game!";
    }

    
    /**
     * A string suitable for describing the action in the UI display.
     *
     * @param actor The actor performing the action.
     * @return a string
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Quit Game";
    }

}
