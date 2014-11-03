package rrpss;
import java.util.*;

public class RevenueReport
{
	public static int printReport (String period, Vector<OrderInvoice> orderInvoices)
	{
		// Daily
		if (period.length() == 8)
		{
			Vector<OrderInvoice> dailyOrderInvoices = new Vector<OrderInvoice>();
			for (OrderInvoice o: orderInvoices)
			{
				Calendar cal = o.getOrder().getDateTime();
				
				// Check the date of each order from each Order Invoice object
				if (cal.get(Calendar.DAY_OF_MONTH) == (Integer.parseInt(period) / 1000000)
						&& cal.get(Calendar.MONTH) + 1 == ((Integer.parseInt(period) % 1000000) / 10000)
						&& cal.get(Calendar.YEAR) == (Integer.parseInt(period) % 10000))
				{
					dailyOrderInvoices.add(o);
				}
			}
			
			if (dailyOrderInvoices.size() == 0) 
			{
				return -1; //No order invoice has the specified date or month!
			}
			else
			{
				printReportByDay(dailyOrderInvoices);
				return 1;
			}
		}
		
		// Monthly
		else if (period.length() == 6)
		{
			Vector<OrderInvoice> monthlyOrderInvoices = new Vector<OrderInvoice>();
			for (OrderInvoice o: orderInvoices)
			{
				Calendar cal = o.getOrder().getDateTime();
				if (cal.get(Calendar.MONTH) + 1 == (Integer.parseInt(period) / 10000)
						&& cal.get(Calendar.YEAR) == (Integer.parseInt(period) % 10000))
				{
					monthlyOrderInvoices.add(o);
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
		else
		{
			return -2; // IF the String given is wrong (not of length 6 or 8)
		}
	}
	
	
	/*
	For by DAY:
	User need enter the date ie DDMMYYYY

	Report will display :
	(1) Each sale invoice (with breakdown of ordered items and total) for the entered date with invoice number (receipt number).
	[similar to function (9) display but multiple order invoices ]
	(2) overall total revenue for the entered date. 
	For Demo, you need to show this function with at least 3 sale invoices created on same day in the past.
	 */
	private static void printReportByDay (Vector<OrderInvoice> dailyOrderInvoices)
	{
		double totalDailyRevenue = 0.0;

		System.out.println("                         DAILY REVENUE REPORT                                   ");
		System.out.println("================================================================================");
		
		// Print the breakdown of the items for each invoice -> call printInvoice for each OrderInvoice object
		for (OrderInvoice oI: dailyOrderInvoices) {
			oI.printInvoice();
			totalDailyRevenue += oI.getGrandTotal();
		}

		System.out.println("================================================================================");
		System.out.println(String.format("%-43s: %-5.2f", "Total daily revenue", totalDailyRevenue));
		System.out.println("================================================================================");
		System.out.println();
	}
	
	/*
	For by MONTH:
	User need enter the month and year ie MMYYYY.

	Report will display :
	(1)  Top sale of the month in DD/MM/YYYY (which day in that month has highest sales) and the total revenue of that day
	(2)  Least sale of the month in DD/MM/YYYY (which day in that month has lowest sales) and the total revenue of that day
	(3)  overall total revenue for the entered month. 
	[ no breakdown of sale invoices is required, just the sale figures ]

	For Demo, you need to show this function with at least 2 sale invoices for each day for 3 different days of the month ( to differentiate highest and lowest sales).
	 */
	private static void printReportByMonth (Vector<OrderInvoice> monthlyOrderInvoices)
	{
		//Find the max, min total price and total revenue for the whole month
		double min, max, grandTotal;
		String minDateTime, maxDateTime;
		double totalMonthlyRevenue = 0.0;
		
		min = max = monthlyOrderInvoices.get(0).getTotalPrice();
		minDateTime = maxDateTime = monthlyOrderInvoices.get(0).getOrder().getDateTimeString();
		
		System.out.println("                        MONTHLY REVENUE REPORT                                  ");
		System.out.println("================================================================================");
		
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
		System.out.println(String.format("%-43s: %-5.2f at %-22s", "Top sale", max, maxDateTime));
		System.out.println(String.format("%-43s: %-5.2f at %-22s", "Least sale", min, minDateTime));
		System.out.println(String.format("%-43s: %-5.2f", "Total monthly revenue", totalMonthlyRevenue));
		System.out.println("================================================================================");
	}
}
