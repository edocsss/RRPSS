package rrpss;

import java.util.Calendar;
import java.util.HashMap;

/*
 * Order status = Active or Closed
 */

public class Order
{
	private int id;
	private Calendar dateTime;
	private String status;
	private Staff staff; // (e)
	private HashMap<AlaCarte, Integer> alaCartes;
	private HashMap<SetPackage, Integer> setPackages;
	
	/*
	 * Customer, Calendar -> from App
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
	
	public void setId (int id)
	{
		this.id = id;
	}
	
	public void setTime (int hour, int minute, int second)
	{
		this.dateTime.set(Calendar.HOUR_OF_DAY, hour);
		this.dateTime.set(Calendar.MINUTE, minute);
		this.dateTime.set(Calendar.SECOND, second);
	}
	
	public void setDate (int day, int month, int year)
	{
		this.dateTime.set(Calendar.DAY_OF_MONTH, day);
		this.dateTime.set(Calendar.MONTH, month - 1);
		this.dateTime.set(Calendar.YEAR, year);
	}
	
	public void setStatus (String status)
	{
		this.status = status;
	}

	// Use table number to get the Order object and addOrderItem to the list
	// Get OrderItem from the MainApp
	public void addAlaCarte (AlaCarte alaCarte, int quantity)
	{
		AlaCarte ac = getAlaCarteByItemId(alaCarte.getId());
		if (ac != null) {
			alaCartes.put(ac, alaCartes.get(ac) + quantity);
		} else {
			alaCartes.put(alaCarte, quantity);
		}
	}
	
	public void removeAlaCarte (AlaCarte alaCarte)
	{
		this.alaCartes.remove(alaCarte);
	}
	
	public HashMap<AlaCarte, Integer> getAlaCartes()
	{
		return this.alaCartes;
	}
	
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
	
	public void addSetPackage (SetPackage setPackage, int quantity)
	{
		SetPackage sp = getSetPackageByItemId(setPackage.getId());
		if (sp != null) {
			setPackages.put(sp, setPackages.get(sp) + quantity);
		} else {
			setPackages.put(setPackage, quantity);
		}
	}
	
	public void removeSetPackage (SetPackage setPackage)
	{
		this.setPackages.remove(setPackage);
	}
	
	public HashMap<SetPackage, Integer> getSetPackages()
	{
		return this.setPackages;
	}
	
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
	
	public void setStaff (Staff staff)
	{
		this.staff = staff;
	}
	
	public int getId ()
	{
		return this.id;
	}
	
	public Calendar getDateTime ()
	{
		return this.dateTime;
	}
	
	public Staff getStaff ()
	{
		return this.staff;
	}

	public String getStatus ()
	{
		return this.status;
	}
	
	public void printOrder ()
	{
		//Something like toString -> like a breakdown of what has been ordered
		for (AlaCarte ac: alaCartes.keySet().toArray(new AlaCarte[0])) {
			System.out.println(String.format("%-5s%-20s @%-8.2f x%-5d = %-10.2f",
					ac.id, ac.name, ac.price, alaCartes.get(ac), ac.price * alaCartes.get(ac)));
		}
		for (SetPackage sp: setPackages.keySet().toArray(new SetPackage[0])) {
			System.out.println(String.format("%-5s%-20s%-8.2f x%-5d = %-10.2f",
					sp.id, sp.name, sp.price, setPackages.get(sp), sp.price * setPackages.get(sp)));
		}
	}
	
}
