package rrpss;

import java.io.*;

public final class Database
{
	private Database () {}
	
	// Path is the place where the .ser file is stored
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
	
	public static int writeRestaurantObject (Restaurant r)
	{
		try
		{
			FileOutputStream fileOut = new FileOutputStream ("data.ser");
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
