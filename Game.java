import java.util.Random;

/**
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author Bradley Risack
 * @version 2023.03.22
 */

public class Game 
{
    private static Parser parser;
    private static Room currentRoom;
    private static Player player;
    static Random rand;
    //define items as class variables so they can be used between methods
    static Item rock, spear, fruit, batflycorpse;
    static Item pearl, silverpearl, tealpearl, goldpearl, karmaflower;
    static Enemy batfly, greenlizard, greenlizard2, bluelizard, vulture, kingvulture;
    
    static Room outskirts, industrial, farmarrays, skyislands, subterranean, filtration, drainage, garbage, shoreline, moon, crypts, citadel, chimney, wall, pebbles, depths, ascension, sea;
    
    public static void main(String[] args){
        //create items
        rock = new Item("rock", "weapon", "It's a small rock, temporarily stuns enemies", 1, 2, 0);
        spear = new Item("spear", "weapon", "It's a piece of sharpened rebar", 3, 10, 1);
        fruit = new Item("fruit", "edible", "lore", 0, 0, 0);
        batflycorpse = new Item("batflycorpse", "edible", "lore", 0, 0, 0);
        
        pearl = new Item("pearl", "collectible", "It's an image of a single grey cloud, hovering above a surface of white clouds under a deep blue sky.", 0, 0, 0);
        
        silverpearl = new Item("silverpearl", "collectible", "Its entire memory is filled with a mantra repeated... 5061 times - and then a termination verse.", 0, 0, 0);
        
        tealpearl = new Item("tealpearl", "collectible", "It's the blueprint for a Void Fluid filtration system.", 0, 0, 0);
        
        goldpearl = new Item("goldpearl", "collectible", "This information is illegal. Please dispose of this pearl immediately.", 0, 0, 0);
        
        karmaflower = new Item("karmaflower", "edible", "This is a hallucinogenic plant, it momentarily enables a creature to let go if its carnal self, and to contact the selves of other planes - dreams, memories, imagined worlds.", 0, 0, 0);

        
        //create enemies (params : health, damage, evaderate, evademult)
        batfly = new Enemy("batfly", "looks tasty", 1, 0, 5, 1);
        batfly.addLoot(batflycorpse);
        greenlizard = new Enemy("greenlizard", "dont mess with them", 6, 1, 2, 2);
        greenlizard2 = new Enemy("greenlizard2", "dont mess with them", 6, 1, 2, 2);
        bluelizard = new Enemy("bluelizard", "quite vulnerable", 2, 2, 3, 2);
        
        vulture = new Enemy("vulture", "excellent predators", 6, 2, 10, 3);
        kingvulture = new Enemy("kingvulture", "king of vultures", 15, 3, 15, 3);
        
        createRooms();
        parser = new Parser();
        player = new Player();
        rand = new Random();
        
        play();
    }
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        //create items
        rock = new Item("rock", "weapon", "It's a small rock, temporarily stuns enemies", 1, 2, 0);
        spear = new Item("spear", "weapon", "It's a piece of sharpened rebar", 3, 10, 1);
        fruit = new Item("fruit", "edible", "lore", 0, 0, 0);
        batflycorpse = new Item("batflycorpse", "edible", "lore", 0, 0, 0);
        
        pearl = new Item("pearl", "collectible", "It's an image of a single grey cloud, hovering above a surface of white clouds under a deep blue sky.", 0, 0, 0);
        
        silverpearl = new Item("silverpearl", "collectible", "Its entire memory is filled with a mantra repeated... 5061 times - and then a termination verse.", 0, 0, 0);
        
        tealpearl = new Item("tealpearl", "collectible", "It's the blueprint for a Void Fluid filtration system.", 0, 0, 0);
        
        goldpearl = new Item("goldpearl", "collectible", "This information is illegal. Please dispose of this pearl immediately.", 0, 0, 0);
        
        karmaflower = new Item("karmaflower", "edible", "This is a hallucinogenic plant, it momentarily enables a creature to let go if its carnal self, and to contact the selves of other planes - dreams, memories, imagined worlds.", 0, 0, 0);

        
        //create enemies (params : health, damage, evaderate, evademult)
        batfly = new Enemy("batfly", "looks tasty", 1, 0, 5, 1);
        batfly.addLoot(batflycorpse);
        greenlizard = new Enemy("greenlizard", "dont mess with them", 6, 1, 2, 2);
        greenlizard2 = new Enemy("greenlizard2", "dont mess with them", 6, 1, 2, 2);
        bluelizard = new Enemy("bluelizard", "quite vulnerable", 2, 2, 3, 2);
        
        vulture = new Enemy("vulture", "excellent predators", 6, 2, 10, 3);
        kingvulture = new Enemy("kingvulture", "king of vultures", 15, 3, 15, 3);
        
        createRooms();
        parser = new Parser();
        player = new Player();
        rand = new Random();
        
        play();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private static void createRooms()
    {   
        /**
         * This method creates all rooms within the game and places their respective items within them
         * @param args Unused
         * @return Nothing
         */
      
        // create the rooms
        outskirts = new Room("You find yourself in a desolate, grassy city");
        industrial = new Room("As you step into the industrial complex, the deafening roar of machinery and acrid scent of metal fill your senses, while towering steel beams and pipes stretch up into the darkness above");
        farmarrays = new Room("As you step into the farm arrays, you are greeted by neatly arranged rows of crops stretching out before you, with the distant hum of machinery filling the air");
        skyislands = new Room("You find yourself surrounded by floating land masses, shrouded in mist and connected by narrow bridges, with the distant sound of rushing waterfalls echoing in the air");
        subterranean = new Room("As you descend into the subterranean depths, the air grows damp and musty, the only sources of light being the flickering torches lining the rough-hewn walls");
        filtration = new Room("You find yourself standing in a dimly lit room filled with towering machines and pipes, the sound of rushing water echoing throughout as you realize you have entered the filtration system");
        drainage = new Room("You find yourself in knee-deep murky water, surrounded by rusted pipes and discarded debris in the dimly-lit drainage system");
        garbage = new Room("You find yourself surrounded by mounds of putrid garbage, emitting a nauseating stench and sttracting swarms of flies");
        shoreline = new Room("You find yourself standing on the sandy shores of a crystal clear lake, surrounded by tall trees and the distant sound of rushing waterfalls");
        moon = new Room("The remains of Moon's complex. You find her lifeless in the center of the room, with bright light pouring in from the many holes in the overgrown walls");
        crypts = new Room("As you enter the memory crypts, the low hum of the massive superstructure above fills the air, while rows of glowing data banks line the walls around you");
        citadel = new Room("As you step into the shaded citadel, the cool dampness of the stone walls envelops you, and the only light filtering through the high arched windows is a dim, eerie glow");
        chimney = new Room("You stand in the shadow of a towering chimney, its canopy looming over you as if to shield you from the industrial wasteland beyond");
        wall = new Room("You stand before the towering side of a massive iterator superstructure, known as 'The Wall', its surface seemingly endless as it dissapears into the distance above and below you");
        pebbles = new Room("An empty room, filled with decaying circuitry and a strange, blue, pulsating mass. A body lay lifeless on the floor.");
        depths = new Room("As you enter the depths, you find yourself in a dimly lit cavern with ominous shadows looming in the distance. Your vision becomes increasingly distorted as you travel deeper", true);
        ascension = new Room("Barely able to see, you stand before a massive, seemingly bottomless sea of void fluid");
        sea = new Room("You jump headfirst into the mysterious fluid, and begin to ascend towards a higher plane");
        
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
        
        depths.setExit("down", ascension);
        
        ascension.setExit("down", sea);
        
        pebbles.setExit("left", wall);
        
        depths.setExit("down", ascension);
        
        
        // add items + enemies
        outskirts.addItem(spear);
        outskirts.addItem(rock);
        
        outskirts.addEnemy(greenlizard);
        outskirts.addEnemy(greenlizard2);
        
        subterranean.addItem(tealpearl);
        
        chimney.addItem(goldpearl);
        
        moon.addItem(silverpearl);
        
        pebbles.addItem(karmaflower);
        pebbles.addItem(pearl);

        currentRoom = outskirts;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public static void play() 
    {   
        /**
         * Starts the game
         */
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
    private static void printWelcome()
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
    private static boolean processCommand(Command command) 
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
                
            case DROP:
                drop(command);
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
    private static void printHelp() 
    {
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private static void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
        if(currentRoom.enemyArray().size() > 0) {
            System.out.println("You can't escape!");
        } else if (nextRoom == null) {
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
    
    /**
     * A method called by the player, attempts to consume an item from their inventory
     * @param command the two phrases entered by the player to execute the command, in this case "eat" and the item to eat
     */
    private static void eat(Command command)
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
    
    /**
     * A method called by the player, attempts to pickup an item from within a room
     * @param command the two phrases entered by the player to execute the command, in this case "pickup" and the item to pickup
     */
    private static void pickup(Command command)
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
                    System.out.println("Picked up a(n) " + pitem.getName() + " (" + pitem.getType() + ")");
                    if(pitem.getType() == "weapon") {
                        player.hold(pitem);
                        System.out.println("You are now wielding the " + pitem.getName());
                    }
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
        
        //enemy attack phase
        enemyPhase();
    }
    
    /**
     * A method called by the player, attempts to print the description of the item requested by the player
     * @param command the two phrases entered by the player to execute the command, in this case "examine" and the item to examine
     */
    private static void examine(Command command)
    {
        if(currentRoom.enemyArray().size() <= 0) {
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
        } else {
            System.out.println("You can't do that now!");
        }
    }
    
    /**
     * A method called by the player, lists all items and enemies within the room they are currently in
     * @param args Unused
     */
    private static void search()
    {
        currentRoom.searching();
    }
    
    /**
     * A method called by the player, attempts to have the player wield a requested item from within their inventory
     * @param command the two phrases entered by the player to execute the command, in this case "wield" and the item to wield
     */
    private static void wield(Command command)
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
    
    /**
     * A method called by the player, attempts to attack a requested enemy in the room using the item they are currently wielding,
     * then causes the attacked enemy to retaliate. If the attacked enemy dies, they are removed from the room and their loot (if any) is added to the
     * player's inventory
     * @param command the two phrases entered by the player to execute the command, in this case "attack" and the enemy to attack
     */
    private static void attack(Command command)
    {
        Enemy toAttack = null;
        boolean evaded = false;
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
                    int rand2 = (rand.nextInt(20) + 1);
                    if(rand2 > enemy.getEvade()) {
                        enemy.attacked(player.getHolding(), player.adrLevel());
                        toAttack = enemy;
                        //damage player weapon
                        player.damageItem(player.getHolding());
                    } else {
                        System.out.println("\nThe enemy evaded the attack!\n");
                        evaded = true;
                    }
                    player.hungry();
                    player.adrDecay();
                    enemy.effectDecay();
                    
                    //enemy attack phase
                    if(enemy.getHealth() > 0 && enemy.getEffect() != "confused") {
                        player.attackedBy(enemy, evaded);
                    } else if(enemy.getEffect() == "confused") {
                        System.out.println("The enemy is confused!");
                    }
                    if(player.getHealth() <= 0) {
                        reset();
                    }
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
        
        if (toAttack == null && evaded == false) {
            System.out.println("You could not find a(n) " + phrase + " in the current room");
        } else if (toAttack != null && toAttack.getHealth() <= 0) { //kill enemy if 0 health
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
    
    /**
     * A method called by the player, attempts to remove a requested item from their inventory, and add it to whichever room they are currently in
     * @param command the two phrases entered by the player to execute the command, in this case "drop" and the item to drop
     */
    private static void drop(Command command) {
        Item toDrop = null;
        String phrase = command.getSecondWord().toString();
        
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Drop what?");
            return;
        }
        
        for(Item ditem : player.inventoryArray())
        {
            if(ditem.getName().equals(phrase)) {
                //drop item and place it in room
                System.out.println("You dropped the " + ditem.getName());
                toDrop = ditem;
                currentRoom.addItem(ditem);
            }
        }
        
        if(toDrop != null) {
            player.lose(toDrop);
        } else {
            System.out.println("You could not find a(n) " + phrase + " in your inventory");
        }
        
        //enemy attack phase
        enemyPhase();
    }
    
    /**
     * A method called by the player, recovers health and evades enemy attacks for one "turn" at the cost of hunger
     * @param args Unused
     */
    private static void evade()
    {
        if(currentRoom.enemyArray().size() > 0) {
            player.evade();
        } else {
            System.out.println("Nothing to evade...");
        }
    }
    
    /**
     * A method called by several other methods, namely drop() and pickup(), that causes a random enemy in the room to attack the player
     * @param args Unused
     */
    private static void enemyPhase()
    {
        //enemy attack phase : player is attacked by random enemy in the room
        if(currentRoom.enemyArray().size() > 0) {
            int rand1 = rand.nextInt(currentRoom.enemyArray().size());
            Enemy toAttack = currentRoom.enemyArray().get(rand1);
            for(Enemy enemy : currentRoom.enemyArray()) {
                enemy.effectDecay();
            }
            if(toAttack.getEffect() != "confused") {
                player.attackedBy(toAttack);
            } else if(toAttack.getEffect() == "confused") {
                System.out.println("The enemy is confused!");
            }
            if(player.getHealth() <= 0) {
                reset();
            }
        }
    }
    
    /**
     * Called when the player dies, resets the player's inventory and all room data
     * @param args Unused
     */
    private static void reset() {
        //display death message
        System.out.println("You died!");
        System.out.println("Game reset");
        
        //reset player
        player.reset();
        
        //clear room items
        outskirts.clearRoom();
        industrial.clearRoom();
        farmarrays.clearRoom();
        skyislands.clearRoom();
        subterranean.clearRoom();
        filtration.clearRoom();
        drainage.clearRoom();
        garbage.clearRoom();
        shoreline.clearRoom();
        moon.clearRoom();
        crypts.clearRoom();
        citadel.clearRoom();
        chimney.clearRoom();
        wall.clearRoom();
        pebbles.clearRoom();
        depths.clearRoom();
        ascension.clearRoom();
        
        //re-add things
        outskirts.addItem(rock);
        
        outskirts.addItem(silverpearl);
        
        pebbles.addItem(karmaflower);
        pebbles.addItem(pearl);
        
        // add enemies
        outskirts.addEnemy(greenlizard);
        outskirts.addEnemy(bluelizard);
        
        //reset player room
        currentRoom = outskirts;
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private static boolean quit(Command command) 
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
