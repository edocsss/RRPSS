package rrpss;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

public class ReservationManager {
	private Vector <Reservation> reservations;
	private static final int BUFFER_TIME = 15;
	
	public ReservationManager() {
		this.reservations = new Vector<Reservation>();
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
		if (dateTime.getTime().before(now.getTime())) {
			System.out.println("Cannot book in past time!");
			return -1;
		}
		
		if (table == null) {
			System.out.println("Restaurant full!");
			return -1;
		}
		
		//Get the Reservation ID based on the Vector size()
		int id = reservations.size() + 1;
			
		//Create and add a new Reservation() object based on the customer object and the table object
		Reservation reservation = new Reservation(dateTime, table, id, customer);
		
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
	
	
	// To get the Customer membership when an printOrderInvoice is called
	public Reservation getReservationByTableNum(int tableNum, String status) {
		for (Reservation reservation: reservations) {
			if (reservation.getTable().getId() == tableNum && reservation.getStatus().equalsIgnoreCase(status)) {
				return reservation;
			}
		}
		
		return null;
	}
	public Reservation getReservationByTableNum(int tableNum) {
		return getReservationByTableNum(tableNum, "Checked-in");
	}
	
	/**
	 * TODO Need implementation!!
	 * **/
	public void checkReservation(int reservationId) {
		checkExpiry();
		//Use getReservationById -> DON'T FORGET TO CHECK Reservation == NULL
		// print data & status based on id
	}
	
	public void checkExpiry() {
		for (Reservation reservation: reservations) {
			checkExpiry(reservation);
		}
	}

	public void checkExpiry(Reservation reservation) {
		Calendar now = new GregorianCalendar();
		Date later = new Date();
		later.setTime(now.getTime().getTime() + BUFFER_TIME * 60 * 100);
		now.setTime(later);
		
		// Reservation will be automatically removed XX minutes after the actual booking time.
		if (reservation.getDateTime().getTime().after(now.getTime())) {
			reservation.setStatus("Expired");
		}
	}
	
	public int cancelReservation (int reservationId) {
		Reservation r = getReservationById(reservationId);
		
		if (r != null) {
			r.setStatus("Cancelled");
			return 1;
		} else {
			return -1;
		}
	}
}
