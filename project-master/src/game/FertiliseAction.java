package game;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Action that alllows farmer to fertlise the crop and decrease the ripe age by 10 turns
 * @author Amil
 *
 */
public class FertiliseAction extends Action {

	/**
	 * Constructor with no input parameter
	 */
	public FertiliseAction() {

	}
	
	/**
	 * get the age of the crop at that location and increase the age of the crop by 10 to reduce the time it takes to ripe the crop.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		int Localage=map.locationOf(actor).getGround().getage();
		map.locationOf(actor).getGround().fertilise(Localage+10);
		
		return actor + " fertilise the crop!";
		
	}
	



	@Override
	public String menuDescription(Actor actor) {
		return null;
	}		
}
