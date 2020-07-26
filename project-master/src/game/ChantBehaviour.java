package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;


/**
 * A class that generates SpawnZombieAction or VanishAction when the actor's player turn exceeds a certain value such as every 10 turns.
 * @author weijianlai
 *
 */
public class ChantBehaviour implements Behaviour {
	protected int turn = 0;
	protected int i =0;

	/**
     * Constructor for ChantBehaviour with no input parameters
     */
	public ChantBehaviour() {
	}

	
	/**
	 * Returns spawnZombieAction every 10 turns and VanishAction after 30 turns in the map.
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		turn++;
		if (turn == 10) {
			return new SpawnZombieAction();
		}
		if (turn == 20) {
			return new SpawnZombieAction();
	}
		if (turn == 30) {
			return new SpawnZombieAction();
	}
		else if (turn>30) {
			return new VanishAction();
		}
		return null;
	}
	
	

}
