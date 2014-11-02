package rrpss;

import java.util.Calendar;
import java.util.Vector;

public class OrderManager 
{
	private Vector<Order> orders;
	private int idCounter;
	
	public OrderManager ()
	{
		this.orders = new Vector<Order> ();
		this.idCounter = 0;
	}
	
	//Get customer from reservation -> get the tableID, compare with the tableID of each reservation objects
	public Order createOrder (Calendar dateTime, Staff staff)
	{
		int id = this.idCounter + 1;
		Order o = new Order (id, dateTime, staff);
		this.idCounter++;
		
		return o; // Set this Object to Table.order
	}
	
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