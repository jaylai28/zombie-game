package game;

import edu.monash.fit2099.engine.Item;

/**
 * Base class for any item that can be picked up and dropped.
 * @author weijianlai
 */
public class PortableItem extends Item {

	public PortableItem(String name, char displayChar) {
		super(name, displayChar, true);
	}
}
