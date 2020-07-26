package game;


import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.WeaponItem;

/**
 * A weapon that uses direction and shoots in a range of 3 and damage the target within the range.
 * @author Amil
 *
 */
public class Shotgun extends WeaponItem {
	protected Location location;
	protected String direction;
	protected String hotkey;

	/**
     * Constructor with no input parameter.
     */
	public Shotgun() {
		super("shotgun", 'S', 5, "shoot");
	}
	
	/**
	 * return the instance of shotgun. It will return true if its a shotgun. This is to allow to check from other classes to see if
	 * the actor is a shotgun
	 */
	public boolean isShotGun() {
		return true;
	}
	
}
