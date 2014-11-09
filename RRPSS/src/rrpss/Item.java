package rrpss;

import java.io.Serializable;

/**
 * Stores information about an item of a {@link Menu}, {@link AlaCarte} and {@link SetPackage}
 * @author Liu Liling
 * @see Serializable
 */
public class Item implements Serializable {
	/**
	 * Data properties of Item (name, description, price, id) are categorised as protected type
	 */
	protected String name;
	
	/**
	 * Description of item
	 */
	protected String description;
	
	/**
	 * Price of item
	 */
	protected double price;
	
	/**
	 * Identifier of item
	 */
	protected int id;

	/**
	 * Constructs a item with specified name, price and description.
	 * @param name          name of this item.
	 * @param price         price of this item.
	 * @param description   description of this item.
	 */
	public Item(String name, double price, String description) {
		this.name = name;
		this.price = price;
		this.description = description;
	}  
	
    /**
     * Returns the name of this item.
     * @return name of this item.
     */
	public String getName() {
		return name;
	}
	
    /**
     * Returns the description of this item.
     * @return description of this item.
     */	
	public String getDescription() {
		return description;
	}
	
    /**
     * Returns the price of this item.
     * @return price of this item.
     */		
	public double getPrice() {
		return price;
	}
	
    /**
     * Returns the id of this item (item number), will always be a positive integer.
     * @return Returns the id of this item (item number).
     */
	public int getId() {
		return id;
	}
	
    /**
     * Sets name for this item
     * @param name of this item.
     */
	public void setName(String name) {
		this.name = name;
	}
	
    /**
     * Set description for this item
     * @param description of this item.
     */	
	public void setDescription(String description) {
		this.description = description;
	}
	
    /**
     * Set price for this item
     * @param price of this item.
     */		
	public void setPrice(double price) {
		this.price = price;
	}
	
    /**
     * Set id for this item
     * @param id of this item.
     */	
	public void setId(int id) {
		this.id = id;
	}
}
