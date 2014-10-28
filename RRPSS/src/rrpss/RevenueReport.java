package rrpss;
import java.util.*;

public class RevenueReport
{
	public static void printReport (String period, Vector<OrderInvoice> orderInvoices)
	{
		// Daily
		if (period.length() == 8)
		{
			Vector<OrderInvoice> dailyOrderInvoices = new Vector<OrderInvoice>();
			for (OrderInvoice o: orderInvoices)
			{
				Calendar cal = o.getOrder().getDateTime();
				
				// Check the date of each order from each Order Invoice object
				if (cal.get(Calendar.DAY_OF_MONTH) == (Integer.parseInt(period) / 1000000) && cal.get(Calendar.MONTH) == ((Integer.parseInt(period) % 1000000) / 10000) && cal.get(Calendar.YEAR) == (Integer.parseInt(period) % 10000))
				{
					dailyOrderInvoices.add(o);
				}
			}
			
			printReportByDay(dailyOrderInvoices);
		}
		
		// Monthly
		else if (period.length() == 6)
		{
			Vector<OrderInvoice> monthlyOrderInvoices = new Vector<OrderInvoice>();
			for (OrderInvoice o: orderInvoices)
			{
				Calendar cal = o.getOrder().getDateTime();
				if (cal.get(Calendar.MONTH) == (Integer.parseInt(period) / 10000) && cal.get(Calendar.YEAR) == (Integer.parseInt(period) % 10000))
				{
					monthlyOrderInvoices.add(o);
				}
			}
			
			printReportByMonth(monthlyOrderInvoices);
		}
	}
	
	/*
	 * User need enter the date ie DDMMYYYY

Report will display :

(1) Each sale invoice (with breakdown of ordered items and total) for the entered date with invoice number (receipt number).

[similar to function (9) display but multiple order invoices ]

(2) overall total revenue for the entered date. 

For Demo, you need to show this function with at least 3 sale invoices created on same day in the past.

 

For by MONTH :

User need enter the month and year ie MMYYYY.

Report will display :

(1)  Top sale of the month in DD/MM/YYYY (which day in that month has highest sales) and the total revenue of that day

(2)  Least sale of the month in DD/MM/YYYY (which day in that month has lowest sales) and the total revenue of that day

(3)  overall total revenue for the entered month. 

[ no breakdown of sale invoices is required, just the sale figures ]

For Demo, you need to show this function with at least 2 sale invoices for each day for 3 different days of the month ( to differentiate highest and lowest sales).
	 */
	private static void printReportByDay (Vector<OrderInvoice> dailyOrderInvoices)
	{
		// TODO Print the breakdown of the items for each invoice -> call printInvoice for each OrderInvoice object
		for (OrderInvoice oI: dailyOrderInvoices) {
			
		}
	}
	
	private static void printReportByMonth (Vector<OrderInvoice> monthlyOrderInvoices)
	{
		//Find the max, min total price and total revenue for the whole month
		OrderInvoice orderInvoiceMax = null, orderInvoiceMin = null;
		double min, max, totalPrice;
		double totalMonthlyRevenue = 0.0;
		
		min = max = monthlyOrderInvoices.get(0).getTotalPrice();
		
		for (OrderInvoice o: monthlyOrderInvoices)
		{
			totalPrice = o.getTotalPrice();
			totalMonthlyRevenue += totalPrice;
			if (totalPrice < min)
			{
				min = totalPrice;
				orderInvoiceMin = o;
			}
			if (totalPrice > max)
			{
				max = totalPrice;
				orderInvoiceMax = o;
			}
		}
		
		// TODO Print max, min, and totalMonthlyRevenue here
	}
}
