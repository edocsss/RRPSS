package rrpss;

import java.io.Serializable;

/**
 * This class represents a restaurant. A restaurant would have
 * a restaurant name ({@link #name}), menu ({@link Menu}) and able to manage orders ({@link Order}), 
 * order invoices ({@link OrderInvoice}), reservations ({@link Reservation}), tables ({@link Table})and staffs ({@link Staff}).
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
	 * Stores menu items ({@code AlaCarte} and {@code SetPackage}) of this restaurant.
	 * <p>A menu object consists of:
	 * <ul>
	 * <li>{@link AlaCarte}, the standalone menu item</li>
	 * <li>{@link SetPackage}, the combination of AlaCarte items</li>
	 * </ul>
	 * {@link Menu} will be able to return both {@code AlaCarte} and {@code SetPackage} objects,
	 * add new menu item, remove menu item and print all menu items 
	 * 
	 */
	private Menu menu;
	
	/**
	 * Manages orders in the restaurant. {@link OrderManager} is able to create order from the customer
	 * and returns the {@code order} object.
	 */
	private OrderManager orderManager;
	
	/**
	 * Manages order invoices in the restaurant. {@link OrderInvoiceManager} is able to create order 
	 * invoices and return the {@code OrderInvoice} object.
	 */
	private OrderInvoiceManager orderInvoiceManager;
	
	/**
	 * Manages reservations in the restaurant. {@link ReservationManager} is able to keep track of 
	 * the reservations, create reservation, check reservation including the reservation expiry and cancel
	 * reservation.
	 */
	private ReservationManager reservationManager;
	
	/**
	 * Manages tables in the restaurant. {@link TableManager} is able to get the list of tables,
	 * get its individual {@link Table} object, add new table, remove existing table, and allocate table
	 * for the customer.
	 */
	private TableManager tableManager;
	
	/**
	 * Manages the staffs in the restaurant. {@link StaffManager} is able to get the staff by his/her id,
	 * add new staff and remove existing staff. 
	 */
	private StaffManager staffManager;
	
	/**
	 * Constructs a {@code Restaurant} object, sets {@code name} as the restaurant name and initialize these attributes:
	 * <p>
	 * {@code orderManager}, {@code orderInvoiceManager}, {@code reservationManager}, {@code tableManager}, and {@code staffManager}
	 * @param name name of the restaurant
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
	 * Gets the {@code Name} of the restaurant
	 * @return name of the restaurant
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Gets the {@code Menu} object of the restaurant
	 * @return menu object of the restaurant
	 */
	public Menu getMenu() {
		return this.menu;
	}
	
	/**
	 * Gets the {@code OrderManager} object of the restaurant
	 * @return OrderManager object of the restaurant
	 */
	public OrderManager getOrderManager() {
		return this.orderManager;
	}
	
	/**
	 * Gets the {@code OrderInvoiceManager} object of the restaurant
	 * @return OrderInvoiceManager object of the restaurant 
	 */
	public OrderInvoiceManager getOrderInvoiceManager() {
		return this.orderInvoiceManager;
	}
	
	/**
	 * Gets the {@code ReservationManager} object of the restaurant
	 * @return ReservationManager object of the restaurant
	 */
	public ReservationManager getReservationManager() {
		return this.reservationManager;
	}
	
	
	/**
	 * Gets the {@code TableManager} object of the restaurant
	 * @return TableManager object of the restaurant
	 */
	public TableManager getTableManager() {
		return this.tableManager;
	}
	
	
	/**
	 * Gets the {@code StaffManager} object of the restaurant
	 * @return StaffManager object of the restaurant
	 */
	public StaffManager getStaffManager() {
		return this.staffManager;
	}
}
