package rrpss;

import java.io.Serializable;

/**
 * Represents a restaurant in real life. A restaurant would have
 * a restaurant name, menu and able to manage orders {@link OrderManager}, 
 * order invoices {@link OrderInvoiceManager}, reservation
 * {@link ReservationManager}, table {@link TableManager} and staff {@link StaffManager}.
 * 
 * @author Deka Auliya Akbar
 * @see Serializable
 * 
 */


public class Restaurant implements Serializable {
	
	/**
	 * The name of this restaurant
	 */
	private String name;
	
	/**
	 * The menu of this restaurant. {@link Menu} is consist of {@link AlaCarte} (standalone menu item)
	 * and  {@link SetPackage} which is the combination of AlaCarte. {@link Menu} will be able to return
	 * both {@link AlaCarte} and {@link SetPackage} objects, add new menu item (either {@link AlaCarte} or 
	 * {@link SetPackage} ), remove menu item (either {@link Menu} or {@link SetPackage}) and print
	 * all menu items 
	 * 
	 */
	private Menu menu;
	
	/**
	 * Manages orders in the restaurant. {@link OrderManager} is able to create order from the customer
	 * and returns the order object.
	 */
	private OrderManager orderManager;
	
	/**
	 * Manages order invoices in the restaurant. {@link OrderInvoiceManager} will be able to create order 
	 * invoices and return the {@link OrderInvoice} object.
	 */
	private OrderInvoiceManager orderInvoiceManager;
	
	/**
	 * Manages reservations in the restaurant. {@link ReservationManager} will be able to keep track of 
	 * reservations, create reservation, check reservation, check reservation expiry and cancel reservation.
	 */
	private ReservationManager reservationManager;
	
	/**
	 * Manages tables in the restaurant. {@link TableManager} will be able to get the list of tables,
	 * get its individual {@link Table} object, add new table, remove existing table, and allocate table
	 * for the customer.
	 */
	private TableManager tableManager;
	
	/**
	 * Manages the staffs in the restaurant. {@link StaffManager} will be able to get the staff by his/her id,
	 * add new staff and remove existing staff. 
	 */
	private StaffManager staffManager;
	
	/**
	 * Constructs an {@code Restaurant} object, set {@link #name} as the restaurant name and initialize the
	 * these attributes : {@code orderManager}, {@code orderInvoiceManager}, {@code reservationManager},
	 * {@code tableManager}, and {@code staffManager}
	 * @param name is the name of the restaurant
	 */
	public Restaurant(String name) {
		this.name = name;
		this.menu = new Menu();
		this.orderManager = new OrderManager();
		this.orderInvoiceManager = new OrderInvoiceManager();
		this.reservationManager = new ReservationManager();
		this.tableManager = new TableManager();
		this.staffManager = new StaffManager();
	}
	/**
	 * Gets the name of the restaurant
	 * @return the name of the restaurant
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Gets the menu object of the restaurant
	 * @return menu object of the restaurant
	 */
	public Menu getMenu() {
		return this.menu;
	}
	
	/**
	 * Gets the order manager object of the restaurant
	 * @return OrderManager object of the restaurant
	 */
	public OrderManager getOrderManager() {
		return this.orderManager;
	}
	
	/**
	 * Gets the order invoice manager object of the restaurant
	 * @return OrderInvoiceManager object of the restaurant 
	 */
	public OrderInvoiceManager getOrderInvoiceManager() {
		return this.orderInvoiceManager;
	}
	
	/**
	 * Gets the reservation manager object of the restaurant
	 * @return ReservationManager object of the restaurant
	 */
	public ReservationManager getReservationManager() {
		return this.reservationManager;
	}
	
	
	/**
	 * Gets the table manager object of the restaurant
	 * @return TableManager object of the restaurant
	 */
	public TableManager getTableManager() {
		return this.tableManager;
	}
	
	
	/**
	 * Gets the staff manager object of the restaurant
	 * @return StaffManager object of the restaurant
	 */
	public StaffManager getStaffManager() {
		return this.staffManager;
	}
}
