package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Menu;

/**
 * 
 * @author weijianlai
 *
 */
public class MenuChoosingAction extends Action {
	private Menu menu = new Menu();
	private Display display;
	protected String hotKey;	
	private Actions actions = new Actions();
	
	public MenuChoosingAction(Display display) {
		this.display = display;
	}
	


	@Override
	public String execute(Actor actor, GameMap map) {
		List<Exit> exits = new ArrayList<Exit>(map.locationOf(actor).getExits());

		for (Exit e: exits) {
			actions.add(new ShotGunShootingAction(e.getName()));			
		}
		return menu.showMenu(actor, actions, display).execute(actor, map);
		
	}
    


	@Override
	public String menuDescription(Actor actor) {
		return actor + " use shotgun to attack";
	}
	
	
	
	
	

	
//	/**
//     * Returns the Action of shooting with shotgun if the target is within the range of the Actor.
//     *
//     * @param actor the actor performing the throwing stun
//     * @param map   the map which the actor is on
//     * @return ShotGunAction class if the target is within the range and null if it is not
//     */

}
