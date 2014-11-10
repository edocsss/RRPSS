package rrpss;

import java.io.Serializable;
import java.util.HashMap;
/**
 * Stores information about the order invoice in the {@link Restaurant}
 * This class will keep track of the purchase ({@link Order}) made by the {@link Customer},
 * calculate the final price ({@link #grandTotal} that {@link Customer} has to pay, and print
 * the order invoice (consist of {@link Order} details and price breakdown).
 * 
 * Before finding the {@link #grandTotal}, {@link OrderInvoice}will first compute
 * the {@link #totalPrice}, which is the sum up the price of all items that Customer 
 * purchased (both {@link AlaCarte} and {@link SetPackage} are considered)
 * multiplied with its ordered quantity. At this stage, {@link #grandTotal} will be
 * made equal to {@link #totalPrice}.
 * 
 * Afterwards, {@link OrderInvoice} will check whether {@link Customer} is a 
 * member of the {@link Restaurant}.If yes, then the {@link Customer} will receive 10%
 * {@link #DISCOUNT} from the original {@link #grandTotal} and {@link #totalDiscount}
 * would be subtracted from the {@link #grandTotal}.
 * 
 * The final price, {@link #grandTotal} is then calculated by adding the {@link #totalGST} to 
 * {@link #totalPrice}. The {@link #GST} is obtained by multiplying 7% to {@link #grandTotal}
 *   
 * @author Deka Auliya Akbar
 * @see Serializable
 */
public class OrderInvoice implements Serializable 
{
	/**
	 * Is the tax rate for Goods and Services Tax. Here, the GST is 7% of the total purchase price
	 * {@link #grandTotal}. The {@link #totalGST} will be added to the {@link #grandTotal}.
	 * And the result is the amount of price a {@link Customer} has to pay. GST rate is constant, hence it is final.
	 */
	private static final double GST = 0.07;
	
	/**
	 * Is the discount rate for members in the restaurant. Here, the Discount rate is 10% of the
	 * total purchase price {@link #grandTotal}. The {@link #totalDiscount} will be subtracted
	 * from the {@link #grandTotal} for all the members in the {@link Restaurant}.
	 * Discount rate is fixed, therefore it is final.
	 *  
	 */
	private static final double DISCOUNT = 0.1;
	
	/**
	 * Is the sum of all orders, which include both the price of {@link AlaCarte} and {@link SetPackage}
	 * multiplied with corresponding requested quantities.
	 */
	private double totalPrice;
	
	/**
	 * Is the order object that customer has requested. An order will contains menu item that a customer
	 * purchased  ( {@link AlaCarte} and {@link SetPackage} ), date and time of the {@link Order} object
	 * creation, the {@link Staff} object who created the {@link Order}. This object also has print 
	 * method to print out the {@link Order} details.
	 * 
	 */
	private Order order;

	/**
	 * Is the table identifier, to indicate from which {@link Table} the {@link Order} is made.
	 * 
	 */
	private int tableId;

	/**
	 * Specify whether a customer who ordered is a member of the {@link Restaurant}.
	 * 
	 */
	private boolean membership;

	/** 
	 * {@link #totalDiscount} is the discounted amount of price to be subtracted from
	 * {@link #grandTotal}. It is calculated by multiplying {@link #DISCOUNT}
	 * with {@link #grandTotal}.
	 * 
	 * This totalDiscount will only be computed when the {@link Customer} is a member of the 
	 * {@link Restaurant}
	 */
	private double totalDiscount;

	/**
	 * {@link #totalGST} is the amount of tax to be added to the {@link #grandTotal}.
	 * It is calculated by multiplying {@link #GST} with {@link #grandTotal}.
	 */
	private double totalGST;

	/**
	 * {@link #grandTotal} is the final amount of price that a {@link Customer} hsa to pay.
	 * This final price is calculated by adding the {@link #totalGST} to {@link #grandTotal}.
	 */
	private double grandTotal;
	
	// Before creating an OrderInvoice object, check whether the table is assigned (if it is not, then wrong tableNo)
	// If the table is assigned, look whether there is an Active reservation having that tableNo
	// If there is, retrieve the customer membership
	// If not, it means the customer is a walk-in -> ask for input whether the customer has a membership
	/**
	 * Construct an {@link OrderInvoice} object.  This constructor will initialize
	 * the attributes of {@link OrderInvoice}.
	 * 
	 * @param order 		is the {@link Order} objects, and assigned to {@link #order} at initialization 
	 * @param membership 	is the status of customer membership in the restaurant
	 * The membership status of a {@link Customer} could only either true or false, and assigned to {@link #membership} at initialization
	 * @param tableId 		is the id of the {@link Table} object and assigned to {@link #tableId} at initialization
	 * Each table has unique id to differentiate between different tables. This id is very similar to table number in real world.
	 * {@link #totalPrice}, {@link #grandTotal}, {@link #totalGST} and {@link #totalDiscount} are initialized to 0
	 * This constructor will call {@link #calculateTotalPrice()} to compute the total price that a customer must pay
	 * after GST and membership discount
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
	 * This method will calculate the {@link #totalPrice}, {@link #totalDiscount}, {@link #totalGST}
	 * and {@link #grandTotal}.
	 * 
	 * It will iterate through all the menu item exist in the {@link Order} object, which is 
	 * stored in HashMap data structures, which exist for both {@link AlaCarte} and {@link SetPackage}. 
	 * For each item, the price will be multiplied with the requested quantity and the result is added up
	 * to the {@link #totalPrice}. 
	 * 
	 * {@link #grandTotal} is computed by subtracting {@link #totalDiscount} if {@link Customer} is a 
	 * member of the {@link Restaurant} and adding the {@link #totalGST}.
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
	 * Returns {@link Order} object from the {@link Customer}.
	 * @return	{@link Order} object requested by the {@link Customer}.
	 */
	public Order getOrder ()
	{
		return this.order;
	}

	
	/**
	 * Returns the {@link #totalPrice} of all items purchased/ordered.
	 * @return the {@link #totalPrice} of the purchase.
	 */
	public double getTotalPrice ()
	{
		return this.totalPrice;
	}

	/**
	 * Returns the {@link #totalDiscount}, the value is 0 if the {@link Customer}
	 * is not a member of the {@link Restaurant} 
	 * @return the total discount.
	 */
	public double getTotalDiscount() {
		return this.totalDiscount;
	}

	/**
	 * Returns the {@link #totalGST} of the purchase.
	 * @return the total tax
	 */
	public double getTotalGST() {
		return this.totalGST;
	}
	
	/**
	 * Returns the {@link #grandTotal}, which is the final amount of price 
	 * {@link Customer} has to pay.
	 * @return {@link #grandTotal} of an {@link Order}.
	 */
	public double getGrandTotal() {
		return this.grandTotal;
	}

	/**
	 * This method will print the Order details, which breakdown of each menu item and the quantity
	 * that {@link Customer} purchase, the date and time when the {@link Order}is created,
	 * and also display the price breakdown, from the {@link #totalPrice}, membership {@link #totalDiscount},
	 * {@link #totalGST}, and the final amount, {@link #grandTotal}.
	 * 
	 */
	public void printInvoice ()
	{
		//Print the order breakdown & dateTime from Order object
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
