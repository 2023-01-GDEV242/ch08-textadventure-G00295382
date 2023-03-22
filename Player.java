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
    private int wc;
    private int karma;
    private int adrenaline;
    private Item holding;

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
    
    public ArrayList<Item> inventoryArray()
    {
        return Inventory;
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
    
    public int weightClass()
    {
        return wc;
    }
    
    public void strength()
    {
        wc += 2;
    }
    
    public void addKarma()
    {
        if(karma <= 10) {
            karma += 1;
        }
    }
    
    public void maxKarma()
    {
        karma = 10;
    }
    
    public void hold(Item item)
    {
        holding = item;
    }
    
    public Item getHolding()
    {
        return holding;
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
    
    public int getKarma()
    {
        return karma;
    }
    
    public int adrLevel()
    {
        return adrenaline;
    }
    
    public void attackedBy(Enemy enemy)
    {
        health -= enemy.getDamage();
        System.out.println("Attacked by " + enemy.getName() + "! You lost " + enemy.getDamage() + " hitpoints!");
    }
    
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
    
    public void adrDecay()
    {
        if(adrenaline > 0) {
            adrenaline -= 1;
            if(adrenaline <= 0) {
                System.out.println("Adrenaline bonus lost..");
            }
        }
    }
    
    public void adrReset()
    {
        adrenaline = 0;
        System.out.println("Adrenaline bonus lost..");
    }
    
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
