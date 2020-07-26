package edu.monash.fit2099.demo.mars;

import java.util.*;

import game.Behaviour;
import edu.monash.fit2099.engine.*;


public class Bug extends Actor {

	private Random rand = new Random();
	public List<Behaviour> actionFactories = new ArrayList<Behaviour>();

	public Bug() {
		super("Feature", 'x', 1);
	}
	
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		for (Behaviour factory : actionFactories) {
			Action action = factory.getAction(this, map);
			if(action != null)
				return action;
		}
		
		return actions.get(rand.nextInt(actions.size()));
	}

	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions list = super.getAllowableActions(otherActor, direction, map);
		list.add(new KickAction(this));
		return list;
	}

	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(1, "stings");
	}

	@Override
	public ArrayList<String> getLimbs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String removeLimbs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void amputatedLegs() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int health() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean maxhealth() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasBattery() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasKey() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeBattery() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeKey() {
		// TODO Auto-generated method stub
		
	}
}
