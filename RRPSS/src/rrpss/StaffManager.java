package rrpss;

import java.io.Serializable;
import java.util.Vector;

/**
 * Manages {@link Staff} of {@link Restaurant} 
 * and keep tracks of the staffs available in this Restaurant 
 * with methods to add Staff and remove Staff 
 * 
 * @author YanHan 
 * @see		Serializable
 */
public class StaffManager implements Serializable {
	/**
	 * A list of {@link Staff} stored in Vector data structure
	 */
	private Vector<Staff> staffs;
	
	/**
	 * Constructs a {@link StaffManager} object with a list of {@link Staff} 
	 * 
	 */
	public StaffManager ()
	{
		this.staffs = new Vector<Staff> ();
	}
	
	/**
	 * Returns a {@link Staff} object by using the {@link Person#id id} of {@link Staff} 
	 * 
	 * @param staffId Identity of staffs working in this Restaurant 
	 * @return a {@link Staff} object by using the {@link Person#id id} of {@link Staff} 
	 */
	public Staff getStaffById (int staffId)
	{
		for (Staff s: this.staffs)
		{
			if (s.getId() == staffId) 
			{
				return s;
			}
		}
		
		return null;
	}
	/** 
	 * Add a {@link Staff} object to the vector 
	 * 
	 * @param s {@link Staff} object to be added 
	 */
	public void addStaff (Staff s)
	{
		this.staffs.add(s);
	}
	/**
	 * Removes a {@link Staff} object from the vector 
	 * 
	 * @param staffId Identity of {@link Staff} to be used for removal 
	 */
	public void removeStaff (int staffId)
	{
		this.staffs.remove(this.getStaffById(staffId));
	}
}