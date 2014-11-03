package rrpss;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

public class ReservationManager {
	private Vector <Reservation> reservations;
	private int idCounter;
	private static final int BUFFER_TIME = 15;
	
	public ReservationManager() {
		this.reservations = new Vector<Reservation>();
		this.idCounter = 0;
	}

	/**
	 * must do checkExpiry before allocateTable!
	 * 
	 * @param	customer	Customer Object, details of who made the reservation, from app
	 * 			dateTime	Calendar Object, date and time for reservation, from app
	 * 			numPeople	number of people that will be allocated to table, from app
	 * 			table		Table Object, table that will be allocated for the reservation, from app that calls TableManager.allocateTable
	 */
	public int createReservation(Customer customer, Calendar dateTime, Table table, int numPeople) {
		
		
		// check if okay for making reservation
		Calendar now = new GregorianCalendar();
		
		// If the given dateTime is before current time -> error
		if (dateTime.compareTo(now) <= 0) {
			return -1;
		}
		
		//Get the Reservation ID based on the Vector size()
		int id = this.idCounter + 1;
			
		//Create and add a new Reservation() object based on the customer object and the table object
		Reservation reservation = new Reservation(dateTime, table, id, customer);
		this.idCounter++;
		
		reservations.add(reservation);
		
		return id;
	}

	private Reservation getReservationById(int reservationId) {
		for (Reservation reservation: reservations) {
			if (reservation.getId() == reservationId) {
				return reservation;
			}
		}
		
		return null;
	}
	
	public Reservation getReservationByTableId(int tableId) {
		return getReservationByTableId(tableId, "checked-in");
	}
	
	// To get the Customer membership when an printOrderInvoice is called
	public Reservation getReservationByTableId(int tableId, String status) {
		for (Reservation reservation: reservations) {
			if (reservation.getTable().getId() == tableId && reservation.getStatus().equalsIgnoreCase(status)) {
				return reservation;
			}
		}
		
		return null;
	}

	public int checkReservation(int reservationId) {
		checkExpiry();
		
		Reservation reservation = getReservationById(reservationId);
		if (reservation == null) {
			return -1;
		} else {
			reservation.printReservation();
			return 1;
		}
	}
	
	public void checkExpiry() {
		for (Reservation reservation: reservations) {
			checkExpiry(reservation);
		}
	}

	public void checkExpiry(Reservation reservation) {
		Calendar reservationTime = reservation.getDateTime();
		long reservationTimeMilli = reservationTime.getTime().getTime();
		Date expiredTime = new Date();
		
		// The expired time is calculated here
		expiredTime.setTime(reservationTimeMilli + BUFFER_TIME * 60 * 1000);
		
		// now = currentTime when this method is called
		// expiredReservationCalendar = reservation time + BUFFER_TIME minutes
		Calendar now = new GregorianCalendar();
		Calendar expiredReservationCalendar = new GregorianCalendar();
		expiredReservationCalendar.setTime(expiredTime);
		
		if (now.compareTo(expiredReservationCalendar) > 0) {
			reservation.setStatus("Expired");
		}
	}
	
	public int cancelReservation (int reservationId) {
		Reservation r = getReservationById(reservationId);
		
		// if reservation is exist and active, can cancel
		if (r != null && r.getStatus().equalsIgnoreCase("active")) {
			r.setStatus("Cancelled");
			return 1;
		} else {
			return -1;
		}
	}
}
