package game;

import edu.monash.fit2099.engine.Ground;

/**
 * A class that represents bare dirt.
 * @author Amil
 */
public class Dirt extends Ground {

	public Dirt() {
		super('.');
	}
	
	public boolean isDirt() {
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
