package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

/**
 * An impenetrable barrier.
 * 
 * @author weijianlai
 *
 */
public class Fence extends Ground {

	public Fence() {
		super('#');
	}
	
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}
	
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
	
	public boolean isFence() {
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
