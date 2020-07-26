package game;

import java.util.ArrayList;
import java.util.List;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.World;


/**
 * Base class for Actors in the Zombie World
 * @author ram
 *
 */
public abstract class ZombieActor extends Actor {
	
	protected List<Behaviour> behaviourlist = new ArrayList<Behaviour>();

	
	protected ZombieActor(String name, char displayChar, int hitPoints, ZombieCapability team) {
		super(name, displayChar, hitPoints);
		
		addCapability(team);
	}
	
	
	/**
	 * Add a behaviour that the Zombie implements
	 * @param behaviour The behaviour to add
	 */
	protected void addBehaviour(Behaviour behaviour) {
		this.behaviourlist.add(behaviour);
	}
	
	
	
	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions list = super.getAllowableActions(otherActor, direction, map);
		if (otherActor.hasCapability(ZombieCapability.UNDEAD) != this.hasCapability(ZombieCapability.UNDEAD))
			list.add(new AttackAction(this));
		return list;
	}
	
	}
	
	
	
	
	



