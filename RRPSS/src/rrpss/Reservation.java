package rrpss;

import java.io.Serializable;
import java.util.*;

/*
 * advance booking: active -> checked-in -> finished
 * 		if no show, active -> expired (check table, reservation)
 * 
 * When allocateTable() is called, check expired reservation based on the reservation status and time 
 */
/**
 * Stores information about a reservation of a {@link Restaurant}.
 * This class keeps track of the information of the date and time, table, id, status, and customer of the reservation.
 * 
 * @author 	Kenrick
 * @see		Serializable
 */

public class Reservation implements Serializable {
	/**
	 * Date and time of this Reservation, stored in {@link Calendar} data structure
	 */
	private Calendar dateTime;
	
	/**
	 * {@link Table} object that is associated with this Reservation
	 */
	private Table table;
	
	/**
	 * Identifier (id) of this Reservation, should be unique positive integer, handled by {@link ReservationManager}
	 */
	private int id;
	
	/**
	 * Status of this reservation:
	 * <li>Active: Reservation has been successfully made</li>
	 * <li>Checked-in: {@link Customer} of the reserved table has made an {@link Order} (i.e. active order).</li>
	 * <li>Cancelled: Reservation has been cancelled by customer</li>
	 * <li>Finished: {@link Customer} of the reserved table has closed the {@link Order}.</li>
	 * <li>Expired: {@link Customer} of the reserved table has not made any order
	 * within the {@link ReservationManager#BUFFER_TIME BUFFER_TIME}
	 * from the specified {@link Reservation#dateTime dateTime}</li>
	 * 
	 * @see Order#status
	 */
	private String status;
	
	/**
	 * {@link Customer} that made this Reservation.
	 */
	private Customer customer;
	
	/**
	 * Constructs the Reservation with specified dateTime, table, id, and customer.
	 * By default, reservation initially will be "active".
	 * @param dateTime	{@link Calendar} object specifying date and time of the reservation,
	 * 					not date and time when it is made.
	 * @param table		{@link Table} that is allocated to this reservation.
	 * @param id		Identified of the reservation, should be unique positive integer.
	 * @param customer	{@link Customer} object that made the reservation.
	 */
	public Reservation(Calendar dateTime, Table table, int id, Customer customer) {
		this.dateTime = dateTime;
		this.table = table;
		this.id = id;
		this.customer = customer;
		setStatus("Active");
	}
	
	/**
	 * Returns the {@link Customer} object that made this reservation
	 * @return	{@link Customer} object that made this reservation
	 */
	public Customer getCustomer() {
		return customer;
	}
	
	/**
	 * Returns the identified (id) of this reservation.
	 * @return identified (id) of this reservation.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Returns {@link Calendar} object specifying date and time information of the reservation.
	 * @return {@link Calendar} object specifying date and time information of the reservation.
	 */
	public Calendar getDateTime() {
		return dateTime;
	}
	
	/**
	 * Sets the status of the reservation.
	 * If new status is "expired", "cancelled", or "finished"
	 * then releases the {@link Table} (sets its avaiability to be true).
	 * @param status	new status of the reservation
	 */
	public void setStatus(String status) {
		this.status = status;
		
		if (status.equalsIgnoreCase("expired") || status.equalsIgnoreCase("cancelled") || status.equalsIgnoreCase("finished")) {
			table.setAvailability(true);
		}
	}
	
	/**
	 * Returns a {@link Table} object that is associated with this reservation.
	 * @return	{@link Table} object that is associated with this reservation.
	 */
	public Table getTable() {
		return table;
	}
	
	/**
	 * Returns the status of this reservation.
	 * @return status of this reservation.
	 */
	public String getStatus() {
		return this.status;
	}
	
	/**
	 * Returns the date and time information in String format: DD MMM YYYY, HH:mm:SS
	 * @return date and time information in String format: DD MMM YYYY, HH:mm:SS
	 */
	private String getDateTimeString() {
		return this.dateTime.get(Calendar.DAY_OF_MONTH) + " "
				+ this.dateTime.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH) + " "
				+ this.dateTime.get(Calendar.YEAR) + ", "
				+ this.dateTime.get(Calendar.HOUR_OF_DAY) + ":"
				+ this.dateTime.get(Calendar.MINUTE) + ":"
				+ this.dateTime.get(Calendar.SECOND);
	}
	
	/**
	 * Prints the current Reservation information to standard output in a nice format.
	 */
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
