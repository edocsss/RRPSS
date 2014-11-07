package rrpss;

import java.io.Serializable;

/**
 * Stores information about a single table, particularly used in context of a restaurant.
 * This class stores the table's number as id, its availability, its capacity, and its current {@link Order}
 * 
 * @author Kenrick
 * @see	 	Serializable
 * @see	 	Comparable
 * 
 */

public class Table implements Comparable<Table>, Serializable {
	/**
	 * The table identifier (id), idiom for table number in real world.
	 */
	private int id;
	
	/**
	 * True if table is available (no active {@link Order} from the table or no active {@link Reservation} for this table)
	 */
	private boolean availability;
	
	/**
	 * Capacity of the table, only even number ranges from 2 until 10.
	 */
	private int capacity;
	
	/**
	 * The {@link Order} associated with this table. Used at {@link RestaurantApp} for viewing the order.
	 */
	private Order order;
	
	/**
	 * Constructs a table with specified id and capacity.
	 * @param id
	 * @param capacity
	 */
	public Table(int id, int capacity) {
		this.id = id;
		this.capacity = capacity;
		this.availability = true;
		// note that order hasn't been created
	}
	
	/**
	 * Returns the id of this table (table number). Will always be a positive integer.
	 * @return Returns the id of this table (table number).
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Returns the availability of this table.
	 * @return availability of this table.
	 */
	public boolean getAvailability() {
		return availability;
	}
	
	/**
	 * Returns the capacity of this table which will be a positive integer from 2 to 10.
	 * @return capacity of this table.
	 */
	public int getCapacity() {
		return capacity;
	}
	
	/**
	 * Returns an {@link Order} object that is currently associated with this table.
	 * @return
	 */
	public Order getOrder() {
		return order;
	}
	
	/**
	 * Sets this table's id (table number). Should be unique across other tables.
	 * @param id	Positive integer representing the table number.
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Associates this table to a particular {@link Order}.
	 * @param o		An active {@link Order}.
	 */
	public void setOrder(Order o) {
		this.order = o;
	}
	
	/**
	 * Sets this table's availability
	 * @param availability	true or false.
	 */
	public void setAvailability(boolean availability) {
		this.availability = availability;
	}
	
	/**
	 * Sets this table's capacity. Should be a positive even integer between 2 and 10, inclusively.
	 * @param capacity	Positive even integer between 2 and 10, inclusively.
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	/**
	 * Compare to other {@link Table} object by capacity.
	 * Used for sorting the table by capacity at {@link TableManager}.
	 */
	@Override
	public int compareTo(Table table) {
		// TODO Auto-generated method stub
		if (this.capacity > table.capacity) {
			return 1;
		} else if (this.capacity == table.capacity) {
			return 0;
		} else {
			return -1;
		}
	}
}
