import java.util.ArrayList;

/**
 * Write a description of class Enemy here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Enemy
{
    private String name;
    private String description;
    private int health;
    private int damage;
    private ArrayList<Item> loot;
    
    public Enemy(String name, String description, int health, int damage)
    {
        this.name = name;
        this.description = description;
        this.health = health;
        this.damage = damage;
        
        loot = new ArrayList<>();
    }
    
    public void addLoot(Item item)
    {
        loot.add(item);
    }
    
    public ArrayList<Item> listLoot()
    {
        return loot;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public int getHealth()
    {
        return health;
    }
    
    public int getDamage()
    {
        return damage;
    }
    
    public void attacked(Item item, int adr)
    {
        int pdamage = item.getDamage() + adr;
        health -= pdamage;
        System.out.println("The " + name + " lost " + pdamage + " hitpoints!");
    }
}
