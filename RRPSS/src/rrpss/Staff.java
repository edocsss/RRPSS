package rrpss;

/**
 * Stores the information of all {@link Staff}, which includes gender and jobTitle. 
 * Class {@link Staff} inherits from superclass {@link Person} 
 * 
 * @author YanHan 
 */
public class Staff extends Person
{
	/**
	 * Distinguish {@link Staff} among different genders 
	 */
	private String gender;
	
	/**
	 * Distinguish {@link Staff} by job title 
	 */
	private String jobTitle;
	
	/**
	 * Constructs a {@link Staff} object with gender, jobTitle, and 
	 * with name and id inherited from superclass {@link Person} 
	 * 
	 * @param name 		Indicate identity of this object 
	 * @param id 		Distinguish one object from another 
	 * @param gender 	Indicate gender of object 
	 * @param jobTitle 	Indicate the job title of object 
	 */
	public Staff (String name, int id, String gender, String jobTitle)
	{
		super(name, id);
		this.gender = gender;
		this.jobTitle = jobTitle;
	}
	
	/**
	 * Sets the {@link Staff#gender gender} attribute for object 
	 * 
	 * @param gender String used to set the {@link Staff#gender gender} attribute 
	 */
	public void setGender (String gender)
	{
		this.gender = gender;
	}
	
	/**
	 * Sets the {@link Staff#jobTitle jobTitle} attribute for object
	 * 
	 * @param jobTitle String used to set  the {@link Staff#jobTitle jobTitle} attribute 
	 */
	public void setJobTitle (String jobTitle)
	{
		this.jobTitle = jobTitle;
	}
	
	/**
	 * Returns the {@link Staff#gender gender} of object 
	 * @return the {@link Staff#gender gender} of object 
	 */
	public String getGender ()
	{
		return this.gender;
	}
	
	/**
	 * Returns the {@link Staff#jobTitle jobTitle} of object
	 * @return the {@link Staff#jobTitle jobTitle} of object 
	 */
	public String getJobTitle ()
	{
		return this.jobTitle;
	}
}
