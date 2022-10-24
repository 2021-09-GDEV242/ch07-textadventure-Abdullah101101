
/**
 * The item class is able to create items. These items will be used in other
 * classes so we can store this items in rooms. The item class asks for
 * name, weight and description for parameters.
 *
 * @author Abdullah Abdulwahab
 * @version 10.23.2022
 */

public class Item 
{
    private String itemName;
    private int itemWeight;
    private String itemDescription;
   
    /**
     * constructor for the item class it asks for 3 parameters
     * @param mame  the items name
     * @param wieght  the items weight (in pounds)
     * @param description  a description of the item
     */
    public Item(String name, int weight,String description)
    {
        itemDescription = description;
        itemWeight = weight;
        itemName = name;
    }   
    
    /**
     * this method return the item name
     * @return name of the item.
     */
    public String getName()
    {
        return itemName;
    }

    /**
     * This method gvies the item weight
     * @return weight of the item
     */
    public int getWeight()
    {
        return itemWeight;
    }
    
    /**
     * this method gives the item description
     * @return description of the item
     */
    public String getDescription()
    {
        return itemDescription;
    }
    
    /**
     * This method will pring out the items name weight and description 
     * @return string of item info
     */
    public String getItemInformation()
    {
        String string = "Item: " + itemName + " weighs nearly " + itemWeight + " pound(s)." + 
        "\nItem description: " + itemDescription + "";
        return string;
    }
}
