import java.util.HashMap;

/**
 * Manages the player's inventory, hunger, and health
 * @author Bradley Risack
 * @version 2023.03.13
 */
public class Player
{
    // instance variables - replace the example below with your own
    private HashMap<String, String> Inventory = new HashMap<String, String>();
    private int hunger;
    private int health;

    public Player()
    {
        Inventory.put("Apple", "e");
        Inventory.put("Rock", "i");
        hunger = 15;
        health = 5;
    }
    
    public void ListInventory()
    {
        System.out.println("Your inventory contains : ");
        Inventory.forEach((key, value) -> {
          System.out.println(key);
        });
    }
    
    public boolean checkForItem(String item)
    {
        String citem = Inventory.get(item);
        if (citem != null) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean checkEdible(String item)
    {
        String isEdible = Inventory.get(item);
        if (isEdible == "e") {
            return true;
        } else {
            return false;
        }
    }
    
    public void tryeat(String item)
    {
        boolean exists = checkForItem(item);
        boolean edible;
        if (exists == true) {
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
    
    private void eat(String item)
    {
        Inventory.remove(item);
        hunger += 7;
        System.out.println("You have eaten a(n) " + item);
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
}
