
/**
 * Write a description of class Item here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String name;
    private String type;
    private String description;
    private int damage;
    private int durability;
    private int weight;

    /**
     * Constructor for objects of class Item
     */
    public Item(String name, String type, String description, int damage, int durability, int weight)
    {
        this.name = name;
        this.type = type;
        this.description = description;
        this.damage = damage;
        this.durability = durability;
        this.weight = weight;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getType()
    {
        return type;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public int getDamage()
    {
        return damage;
    }
    
    public int getDurability()
    {
        return durability;
    }
    
    public int getWeight()
    {
        return weight;
    }
    
    public void damaged()
    {
        durability -= 1;
    }
    
    public void upgrade()
    {
        damage += 3;
    }
}
