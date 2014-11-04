package rrpss;

import java.io.Serializable;

public class Restaurant implements Serializable {
	private String name;
	private Menu menu;
	private OrderManager orderManager;
	private OrderInvoiceManager orderInvoiceManager;
	private ReservationManager reservationManager;
	private TableManager tableManager;
	private StaffManager staffManager;
	
	public Restaurant(String name) {
		this.name = name;
		this.menu = new Menu();
		this.orderManager = new OrderManager();
		this.orderInvoiceManager = new OrderInvoiceManager();
		this.reservationManager = new ReservationManager();
		this.tableManager = new TableManager();
		this.staffManager = new StaffManager();
	}
	
	public String getName() {
		return this.name;
	}
	
	public Menu getMenu() {
		return this.menu;
	}
	
	public OrderManager getOrderManager() {
		return this.orderManager;
	}
	
	public OrderInvoiceManager getOrderInvoiceManager() {
		return this.orderInvoiceManager;
	}
	
	public ReservationManager getReservationManager() {
		return this.reservationManager;
	}
	
	public TableManager getTableManager() {
		return this.tableManager;
	}
	
	public StaffManager getStaffManager() {
		return this.staffManager;
	}
}
