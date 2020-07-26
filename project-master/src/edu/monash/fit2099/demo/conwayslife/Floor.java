package edu.monash.fit2099.demo.conwayslife;

import edu.monash.fit2099.engine.Ground;

public class Floor extends Ground {

	public Floor() {
		super('.');
		addCapability(Status.DEAD);
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
