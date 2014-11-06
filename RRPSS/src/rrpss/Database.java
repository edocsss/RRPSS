package rrpss;

import java.io.*;

/**
 * Stores and retrieves serialized {@link Restaurant Restaurant} object. 
 * This class uses FileInputStream class and ObjectInputStream class in order to read an object from a ".ser" file.
 * For the purpose of writing a serialized object into a ".ser" file, FileOutputStream and ObjectInputStream class are used.
 * This Database class allows the main application to load previous saved data and to store any changes made during application
 * execution.
 * 
 * <p>
 * <b>Warning:</b> All classes which are serialized must implement the {@link Serializable} interface!
 * </p>
 * 
 * @author Edwin Candinegara
 *
 */
public final class Database
{
	/**
	 * Constructs nothing. This class does not have to be instantiated to read and write a serialized object.
	 */
	private Database () {}
	
	/**
	 * Read a serialized object from a ".ser" file from the given path. The object read must implement
	 * the {@link Serializable} interface.
	 * This method returns the {@link Restaurant Restaurant} object if it is found in the ".ser" file or {@code null} otherwise
	 * 
	 * @param 	path 	a relative path to the project folder where the ".ser" file resides
	 * @return 			the {@link Restaurant Restaurant} object inside the ".ser" file if it is found, {@code null} otherwise
	 * @see 			Restaurant
	 * @see 			FileInputStream
	 * @see 			ObjectInputStream
	 */
	public static Restaurant readRestaurantObject (String path)
	{
		try
		{
			FileInputStream fileIn = new FileInputStream (path);
			ObjectInputStream objectIn = new ObjectInputStream (fileIn);
			
			// Read the restaurant object
			Restaurant r = (Restaurant) objectIn.readObject();
			objectIn.close();
			fileIn.close();
			
			return r;
		}
		catch (ClassNotFoundException c)
		{
			System.out.println("Error: ClassNotFoundException occurs!");
			c.printStackTrace();
			return null;
		}
		catch (StreamCorruptedException s)
		{
			System.out.println("Error: StreamCorruptedException occurs!");
			s.printStackTrace();
			return null;
		}
		catch (InvalidClassException i)
		{
			System.out.println("Error: InvalidClassException occurs!");
			i.printStackTrace();
			return null;
		}
		catch (OptionalDataException o)
		{
			System.out.println("Error: OptionalDataException occurs!");
			o.printStackTrace();
			return null;
		}
		catch (IOException i)
		{
			System.out.println("Error: IOException occurs!");
			i.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Write a {@link Restaurant Restaurant} object to a ".ser" file stored in the given path.
	 * All object which are going to be serialized must implements the {@link Serializable} interface.
	 * This method returns {@code 1} if the serialization is successful and {@code -1} otherwise.
	 * 
	 * @param 	r		the {@link Restaurant Restaurant} object to be serialized
	 * @param 	path	a relative path to the project folder where the ".ser" file will be written 
	 * @return			{@code 1} if the serialization is successful and {@code -1} otherwise
	 * @see 			Restaurant
	 * @see 			FileOutputStream
	 * @see 			ObjectOutputStream
	 */
	public static int writeRestaurantObject (Restaurant r, String path)
	{
		try
		{
			FileOutputStream fileOut = new FileOutputStream (path);
			ObjectOutputStream objectOut = new ObjectOutputStream (fileOut);
			
			objectOut.writeObject(r);
			objectOut.close();
			fileOut.close();
			
			return 1;
		}
		catch (InvalidClassException i)
		{
			System.out.println("Error: InvalidClassException occurs!");
			i.printStackTrace();
			return -1;
		}
		catch (NotSerializableException n)
		{
			System.out.println("Error: NotSerializableException occurs!");
			n.printStackTrace();
			return -1;
		}
		catch (IOException i)
		{
			System.out.println("Error: IOException occurs!");
			i.printStackTrace();
			return -1;
		}
	}
}
