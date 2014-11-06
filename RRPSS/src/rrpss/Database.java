package rrpss;

import java.io.*;

/**
 * Stores and retrieves serialized {@link Restaurant Restaurant} object. 
 * This class uses FileInputStream class and ObjectInputStream class in order to read an object from a ".ser" file.
 * For the purpose of writing a serialized object into a ".ser" file, FileOutputStream and ObjectInputStream class are used.
 * 
 * <p>
 * This Database class allows the main application to load previous saved data and to store any changes made during application
 * execution.
 * </p>
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
	 * Read a serialized object from a ".ser" file from the given path. 
	 * 
	 * <p>
	 * <b>Warning:</b> All classes which are serialized must implement the {@link Serializable} interface!
	 * </p>
	 * 
	 * <p>
	 * Exceptions which may occur during data reading include:
	 * <ul>
	 * <li>{@link ClassNotFoundException}</li>
	 * <li>{@link StreamCorruptedException}</li>
	 * <li>{@link InvalidClassException}</li>
	 * <li>{@link OptionalDataException}</li>
	 * <li>{@link IOException}</li>
	 * </ul>
	 * <br>
	 * All exceptions have been handled inside the method.
	 * <br>
	 * If there is no exception, the {@link Restaurant Restaurant} object inside the ".ser" file will be returned.
	 * Otherwise, {@code null}.
	 * </p>
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
			
			// Close the created streams
			objectIn.close();
			fileIn.close();
			
			// Return the read Restaurant object
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
	 * 
	 * <p>
	 * If the {@link Restaurant Restaurant} object passed in through the argument is null, return {@code -1}. 
	 * </p>
	 * 
	 * <p>
	 * <b>Warning:</b> All classes which are serialized must implement the {@link Serializable} interface!
	 * </p>
	 * 
	 * <p>
	 * Exceptions which may occur during data reading include:
	 * <ul>
	 * <li>{@link InvalidClassException}</li>
	 * <li>{@link NotSerializableException}</li>
	 * <li>{@link IOException}</li>
	 * </ul>
	 * <br>
	 * All exceptions have been handled inside the method.
	 * <br>
	 * If there is no exception, {@code 1} will be returned.
	 * Otherwise, {@code -1}.
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
		// If it is a null object, do not serialize the object and return an error code
		if (r == null)
		{
			return -1;
		}
		
		try
		{
			FileOutputStream fileOut = new FileOutputStream (path);
			ObjectOutputStream objectOut = new ObjectOutputStream (fileOut);
			
			// Write the object to the specified ".ser" file
			objectOut.writeObject(r);
			
			// Close the created streams
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
