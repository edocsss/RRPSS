package rrpss;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

/**
 * Manages the {@link Reservation} of the {@link Restaurant} by keeping track of list of reseration
 * and a counter to help ensure the uniqueness of the id of reservation.
 * 
 * @author Kenrick
 *
 */
public class ReservationManager implements Serializable {
	/**
	 * List of reservations, implemented in {@link Vector} data structure.
	 * Each entry consists of a reference to existing {@link Reservation} object.
	 */
	private Vector <Reservation> reservations;
	
	/**
	 * Identifier (id) counter of the reservations, used to assign a unique id for new reservation.
	 * The value is one more than the size of {@link ReservationManager#reservations reservations} vector.
	 */
	private int idCounter;
	
	/**
	 * Buffer time (in minutes) for {@link Reservation} to expire from its dateTime.
	 * If BUFFER_TIME minutes have passed and no order has been made from that reserved table, then the reservation is set to be expired.
	 */
	private static final int BUFFER_TIME = 15;
	
	/**
	 * Constructs the {@link ReservationManager} object
	 * and initializes the reservation list to empty and id counter to 1.
	 */
	public ReservationManager() {
		this.reservations = new Vector<Reservation>();
		this.idCounter = 1;
	}

	/**
	 * Creates new Reservation for a specified {@link Customer} on a specified {@link Calendar dateTime}
	 * for an allocated {@link Table}.
	 * 
	 * <p>This method checks if the given parameter {@code dateTime} is valid by comparing it to the current time. It is valid if the reservation is made for a future time.</p>
	 * 
	 * <p>Next, the method creates a new {@link Reservation} object with incremental id and store it to the reservation list ({@code reservations}).</p>
	 * 
	 * <p>Finally, it returns the identified (id) of the newly created {@link Reservation} object.
	 * 
	 * @param	customer	{@link Customer} object containing details of who made the reservation
	 * @param	dateTime	{@link Calendar} object representing date and time for reservation
	 * @param	table		{@link Table} object representing table that will be allocated for the reservation,
	 * 						should be obtained from {@link TableManager#allocateTable}.
	 * 						<b>{@link ReservationManager#checkExpiry} must be called before calling {@link TableManager#allocateTable}!</b>
	 * @return 	identifier (id) of {@link Reservation} that is created here.
	 */
	public int createReservation(Customer customer, Calendar dateTime, Table table) {
		// check if okay for making reservation
		Calendar now = new GregorianCalendar();
		
		// If the given dateTime is before current time -> error
		if (dateTime.compareTo(now) <= 0) {
			return -1;
		}
		
		//Get the Reservation ID based on the idCounter
		int id = this.idCounter++;
			
		//Create and add a new Reservation() object based on the customer object and the table object
		Reservation reservation = new Reservation(dateTime, table, id, customer);
		
		// Store the reservation
		reservations.add(reservation);
		
		return id;
	}

	/**
	 * Traverses through the reservation list to return the {@link Reservation} object
	 * with the specified identifier (id). Returns {@code null} if not found.
	 * @param reservationId	the identified (id) of a reservation
	 * @return	{@link Reservation} object with the specified identifier (id); {@code null} if not found.
	 */
	private Reservation getReservationById(int reservationId) {
		for (Reservation reservation: reservations) {
			if (reservation.getId() == reservationId) {
				return reservation;
			}
		}
		return null;
	}
	
	/**
	 * Returns a {@link Reservation} object that being allocated to a specified table
	 * with a specified reservation status by traversing the reservaton list.
	 * Returns {@code null} if not found.
	 * 
	 * <p>This method is being used to get {@link Customer#membership}
	 * when {@link OrderInvoice#printInvoice()} is called</p>
	 * 
	 * @param tableId	{@link Table#id id} of a table (table number).
	 * @param status	status of the reservation.
	 * @return	{@link Reservation} object that being allocated to a specified table
	 * with a specified reservation status; {@code null} if not found.
	 */
	public Reservation getReservationByTableId(int tableId, String status) {
		for (Reservation reservation: reservations) {
			if (reservation.getTable().getId() == tableId && reservation.getStatus().equalsIgnoreCase(status)) {
				return reservation;
			}
		}
		return null;
	}

	/**
	 * Checks the current reservation by first checking the expiration of all reservations
	 * (by calling {@link ReservationManager#checkExpiry() checkExpiry})
	 * and then gets the {@link Reservation} object by its id (by calling {@link ReservationManager#getReservationById(int reservationId) getReservationById}).
	 * Finally prints the {@link Reservation} object (by calling {@link Reservation#printReservation()})
	 * 
	 * <p>Returns 1 on successful printing, -1 otherwise</p>
	 * 
	 * @param reservationId		Identifier (id) of a reservation to be checked.
	 * @return	1 on successful printing, -1 otherwise
	 */
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
	
	/**
	 * Calls {@link ReservationManager#checkExpiry(Reservation) checkExpiry(Reservation)}
	 * for every {@link Reservation} object stored in reservation list ({@code reservations})
	 */
	public void checkExpiry() {
		for (Reservation reservation: reservations) {
			checkExpiry(reservation);
		}
	}

	/**
	 * Checks the expiration of the specified {@link Reservation} object.
	 * If the current time exceeds the reservation time plus the buffer time, then
	 * sets the reservation status to be "Expired".
	 * @param reservation	{@link Reservation} object to be checked.
	 */
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
	
	/**
	 * Cancels the reservation of the specified identified (id) by first getting the specified reservation object
	 * and then sets the status to "Cancelled" if the current status is "active"
	 * 
	 * Returns 1 on successful cancellation, -1 otherwise.
	 * @param reservationId		identifier (id) of the reservation.
	 * @return 1 on successful cancellation, -1 otherwise.
	 */
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
