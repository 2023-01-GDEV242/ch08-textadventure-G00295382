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
    private String effect;
    private int effectduration;
    private int evaderate;
    private int evademult;
    
    /**
     * Initialize all fields based on the constructor
     * @param name The name of the enemy
     * @param description A description of the enemy
     * @param health The health of the enemy
     * @param damage The amount of damage the enemy deals
     */
    public Enemy(String name, String description, int health, int damage, int evaderate, int evademult)
    {
        this.name = name;
        this.description = description;
        this.health = health;
        this.damage = damage;
        this.evaderate = evaderate;
        this.evademult = evademult;
        
        effect = "";
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
     * Sets the active effect of the enemy
     * @param String the name of the effect
     */
    public void effectDecay()
    {
        if(effect != "") {
            effectduration -= 1;
            if(effectduration <= 0) {
                System.out.println(getName() + " is no longer " + effect);
                effect = "";
            }
        }
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
     * @return String the effect the enemy has
     */
    public String getEffect()
    {
        return effect;
    }
    
    /**
     * @return the evasion rate of the enemy
     */
    public int getEvade()
    {
        return evaderate;
    }
    
    /**
     * @return the evasion multiplier of the enemy
     */
    public int getEvadeMult()
    {
        return evademult;
    }
    
    /**
     * Called by the player, deals damage to the enemy based on the value of the getDamage() method of the item the player is currently holding
     * @param item The item the enemy was attacked with, otherwise, the item the player is currently holding
     * @param int The player's current adrenaline level, modifies the amount of damage dealt to the enemy
     */
    public void attacked(Item item, int adr)
    {
        int pdamage = item.getDamage() + adr;
        if(effect == "confused") {
            pdamage = (item.getDamage() * 2) + adr;
        }
        health -= pdamage;
        System.out.println("The " + name + " lost " + pdamage + " hitpoints!");
        if(item.getName() == "rock") {
            effect = "confused";
            effectduration = 2;
        }
    }
}
