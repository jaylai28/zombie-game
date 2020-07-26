package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * Class that represents a weapon that crafted from zombie legs
 * @author weijianlai
 *
 */
public class ZombieMace extends WeaponItem {

	/**
	 * Constructor that takes the name of the zombie, displayChar, damage and the attack verb as the input parameters
	 * @param name name of the zombie legs/arms
	 * @param displayChar the displayChar that represents zombie mace
	 * @param damage damage dealt in Integer
	 * @param verb String representing the attack verbs used
	 */
	public ZombieMace(String name, char displayChar, int damage, String verb) {
		super(verb, displayChar, damage, verb);
	}
	
	
}
