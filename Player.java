import java.util.ArrayList;

/**
 * Manages the player's inventory, hunger, and health
 * @author Bradley Risack
 * @version 2023.03.22
 */
public class Player
{
    // instance variables - replace the example below with your own
    private ArrayList<Item> Inventory;
    private int hunger;
    private int health;
    private int wc;
    private int karma;
    private int adrenaline;
    private Item holding;
    
    /**
     * Initialize default player values
     */
    public Player()
    {
        Inventory = new ArrayList<Item>();
        holding = null;
        hunger = 15;
        health = 5;
        wc = 2;
        karma = 1;
        adrenaline = 0;
    }
    
    /**
     * Adds an item to the player's inventory
     * @param item An item from the player's inventory
     */
    public void obtain(Item item)
    {
        Inventory.add(item);
    }
    
    /**
     * Removes an item from the player's inventory
     * @param item An item from the player's inventory
     */
    public void lose(Item item)
    {
        Inventory.remove(item);
    }
    
    /**
     * Displays all items within the player's inventory
     */
    public void ListInventory()
    {
        System.out.println("Your inventory contains : ");
        for(Item item : Inventory) {
            System.out.println(item.getName());
        }
        if(Inventory.size() == 0) {
            System.out.println("nothing");
        }
    }
    
    /**
     * @return ArrayList<Item> Returns a copy of the player's inventory arraylist
     */
    public ArrayList<Item> inventoryArray()
    {
        return Inventory;
    }
    
    /**
     * Checks whether or not an item is in the player's inventory
     * @param item An item from the player's inventory
     * @return boolean True if item is found, False if it is not found
     */
    public boolean checkForItem(Item item)
    {
        if (Inventory.contains(item)) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Checks whether or not an item within the player's inventory is edible
     * @param item An item from the player's inventory
     * @return boolean True if item is edible, False if it is not edible
     */
    public boolean checkEdible(Item item)
    {
        String isEdible = item.getType();
        if (isEdible == "edible") {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Calls checkForItem() and checkEdible() on an item within the player's inventory
     * Then calls eat() if both methods return true
     * @param item An item from the player's inventory
     */
    public void tryeat(Item item)
    {
        //check if item exists
        boolean exists = checkForItem(item);
        boolean edible;
        if (exists == true) {
            //check if item is edible
            edible = checkEdible(item);
            if (edible == true) {
                eat(item);
            } else {
                System.out.println(item + " is not edible!");
            }
        } else {
            System.out.println("You do not have a(n) " + item);
        }
    }
    
    /**
     * Removes an item from the player's inventory and adds a value to the player's hunger field
     * Also calls another method, strength(), if the item happens to be a fruit
     * @param item An item from the player's inventory
     */
    private void eat(Item item)
    {
        Inventory.remove(item);
        hunger += 7;
        System.out.println("You have eaten a(n) " + item.getName());
        
        //game state changes
        if(item.getName() == "fruit") {
            strength();
            System.out.println("You feel stronger");
        }
        
        if (hunger <= 7) {
            System.out.println("You are still hungry");
        } else {
            System.out.println("You feel refreshed");
        }
    }
    
    /**
     * Called when a player moves to another room, reduces the player's hunger field as well as it's health if said field happens to be below 1
     * @param args Unused
     */
    public void hungry()
    {
        hunger -= 1;
        if (hunger <= 7) {
            System.out.println("You feel hungry.");
        } else if (hunger <= 1) {
            System.out.println("You are starving. Find food.");
            health -= 1;
        }
    }
    
    /**
     * @return int The player's wc or "weight class" field
     */
    public int weightClass()
    {
        return wc;
    }
    
    /**
     * Adds a value of 2 to the player's wc field
     */
    public void strength()
    {
        wc += 2;
    }
    
    /**
     * Adds a value of 1 to the player's karma field if karma is less than or equal to 10
     */
    public void addKarma()
    {
        if(karma <= 10) {
            karma += 1;
        }
    }
    
    /**
     * Sets the player's karma value to 10
     */
    public void maxKarma()
    {
        karma = 10;
    }
    
    /**
     * Sets the player's holding field to the requested item
     * @param item The item the player would like to hold
     */
    public void hold(Item item)
    {
        holding = item;
    }
    
    /**
     * @return Item Return the item the player is currently holding
     */
    public Item getHolding()
    {
        return holding;
    }
    
    /**
     * Finds an item within the players inventory using a string and returns it as an item
     * @param name The name of the item to find
     * @return Item Returns the object within the player's inventory matching the string parameter
     */
    //converts string to item
    public Item findItem(String name)
    {
        for(Item fitem : Inventory) {
            if(fitem.getName().equals(name)) {
                return fitem;
            }
        }
        return null;
    }
    
    /**
     * @return The player's current karma
     */
    public int getKarma()
    {
        return karma;
    }
    
    /**
     * @return The player's current adrenaline level
     */
    public int adrLevel()
    {
        return adrenaline;
    }
    
    /**
     * Called by the attack method within the Game class, reduces player health by the amount returned by enemy.getDamage()
     * @param enemy The enemy to attack the player
     */
    public void attackedBy(Enemy enemy)
    {
        health -= enemy.getDamage();
        System.out.println("Attacked by " + enemy.getName() + "! You lost " + enemy.getDamage() + " hitpoints!");
    }
    
    /**
     * Called by the player, restores health and drains hunger
     */
    public void evade()
    {
        if(hunger > 2) {
            health += 4;
            hunger -= 5;
            System.out.println("You evaded the enemie(s)!");
            System.out.println("You restored 4 health!");
            if(adrenaline <= 0) {
                adrenaline += 2;
                System.out.println("Adrenaline bonus activated! (+" + adrenaline + " damage)");
            }
        } else {
            System.out.println("You are too hungry! Cost: 5");
        }
    }
    
    /**
     * Called whenever the player picks up / drops an item or attacks an enemy, reduces the player's adrenaline level by 1, displaying a message if it happens to be fully depleted
     */
    public void adrDecay()
    {
        if(adrenaline > 0) {
            adrenaline -= 1;
            if(adrenaline <= 0) {
                System.out.println("Adrenaline bonus lost..");
            }
        }
    }
    
    /**
     * Called whenever a player moves between rooms, fully resets the player's adrenaline level and displays a message
     */
    public void adrReset()
    {
        adrenaline = 0;
        System.out.println("Adrenaline bonus lost..");
    }
    
    /**
     * Called after the player attacks an enemy, reduces the durability of the item the player is currently holding
     * @param item The item the player is currently holding
     */
    public void damageItem(Item item)
    {
        item.damaged();
        if(item.getDurability() <= 0) {
            if(holding == item) {
                holding = null;
            }
            System.out.println("Your " + item.getName() + " broke!");
            lose(item);
        }
    }
}
