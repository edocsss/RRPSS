package rrpss;

import java.util.HashMap;

public class OrderInvoice
{
	private static final double GST = 0.07;
	private static final double DISCOUNT = 0.1;
	private double totalPrice;
	private Order order;
	private int tableNum;
	private boolean membership;
	private double totalDiscount;
	private double totalGST;
	private double grandTotal;
	
	// Before creating an OrderInvoice object, check whether the table is assigned (if it is not, then wrong tableNo)
	// If the table is assigned, look whether there is an Active reservation having that tableNo
	// If there is, retrieve the customer membership
	// If not, it means the customer is a walk-in -> ask for input whether the customer has a membership
	public OrderInvoice (Order order, boolean membership, int tableNum)
	{
		this.order = order;
		this.totalPrice = 0.0;
		this.membership = membership;
		this.tableNum = tableNum;
		this.grandTotal = 0.0;
		this.totalGST = 0.0;
		this.totalDiscount = 0.0;
		
		this.calculateTotalPrice();
	}
	
	public void calculateTotalPrice ()
	{
		double price;
		int quantity;
		
		HashMap<AlaCarte, Integer> alaCartes = order.getAlaCartes();
		AlaCarte[] ac = alaCartes.keySet().toArray(new AlaCarte[0]); // To iterate through the HashMap
		
		HashMap<SetPackage, Integer> setPackages = order.getSetPackages();
		SetPackage[] sp = setPackages.keySet().toArray(new SetPackage[0]);
		
		// Iterate through the Array to get the Quantity from the HashMap -> add up to the TotalPrice
		for (AlaCarte a: ac)
		{
			price = a.getPrice();
			quantity = alaCartes.get(a);
			
			this.totalPrice += price * quantity;
		}
		
		// Iterate through the Array to get the Quantity from the HashMap -> add up to the TotalPrice
		for (SetPackage s: sp)
		{
			price = s.getPrice();
			quantity = setPackages.get(s);
			
			this.totalPrice += price * quantity;
		}
		this.grandTotal = this.totalPrice;
		if (this.membership) {
			this.totalDiscount = this.grandTotal * DISCOUNT;
			this.grandTotal -= this.totalDiscount;
		}
		
		this.totalGST = this.grandTotal * GST;
		this.grandTotal -= this.totalGST;
		
		
	}
	
	
	public Order getOrder ()
	{
		return this.order;
	}
	
	public double getTotalPrice ()
	{
		return this.totalPrice;
	}
	public double getTotalDiscount() {
		return this.totalDiscount;
	}
	public double getTotalGST() {
		return this.totalGST;
	}
	public double getGrandTotal() {
		return this.grandTotal;
	}
	
	public void printInvoice ()
	{
		//Print the order breakdown & dateTime from Order object
		
		System.out.println(String.format("Order Invoice #%d", order.getId()));
		System.out.println(String.format("Table: %5d Date: %s",
				tableNum, order.getDateTime().toString())); //TODO make it more readable
		System.out.println(String.format("Order breakdown"));
		order.printOrder();
		System.out.println(String.format("%-25s: %5.2f", "Total price", this.totalPrice));
		System.out.println(String.format("%-25s: %5.2f", "Membership discount", this.totalDiscount));
		System.out.println(String.format("%-25s: %5.2f", "GST", this.totalGST));
		System.out.println(String.format("%-25s: %5.2f", "Grand total", this.grandTotal));
	}
	
}
