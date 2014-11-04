package rrpss;

import java.io.Serializable;
import java.util.Vector;

public class OrderInvoiceManager implements Serializable  {
	private Vector <OrderInvoice> orderInvoices;
	
	public OrderInvoiceManager() {
		orderInvoices = new Vector<OrderInvoice>();
	}
	
	public OrderInvoice createOrderInvoice(Order order, boolean membership, int tableId) {
		OrderInvoice oI = new OrderInvoice(order, membership, tableId);
		orderInvoices.add(oI);
		
		// This returned object will be used to call printInvoice() -> directly from the RestaurantApp
		return oI;
	}

	public Vector<OrderInvoice> getOrderInvoices() {
		return orderInvoices;
	}
}
