package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * Class that represents a weapon that crafted from zombie arms
 * @author weijianlai
 *
 */
public class ZombieClub extends WeaponItem {
	/**
	 * Constructor that takes the name of the zombie, displayChar, damage and the attack verb as the input parameters
	 * @param name name of the zombie legs/arms
	 * @param displayChar the displayChar that represents zombie club
	 * @param damage damage dealt in Integer
	 * @param verb String representing the attack verbs used
	 */
	public ZombieClub(String name, char displayChar, int damage, String verb) {
		super(verb, displayChar, damage, verb);
	}
	
	
}
