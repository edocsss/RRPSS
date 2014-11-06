package rrpss;
import java.util.*;

/**
 * Prints a restaurant revenue report daily or monthly.
 * This class only consists of Static methods which are capable of printing a revenue report.
 * The revenue report generated can either be a daily report or monthly report, based on the given user input.
 * 
 * In order to print a revenue report, the method needs to obtain all order invoices of a {@link Restaurant Restaurant} object
 * through this class method's parameter.
 * 
 * @author Edwin Candinegara
 *
 */

public class RevenueReport
{
	
	/**
	 * Traverses through all the order invoice inside the {@code orderInvoices} vector to look for 
	 * {@link OrderInvoice OrderInvoice} object with the same date or month specified in {@code period}.
	 *
	 * This method differentiates the {@code period}, whether it is in the format of DDMMYYYY or MMYYY, by
	 * comparing the length of the String {@code period} and prints the revenue report accordingly.
	 * 
	 * <p>
	 * For daily report, each {@link OrderInvoice OrderInvoice} object will invoke its 
	 * {@link OrderInvoice#printInvoice() printInvoice()} method and in the end, the total
	 * revenue gained on that particular day will also be printed.
	 * <br>
	 * For monthly report, the top and least sales of the month are printed with its date.
	 * In addition, the total revenue is also printed. The breakdown of each {@link Order Order}
	 * object is not needed.
	 * </p> 
	 * 
	 * <p>
	 * In case no {@link OrderInvoice} object with the correct date is found, this method returns {@code -1}.
	 * If {@code period} is given as an input with the wrong format, this method returns {@code -2}.
	 * </p>
	 * 
	 * @param 	period			A String indicating the period of the revenue report to be printed
	 * @param 	orderInvoices	A Vector containing all order invoices of a {@link Restaurant Restaurant} object
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
			
			// No order invoice has the specified date or month
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
			
			if (monthlyOrderInvoices.size() == 0)
			{
				return -1; // No order invoice has the specified date or month!
			}
			else
			{
				printReportByMonth(monthlyOrderInvoices);
				return 1;
			}
		}
		
		// The given String has the wrong format (not of length 6 or 8)
		else
		{
			return -2; 
		}
	}
	
	/**
	 * Prints all {@link OrderInvoice OrderInvoice} objects inside the Vector object passed in to this method.
	 * Each {@link OrderInvoice OrderInvoice} object prints a breakdown of ordered items and its total price by invoking
	 * {@link OrderInvoice#printInvoice() printInvoice}.
	 * 
	 * <p>
	 * This method also prints the total revenue gained by the restaurant in a day.
	 * </p>
	 * 
	 * @param dailyOrderInvoices	A Vector object consisting of {@link OrderInvoice OrderInvoice} objects with the correct
	 * 								date as checked in {@link #printReport(String, Vector)} 
	 */
	private static void printReportByDay (Vector<OrderInvoice> dailyOrderInvoices)
	{
		double totalDailyRevenue = 0.0;

		System.out.println("                                                DAILY REVENUE REPORT                                                  ");
		System.out.println("=====================================================================================================================");
		
		// Print the breakdown of the items for each invoice -> call printInvoice for each OrderInvoice object
		for (OrderInvoice oI: dailyOrderInvoices) {
			oI.printInvoice();
			totalDailyRevenue += oI.getGrandTotal();
		}

		System.out.println("=====================================================================================================================");
		System.out.println(String.format("%-75s: %-10.2f", "Total daily revenue", totalDailyRevenue));
		System.out.println("=====================================================================================================================");
		System.out.println();
	}
	
	/**
	 * Prints the top and least sales of the month with its date. In addition, the total revenue is also printed. 
	 * The breakdown of each {@link Order Order} object is not needed.
	 *  
	 * @param monthlyOrderInvoices	A Vector object consisting of {@link OrderInvoice OrderInvoice} objects within the specified
	 * 								month as checked in {@link #printReport(String, Vector)} 
	 */
	private static void printReportByMonth (Vector<OrderInvoice> monthlyOrderInvoices)
	{
		//Find the max, min total price and total revenue for the whole month
		double min, max, grandTotal;
		String minDateTime, maxDateTime;
		double totalMonthlyRevenue = 0.0;
		
		min = max = monthlyOrderInvoices.get(0).getTotalPrice();
		minDateTime = maxDateTime = monthlyOrderInvoices.get(0).getOrder().getDateTimeString();
		
		System.out.println("                                               MONTHLY REVENUE REPORT                                                ");
		System.out.println("=====================================================================================================================");
		
		for (OrderInvoice oI: monthlyOrderInvoices)
		{
			grandTotal = oI.getGrandTotal();
			totalMonthlyRevenue += grandTotal;
			if (grandTotal < min)
			{
				min = grandTotal;
				minDateTime = oI.getOrder().getDateTimeString();
			}
			
			if (grandTotal > max)
			{
				max = grandTotal;
				maxDateTime = oI.getOrder().getDateTimeString();
			}
		}
		
		// Print max, min, and totalMonthlyRevenue here
		System.out.println(String.format("%-75s: %-10.2f at %-30s", "Top sale", max, maxDateTime));
		System.out.println(String.format("%-75s: %-10.2f at %-30s", "Least sale", min, minDateTime));
		System.out.println(String.format("%-75s: %-10.2f", "Total monthly revenue", totalMonthlyRevenue));
		System.out.println("=====================================================================================================================");
	}
}
