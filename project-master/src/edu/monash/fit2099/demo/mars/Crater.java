package edu.monash.fit2099.demo.mars;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;


public class Crater extends Ground {

	public Crater() {
		super('o');
	}
	
	@Override
	public boolean canActorEnter(Actor a) {
		return a.hasCapability(DemoCapabilities.SPACETRAVELLER);
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