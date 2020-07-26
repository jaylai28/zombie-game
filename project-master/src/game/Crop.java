package game;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;


/**
 * A crop that will grow from an unripe crop to a ripe crop that can be harvested by either farmer or Player into food.
 * @author Amil
 *
 */
public class Crop extends Ground{
	public int age = 0;
	public boolean ripe=false;
	
	/**
	 * Constructor with no input parameters and a display character of v
	 */
	public Crop() {
		super('v');
	}
	
	/**
	 *	the actor can enter this ground
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return true;
	}
	
	
	/**
	 * This method is override from its parent class, Ground class. It will be called once per turn that will experience the joy of
	 * time. It will increase the tick and counter by 1 each turn by adding the age of the crops. If the crop is the same as the 
	 * mature age of the crop which is 20, it will then turn into a ripe crop and change the displayChar to V. It uses the 
	 * ItemCapability to determine if the crop is RIPE or UNRIPE. When the crop is UNRIPE, the itemCapability will be in UNRIPE state.
	 * 
	 */
	@Override
	public void tick(Location location) {
		super.tick(location);
		age++;
		if (age <20) {
			displayChar = 'v';
			location.getGround().addCapability(ItemCapability.UNRIPE);
		}
		else {
			displayChar = 'V';
			location.getGround().addCapability(ItemCapability.RIPE);
	}
	}
	
	/**
	 * get the age of the crop
	 */
	public int getage() {
		return this.age;}
	
	
	/**
	 * change the current age of the crop to the changed age
	 */
	public void fertilise(int age) {
		this.age=age;}
	
		
	}
	
	
	


