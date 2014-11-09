package rrpss;

import java.util.Vector;
/**
 * Stores information about a single setPackage, particularly used in context of a menu.
 * This class stores the name, price, description of setPackage {@link menu}.
 * various methods to add new alaCartes/setPackages and remove existing alaCartes/setPackages.
 * @author Liu Liling
 */
public class SetPackage extends Item {
	/**
	 * Class SetPackage inherits attributes and methods from superclass Item
	 */
	private Vector<AlaCarte> alaCartes;
	
	/**
	 * Constructs a set package with specified name, price and description.
	 * @param name          name of this setPackage.
	 * @param price         price of this setPackage.
	 * @param description   description of this setPackage.
	 */
	public SetPackage(String name, double price, String description) {
		super(name, price, description);
		this.alaCartes = new Vector<AlaCarte>();
	}
	
    /**
     * @return list of alaCartes in {@link Vector} data structure
     */
	public Vector<AlaCarte> getAlaCartes() {
		return alaCartes;
	}
	
    /**
     * Set alaCartes for this setPackage.
     * @param alaCarte of this setPackage.
     */
	public void setAlaCartes(Vector<AlaCarte> alaCartes) {
		this.alaCartes = alaCartes;
	}
	
	/**
     * Add alaCarte to the vector
     * @param a	 the alaCarte object to be added
     */		
	public void addAlaCarte(AlaCarte a) {
		this.alaCartes.add(a);
	}
	
	/**
     * Remove alaCarte from the vector
     * @param a	 the alaCarte object to be removed
     */		
	public void removeAlaCarte(AlaCarte a) {
		this.alaCartes.remove(a);
	}
	
	/**
	 * Returns a duplicate of this object
	 * @return duplicate of this object
	 */
	public SetPackage copy() {
		SetPackage sp = new SetPackage(name, price, description);
		sp.setAlaCartes(alaCartes);
		sp.setId(this.id); 
		return sp;
	}
}
