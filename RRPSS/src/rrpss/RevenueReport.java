package rrpss;
import java.util.*;

/**
 * Prints a restaurant revenue report daily or monthly.
 * This class only consists of Static methods which are capable of printing a revenue report.
 * The revenue report generated can either be a daily report or monthly report, based on the given user input.
 * 
 * In order to print a revenue report, the method needs to obtain all order invoices of a {@link Restaurant} object
 * through this class method's parameter.
 * 
 * @author Edwin Candinegara
 *
 */

public class RevenueReport
{
	/**
	 * Constructs nothing as this class does not have to be instantiated to print the revenue report of a restaurant.
	 * Only {@link OrderInvoice} objects need to be passed in to {@link #printReport(String, Vector)} in order to print 
	 * the revenue report either daily or monthly.
	 */
	public RevenueReport () {}
	
	/**
	 * Traverses through all the order invoice inside the argument {@code orderInvoices} to look for 
	 * {@link OrderInvoice} object with the same date or month specified in {@code period}.
	 *
	 * This method differentiates the {@code period}, whether it is in the format of DDMMYYYY or MMYYY, by
	 * comparing the length of the String {@code period} and prints the revenue report accordingly.
	 * 
	 * <p>
	 * After determining whether it is a daily or monthly report, this method traverses through all 
	 * {@link OrderInvoice} object and stores all {@link OrderInvoice} object
	 * with the same date or month into another temporary vector object which will be then passed into
	 * {@link #printReportByDay(Vector)} or {@link #printReportByMonth(Vector)}.
	 * </p>
	 * 
	 * <p>
	 * In the case of daily report, {@link #printReportByDay(Vector)} will be called
	 * while {@link #printReportByMonth(Vector)} will be called for monthly report.
	 * </p>
	 * 
	 * <p>
	 * In case no {@link OrderInvoice} object with the correct date is found, this method returns {@code -1}.
	 * If {@code period} is given has the wrong format, this method returns {@code -2}.
	 * </p>
	 * 
	 * @param 	period			A String indicating the period of the revenue report to be printed (must follow the format
	 * 							DDMMYYYY or MMYYYY for daily and monthly respectively)
	 * @param 	orderInvoices	A Vector containing all order invoices of a {@link Restaurant} object
	 * @return					{@code 1} if the revenue report is successfully printed, {@code -1} if there is no order 
	 * 							invoice with the specified date or month, and {@code -2} if the given period String input 
	 * 							is in the wrong format.
	 */
	
	public static int printReport (String period, Vector<OrderInvoice> orderInvoices)
	{
		// Daily revenue report
		if (period.length() == 8)
		{
			// This Vector stores all order invoices with the targeted date
			Vector<OrderInvoice> dailyOrderInvoices = new Vector<OrderInvoice>();
			
			// Traverse through all order invoices
			for (OrderInvoice o: orderInvoices)
			{
				// Get the date of an OrderInvoice object
				Calendar cal = o.getOrder().getDateTime();
				
				// In case an exception occurs because the String "period" contains a non digit character
				try 
				{
					// Check the date of each order from each Order Invoice object
					if (cal.get(Calendar.DAY_OF_MONTH) == (Integer.parseInt(period) / 1000000)
						&& cal.get(Calendar.MONTH) + 1 == ((Integer.parseInt(period) % 1000000) / 10000)
						&& cal.get(Calendar.YEAR) == (Integer.parseInt(period) % 10000))
					{
						dailyOrderInvoices.add(o);
					}
				}
				catch (Exception e)
				{
					return -2;
				}
			}
			
			// No order invoice has the specified date
			if (dailyOrderInvoices.size() == 0) 
			{
				return -1;
			}
			else
			{
				printReportByDay(dailyOrderInvoices);
				return 1;
			}
		}
		
		// Monthly revenue report
		else if (period.length() == 6)
		{
			// This Vector stores all order invoices within the targeted month
			Vector<OrderInvoice> monthlyOrderInvoices = new Vector<OrderInvoice>();
			
			// Traverse through all order invoices
			for (OrderInvoice o: orderInvoices)
			{
				// Get the date of an OrderInvoice object
				Calendar cal = o.getOrder().getDateTime();
				
				// In case an exception occurs because the String "period" contains a non digit character
				try
				{
					// Check the date of each order from each Order Invoice object
					if (cal.get(Calendar.MONTH) + 1 == (Integer.parseInt(period) / 10000)
							&& cal.get(Calendar.YEAR) == (Integer.parseInt(period) % 10000))
					{
						monthlyOrderInvoices.add(o);
					}
				}
				catch (Exception e)
				{
					return -2;
				}
			}
			
			// No order invoice has the specified month
			if (monthlyOrderInvoices.size() == 0)
			{
				return -1;
			}
			else
			{
				printReportByMonth(monthlyOrderInvoices);
				return 1;
			}
		}
		
		// The given String is in the wrong format (not of length 6 or 8)
		else
		{
			return -2; 
		}
	}
	
	/**
	 * Prints all {@link OrderInvoice} objects inside the Vector object passed in to this method.
	 * Each {@link OrderInvoice} object prints a breakdown of ordered items and its total price by invoking
	 * {@link OrderInvoice#printInvoice()}.
	 * 
	 * <p>
	 * This method also prints the total revenue gained by the restaurant in a day.
	 * </p>
	 * 
	 * @param dailyOrderInvoices	A Vector object consisting of {@link OrderInvoice} objects with the correct
	 * 								date as checked in {@link #printReport(String, Vector)} 
	 * 
	 * @see #printReport(String, Vector)
	 */
	private static void printReportByDay (Vector<OrderInvoice> dailyOrderInvoices)
	{
		// Variable initialization
		double totalDailyRevenue = 0.0;

		System.out.println("                                                DAILY REVENUE REPORT                                                  ");
		System.out.println("=====================================================================================================================");
		
		// Print the breakdown of the items for each invoice by calling printInvoice
		for (OrderInvoice oI: dailyOrderInvoices) {
			oI.printInvoice();
			totalDailyRevenue += oI.getGrandTotal();
		}

		// Print total daily revenue
		System.out.println("=====================================================================================================================");
		System.out.println(String.format("%-75s: %-10.2f", "Total daily revenue", totalDailyRevenue));
		System.out.println("=====================================================================================================================");
		System.out.println();
	}
	
	/**
	 * Prints the top and least sales of the month with its date. In addition, the total revenue is also printed. 
	 * The breakdown of each {@link Order Order} object is not needed.
	 *  
	 * @param monthlyOrderInvoices	A Vector object consisting of {@link OrderInvoice} objects within the specified
	 * 								month as checked in {@link #printReport(String, Vector)}
	 * 
	 * @see #printReport(String, Vector)
	 */
	private static void printReportByMonth (Vector<OrderInvoice> monthlyOrderInvoices)
	{
		double min, max, grandTotal;
		String minDateTime, maxDateTime;
		double totalMonthlyRevenue = 0.0;
		
		// Variables initialization
		min = max = monthlyOrderInvoices.get(0).getTotalPrice();
		minDateTime = maxDateTime = monthlyOrderInvoices.get(0).getOrder().getDateTimeString();
		
		System.out.println("                                               MONTHLY REVENUE REPORT                                                ");
		System.out.println("=====================================================================================================================");
		
		for (OrderInvoice oI: monthlyOrderInvoices)
		{
			// Accumulate the total price for each invoice
			grandTotal = oI.getGrandTotal();
			totalMonthlyRevenue += grandTotal;
			
			// Check for minimum sales
			if (grandTotal < min)
			{
				min = grandTotal;
				minDateTime = oI.getOrder().getDateTimeString();
			}
			
			// Check for maximum sales
			if (grandTotal > max)
			{
				max = grandTotal;
				maxDateTime = oI.getOrder().getDateTimeString();
			}
		}
		
		// Print the maximum, minimum, and total monthly revenue
		System.out.println(String.format("%-75s: %-10.2f at %-30s", "Top sale", max, maxDateTime));
		System.out.println(String.format("%-75s: %-10.2f at %-30s", "Least sale", min, minDateTime));
		System.out.println(String.format("%-75s: %-10.2f", "Total monthly revenue", totalMonthlyRevenue));
		System.out.println("=====================================================================================================================");
	}
}
