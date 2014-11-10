package rrpss;

/**
 * Stores information about a single alaCarte, particularly used in context of a menu.
 * This class stores the name, price, description and type of alaCarte {@link Menu}.
 * @author Liu Liling
 * @see    Comparable
 */
public class AlaCarte extends Item implements Comparable<AlaCarte> {
	/**
	 * Class AlaCarte inherits attributes and methods from superclass Item
	 */
	private String type;
	
	/**
	 * Constructs an ala carte item with the specified parameter
	 * @param name		name of the item
	 * @param price		price of item
	 * @param description	description of item
	 * @param type		type of item
	 */
	public AlaCarte(String name, double price, String description, String type) {
		super(name, price, description);
		this.type = type;
	}
	
    /**
     * Returns the type of this alaCarte.
     * @return type of this alaCarte.
     */	
	public String getType() {
		return type;
	}
	
	/**
    * Sets the type of alaCarte
    * @param type	type of alaCarte
    */	
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * Returns a duplicate of this object
	 * @return duplicate of this object
	 */
	public AlaCarte copy() {
		AlaCarte ac = new AlaCarte(name, price, description, type);
		ac.setId(this.id); 
		return ac;
	}
	
	/**
	 * This method is used for sorting the alacarte item by type at Menu class
	 */
	@Override
	public int compareTo(AlaCarte ac) {
		return this.type.compareTo(ac.type);
	}
}
