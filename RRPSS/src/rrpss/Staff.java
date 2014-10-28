package rrpss;
public class Staff extends Person
{
	private String gender;
	private String jobTitle;
	
	public Staff (String name, int id, String gender, String jobTitle)
	{
		super(name, id);
		this.gender = gender;
		this.jobTitle = jobTitle;
	}
	
	public void setGender (String gender)
	{
		this.gender = gender;
	}
	
	public void setJobTitle (String jobTitle)
	{
		this.jobTitle = jobTitle;
	}
	
	public String getGender ()
	{
		return this.gender;
	}
	
	public String getJobTitle ()
	{
		return this.jobTitle;
	}
}
