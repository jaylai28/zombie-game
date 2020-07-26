package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;


/**
 * 
 * @author Amil
 *
 */
public class ShotGunShootingAction extends Action {
	private String direction;

	public ShotGunShootingAction(String direction) {
		this.direction = direction;
	}

	@Override
	public String execute(Actor actor, GameMap map) {

		return null;
	}

	@Override
	public String menuDescription(Actor actor) {

		return actor + " shoots in " + direction + " direction";
	}



}
