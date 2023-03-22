
/**
 * Used in the Game class to create Item objects for the player to pickup, hold, eat, examine, and attack with!
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
     * @param name The name of the item
     * @param type The type of the item, can be "weapon", "edible", or "collectible"
     * @param damage The damage the item deals
     * @param durability The durability of the item
     * @param weight The weight of the item
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
    
    /**
     * @return String Returns the name of the item
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * @return String Returns the type of the item
     */
    public String getType()
    {
        return type;
    }
    
    /**
     * @return String Returns the description of the item
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * @return int Returns the damage of the item
     */
    public int getDamage()
    {
        return damage;
    }
    
    /**
     * @return int Returns the durability of the item
     */
    public int getDurability()
    {
        return durability;
    }
    
    /**
     * @return int Returns the weight of the item
     */
    public int getWeight()
    {
        return weight;
    }
    
    /**
     * Called when the player attacks an enemy, lowers the durability of the item by 1
     */
    public void damaged()
    {
        durability -= 1;
    }
    
    /**
     * Called never, unused method. Raises the damage of the item by 3
     */
    public void upgrade()
    {
        damage += 3;
    }
}
