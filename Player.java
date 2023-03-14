import java.util.ArrayList;

/**
 * Manages the player's inventory, hunger, and health
 * @author Bradley Risack
 * @version 2023.03.13
 */
public class Player
{
    // instance variables - replace the example below with your own
    private ArrayList<Item> Inventory;
    private int hunger;
    private int health;

    public Player()
    {
        Inventory = new ArrayList<Item>();
        hunger = 15;
        health = 5;
    }
    
    public void obtain(Item item)
    {
        Inventory.add(item);
    }
    
    public void lose(Item item)
    {
        Inventory.remove(item);
    }
    
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
    
    public boolean checkForItem(Item item)
    {
        if (Inventory.contains(item)) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean checkEdible(Item item)
    {
        String isEdible = item.getType();
        if (isEdible == "edible") {
            return true;
        } else {
            return false;
        }
    }
    
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
    
    private void eat(Item item)
    {
        Inventory.remove(item);
        hunger += 7;
        System.out.println("You have eaten a(n) " + item.getName());
        if (hunger <= 7) {
            System.out.println("You are still hungry");
        } else {
            System.out.println("You feel refreshed");
        }
    }
    
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
}
