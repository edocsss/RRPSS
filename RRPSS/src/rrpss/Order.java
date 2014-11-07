package rrpss;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

/**
 * Stores all important information of an order created for a customer. This class stores
 * the ID, date and time of the {@link Order} object creation, the {@link Staff} 
 * object who created the {@link Order}, and the most important thing is the menu item
 * the customer ordered.
 * 
 * @author Edwin Candinegara
 * @see Serializable
 * @see HashMap
 * @see Staff
 */
public class Order implements Serializable 
{
	/**
	 * Distinguish one object with another. This attribute is assigned in 
	 * {@link OrderManager#createOrder(Calendar, Staff) createOrder(Calendar, Staff)} from
	 * {@link OrderManager} class.
	 */
	private int id;
	
	/**
	 * Specify when this object is created. This attribute is assigned in 
	 * {@link OrderManager#createOrder(Calendar, Staff) createOrder(Calendar, Staff)} from 
	 * {@link OrderManager} class.
	 */
	private Calendar dateTime;
	
	/**
	 * Specify the status of this object.
	 * 
	 * <p>
	 * There are 2 type of status.
	 * <ul>
	 * <li>Active</li>
	 * <li>Closed</li>
	 * </ul>
	 * </p>
	 */
	private String status;
	
	/**
	 * Specify the {@link Staff Staff} who created this {@code Order} object.
	 */
	private Staff staff;
	
	/**
	 * A {@link HashMap HashMap} used to store all {@link AlaCarte} objects
	 * ordered by the customer. The key is the {@link AlaCarte} object and
	 * the value associated is the quantity.
	 */
	private HashMap<AlaCarte, Integer> alaCartes;
	
	/**
	 * A {@link HashMap HashMap} used to store all {@link SetPackage} objects
	 * ordered by the customer. The key is the {@link SetPackage} object and
	 * the value associated is the quantity.
	 */
	private HashMap<SetPackage, Integer> setPackages;
	
	/**
	 * Constructs an {@link Order} object with ID, date and time when it is made, 
	 * and {@link Staff} object indicating who created this object.
	 * 
	 * <p>
	 * When an object is created, the {@link #status} is set to "Active" automatically.
	 * The Vector {@link #alaCartes} and {@link #setPackages} are initialized without
	 * any element. 
	 * </p>
	 * 
	 * @param id		Distinguish one object with another
	 * @param dateTime	Indicate when this object is created
	 * @param staff		Indicate who created this object
	 */
	public Order (int id, Calendar dateTime, Staff staff)
	{
		this.id = id;
		this.dateTime = dateTime;
		this.status = "Active";
		this.staff = staff;
		this.alaCartes = new HashMap<AlaCarte, Integer> ();
		this.setPackages = new HashMap<SetPackage, Integer> ();
	}
	
	/**
	 * Set the {@link #id} attribute with the specified value.
	 * 
	 * @param id	An integer used to set the {@link #id} attribute 
	 */
	public void setId (int id)
	{
		this.id = id;
	}
	
	/**
	 * Set the {@link #status} attribute to the specified String argument.
	 * 
	 * @param status	String used to set the {@link #status} attribute (must be "Active" or "Closed")
	 */
	public void setStatus (String status)
	{
		this.status = status;
	}

	/**
	 * Add an {@link AlaCarte} object ordered by the customer to the attribute {@link #alaCartes}.
	 * 
	 * <p>
	 * The method first checks whether the specified {@link AlaCarte} object has already been inside
	 * the {@link #alaCartes} attribute.
	 * <br>
	 * If it is, then this method only update the quantity of that particular {@link AlaCarte} object
	 * If it is not, then this method create a new entry in {@link #alaCartes} attribute with the specified quantity.
	 * </p>
	 * 
	 * @param alaCarte	An {@link AlaCarte} object which is added to {@link #alaCartes}
	 * @param quantity	An integer indicating the quantity of the ordered item (must be a positive integer)
	 */
	public void addAlaCarte (AlaCarte alaCarte, int quantity)
	{
		AlaCarte ac = getAlaCarteByItemId(alaCarte.getId());
		
		// If the AlaCarte is already inside the HashMap, update the quantity only
		if (ac != null) 
		{
			alaCartes.put(ac, alaCartes.get(ac) + quantity);
		} 
		// If not, put a new entry with ac as the key and quantity as the value
		else 
		{
			alaCartes.put(alaCarte, quantity);
		}
	}
	
	/**
	 * Return {@link AlaCarte} object with the specified ID. If no such {@link AlaCarte} object is found,
	 * this method returns {@code null}.
	 * 
	 * @param 	itemId	The ID of an {@link AlaCarte} object that is looked for (should be an ID of an existing {@link AlaCarte}
	 * 					object)
	 * @return			{@link AlaCarte} object with the specified ID or {@code null} if no such object is found.
	 */
	private AlaCarte getAlaCarteByItemId (int itemId)
	{
		// Array of alaCartes HashMap keys
		AlaCarte[] acs = this.alaCartes.keySet().toArray(new AlaCarte[0]);

		// Search through the keys to get the AlaCarte object with the specified ID
		for (AlaCarte ac: acs) {
			if (ac.getId() == itemId) {
				return ac;
			}
		}
		
		// Return null if no such AlaCarte object is found
		return null;
	}
	
	/**
	 * Remove an {@link AlaCarte} object with the specified ID.
	 * 
	 * @param 	itemId	The ID of the {@link AlaCarte} object to be removed (should be an ID of an existing {@link AlaCarte}
	 * 					object)
	 * @return			{@code 1} if the targeted {@link AlaCArte} object is successfully removed
	 * 					or {@code -1} otherwise
	 */
	public int removeAlaCarteByItemId (int itemId)
	{
		AlaCarte ac = this.getAlaCarteByItemId(itemId);
		
		// Unsuccessful
		if (ac == null) 
		{
			return -1;
		} 
		// Successful
		else 
		{
			this.alaCartes.remove(ac);
			return 1;
		}
	}
	
	/**
	 * Returns the {@link AlaCarte} objects.
	 * 
	 * @return The {@link AlaCarte} objects
	 */
	public HashMap<AlaCarte, Integer> getAlaCartes()
	{
		return this.alaCartes;
	}
	
	/**Add an {@link SetPackage} object ordered by the customer to the attribute {@link #setPackages}.
	 * 
	 * <p>
	 * The method first checks whether the specified {@link SetPackage} object has already been inside
	 * the {@link #setPackages} attribute.
	 * <br>
	 * If it is, then this method only update the quantity of that particular {@link SetPackage} object
	 * If it is not, then this method create a new entry in {@link #setPackages} attribute with the specified quantity.
	 * </p>
	 * 
	 * @param alaCarte	An {@link SetPackage} object which is added to {@link #setPackages}
	 * @param quantity	An integer indicating the quantity of the ordered item (must be a positive integer)
	 */ 
	public void addSetPackage (SetPackage setPackage, int quantity)
	{
		SetPackage sp = getSetPackageByItemId(setPackage.getId());
		
		// If the SetPackage is already inside the HashMap, update the quantity only
		if (sp != null) 
		{
			setPackages.put(sp, setPackages.get(sp) + quantity);
		}
		// If not, put a new entry with ac as the key and quantity as the value
		else 
		{
			setPackages.put(setPackage, quantity);
		}
	}
	
	/**
	 * Return {@link SetPackage} object with the specified ID. If no such {@link SetPackage} object is found,
	 * this method returns {@code null}.
	 * 
	 * @param 	itemId	The ID of an {@link SetPackage} object that is looked for (should be an ID of an existing {@link SetPackage}
	 * 					object)
	 * @return			{@link SetPackage} object with the specified ID or {@code null} if no such object is found.
	 */
	private SetPackage getSetPackageByItemId (int itemId)
	{
		// Array of alaCartes HashMap keys
		SetPackage[] acs = this.setPackages.keySet().toArray(new SetPackage[0]);
		
		// Search through the keys to get the AlaCarte object with the specified ID
		for (SetPackage ac: acs) 
		{
			if (ac.getId() == itemId) 
			{
				return ac;
			}
		}
		
		return null;
	}
	
	/**
	 * Remove an {@link SetPackage} object with the specified ID.
	 * 
	 * @param itemId 	(should be an ID of an existing {@link SetPackage}
	 * 				  	object)
	 * @return			{@code 1} if the targeted {@link AlaCArte} object is successfully removed
	 * 					or {@code -1} otherwise
	 */
	public int removeSetPackageByItemId (int itemId)
	{
		SetPackage sp = this.getSetPackageByItemId(itemId);
		
		// Unsuccessful
		if (sp == null) 
		{
			return -1;
		} 
		// Successful
		else 
		{
			this.setPackages.remove(sp);
			return 1;
		}
	}
	
	/**
	 * Returns the {@link SetPackage} objects.
	 * 
	 * @return The {@link SetPackage} objects
	 */
	public HashMap<SetPackage, Integer> getSetPackages()
	{
		return this.setPackages;
	}
	
	/**
	 * Returns the {@link #id} of this object
	 * 
	 * @return The {@link #id} of this object
	 */
	public int getId ()
	{
		return this.id;
	}
	
	/**
	 * Returns the {@link #dateTime} of this object.
	 * 
	 * @return The {@link #dateTime} of this object.
	 */
	public Calendar getDateTime ()
	{
		return this.dateTime;
	}
	
	/**
	 * Returns the {@link #staff} of this object
	 * 
	 * @return 
	 */
	public Staff getStaff ()
	{
		return this.staff;
	}

	/**
	 * Return the {@link #dateTime} in a formatted String.
	 * 
	 * @return A formatted String representing {@link #dateTime}
	 */
	public String getDateTimeString() {
		return this.dateTime.get(Calendar.DAY_OF_MONTH) + " "
				+ this.dateTime.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH) + " "
				+ this.dateTime.get(Calendar.YEAR) + ", "
				+ this.dateTime.get(Calendar.HOUR_OF_DAY) + ":"
				+ this.dateTime.get(Calendar.MINUTE) + ":"
				+ this.dateTime.get(Calendar.SECOND);
	}
	
	/**
	 * Print the breakdown of items of this {@link Order} object. This method prints all
	 * Ala Carte and Set Package items details including the price and the quantity.
	 */
	public void printOrder ()
	{
		System.out.println("                                                       ORDER                                                         ");
		System.out.println("=====================================================================================================================");
		System.out.println(String.format("%-5sid: %-5s status: %-20s staff: %-20s Date/time: %-30s",
				" ", id, status, staff.getName(), getDateTimeString()));
		System.out.println("=====================================================================================================================");
		
		for (AlaCarte ac: alaCartes.keySet().toArray(new AlaCarte[0])) 
		{
			System.out.println(String.format("%-5s%-50s @%-10.2f x%-5d = %-10.2f",
					ac.id, ac.name, ac.price, alaCartes.get(ac), ac.price * alaCartes.get(ac)));
		}
		
		for (SetPackage sp: setPackages.keySet().toArray(new SetPackage[0])) 
		{
			System.out.println(String.format("%-5s%-50s @%-10.2f x%-5d = %-10.2f",
					sp.id, sp.name, sp.price, setPackages.get(sp), sp.price * setPackages.get(sp)));
		}
		
		System.out.println();
	}
	
}
