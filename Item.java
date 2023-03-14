
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
    private int damage;
    private int durability;

    /**
     * Constructor for objects of class Item
     */
    public Item(String name, String type, int damage, int durability)
    {
        this.name = name;
        this.type = type;
        this.damage = damage;
        this.durability = durability;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getType()
    {
        return type;
    }
    
    public int getDamage()
    {
        return damage;
    }
    
    public int getDurability()
    {
        return durability;
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
