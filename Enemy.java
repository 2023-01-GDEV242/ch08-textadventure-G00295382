import java.util.ArrayList;

/**
 * Used in the Game class to create Enemy objects to attack the player
 *
 * @author Bradley Risack
 * @version 2023.03.22
 */
public class Enemy
{
    private String name;
    private String description;
    private int health;
    private int damage;
    private ArrayList<Item> loot;
    
    /**
     * Initialize all fields based on the constructor
     * @param name The name of the enemy
     * @param description A description of the enemy
     * @param health The health of the enemy
     * @param damage The amount of damage the enemy deals
     */
    public Enemy(String name, String description, int health, int damage)
    {
        this.name = name;
        this.description = description;
        this.health = health;
        this.damage = damage;
        
        loot = new ArrayList<>();
    }
    
    /**
     * Adds an item to the enemy's drop table, all of which are given to the player upon it's death
     * @param item The name of the item to add to the drop table
     */
    public void addLoot(Item item)
    {
        loot.add(item);
    }
    
    /**
     * @return ArrayList<Item> A copy of the enemy's drop table
     */
    public ArrayList<Item> listLoot()
    {
        return loot;
    }
    
    /**
     * @return String The name of the enemy
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * @return String The description of the enemy
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * @return int The health of the enemy
     */
    public int getHealth()
    {
        return health;
    }
    
    /**
     * @return int The damage the enemy deals
     */
    public int getDamage()
    {
        return damage;
    }
    
    /**
     * Called by the player, deals damage to the enemy based on the value of the getDamage() method of the item the player is currently holding
     * @param item The item the enemy was attacked with, otherwise, the item the player is currently holding
     * @param int The player's current adrenaline level, modifies the amount of damage dealt to the enemy
     */
    public void attacked(Item item, int adr)
    {
        int pdamage = item.getDamage() + adr;
        health -= pdamage;
        System.out.println("The " + name + " lost " + pdamage + " hitpoints!");
    }
}
