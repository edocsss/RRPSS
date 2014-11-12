package rrpss;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Stores information of all order invoices in the restaurant.
 * <p>
 * This class will keep track of the purchases ({@link Order}) made by the customer,
 * calculate the final price ({@link #grandTotal}), and print the order invoice which consist
 * of {@code Order} details and price breakdown.
 *
 * <p>
 * The price is broken down into:
 * <ul>
 * 	<li>{@code TotalPrice}: The price for all items that customer has to pay. This price is the sum of price x quantity
 * of each item being ordered. 
 * 	<li>{@code TotalGST}: Goods and service tax of 7%
 * 	<li>{@code TotalDiscount}: 10% discount entitled to members of the restaurant.
 * 	<li>{@code GrandTotal}: The amount that customer will pay. This final price take accounts the GST
 * and membership discount.
 * </ul>
 *  
 * @author Deka Auliya Akbar
 * @see Serializable
 */
public class OrderInvoice implements Serializable 
{
	/**
	 * Constant that represents the tax rate for Goods and Services Tax. The rate of this GST is 7%.
	 * The {@link #totalGST} will be added to the {@link #grandTotal}.
	 */
	private static final double GST = 0.07;
	
	/**
	 * Constant that represents the discount rate entitled for the members in the restaurant. The discount rate is 10%.
	 * The {@link #totalDiscount} will be subtracted from the {@link #grandTotal} if a customer is a member in
	 * the {@code Restaurant}. 
	 */
	private static final double DISCOUNT = 0.1;
	
	/**
	 * Stores the gross price of an order. This price is the sum of the costs of all items in exist the order.
	 * The cost of each item is determined by multiplying the price of individual item with its requested quantities.
	 */
	private double totalPrice;
	
	/**
	 * Stores the {@link Order} object that customer has requested. This order will contain menu items that
	 * the customer purchased  ({@link AlaCarte} and {@link SetPackage}) and its requested quantities, date and time of the
	 * {@code Order} creation, and the staff who created the {@code Order}. This object also has a print method to print out its
	 * details. 
	 */
	private Order order;

	/**
	 * Stores the table number to indicate from which {@link Table} an {@code Order} is made.
	 */
	private int tableId;

	/**
	 * Stores the membership status of the {@code Customer}.
	 * 
	 */
	private boolean membership;

	/** 
	 * Stores the discounted amount of price to be subtracted from
	 * {@link #grandTotal}. It is calculated by multiplying {@link #DISCOUNT}
	 * with the {@code grandTotal}.
	 * 
	 * <p>This {@code totalDiscount} will only be computed if the {@code Customer} is a member of the 
	 * {@link Restaurant}, otherwise the value is 0 (no discount).
	 * </p>
	 */
	private double totalDiscount;

	/**
	 * Stores the amount of tax to be added to the {@link #grandTotal}.
	 * It is calculated by multiplying {@link #GST} with {@link #grandTotal}.
	 */
	private double totalGST;
	
	/**
	 * Stores the net price that a {@code Customer} has to pay.
	 * This net price have already subtracted the discount {@code totalDiscount} and added with the GST {@code totalGST}.
	 */
	private double grandTotal;
	
	/**
	 * Constructs an {@link OrderInvoice} object.  This constructor will initialize
	 * the attributes of {@code OrderInvoice}.
	 * 
	 * @param order 		is the {@code Order} objects, and assigned to {@link #order} at initialization 
	 * @param membership 	is the membership status of a customer in the restaurant and assigned to
	 * 						{@link #membership}
	 * @param tableId 		is the identifier of the {@link Table} object and assigned to {@link #tableId}
	 * 
	 * <p>{@link #totalPrice}, {@link #grandTotal}, {@link #totalGST} and {@link #totalDiscount} are initialized to 0.
	 * This constructor will evoke {@link #calculateTotalPrice()} to compute the cost that a customer has to pay.
	 * </p>
	 *
	 * 
	 */
	public OrderInvoice (Order order, boolean membership, int tableId)
	{
		this.order = order;
		this.totalPrice = 0.0;
		this.membership = membership;
		this.tableId = tableId;
		this.grandTotal = 0.0;
		this.totalGST = 0.0;
		this.totalDiscount = 0.0;
		
		this.calculateTotalPrice();
	}

	/**
	 * Calculates the {@link #totalPrice}, {@link #totalDiscount}, {@link #totalGST}
	 * and {@link #grandTotal}.
	 * 
	 * <p>This method iterates through every menu item exists in the {@link Order} object, which are 
	 * stored in {@code HashMap} data structures. For each item, the price will be multiplied with the requested quantity
	 * and the result is added up to the gross price ({@code totalPrice}). 
	 * <p>The net price ({@code grandTotal}) is computed by subtracting {@code totalDiscount} and adding the {@code totalGST}.
	 *
	 */
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
		this.grandTotal += this.totalGST;
		
		
	}
	
	/**
	 * Returns {@link Order} object requested by the {@code Cusomer}.
	 * @return	{@link Order} object made by the customer.
	 */
	public Order getOrder ()
	{
		return this.order;
	}

	
	/**
	 * Returns the total price (gross price) of all items purchased/ordered.
	 * @return the {@code totalPrice} of the purchase.
	 */
	public double getTotalPrice ()
	{
		return this.totalPrice;
	}

	/**
	 * Returns the discounted amount, the value is 0 if the {@link Customer} is not a member. 
	 * @return the total discount.
	 */
	public double getTotalDiscount() {
		return this.totalDiscount;
	}

	/**
	 * Returns the GST of the purchase.
	 * @return the total tax
	 */
	public double getTotalGST() {
		return this.totalGST;
	}
	
	/**
	 * Returns the net price, which is the final amount of price the {@link Customer} has to pay.
	 * @return {@code grandTotal} of an {@code Order} object.
	 */
	public double getGrandTotal() {
		return this.grandTotal;
	}

	/**
	 * Prints the {@link Order} details. This method prints every menu item and the respective quantity that the {@code Customer}
	 * has ordered, the date and time when the {@code Order} is created, and display the price breakdown, which consists of 
	 * the gross price ({@link #totalPrice}), membership discount ({@link #totalDiscount}),
	 * tax({@link #totalGST}), and the net price ({@link #grandTotal}).
	 * 
	 */
	public void printInvoice ()
	{
		System.out.println("=====================================================================================================================");
		System.out.println(String.format("                                                  ORDER INVOICE #%-5d                                                 ", order.getId()));
		System.out.println(String.format("                                                      TABLE #%-5d                                                     ", tableId));
		
		order.printOrder();
		System.out.println(String.format("%-75s: %-10.2f", "Total price", this.totalPrice));
		System.out.println(String.format("%-75s: %-10.2f", "Membership discount", this.totalDiscount));
		System.out.println(String.format("%-75s: %-10.2f", "GST", this.totalGST));
		System.out.println(String.format("%-75s: %-10.2f", "Grand total", this.grandTotal));
		System.out.println("=====================================================================================================================");
		System.out.println();
	}
}
