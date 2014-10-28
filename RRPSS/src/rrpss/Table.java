package rrpss;

public class Table implements Comparable<Table> {
	private int id;
	private boolean availability;
	private int capacity;
	private Order order; // for view order (input: table number)
	
	
	public Table(int id, int capacity) {
		this.id = id;
		this.capacity = capacity;
		this.availability = true;
		// note that order hasn't been created
	}
	public int getId() {
		return id;
	}
	public boolean getAvailability() {
		return availability;
	}
	public int getCapacity() {
		return capacity;
	}
	public Order getOrder() {
		return order;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setOrder(Order o) {
		this.order = o;
	}
	public void setAvailability(boolean availability) {
		this.availability = availability;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	/**
	 * This method is used for sorting the table by capacity at TableManager class
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
