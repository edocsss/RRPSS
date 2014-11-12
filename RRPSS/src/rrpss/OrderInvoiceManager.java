package rrpss;

import java.io.Serializable;
import java.util.Vector;

/**
 * Manages the {@link OrderInvoice} objects in the {@link Restaurant}.
 * 
 * @author Deka Auliya Akbar
 * @see Serializable
 */
public class OrderInvoiceManager implements Serializable  {
	/**
	 * List of {@code OrderInvoice} implemented in {@link Vector} data structure.
	 * Each entry consists of reference to the existing {@link OrderInvoice} object.
	 */
	private Vector <OrderInvoice> orderInvoices;

	/**
	 * Constructs the {@code OrderInvoiceManager} object. At initial,
	 * the {@code orderInvoices} is initialized as an empty list.
	 *  
	 */
	public OrderInvoiceManager() {
		orderInvoices = new Vector<OrderInvoice>();
	}

	/**
	 * Creates an {@link OrderInvoice} object and returns the newly created {@code OrderInvoice} object. Each of the newly created
	 * {@code OrderInvoice} will be appended to the list of order invoices ({@link #orderInvoices}).
	 * 
	 * @param order		 {@link Order} object that {@link Customer} requested
	 * @param membership membership status of the {@code Customer}.
	 * @param tableId	 table identifier to indicate from which table the
	 * 					{@code Order} is made.
	 * 
	 * @return {@code OrderInvoice} object that is just created.
	 */
	public OrderInvoice createOrderInvoice(Order order, boolean membership, int tableId) {
		OrderInvoice oI = new OrderInvoice(order, membership, tableId);
		orderInvoices.add(oI);
		
		return oI;
	}

	/**
	 * Returns the list of {@code OrderInvoice} objects exist in the restaurant
	 * @return {@link #orderInvoices}, the list of order invoices 
	 *
	 */
	public Vector<OrderInvoice> getOrderInvoices() {
		return orderInvoices;
	}
}
