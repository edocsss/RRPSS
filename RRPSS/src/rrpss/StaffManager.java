package rrpss;

import java.util.Vector;

public class StaffManager {
	private Vector<Staff> staffs;
	public StaffManager ()
	{
		this.staffs = new Vector<Staff> ();
	}
	
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
	
	public void addStaff (Staff s)
	{
		this.staffs.add(s);
	}
	
	public void removeStaff (int staffId)
	{
		this.staffs.remove(this.getStaffById(staffId));
	}
}