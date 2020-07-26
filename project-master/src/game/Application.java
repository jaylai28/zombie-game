package game;

import java.util.Arrays;
import java.util.List;


import edu.monash.fit2099.engine.*;

/**
 *  The main class for the zombie apocalypse game.

 * @author weijianlai
 *
 */
public class Application {
	
	public static void main(String[] args) {
		World world = new GameWorld(new Display());
		
		GameMap gameMap;
		
		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Fence(), new Tree());
		
		List<String> map = Arrays.asList(
		"................................................................................",
		"................................................................................",
		"....................................##########..................................",
		"..........................###########........#####..............................",
		"............++...........##......................########.......................",
		"..............++++.......#..............................##......................",
		".............+++...+++...#...............................#......................",
		".........................##..............................##.....................",
		"..........................#...............................#.....................",
		".........................##...............................##....................",
		".........................#...............................##.....................",
		".........................###..............................##....................",
		"...........................####......................######.....................",
		"..............................#########.........####............................",
		"............+++.......................#.........#...............................",
		".............+++++....................#.........#...............................",
		"...............++........................................+++++..................",
		".............+++....................................++++++++....................",
		"............+++.......................................+++.......................",
		"................................................................................",
		".........................................................................++.....",
		"........................................................................++.++...",
		".........................................................................++++...",
		"..........................................................................++....",
		"................................................................................");
		gameMap = new GameMap(groundFactory, map );
		world.addGameMap(gameMap);
		
		
		
		List<String> townlist = Arrays.asList(
				".........................................",
				".....+++.................................",
				"......+..........####################....",
				"......+..........#..................#....",
				".................#..................#....",
				".................####..........######....",
				".....+..............#..........#.........",
				".....++........+....#..........#..+......",
				"....++++.......+.................++......",
				"...............+...............+++++.....",
				".........................................");
		
		// create the new town map
		GameMap townMap = new GameMap(groundFactory, townlist );
		world.addGameMap(townMap);

		// add the player into the map
        Actor player = new Player("Player", '@', 100);
		world.addPlayer(player, gameMap.at(42, 15));
//		world.addPlayer(player, townMap.at(35, 4));


		// create the vehicle and place it into both the maps so the player can travel between maps.
		Location mapDestination = gameMap.at(42, 16);
		Location townDestination = townMap.at(35, 4);
		
		Vehicle vehicle = new Vehicle(mapDestination,townDestination,player);
		
		mapDestination.addItem(vehicle);
        townDestination.addItem(vehicle);
		
        // add the vehicle battery and vehicle key into both the maps so the player can pick up the battery and key to access
        // between different maps.
		gameMap.at(30, 15).addItem(new VehicleBattery());
		gameMap.at(24, 22).addItem(new VehicleKey());
		gameMap.at(52, 16).addItem(new VehicleBattery());
		gameMap.at(35, 20).addItem(new VehicleKey());
		gameMap.at(41, 16).addItem(new VehicleBattery());
		gameMap.at(46, 17).addItem(new VehicleKey());
		
		
		townMap.at(20, 4).addItem(new VehicleBattery());
		townMap.at(24, 6).addItem(new VehicleKey());
		townMap.at(28, 8).addItem(new VehicleBattery());
		townMap.at(32, 3).addItem(new VehicleKey());
		
		
		// add new humans and farmers into the new map
		gameMap.at(1, 1).addActor(new Human("Jerry"));	
		gameMap.at(14, 10).addActor(new Human("Tom"));	
		gameMap.at(22, 6).addActor(new Human("Polly"));	
		gameMap.at(30,  4).addActor(new Human("Susan"));	
		gameMap.at(26,  8).addActor(new Human("Nichole"));	
		
		gameMap.at(23, 6).addActor(new Farmer("Polly"));	
		gameMap.at(31,  7).addActor(new Farmer("Susan"));	
		gameMap.at(28,  9).addActor(new Farmer("Nichole"));
		
		
	    // Place some random humans
		String[] humans = {"Carlton", "May", "Vicente", "Andrea", "Wendy",
				"Elina", "Winter", "Clem", "Jacob", "Jaquelyn"};
		int x, y;
		for (String name : humans) {
			do {
				x = (int) Math.floor(Math.random() * 20.0 + 30.0);
				y = (int) Math.floor(Math.random() * 7.0 + 5.0);
			} 
			while (gameMap.at(x, y).containsAnActor());
			gameMap.at(x,  y).addActor(new Human(name));	
		}
		
		//place the farmers
		String[] farmers = {"BillieF", "MichaelF", "JayF", "AmilF", "SarahF",
				"ElisaF", "SummerF", "OliverF", "CandyF", "UbuntuF"};
		int w, z;
		for (String name : farmers) {
			do {
				w = (int) Math.floor(Math.random() * 20.0 + 30.0);
				z = (int) Math.floor(Math.random() * 7.0 + 5.0);
			} 
			while (gameMap.at(w, z).containsAnActor());
			gameMap.at(w, z).addActor(new Farmer(name));	
		}
		
		// place a simple weapon
		gameMap.at(42, 15).addItem(new Plank());
		gameMap.at(30, 19).addItem(new Plank());
		gameMap.at(30, 21).addItem(new Plank());
		gameMap.at(29, 20).addItem(new Plank());
		gameMap.at(28, 18).addItem(new Plank());
		
		// place the shotgun and sniper
		townMap.at(34, 4).addItem(new Shotgun());
		gameMap.at(42, 14).addItem(new Shotgun());


		// place the Mambo Marie into the map
		gameMap.at(41, 15).addActor(new MamboMarie("Mambo Marie"));

		
		// place the Witch into the map
		gameMap.at(20, 14).addActor(new Witch("Witch"));
		gameMap.at(34, 22).addActor(new Witch("Witch"));





		// Add more zombies into town map
		townMap.at(10, 3).addActor(new Zombie("Biter"));
		townMap.at(15,  6).addActor(new Zombie("Geek"));
		townMap.at(28, 7).addActor(new Zombie("Floaters"));
		townMap.at(40, 4).addActor(new Zombie("Cold Bodies"));

		
		
		// FIXME: Add more zombies!
		gameMap.at(30, 20).addActor(new Zombie("Groan"));
		gameMap.at(30,  18).addActor(new Zombie("Boo"));
		gameMap.at(10,  4).addActor(new Zombie("Uuuurgh"));
		gameMap.at(50, 18).addActor(new Zombie("Mortalis"));
		gameMap.at(1, 10).addActor(new Zombie("Gaaaah"));
		world.run();
	}
		
}
