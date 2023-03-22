import java.util.Random;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Player player;
    Random rand;
    //define items as class variables so they can be used between methods
    Item rock, spear, fruit, batflycorpse;
    Item silverpearl;
    Enemy batfly, greenlizard, bluelizard;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        //create items
        rock = new Item("rock", "weapon", "It's a rock", 1, 2, 0);
        spear = new Item("spear", "weapon", "It's a piece of sharpened rebar", 3, 10, 1);
        fruit = new Item("fruit", "edible", "lore", 0, 0, 0);
        batflycorpse = new Item("batfly", "edible", "lore", 0, 0, 0);
        
        silverpearl = new Item("silverpearl", "collectible", "lore", 0, 0, 0);
        
        //create enemies
        batfly = new Enemy("batfly", "looks tasty", 1, 0);
        batfly.addLoot(batflycorpse);
        //batfly.addLoot(batflycorpse);
        greenlizard = new Enemy("greenlizard", "dont mess with them", 10, 1);
        bluelizard = new Enemy("bluelizard", "quite vulnerable", 3, 2);
        
        createRooms();
        parser = new Parser();
        player = new Player();
        rand = new Random();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {   
        Room outskirts, industrial, farmarrays, skyislands, subterranean, filtration, drainage, garbage, shoreline, moon, crypts, citadel, chimney, wall, pebbles, depths, ascension;
      
        // create the rooms
        outskirts = new Room("You find yourself in a desolate, grassy city.");
        industrial = new Room("A white room");
        farmarrays = new Room("A white room");
        skyislands = new Room("A white room");
        subterranean = new Room("A white room");
        filtration = new Room("A white room");
        drainage = new Room("A white room");
        garbage = new Room("A white room");
        shoreline = new Room("A white room");
        moon = new Room("A white room");
        crypts = new Room("A white room");
        citadel = new Room("A white room");
        chimney = new Room("A white room");
        wall = new Room("A white room");
        pebbles = new Room("A white room");
        depths = new Room("A white room", true);
        ascension = new Room("room");
        
        // initialise room exits
        outskirts.setExit("left", farmarrays);
        outskirts.setExit("right", industrial);
        outskirts.setExit("down", drainage);
        
        industrial.setExit("left", outskirts);
        industrial.setExit("up", chimney);
        industrial.setExit("right", citadel);
        
        farmarrays.setExit("right", outskirts);
        farmarrays.setExit("up", skyislands);
        farmarrays.setExit("down", subterranean);
        
        skyislands.setExit("down", farmarrays);
        skyislands.setExit("right", chimney);
        
        subterranean.setExit("up", farmarrays);
        subterranean.setExit("right", shoreline);
        
        filtration.setExit("down", depths);
        filtration.setExit("right", drainage);
        
        drainage.setExit("left", filtration);
        drainage.setExit("right", garbage);
        drainage.setExit("up", outskirts);
        
        garbage.setExit("left", drainage);
        garbage.setExit("up", industrial);
        garbage.setExit("right", shoreline);
        
        shoreline.setExit("left", garbage);
        shoreline.setExit("right", moon);
        shoreline.setExit("down", subterranean);
        shoreline.setExit("up", citadel);
        
        moon.setExit("left", shoreline);
        
        citadel.setExit("right", shoreline);
        citadel.setExit("down", crypts);
        citadel.setExit("left", industrial);
        
        chimney.setExit("down", industrial);
        chimney.setExit("left", skyislands);
        chimney.setExit("right", wall);
        
        wall.setExit("down", crypts);
        wall.setExit("left", chimney);
        wall.setExit("up", pebbles);
        
        pebbles.setExit("left", wall);
        
        depths.setExit("down", ascension);
        
        
        // add items
        outskirts.addItem(silverpearl);
        outskirts.addItem(rock);
        
        // add enemies
        outskirts.addEnemy(greenlizard);
        outskirts.addEnemy(bluelizard);

        currentRoom = outskirts;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;
                
            case EAT:
                eat(command);
                break;
                
            case INVENTORY:
                player.ListInventory();
                break;
                
            case PICKUP:
                pickup(command);
                break;
                
            case SEARCH:
                search();
                break;
                
            case LOOK:
                System.out.println(currentRoom.getLongDescription());
                break;
                
            case EXAMINE:
                examine(command);
                break;
                
            case WIELD:
                wield(command);
                break;
            
            case ATTACK:
                attack(command);
                break;
                
            case EVADE:
                evade();
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            if (nextRoom.getLocked() == false)
            {
                currentRoom = nextRoom;
                System.out.println(currentRoom.getLongDescription());
                //drain hunger
                player.hungry();
                player.adrReset();
            } else {
                if (player.getKarma() >= 10) {
                    currentRoom = nextRoom;
                    System.out.println("A force dissipates");
                    System.out.println(currentRoom.getLongDescription());
                    //drain hunger
                    player.hungry();                    
                } else {
                System.out.println("A force prevents you from entering");
                }
            }
        }
    }
    
    private void eat(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Eat what?");
            return;
        }
        // Attempt to eat the item
        player.tryeat(player.findItem(command.getSecondWord()));
        player.adrDecay();
    }
    
    private void pickup(Command command)
    {
        Item toRemove = null;
        boolean heavy = false;
        String phrase = command.getSecondWord().toString();

        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Pickup what?");
            return;
        }
        
        //run through each object in the snapshot array of the current room, checking if its name matches the second command word
        //if it does, give the object to the player and send a message
        for(Item pitem : currentRoom.itemArray()) {
            if(pitem.getName().equals(phrase)) {
                if(player.weightClass() >= pitem.getWeight())
                {
                    player.obtain(pitem);
                    player.adrDecay();
                    System.out.println("Picked up a(n) " + pitem.getName());
                    toRemove = pitem;
                } else {
                    System.out.println("The " + pitem.getName() + " is too heavy for you");
                    heavy = true;
                }
            }
        }
        //remove elements after for-each to avoid concurrent modification exception
        if (toRemove == null) {
            if(heavy == false) {
                System.out.println("You could not find a(n) " + phrase);
            }
        } else {
            currentRoom.removeItem(toRemove);
        }
        
        //enemy attack phase : player is attacked by random enemy in the room
        Enemy toAttack = null;
        int rand1 = rand.nextInt(currentRoom.enemyArray().size());
        player.attackedBy(currentRoom.enemyArray().get(rand1));
    }
    
    private void examine(Command command)
    {
        Item toExamine = null;
        
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Examine what?");
            return;
        }
        
        String phrase = command.getSecondWord().toString();
        
        for(Item eitem : player.inventoryArray()) {
            if(eitem.getName().equals(phrase)) {
                System.out.println(eitem.getDescription());
                toExamine = eitem;
            }
        }
        
        if (toExamine == null) {
            System.out.println("You could not find a(n) " + phrase + " in your inventory");
        }
    }
    
    private void search()
    {
        currentRoom.searching();
    }
    
    private void wield(Command command)
    {
        Item isHolding = null;
        
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Hold what?");
            return;
        }
        
        String phrase = command.getSecondWord().toString();
        
        for(Item hitem : player.inventoryArray()) {
            if(hitem.getName().equals(phrase)) {
                player.hold(hitem);
                isHolding = hitem;
                System.out.println("You are now wielding the " + hitem.getName());
            }
        }
        
        if (isHolding == null) {
            System.out.println("You could not find a(n) " + phrase + " in your inventory");
        }
    }
    
    private void attack(Command command)
    {
        Enemy toAttack = null;
        String phrase = command.getSecondWord().toString();
        
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Attack who?");
            return;
        }
        
        for(Enemy enemy : currentRoom.enemyArray()) {
            if(enemy.getName().equals(phrase)) {
                if(player.getHolding() != null)
                {
                    //attack enemy
                    enemy.attacked(player.getHolding(), player.adrLevel());
                    toAttack = enemy;
                    //damage player weapon
                    player.damageItem(player.getHolding());
                    player.adrDecay();
                    
                    //enemy attack phase
                    player.attackedBy(enemy);
                    //can't have multiple enemies with the same name in the same room without return statement here
                    //but that feature was janky anyway
                    //return;
                } else {
                    System.out.println("You aren't holding anything!");
                    return;
                }
                //get attacked by every enemy in the room
            }
            //damage player
            //fixed this by removing return in first if statement
        }
        
        if (toAttack == null) {
            System.out.println("You could not find a(n) " + phrase + " in the current room");
        } else if (toAttack.getHealth() <= 0) { //kill enemy if 0 health
            System.out.println("The " + toAttack.getName() + " has died!");
            //if enemy has loot, give loot
            if(toAttack.listLoot().size() > 0) {
                System.out.println("You received : ");
                for(Item loot : toAttack.listLoot()) {
                    System.out.println("- " + loot.getName());
                    player.obtain(loot);
                }
            }
            currentRoom.removeEnemy(toAttack);
                        
        }
    }
    
    private void evade()
    {
        if(currentRoom.enemyArray().size() > 0) {
            player.evade();
        } else {
            System.out.println("Nothing to evade...");
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
