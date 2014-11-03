package rrpss;

import java.util.*;

/*
 * advance booking: active -> checked-in -> finished
 * 		if no show, active -> expired (check table, reservation)
 * 
 * When allocateTable() is called, check expired reservation based on the reservation status and time 
 */

public class Reservation {
	private Calendar dateTime;
	private Table table;
	private int id;
	/**
	 * status: Active, Checked-in, Cancelled, Finished, Expired
	 */
	private String status;
	private Customer customer;
	
	public Reservation(Calendar dateTime, Table table, int id, Customer customer) {
		this.dateTime = dateTime;
		this.table = table;
		this.id = id;
		this.customer = customer;
		setStatus("Active");
	}
	public Customer getCustomer() {
		return customer;
	}
	public int getId() {
		return id;
	}
	//Probably not needed
	public void updateReservation(Calendar dateTime, Table table, Customer customer) {
		this.dateTime = dateTime;
		this.table = table; //update table
		this.customer = customer;
	}
	public Calendar getDateTime() {
		return dateTime;
	}
	public void setStatus(String status) {
		this.status = status;
		
		if (status.equalsIgnoreCase("expired") || status.equalsIgnoreCase("cancelled") || status.equalsIgnoreCase("finished")) {
			table.setAvailability(true);
		}
	}
	public Table getTable() {
		return table;
	}
	public String getStatus() {
		return this.status;
	}
	
	public String getDateTimeString() {
		return this.dateTime.get(Calendar.DAY_OF_MONTH) + " "
				+ this.dateTime.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH) + " "
				+ this.dateTime.get(Calendar.YEAR) + ", "
				+ this.dateTime.get(Calendar.HOUR_OF_DAY) + ":"
				+ this.dateTime.get(Calendar.MINUTE) + ":"
				+ this.dateTime.get(Calendar.SECOND);
	}
	
	//TODO print reservation details
	public void printReservation() {
		System.out.println("                              RESERVATION                                       ");
		System.out.println("================================================================================");
		System.out.println(String.format("%-43s: %-5d", "ID", id));
		System.out.println(String.format("%-43s: %-5d", "Table", table.getId()));
		System.out.println(String.format("%-43s: %-22s", "Status", status));
		System.out.println(String.format("%-43s: %-22s", "Date/time", getDateTimeString()));
		System.out.println(String.format("%-43s: %-22s", "Customer", customer.getName()));
		System.out.println("================================================================================");
	}
}
