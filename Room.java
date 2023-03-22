import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private ArrayList<Item> items;      // stores items
    private ArrayList<Enemy> enemies;   // stores enemies
    private boolean locked;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        locked = false;
        exits = new HashMap<>();
        items = new ArrayList<>();
        enemies = new ArrayList<>();
    }
    
    public Room(String description, boolean locked) 
    {
        this.description = description;
        this.locked = locked;
        exits = new HashMap<>();
        items = new ArrayList<>();
        enemies = new ArrayList<>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }
    
    public void addEnemy(Enemy enemy)
    {
        enemies.add(enemy);
    }
    
    public void removeEnemy(Enemy enemy)
    {
        enemies.remove(enemy);
    }

    public void addItem(Item item)
    {
        items.add(item);
    }
    
    public void removeItem(Item item)
    {
        items.remove(item);
    }
    
    public void lock()
    {
        locked = true;
    }
    
    public void unlock()
    {
        locked = false;
    }
    
    public void searching()
    {
        System.out.println("After searching the room, you find it contains : ");
        for(Item item : items) {
            System.out.println(item.getName());
        }
        System.out.println("Along with some enemies : ");
        for(Enemy enemy : enemies) {
            System.out.println(enemy.getName());
        }
    }
    
    //send room item array into method for other classes
    public ArrayList<Item> itemArray()
    {
        return items;
    }
    
    //same for enemy array
    public ArrayList<Enemy> enemyArray()
    {
        return enemies;
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return description + ".\n" + getExitString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }
    
    public boolean getLocked()
    {
        return locked;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
}
