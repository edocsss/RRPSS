package rrpss;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

/*
 * Order status = Active or Closed
 */

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
	
	
	// TODO JavaDoc for the remaining -> Constructor + Methods
	// TODO Remove all methods which are not used by any class
	/**
	 * 
	 * @param id
	 * @param dateTime
	 * @param staff
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
	 * 
	 * @param id
	 */
	public void setId (int id)
	{
		this.id = id;
	}
	
	/**
	 * 
	 * @param hour
	 * @param minute
	 * @param second
	 */
	public void setTime (int hour, int minute, int second)
	{
		this.dateTime.set(Calendar.HOUR_OF_DAY, hour);
		this.dateTime.set(Calendar.MINUTE, minute);
		this.dateTime.set(Calendar.SECOND, second);
	}
	
	/**
	 * 
	 * @param day
	 * @param month
	 * @param year
	 */
	public void setDate (int day, int month, int year)
	{
		this.dateTime.set(Calendar.DAY_OF_MONTH, day);
		this.dateTime.set(Calendar.MONTH, month - 1);
		this.dateTime.set(Calendar.YEAR, year);
	}
	
	/**
	 * 
	 * @param status
	 */
	public void setStatus (String status)
	{
		this.status = status;
	}

	// Use table number to get the Order object and addOrderItem to the list
	// Get OrderItem from the MainApp
	/**
	 * 
	 * @param alaCarte
	 * @param quantity
	 */
	public void addAlaCarte (AlaCarte alaCarte, int quantity)
	{
		AlaCarte ac = getAlaCarteByItemId(alaCarte.getId());
		if (ac != null) {
			alaCartes.put(ac, alaCartes.get(ac) + quantity);
		} else {
			alaCartes.put(alaCarte, quantity);
		}
	}
	
	/**
	 * 
	 * @param itemId
	 * @return
	 */
	private AlaCarte getAlaCarteByItemId (int itemId)
	{
		AlaCarte[] acs = this.alaCartes.keySet().toArray(new AlaCarte[0]);
		
		for (AlaCarte ac: acs) {
			if (ac.getId() == itemId) {
				return ac;
			}
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param itemId
	 * @return
	 */
	public int removeAlaCarteByItemId (int itemId)
	{
		AlaCarte ac = this.getAlaCarteByItemId(itemId);
		
		if (ac == null) 
		{
			return -1;
		} 
		else 
		{
			this.alaCartes.remove(ac);
			return 1;
		}
	}
	
	/**
	 * 
	 * @param alaCarte
	 */
	public void removeAlaCarte (AlaCarte alaCarte)
	{
		this.alaCartes.remove(alaCarte);
	}
	
	/**
	 * 
	 * @return
	 */
	public HashMap<AlaCarte, Integer> getAlaCartes()
	{
		return this.alaCartes;
	}
	
	/**
	 * 
	 * @param setPackage
	 * @param quantity
	 */
	public void addSetPackage (SetPackage setPackage, int quantity)
	{
		SetPackage sp = getSetPackageByItemId(setPackage.getId());
		if (sp != null) {
			setPackages.put(sp, setPackages.get(sp) + quantity);
		} else {
			setPackages.put(setPackage, quantity);
		}
	}
	
	/**
	 * 
	 * @param itemId
	 * @return
	 */
	private SetPackage getSetPackageByItemId (int itemId)
	{
		SetPackage[] acs = this.setPackages.keySet().toArray(new SetPackage[0]);
		
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
	 * 
	 * @param itemId
	 * @return
	 */
	public int removeSetPackageByItemId (int itemId)
	{
		SetPackage sp = this.getSetPackageByItemId(itemId);
		
		if (sp == null) {
			return -1;
		} else {
			this.setPackages.remove(sp);
			return 1;
		}
	}
	
	/**
	 * 
	 * @param setPackage
	 */
	public void removeSetPackage (SetPackage setPackage)
	{
		this.setPackages.remove(setPackage);
	}
	
	/**
	 * 
	 * @return
	 */
	public HashMap<SetPackage, Integer> getSetPackages()
	{
		return this.setPackages;
	}
	
	/**
	 * 
	 * @param staff
	 */
	public void setStaff (Staff staff)
	{
		this.staff = staff;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getId ()
	{
		return this.id;
	}
	
	/**
	 * 
	 * @return
	 */
	public Calendar getDateTime ()
	{
		return this.dateTime;
	}
	
	/**
	 * 
	 * @return
	 */
	public Staff getStaff ()
	{
		return this.staff;
	}

	/**
	 * 
	 * @return
	 */
	public String getStatus ()
	{
		return this.status;
	}
	
	/**
	 * 
	 * @return
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
	 * 
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
