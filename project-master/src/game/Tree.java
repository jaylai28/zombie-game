package game;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * A tree that starts as a sapling and grows into a large tree.
 * 
 * @author weijianlai
 *
 */
public class Tree extends Ground {
	private int age = 0;

	public Tree() {
		super('+');
	}

	@Override
	public void tick(Location location) {
		super.tick(location);

		age++;
		if (age == 10)
			displayChar = 't';
		if (age == 20)
			displayChar = 'T';
	}
	
	public boolean isTree() {
		return true;
	}


	@Override
	public int getage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void fertilise(int age) {
		// TODO Auto-generated method stub
		
	}
}
