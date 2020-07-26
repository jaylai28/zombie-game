package game;

/**
 * Class represents the zombie legs which will be drop as an item
 * @author weijianlai
 *
 */
public class ZombieLegs extends PortableItem {

	/**
	 * Constructor that takes the zombie name and the displayChar 'L' as the parameters
	 * @param name the zombie name
	 */
	public ZombieLegs(String name, char displayChar) {
		super(name,displayChar);
		this.addCapability(ItemCapability.CRAFT);

	}
	
	
	/**
	 * return the instance of zombie. It will return true if its a zombie. This is to allow to check from other classes to see if
	 * the actor is a zombie
	 */
	public boolean isZombieLegs() {
		return true;
	}
	

}