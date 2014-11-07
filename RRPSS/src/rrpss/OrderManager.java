package rrpss;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Vector;

/**
 * Stores all {@link Order} objects of the whole restaurants and provides ways to
 * creating new {@link Order} and getting existing {@link Order} object.
 * 
 * <p>
 * Basically, this class is the "manager" of a {@link Restaurant} whose main job
 * is to manage all things related to {@link Order} creation.
 * </p>
 * 
 * <p>
 * This class implements the {@link Serializable} interface as this object may be saved
 * into a ".ser" file during the application execution.
 * </p>
 * 
 * @author 	Edwin Candinegara
 * @see	 	Serializable
 *
 */

public class OrderManager implements Serializable 
{
	/**
	 * A {@link Vector} object which stores all {@link Order} objects of a {@link Restaurant}
	 */
	private Vector<Order> orders;
	
	/**
	 * An integer keeping track of the {@link Order} object ID in order to avoid having an {@link Order} object
	 * with the same ID as an existing {@link Order} object. 
	 * This integer will be used when a new {@link Order} object is created which is done by calling 
	 * {@link #createOrder(Calendar, Staff)}.
	 * 
	 * <p>
	 * Initially, the value of this integer is {@code 1}.
	 * </p>
	 */
	private int idCounter;
	
	/**
	 * Constructs an {@code OrderManager} object and initially it has no {@link Order} object at all.
	 */
	public OrderManager ()
	{
		this.orders = new Vector<Order> ();
		this.idCounter = 1;
	}
	
	/**
	 * Creates a new {@link Order} object with no menu items at all initially. Inside this method,
	 * the {@link #idCounter} is assigned to the new {@link Order} object's ID attribute
	 * and {@link #idCounter} is then incremented. 
	 * 
	 * <p>
	 * Return the created {@link Order} object to the caller.
	 * </p>
	 * 
	 * @param 	dateTime	A {@link Calendar} object indicating the date and time when an 
	 * 						{@link Order} object is created
	 * @param 	staff		A {@link Staff} object indicating which {@code Staff} creates the {@link Order}
	 * 						object
	 * @return	An {@link Order} object storing the {@code dateTime} and {@code staff} argument.
	 */
	public Order createOrder (Calendar dateTime, Staff staff)
	{
		int id = this.idCounter++;
		Order o = new Order (id, dateTime, staff);
		
		// Return the Order object so that it can be stored into the allocated table attribute in RestaurantApp
		return o; 
	}
	
	/**
	 * Returns the {@link Order} object with the specified ID attribute. If no such {@link Order} object
	 * is found, return {@code null}.
	 * 
	 * @param 	orderId			The ID of the {@link Order} object (should be an ID of an existing {@link AlaCarte}
	 * 							object)
	 * @return	The {@link Order} object which has the same ID as {@code orderId}
	 */
	public Order getOrderById (int orderId)
	{
		for (Order o: this.orders)
		{
			if (o.getId() == orderId)
			{
				return o;
			}
		}
		
		return null;
	}
	
}