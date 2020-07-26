package game;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Menu;

/**
 * Class representing the Player. The player is able to input the action it wants to perform each turn.
 * @author weijianlai
 */
public class Player extends Human {
    private Item stunOnMap = null;
    private int count=0;
	private Menu menu = new Menu();
	
	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
	}
	
	
	/**
	 * Return an Action to be performed during its turn. The player will have a list of actions to be performed that will be printed
	 * in a menu for the player to select. The lists of actions that the player can perform are such as choosing direction to move
	 * to, attacking the enemy, harvesting crops, eating food and etc.
	 * 
	 * @param actions list of possible Actions
	 * @param lastAction previous Action, if it was a multiturn action
	 * @param map the map where the current player is
	 * @param display the Display where the player's utterances will be displayed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		actions.add(new QuitGameAction());
		
		if (isStunned()) {
            updateStunnedStatus(actions);
        }

		// Handle multi-turn Actions
		if(map.locationOf(this).getGround().hasCapability(ItemCapability.RIPE)) {
			actions.add(new HarvestBehaviour());
				}
		
		
		for(Item i : this.getInventory()) {
			if (i.hasCapability(ItemCapability.CRAFT)) {
				actions.add(new CraftAction(i));
			}
			else if (i.hasCapability(ItemCapability.EAT)) {
				actions.add(new EatAction(i));
			}
			else if (i.isShotGun()) {
				actions.add(new MenuChoosingAction(display));
				
			}
		}
		
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction(); 
		
		return menu.showMenu(this, actions, display);
	}
	
	
	
    
	
	/**
     * Returns true if the player is stunned by checking if the player is stunned.
     *
     * @param map map which the player is on
     * @return true if and only if the player is stunned that contain the stun item in its inventory.
     */
    public boolean isStunned() {
		for(Item i : this.getInventory()) {
            if (i.isStun()) {
                stunOnMap = i;
                return true;
            }
        }
        return false;
    }
   

    /**
     * Updates the stunned status by decreasing the count by 1 if it is stunned and forces the player to
     * skip its turn. Otherwise, it removes the stun item from the player's location.
     *
     * @param actions a list of actions to be returned
     */
    private void updateStunnedStatus(Actions actions) {
        if (count != 2) {
            actions.clear();
            actions.add(new DoNothingAction());
            count++;
        } else {
            count = 0;
            this.removeItemFromInventory(stunOnMap);
            stunOnMap = null;
        }
    }
    
    
    /**
     * check if the player's inventory contains the item Battery by checking its itemCapability. Return true if the player's inventory
     * contains the item and false if it's not inside.
     */
    public boolean hasBattery() {
    	for(Item i : this.getInventory()) {
            if (i.hasCapability(ItemCapability.BATTERY)) {
                return true;
            }
        }
        return false;
		
		}
    
    
    /**
     * check if the player's inventory contains the item key by checking its itemCapability. Return true if the player's inventory
     * contains the item and false if it's not inside.
     */
    public boolean hasKey() {
    	for(Item i : this.getInventory()) {
            if (i.hasCapability(ItemCapability.KEY)) {
                return true;
            }
        }
        return false;
		
		}
    
    
    
    /**
     * Removes a Vehicle Battery from the player's inventory
     *
     */
    public void removeBattery() {
    	for(Item i : this.getInventory()) {
            if (i.hasCapability(ItemCapability.BATTERY)) {
            	this.removeItemFromInventory(i);
            	break;
            }
    	}
    }
    
    
    
    /**
     * Removes a Vehicle Key from the player's inventory
     *
     */
    public void removeKey() {
    	for(Item i : this.getInventory()) {
            if (i.hasCapability(ItemCapability.KEY)) {
            	this.removeItemFromInventory(i);
            	break;
            }
    	}
    }

    
 
	@Override
	public void heal(int points) {
		hitPoints += points; 
		hitPoints = Math.min(hitPoints, maxHitPoints);
		System.out.println(Integer.toString(points)+" Health is added to " + name);
		}
	
	
	@Override
	public boolean isConscious() {
		if (hitPoints > 0) {
			return true;
		}
		return false;
		
		}

	
	public int health() {
		return hitPoints;
	}
	
	
	/**
	 * return the instance of player. It will return true if its a player. This is to allow to check from other classes to see if
	 * the actor is a player.
	 */
	public boolean isPlayer() {
		return true;
	}
	
	@Override
	public boolean isHuman() {
		return false;
	}
	
	public boolean maxhealth() {
		if (hitPoints==maxHitPoints) {
			return true;
	}
		return false;
	}
	
	
	@Override
	public boolean isZombie() {
		return false;
	}

}
