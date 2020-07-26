package game;


/**
 * Class represents the zombie arms which will be drop as an item
 * @author weijianlai
 *
 */
public class ZombieArms extends PortableItem {
	
	/**
	 * Constructor that takes the zombie name and the displayChar 'A' as the parameters
	 * @param name the zombie name
	 * @param displayChar the char of that represents the zombie Arms in the map
	 */
	public ZombieArms(String name, char displayChar) {
		super(name,displayChar);
		this.addCapability(ItemCapability.CRAFT);

	}
	
	
	/**
	 * return the instance of zombie. It will return true if its a zombie. This is to allow to check from other classes to see if
	 * the actor is a zombie
	 */
	public boolean isZombieArms() {
		return true;
	}
	

}