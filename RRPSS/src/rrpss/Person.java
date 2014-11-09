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
	 * Set the {@link #name} attribute with the specified string argument 
	 * 
	 * @param name String used to set the {@link #name} attribute 
	 */
	public void setName (String name)
	{
		this.name = name;
	}
	
	/**
	 * Set the {@link #id} attribute with the specified value 
	 * 
	 * @param id An integer used to set the {@link #id} attribute 
	 */
	public void setId (int id)
	{
		this.id = id;
	}
	
	/**
	 * Returns the {@link #name} of this object
	 * 
	 * @return the {@link #name} of this object 
	 */
	public String getName ()
	{
		return this.name;
	}
	
	/** 
	 * Returns the {@link #id} of this object 
	 * 
	 * @return the {@link #id} of this object 
	 */
	public int getId ()
	{
		return this.id;
	}
}
