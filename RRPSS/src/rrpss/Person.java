package rrpss;

import java.io.Serializable;

/**
 * Stores the name and ID of objects of {@link Staff} and of {@link Customer}. 
 * Extended by {@link Staff} and {@link Customer} 
 * 
 * @author YanHan
 * 
 */
public class Person implements Serializable 
{	
	/**
	 * Distinguish among objects of {@link Staff} and {@link Customer} 
	 */
	private String name;
	
	/**
	 * Identifier (id) of this Person 
	 */
	private int id;
	
	/**
	 * Constructs an {@link Person} object with name and id 
	 * 
	 *  @param name Indicate identity of this object 
	 *  @param id 	Distinguish one object from another 
	 */
	public Person (String name, int id)
	{
		this.name = name;
		this.id = id;
	}
	
	/**
	 * Sets the {@link Person#name name} attribute with the specified string argument 
	 * 
	 * @param name String used to set the {@link Person#name name} attribute 
	 */
	public void setName (String name)
	{
		this.name = name;
	}
	
	/**
	 * Sets the {@link Person#id} attribute with the specified value 
	 * 
	 * @param id An integer used to set the {@link Person#id id} attribute 
	 */
	public void setId (int id)
	{
		this.id = id;
	}
	
	/**
	 * Returns the {@link Person#name name} of this object
	 * 
	 * @return the {@link Person#name name} of this object 
	 */
	public String getName ()
	{
		return this.name;
	}
	
	/** 
	 * Returns the {@link Person#id id} of this object 
	 * 
	 * @return the {@link Person#id id} of this object 
	 */
	public int getId ()
	{
		return this.id;
	}
}
