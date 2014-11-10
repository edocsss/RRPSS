package rrpss;

import java.io.Serializable;
import java.util.Vector;

/**
 * Manages the {@link OrderInvoice} of the in the {@link Restaurant}
 * 
 * @author Deka Auliya Akbar
 * @see Serializable
 */
public class OrderInvoiceManager implements Serializable  {
	/**
	 * List of Order Invoices, implemented in {@link Vector} data structure.
	 * Each entry consists of reference to existing {@link OrderInvoice} object.
	 */
	private Vector <OrderInvoice> orderInvoices;

	/**
	 * Construct the Order Invoice Manager initialized with empty list of order invoice.
	 *  
	 */
	public OrderInvoiceManager() {
		orderInvoices = new Vector<OrderInvoice>();
	}

	/**
	 * This method will evoke the creation of {@link OrderInvoice}. Each of the newly created
	 *  {@link OrderInvoice} will be appended to the list of order invoices {@link #orderInvoices}
	 * 
	 * @param order		 is the {@link Order} object that {@link Customer} requested
	 * @param membership specify whether the {@link Customer} is a member of the {@link Restaurant}
	 * @param tableId	 is the table identifier (table number) corresponds to which table the
	 * 					{@link Order} is requested.
	 * 
	 * @return Returns the id of this table (table number).
	 */
	public OrderInvoice createOrderInvoice(Order order, boolean membership, int tableId) {
		OrderInvoice oI = new OrderInvoice(order, membership, tableId);
		orderInvoices.add(oI);
		
		// This returned object will be used to call printInvoice() -> directly from the RestaurantApp
		return oI;
	}

	/**
	 * This method will return the list of {@link OrderInvoice} objects
	 * @return orderInvoices which are the list of order invoices exist in the restaurant
	 *
	 */
	public Vector<OrderInvoice> getOrderInvoices() {
		return orderInvoices;
	}
}
