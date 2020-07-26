package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Class representing the GameWorld that extends from the world
 * @author Amil
 *
 */
public class GameWorld extends World {

    private static Actor player;
    protected boolean win = true;
    protected boolean lost;
    protected boolean valid = false;
    private boolean alive = false;
    private boolean resume;
    private int value = 0;

	protected Random rand = new Random();


    /**
     * Constructor with the display input parameter
     *
     * @param display the display of the GameWorld
     */
    public GameWorld(Display display) {
        super(display);
    }
    
    
    
    /**
    * Gets the player that is running in the world.
    *
    * @return the player
    */
   public static Actor getPlayer() {
       return player;
   }
    
    
 
    /**
	 * Set an actor as the player. The map is drawn just before this Actor's turn
	 * 
	 * @param p   the player to add
	 * @param location the Location where the player is to be added
	 */
    @Override
	public void addPlayer(Actor p, Location location) {
		player = p;
		actorLocations.add(player, location.map().at(location.x(), location.y()));
		actorLocations.setPlayer(player);
	}
    

    /**
     * Run the game.
     * 
     * On each iteration the game loop does the following:
     * It detects if the player is killed or all the other humans, human and farmer are killed in the map and stop the game.
     * Displaying the player lost. If all the zombies and mambo marie are killed and not in the map, stop and end the game too.
     * Display the player wins the game. The game stops running when the player is removed from map.
     * It display message for player who either quit the game / wins / loses.
     * Else, if none of the above happened, it will process the actions of every Actor in the game, regardless of the map.
     * @see World run()
     */
    @Override
    public void run() {
        if (player == null)
            throw new IllegalStateException();
        
     // initialize the last action map to nothing actions;
     		for (Actor actor : actorLocations) {
     			lastActionMap.put(actor, new DoNothingAction());
     		}

     		// This loop is basically the whole game
     		while (stillRunning()) {
     			GameMap playersMap = actorLocations.locationOf(player).map();
     			playersMap.draw(display);
     			resume=false;
     			lost=false;

     			// Process all the actors.
     			for (Actor actor : actorLocations) {	
     				if (actor.isZombie() || actor.isMambo() || alive == true ) {
     					resume=true;
     				}
     				if (actor.isHuman() || actor.isFarmer() ) {
     					lost = true;
     					
     				}
     				if (stillRunning())
     					processActorTurn(actor);
     			}
     			
     			if (resume == false) {
     				valid=true;
     				win=true;
     				playersMap.removeActor(player);
     	        }
     			else if (lost == false) {
     				valid=true;
     				win=false;
     				System.out.println("All the Humans are killed!");
     				playersMap.removeActor(player);
     			}
     			

     			// Tick over all the maps. For the map stuff.
     			for (GameMap gameMap : gameMaps) {
     				if (valid== false) {
        				if(rand.nextDouble()<= 0.05) {
        					System.out.println(summonMambo(gameMap));
        	        		valid=true;
        					}
        				else {
        					System.out.println("Mambo Marie missed her turn to appear in map!");
        				}
        				}		
     				gameMap.tick();
     			}
     			
     			if (value!=0) {
 					valid=false;
 					value=0;
     			}
     			
        }

        if (!player.isConscious() || win==false) {  // Print lose game message when the player is knocked out
            display.println(playerLost());
        } 
        else if (win) {
            display.println(playerWin()); // Print end game message when the player wins
        }
        display.println(endGameMessage());
    }
    
    
    
    /**
	 * Gives an Actor its turn.
	 *
	 * The Actions an Actor can take include:
	 * <ul>
	 * <li>those conferred by items it is carrying</li>
	 * <li>movement actions for the current location and terrain</li>
	 * <li>actions that can be done to Actors in adjacent squares</li>
	 * <li>actions that can be done using items in the current location</li>
	 * <li>skipping a turn</li>
	 * </ul>
	 *
	 * @param actor the Actor whose turn it is.
	 */
	protected void processActorTurn(Actor actor) {
		Location here = actorLocations.locationOf(actor);
		GameMap map = here.map();

		Actions actions = new Actions();
		for (Item item : actor.getInventory()) {
			actions.add(item.getAllowableActions());
			// Game rule. If you're carrying it, you can drop it.
			actions.add(item.getDropAction());
		}

		for (Exit exit : here.getExits()) {
			Location destination = exit.getDestination();

			// Game rule. You don't get to interact with the ground if someone is standing
			// on it.
			if (actorLocations.isAnActorAt(destination)) {
				actions.add(actorLocations.getActorAt(destination).getAllowableActions(actor, exit.getName(), map));
			} else {
				actions.add(destination.getGround().allowableActions(actor, destination, exit.getName()));
			}
			actions.add(destination.getMoveAction(actor, exit.getName(), exit.getHotKey()));
		}

		for (Item item : here.getItems()) {
			actions.add(item.getAllowableActions());
			// Game rule. If it's on the ground you can pick it up.
			actions.add(item.getPickUpAction());
		}
		actions.add(new DoNothingAction());

		Action action = actor.playTurn(actions, lastActionMap.get(actor), map, display);
		lastActionMap.put(actor, action);
	
			
		
		String result = action.execute(actor, map);
		
		
		if (actor.isMambo() && (actor.isAlive()== false)) {
			System.out.println("ISALIVE");
//			valid = false;
			value++;
			resume=true;
			alive=true;
			actor.disappear(true);
		}
		else if (actor.isMambo() && (actor.isAlive()==true)) {
			valid=true;
			alive=false;
		}
		
	

		
		display.println(result);
	}
	
	
	
	/**
	 * Summon the Mambo Marie at the edge of the map. Use random() to randomly select where the Mambo should be summoned in the map.
	 * @param gameMap the map in which the actor is in
	 * @return
	 */
	public String summonMambo(GameMap gameMap) {
		List<Integer> xlocations = new ArrayList<Integer>();
		List<Integer> ylocations = new ArrayList<Integer>();


		for (int x=gameMap.getXRange().min(); x<(gameMap.getXRange().max()); x++) {
			xlocations.add(x);
		}
		
		for (int y=gameMap.getYRange().min(); y<(gameMap.getYRange().max()); y++) {
			ylocations.add(y);
		}
		Collections.shuffle(xlocations);
		Collections.shuffle(ylocations);
		
		if (rand.nextDouble()<=0.50) {
			if (rand.nextDouble()<=0.50) {
				for (int x:xlocations) {
					gameMap.addActor(new MamboMarie("Mambo Marie"), gameMap.at(x,0));
					return "Mambo Marie is summoned to the Map!";
				}
			}
			for (int x:xlocations) {
				gameMap.addActor(new MamboMarie("Mambo Marie"), gameMap.at(x,gameMap.getYRange().max()));
				return "Mambo Marie is summoned to the Map!";
			}
		}
		else {
			if (rand.nextDouble()<=0.50) {
				for (int y:ylocations) {
					gameMap.addActor(new MamboMarie("Mambo Marie"), gameMap.at(0,y));
					return "Mambo Marie is summoned to the Map!";
				}
		}
			for (int y:ylocations) {
				gameMap.addActor(new MamboMarie("Mambo Marie"), gameMap.at(gameMap.getXRange().max(),y));
				return "Mambo Marie is summoned to the Map!";
			}
		}
		return null;
		
	}
	
	
	
	
    /**
	 * Returns true if the game is still running.
	 *
	 * The game is considered to still be running if the player is still around.
	 *
	 * @return true if the player is still on the map.
	 */
    @Override
	protected boolean stillRunning() {
		return actorLocations.contains(player);
	}
    



    

    /**
     * Return a string that can be displayed when the game ends.
     * @return return the String "---Game Over---"
     */
    @Override
    protected String endGameMessage() {
        return "---Game Over---";
    }

    /**
     * Return a string that can be displayed when the player wins the game.
     * @return the String "Congratulations! You won!"
     */
    protected String playerWin() {
    	System.out.println("No zombies or Mambo is in the map!");
        return "Congratulations! You won!\n";
    }

    /**
     * Return a string that can be displayed when the player loses the game.
     * @return the string "You lost! Try harder next time!"
     */
    protected String playerLost() {
        return "You lost! Try harder next time!\n";
    }
    

}