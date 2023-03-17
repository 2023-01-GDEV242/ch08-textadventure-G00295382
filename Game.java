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
    //define items as class variables so they can be used between methods
    Item apple, knife, stick;
    Enemy zombie1, zombie2;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        //create items
        apple = new Item("apple", "edible", "A big red apple, yummy.", 0, 0, 0);
        knife = new Item("knife", "weapon", "A dull kitchen knife", 3, 10, 1);
        stick = new Item("stick", "weapon", "A thin stick", 1, 2, 1);
        
        //create enemies
        zombie1 = new Enemy("zombie", "abomination", 2, 1);
        zombie2 = new Enemy("zombie", "abomination", 2, 1);
        
        createRooms();
        parser = new Parser();
        player = new Player();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {   
        Room w1, w2, w3, w4;
      
        // create the rooms
        w1 = new Room("A white room");
        w2 = new Room("A white room");
        
        // initialise room exits
        
        //progresses e/s
        
        // add items
        w1.addItem(stick);
        
        // add enemies
        w1.addEnemy(zombie1);
        w1.addEnemy(zombie2);

        currentRoom = w1;  // start game outside
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
                
            case HOLD:
                hold(command);
                break;
            
            case ATTACK:
                attack(command);
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
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            //drain hunger
            player.hungry();
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
        currentRoom.listItems();
    }
    
    private void hold(Command command)
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
                System.out.println("You are now holding the " + hitem.getName());
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
                    enemy.attacked(player.getHolding());
                    toAttack = enemy;
                    //kill enemy if 0 health
                    if(toAttack.getHealth() <= 0) {
                        currentRoom.removeEnemy(toAttack);
                        System.out.println("The " + toAttack.getName() + " has died!");
                    }
                    //damage player weapon
                    player.damageItem(player.getHolding());
                } else {
                    System.out.println("You aren't holding anything!");
                    return;
                }
                //get attacked by every enemy in the room
            }
            //damage player
            player.attackedBy(enemy);
        }
        
        if (toAttack == null) {
            System.out.println("You could not find a(n) " + phrase + " in the current room");
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
