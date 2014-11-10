package rrpss;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * The main application where the RRPSS works. This class provides the interface for the user.
 * 
 * @author Edwin Candinegara, Kenrick, Deka Auliya Akbar, Yanhan, Liling
 */
public class RestaurantApp 
{
	/**
	 * An alias for System.out.print(). This method makes it shorter in writing the code.
	 * 
	 * @param line the String which is going to be printed
	 */
	private static void print(Object line) {
	    System.out.print(line);
	}
	
	/**
	 * An alias for System.out.println(). This method makes it shorter in writing the code.
	 * 
	 * @param line the String which is going to be printed
	 */
	private static void println(Object line) {
	    System.out.println(line);
	}
	
	/**
	 * An alias for System.out.print() but only prints a newline character. 
	 * The difference between this method and {@link #println(Object)} is that
	 * this method does not take any argument. This method is only used to write a newline character.
	 */
	private static void println() {
	    System.out.println();
	}
	
	/**
	 * Main method for the RRPSS application.
	 * 
	 * @param args	Input from the command line 
	 */
	public static void main (String[] args) {
		
		// Create a new Restaurant object with the name "The Lyked"
		Restaurant r = new Restaurant("The Lyked");
		
		// Read data from Database
		r = Database.readRestaurantObject("Database//" + r.getName() + ".ser");
		
		// Only start the program if the initial data has been successfully fetched from Database
		if (r != null) {
			// Manager variables
			TableManager tableManager = r.getTableManager();
			Menu menu = r.getMenu();
			StaffManager staffManager = r.getStaffManager();
			OrderInvoiceManager orderInvoiceManager = r.getOrderInvoiceManager();
			OrderManager orderManager = r.getOrderManager();
			ReservationManager reservationManager = r.getReservationManager();
			
			// Temporary variables used throughout the application
			SetPackage sp;
			AlaCarte ac;
			
			// Scanner
			Scanner sc = new Scanner(System.in);
			
			// TODO need to delete this part BEFORE submitting the code
			/*
			// Adding table
			tableManager.addTable(2);
			tableManager.addTable(2);
			tableManager.addTable(4);
			tableManager.addTable(4);
			tableManager.addTable(4);
			tableManager.addTable(4);
			tableManager.addTable(4);
			tableManager.addTable(4);
			tableManager.addTable(6);
			tableManager.addTable(8);
			tableManager.addTable(8);
			tableManager.addTable(8);
			tableManager.addTable(10);
			tableManager.addTable(10);
			tableManager.addTable(10);
			
			// Adding AlaCarte
			menu.addAlaCarte(new AlaCarte("Hummus and Crackers", 10.5, "Hummus with raw vegan crackers", "Appetizers")); // ID: 1
			menu.addAlaCarte(new AlaCarte("Salsa, Sushi, and Guacamole", 11.5, "Salsa, guacamole, and crackers", "Appetizers")); // ID: 2
			menu.addAlaCarte(new AlaCarte("Sushi Hand Roll", 12.0, "With veggies and a savoury no tuna pate", "Appetizers")); // ID: 3
			
			menu.addAlaCarte(new AlaCarte("Pumpkin Soup", 8.5, "Soup with pumpkin flowers", "Soup & Pasta"));// ID: 4
			menu.addAlaCarte(new AlaCarte("Goulash Soup", 8.5, "Hungarian style beef and potato soup", "Soup & Pasta")); // ID: 5
			menu.addAlaCarte(new AlaCarte("Homemade Soup", 8.0, "Soup made fresh daily by our chef", "Soup & Pasta")); // ID: 6
			menu.addAlaCarte(new AlaCarte("Risotto", 16.0, "With champagne", "Soup & Pasta")); // ID: 7
			menu.addAlaCarte(new AlaCarte("Signature Pasta", 16.0, "With seafood and parsley sauce", "Soup & Pasta")); // ID: 8
			menu.addAlaCarte(new AlaCarte("Chef Pasta", 18.5, "with stewed beef, thyme, and mushrooms", "Soup & Pasta")); // ID: 9
			
			menu.addAlaCarte(new AlaCarte("Veal Tournedos", 21.5, "With carrot and ginger flan", "Meat")); // ID: 10
			menu.addAlaCarte(new AlaCarte("Flap Sirloin Beef", 23.75, "With celery root and mushrooms", "Meat")); // ID: 11
			menu.addAlaCarte(new AlaCarte("Lamb Loin", 23.75, "With pepper, potato, and spicy green chicory", "Meat")); // ID: 12
			
			menu.addAlaCarte(new AlaCarte("Field Greens", 8.5, "Hydroponic vegetable salads", "Vegetarian Dishes")); // ID: 13
			menu.addAlaCarte(new AlaCarte("Braised Tofu", 7.5, "With vegetable and Chinese mushrooms", "Vegetarian Dishes")); // ID: 14
			
			menu.addAlaCarte(new AlaCarte("Soya Milk", 3.0, "Fresh soya milk", "Drinks")); // ID: 15
			menu.addAlaCarte(new AlaCarte("Fruit Juice", 3.5, "Apple, orange, mango, or kiwi juice", "Drinks")); // ID: 16
			menu.addAlaCarte(new AlaCarte("Chinese Tea", 2.0, "Chrysanthemum", "Drinks")); // ID: 17
			
			// Adding Set Package
			sp = new SetPackage("Family Set", 62, "Package aimed for those coming with their family");
			sp.addAlaCarte(menu.getAlaCarteById(2));
			sp.addAlaCarte(menu.getAlaCarteById(6));
			sp.addAlaCarte(menu.getAlaCarteById(7));
			sp.addAlaCarte(menu.getAlaCarteById(10));
			sp.addAlaCarte(menu.getAlaCarteById(13));
			sp.addAlaCarte(menu.getAlaCarteById(17));
			menu.addSetPackage(sp);
			
			sp = new SetPackage("Executive Set", 68, "Package aimed for company dinner with executives");
			sp.addAlaCarte(menu.getAlaCarteById(1));
			sp.addAlaCarte(menu.getAlaCarteById(6));
			sp.addAlaCarte(menu.getAlaCarteById(8));
			sp.addAlaCarte(menu.getAlaCarteById(12));
			sp.addAlaCarte(menu.getAlaCarteById(14));
			sp.addAlaCarte(menu.getAlaCarteById(13));
			sp.addAlaCarte(menu.getAlaCarteById(16));
			menu.addSetPackage(sp);
			
			// Adding Staffs
			staffManager.addStaff(new Staff("Liling", 524, "Female", "Staff"));
			staffManager.addStaff(new Staff("Yanhan", 123, "Male", "Staff"));
			staffManager.addStaff(new Staff("Kenrick", 314, "Male", "Staff"));
			staffManager.addStaff(new Staff("Deka", 324, "Female", "Staff"));
			staffManager.addStaff(new Staff("Edwin", 053, "Male", "Staff"));
			*/
			
			// Variable Declaration
			int choice = 0, subChoice = 0, subChoice2 = 0;
			String name, description, type, membershipString, dateTimeString, contact;
			int id, tableId, staffId, quantity, numPeople, reservationId, ret;
			boolean membership = false;
			double price;
			Table table;
			Staff staff;
			Order order;
			OrderInvoice orderInvoice;
			Customer customer;
			Reservation reservation;
			Calendar dateTime = new GregorianCalendar();
			
			// ======== "Real" start of the application ======== //
						
			do {
				println("=================================================");
				println("Restaurant Reservation and Point of Sale System: ");
				println("=================================================");
				println("1. Create/Update/Remove menu item");
				println("2. Create/Update/Remove promotion");
				println("3. Create order");
				println("4. View order");
				println("5. Add/Remove order item/s to/from order");
				println("6. Create reservation booking");
				println("7. Check/Remove reservation booking");
				println("8. Check table availability");
				println("9. Print order invoice");
				println("10. Print sale revenue report by period (eg day or month)");
				println("11. Exit");
				print("Enter your choice: ");
				
				// Input choice
				choice = sc.nextInt();
				
				// Flush
				sc.nextLine(); 
				println();
				
				switch (choice) {
				
				// Create/Update/Remove menu item
				case 1:
					do {
						// Print menu
						menu.printMenu();
						
						// Options
						println("1. Create menu item");
						println("2. Update menu item");
						println("3. Remove menu item");
						println("4. Back");
						print("Enter your choice: ");
						
						// Submenu selection
						subChoice = sc.nextInt();
						sc.nextLine(); // "flush"
						
						switch(subChoice) {
						
						// Add an Ala Carte
						case 1:
							print("\nEnter Ala Carte name: ");
							name = sc.nextLine();
							
							print("Enter Ala Carte price: ");
							price = sc.nextDouble();
							sc.nextLine();
							
							print("Enter Ala Carte description in one line: ");
							description = sc.nextLine();
							
							print("Enter Ala Carte type: ");
							type = sc.nextLine();
							
							// Adding an Ala Carte Menu Item
							ac = new AlaCarte(name, price, description, type);
							menu.addAlaCarte(ac);
							
							// Feedback
							println("Ala Carte: " + name + "has been added to the Menu!\n");
							break;
							
						// Update particulars of an Ala Carte object
						case 2:
							
							// Input the ID of the Ala Carte which is going to be modified
							print("Enter the Ala Carte ID: ");
							id = sc.nextInt();
							
							// Check whether the Ala Carte specified by the ID exists
							ac = menu.getAlaCarteById(id);
							if (ac == null) {
								println("Error: Ala Carte with ID: " + id + " does not exist!");
								continue;
							}
							
							do {
								// Options
								println("\n1. Update name");
								println("2. Update price");
								println("3. Update description");
								println("4. Update type");
								println("5. Update all fields"); // Rather than choosing 1 and then 2 etc.
								println("6. Back");
								
								// Enter selection
								print("Enter your choice: ");
								subChoice2 = sc.nextInt();
								
								// Flush 
								sc.nextLine();
								
								switch (subChoice2) {
								
								// Update AlaCarte object name
								case 1:
									print("Enter the new name: ");
									name = sc.nextLine();
									
									// Update name
									ac.setName(name);
									println("The name of Ala Carte with ID: " + id + " has been modified!");
									break;
								
								// Update AlaCarte object price
								case 2:
									print("Enter the new price: ");
									price = sc.nextDouble();
									
									// Update price
									ac.setPrice(price);
									println("The price of Ala Carte with ID: " + id + " has been modified!");
									break;
									
								// Update AlaCarte object description
								case 3:
									print("Enter the new description in one line: ");
									description = sc.nextLine();
									
									// Update description
									ac.setDescription(description);
									println("The description of Ala Carte with ID: " + id + " has been modified!");
									break;
									
								// Update AlaCarte object type
								case 4:
									print("Enter the new type: ");
									type = sc.nextLine();
									
									// Update type
									ac.setType(type);
									println("The type of Ala Carte with ID: " + id + " has been modified!");
									break;
								
								// Update all attributes of AlaCarte object
								case 5:
									print("Enter the new name: ");
									name = sc.nextLine();
									
									print("Enter the new price: ");
									price = sc.nextDouble();
									sc.nextLine(); // "flush"
									
									print("Enter the new description in one line: ");
									description = sc.nextLine();
									
									print("Enter the new type: ");
									type = sc.nextLine();
									
									// Update particulars
									ac.setName(name);
									ac.setPrice(price);
									ac.setDescription(description);
									ac.setType(type);
									
									println("Ala Carte with ID: " + id + " has been modified!");
									break;
								}
							} while (1 <= subChoice2 && subChoice2 <= 5);
									
							break;
							
						// Remove an Ala Carte object
						case 3:
							
							// Input the ID of the Ala Carte which is going to be modified
							print("Enter Ala Carte ID to be removed: ");
							id = sc.nextInt();
							
							// Removing an Ala Carte based on the ID
							// Return -1 means error, return 1 means successful
							if (menu.removeAlaCarteById(id) == -1) {
								println("Error: Ala Carte with ID: " + id + " does not exist!");
							} else {
								println("Ala Carte with ID: " + id + " has been removed from the menu!");
							}
							
							break;
						}
					} while (1 <= subChoice && subChoice <= 3);
					
					break;
					
				// Create/Update/Remove Set Package
				case 2: 
					do {
						// Print menu
						menu.printMenu();
						
						// Options
						println("\n1. Create Set Package");
						println("2. Update Set Package");
						println("3. Remove Set Package");
						println("4. Back");
						print("Enter your choice: ");
						
						// Submenu selection
						subChoice = sc.nextInt();
						
						// Flush
						sc.nextLine();					
						
						switch(subChoice) {
						
						// Add Set Package
						case 1:
							print("\nEnter Set Package name: ");
							name = sc.nextLine();
							
							print("Enter Set Package price: ");
							price = sc.nextDouble();
							sc.nextLine(); // "flush"
							
							print("Enter Set Package description in one line: ");
							description = sc.nextLine();
							
							// Create a new SetPackage object
							sp = new SetPackage(name, price, description);
							println("Enter a line of Ala Carte IDs to be added to the Set Package | Enter -1 to end: ");
							
							// Getting and Adding AlaCarte object to the SetPackage
							while (sc.hasNextInt()) {
								
								// Input AlaCarte ID
								id = sc.nextInt();
								
								// If the input is -1, stop the loop
								if (id == -1) {
									break;
								}
								
								// Get the AlaCarte object based on the ID
								ac = menu.getAlaCarteById(id);
								
								// Error checking
								if (ac == null) {
									println("Error: Ala Carte with ID: " + id + " does not exist!");
								} else {
									// Add AlaCarte to SetPackage
									// Use copy() method in order to preserve the information although the item has been removed
									// from the menu
									sp.addAlaCarte(ac.copy());
									
									// Feedback
									println("Ala Carte with ID: " + id + " has been added to the Set Package!");
								}
							}
							
							// Add SetPackage to the Menu
							menu.addSetPackage(sp);
							
							// Feedback
							println("Set Package: " + name + " has been added to the Menu!");
							
							break;
						
						// Update particulars of Set Package
						case 2:
							
							// Print menu
							menu.printMenu();
							
							// Input the SetPackage ID
							print("Enter the Set Package ID: ");
							id = sc.nextInt();
							
							// Get the SetPackage object with the specified ID
							sp = menu.getSetPackageById(id);
							if (sp == null) {
								println("Error: Set Package with ID: " + id + " does not exist!");
								continue;
							}
							
							do {
								// Options
								println("\n1. Update name");
								println("2. Update price");
								println("3. Update description");
								println("4. Update all fields");
								println("5. Back");
								
								// Menu selection
								print("Enter your choice: ");
								subChoice2 = sc.nextInt();
								
								// Flush
								sc.nextLine();
								
								switch (subChoice2) {
								// Update SetPackage object name
								case 1:
									print("Enter the new name: ");
									name = sc.nextLine();
									
									// Update name
									sp.setName(name);
									println("The name of Set Package with ID: " + id + " has been modified!");
									break;
								
								// Update SetPackage object price
								case 2:
									print("Enter the new price: ");
									price = sc.nextDouble();
									
									// Update price
									sp.setPrice(price);
									println("The price of Set Package with ID: " + id + " has been modified!");
									break;
									
								// Update SetPackage object description	
								case 3:
									print("Enter the new description in one line: ");
									description = sc.nextLine();
									
									// Update description
									sp.setDescription(description);
									println("The description of Set Package with ID: " + id + " has been modified!");
									break;
								
								// Update all particulars of SetPackage object
								case 4:
									print("Enter the new name: ");
									name = sc.nextLine();
									
									print("Enter the new price: ");
									price = sc.nextDouble();
									sc.nextLine(); // "flush"
									
									print("Enter the new description in one line: ");
									description = sc.nextLine();
									
									// Update particulars
									sp.setName(name);
									sp.setPrice(price);
									sp.setDescription(description);
	
									println("Set Package with ID: " + id + " has been modified!");
									break;
								}
							} while (1 <= subChoice2 && subChoice2 <= 4);
									
							break;
						
						// Remove a Set Package object from the Menu
						case 3:
							
							// Enter the ID of the SetPackage
							print("Enter Set Package ID to be removed: ");
							id = sc.nextInt();
							
							// If the SetPackage object specified by the ID is successfully removed, return 1
							// Otherwise, return -1
							if (menu.removeSetPackageById(id) == -1) {
								println("Error: Set Package with ID: " + id + " does not exist!");
							} else {
								println("Set Package with ID: " + id + " has been removed from the Menu!");
							}
							
							break;
						}
					} while (1 <= subChoice && subChoice <= 3);
					
					break;
					
				// Create a new Order object
				case 3:
					
					// Input Table number which has the ordering customer
					print("Enter table number: ");
					tableId = sc.nextInt();
					
					// Retrieve the Table object specified by the table number / ID
					table = tableManager.getTableById(tableId);
					
					// Error checking
					if (table == null) {
						println("Error: Wrong table number!\n");
						continue;
					}
					
					/*	Check whether a customer is a walk-in customer or with reservation:
					 * 	1. Check whether a Reservation object which is assigned to the correct table and it is still active
					 *  2. If it is found, then the customer has made a reservation
					 *  3. If it is not found, check the Table object availability
					 *  	- If it is available, it means the customer is a walk-in customer
					 *  	- If it is not available, it means the Table has been assigned to other customers
					 */
	
					reservation = reservationManager.getReservationByTableId(tableId, "Active");
					if (reservation == null) {
						if (table.getAvailability()) {
							// Update table availability to false for walk-in customers
							table.setAvailability(false);
						}
					} else {
						// Update Reservation status to "Checked-in" as it means the customer has checked in to the restaurant
						reservation.setStatus("checked-in");
					}
					
					// If the Table object has already had an Order, it means error
					if (table.getOrder() != null) {
						println("Error: Table number " + tableId + " has already had an order!");
						println("Please use the update function to update this order!");
						continue;
					}
					
					// Assign a Staff to the Order
					print("Enter staff ID: ");
					staffId = sc.nextInt();
					staff = staffManager.getStaffById(staffId);
					
					// Error checking
					if (staff == null) {
						println("Error: Wrong Staff ID!");
						continue;
					}
					
					// Create new Order object
					order = orderManager.createOrder(new GregorianCalendar(), staff);
					
					// Print menu
					menu.printMenu();
					
					// Adding AlaCarte items to the Order
					println("Input Ala Carte IDs and quantity (space-separated) | Enter -1 to end: ");
					while (sc.hasNextInt()) {
						id = sc.nextInt();
						
						// Stop adding if the input is -1 (sentinel value)
						if (id == -1) {
							break;
						}
						
						// Get the AlaCarte object specified by the ID
						ac = menu.getAlaCarteById(id);
						quantity = sc.nextInt();
						
						// Error checking
						if (ac == null) {
							println("Error: Ala Carte with ID: " + id + " does not exist!");
						} else if (quantity <= 0) {
							println("Error: Please enter positive integer for quantity!");
						} else {
							// Use copy() method in order to preserve the information although the item has been removed
							// from the menu
							order.addAlaCarte(ac.copy(), quantity);
						}
					}
					
					// Adding SetPackage items to the Order
					println("Input Set Package IDs and quantity (space-separated) | Enter -1 to end: ");
					while (sc.hasNextInt()) {
						id = sc.nextInt();
						
						// Stop adding if the input is -1 (sentinel value)
						if (id == -1) {
							break;
						}
						
						// Get the SetPackage object specified by the ID
						sp = menu.getSetPackageById(id);
						quantity = sc.nextInt();
						
						// Error checking
						if (sp == null) {
							println("Error: Set Package with ID: " + id + " does not exist!\n");
						} else if (quantity <= 0) {
							println("Error: Please enter positive integer for quantity!");
						} else {
							// Use copy() method in order to preserve the information although the item has been removed
							// from the menu
							order.addSetPackage(sp.copy(), quantity);
						}
					}
					
					// Put the Order object to the corresponding Table object
					table.setOrder(order);
					
					// Feedback
					println("An order for table number " + tableId + " has been created!");
					
					break;
					
				// View order
				case 4: 
					
					// Input the table number / ID
					print("Enter table number: ");
					tableId = sc.nextInt();
					
					// Get Table object specified by the number / ID
					table = tableManager.getTableById(tableId);
					
					// Error checking
					if (table == null) {
						println("Error: Wrong table number!\n");
						continue;
					}
					
					// Retrieve the Order object of that Table
					order = table.getOrder();
					
					// Error checking
					if (order == null) {
						println("Error: Table " + tableId + " does not have any order!\n");
						continue;
					}
					
					// Print the Order
					order.printOrder();
					
					break;
					
				// Add / Remove order item/s to / from order
				case 5:
					
					// Input the table number / ID
					print("Enter table number: ");
					tableId = sc.nextInt();
					
					// Get Table object specified by the number / ID
					table = tableManager.getTableById(tableId);
					
					// Error checking
					if (table == null) {
						println("Error: Wrong table number!");
						continue;
					}
					
					// Retrieve the Order object of that Table
					order = table.getOrder();
					
					// Error checking
					if (order == null) {
						println("Error: Table number " + tableId + " does not have any order!");
					}
	
					// Options
					println("1. Add order items to order");
					println("2. Remove order items from order");
					println("3. Back");
					
					// Submenu selection
					print("Enter your choice: ");
					subChoice = sc.nextInt();
					
					// Add or remove items
					switch(subChoice) {
					case 1:

						// Print menu
						menu.printMenu();
						
						// Adding AlaCarte items to the Order
						println("\nInput AlaCarte IDs and quantity (space-separated) | Enter -1 to end: ");
						while (sc.hasNextInt()) {
							id = sc.nextInt();
							
							// Stop adding if id = -1 (sentinel value)
							if (id == -1) {
								break;
							}
							
							// Get the AlaCarte object specified by the ID
							ac = menu.getAlaCarteById(id);
							quantity = sc.nextInt();
							
							// Error checking
							if (ac == null) {
								println("Error: Ala Carte with the given ID does not exist!");
							} else if (quantity <= 0) {
								println("Error: Please enter positive integer for quantity!");
							} else {
								// Use copy() method in order to preserve the information although the item has been removed
								// from the menu
								order.addAlaCarte(ac.copy(), quantity);
							}
						}
						// Adding SetPackage items to the Order
						println("Input SetPackage IDs and quantity (space-separated) | Enter -1 to end: ");
						while (sc.hasNextInt()) {
							id = sc.nextInt();
							
							// Stop adding if id = -1 (sentinel value)
							if (id == -1) {
								break;
							}
							
							// Get the SetPackage object specified by the ID
							sp = menu.getSetPackageById(id);
							quantity = sc.nextInt();
							
							// Error checking
							if (sp == null) {
								println("Error: Set Package with the given ID does not exist!");
							} else if (quantity <= 0) {
								println("Error: Please enter positive integer for quantity!");
							} else {
								// Use copy() method in order to preserve the information although the item has been removed
								// from the menu
								order.addSetPackage(sp.copy(), quantity);
							}
						}
						
						break;
						
					case 2:
						
						// Print order
						order.printOrder();
						
						// Removing AlaCarte items to the Order
						println("Input AlaCarte IDs (space-separated) | Enter -1 to end: ");
						while (sc.hasNextInt()) {
							id = sc.nextInt();
							
							// Stop removing if id = -1 (sentinel value)
							if (id == -1) {
								break;
							}
							
							// Removing AlaCarte object from Order based on the ID
							// Return 1 if it is successful
							// Return -1 otherwise
							if (order.removeAlaCarteByItemId(id) == 1) {
								println("Ala Carte with ID: " + id + " has been removed from the order!");
							} else {
								println("Error: Ala Carte with ID: " + id + " does not exist in the order!");
							}
						}
						
						// Removing AlaCarte items to the Order
						println("Input SetPackage IDs (space-separated) | Enter -1 to end: ");
						while (sc.hasNextInt()) {
							id = sc.nextInt();
							
							// Stop removing if id = -1 (sentinel value)
							if (id == -1) {
								break;
							}
							
							// Removing SetPackage object from Order based on the ID
							// Return 1 if it is successful
							// Return -1 otherwise
							if (order.removeSetPackageByItemId(id) == 1) {
								println("Set Package with ID: " + id + " has been removed from the order!");
							} else {
								println("Error: Set Package with ID: " + id + " does not exist in the order!");
								continue;
							}
						}
						
						break;
					}
					
					break;
				
				// Create reservation booking
				case 6: 
					// Input customer name
					println("Input customer details:");
					print("Name: ");
					name = sc.nextLine();
					
					// Input customer ID
					print("ID: ");
					id = sc.nextInt();
					sc.nextLine();
					
					// Ask whether there is a membership with error checking
					do {
						print("Membership (Y/N)? ");
						membershipString = sc.nextLine();
						
						if (membershipString.equalsIgnoreCase("y")) {
							membership = true;
						} else if (membershipString.equalsIgnoreCase("n")) {
							membership = false;
						} else {
							println("Error: Please enter only Y or N!");
						}
					} while (!membershipString.equalsIgnoreCase("y") && !membershipString.equalsIgnoreCase("n"));
					
					// Input customer contact number
					print("Contact: ");
					contact = sc.nextLine();
					
					// Create a new Customer object
					customer = new Customer(name, id, membership, contact);
					
					// Input date and time of the reservation
					System.out.println("Input date and time to reserve (Format: dd-MM-yyyy HH:mm, e.g. 01-01-2014 09:00): ");
					SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
					dateTimeString = sc.nextLine();
					
					// Parse and format the String input
					try {
						dateTime.setTime(format.parse(dateTimeString));
					} catch (ParseException e) {
						println("Error: Wrong date format!");
						e.printStackTrace();
						continue;
					}
					
					// Input number of people 
					print("Input number of people: ");
					numPeople = sc.nextInt();
					
					// Check for any expired reservations
					reservationManager.checkExpiry();
					
					// Search a suitable table for this reservation
					table = tableManager.allocateTable(numPeople);
					
					// If table is null, it means the restaurant is full
					if (table == null) {
						println("No table is allocated because the restaurant is full or no table with enough seats is available!");
					} 
					// If not full, create the reservation
					else {
						reservationId = reservationManager.createReservation(customer, (Calendar) dateTime.clone(), table);
						
						// Error checking
						if (reservationId != -1) {
							println("A new reservation is created with ID: " + reservationId + "!");
						} else {
							System.out.println("Error: Cannot book in past time!");
						}
					}
					
					break;
				
				// Check / Remove reservation
				case 7: 
					
					// Input reservation ID
					print("Enter reservation ID: ");
					reservationId = sc.nextInt();
									
					// Options
					println("1. Check reservation");
					println("2. Remove reservation");
					println("3. Back");
					
					// Submenu selection
					print("Enter your choice: ");
					subChoice = sc.nextInt();
					
					switch (subChoice) {
					case 1:
						// If checkReservation() returns 1, it will printout the details of the reservation
						// If it returns -1, the reservation with the specified ID does not exist
						if (reservationManager.checkReservation(reservationId) == -1) {
							println("Error: Reservation with ID: " + reservationId + " does not exist!");
						}
						
						break;
						
					case 2:
						// If cancelReservation() returns 1, the reservation has been removed
						// Otherwise, the removal is unsuccessful
						if (reservationManager.cancelReservation(reservationId) == 1) {
							println("Reservation with ID: " + reservationId + " has been removed!");
						} else {
							println("Error: Reservation with ID: " + reservationId + " does not exist!");
						}
						
						break;
					}
					
					break;
					
				// Check table availability
				case 8: 
					// Input table number / ID
					print("Input table number: ");
					tableId = sc.nextInt();
					
					// Get the Table object with the specified number / ID
					table = tableManager.getTableById(tableId);
					
					// Error checking
					if (table == null) {
						println("Error: Table number " + tableId + " does not exist!\n");
					} else {
						// Check whether the Table is available
						if (table.getAvailability()) {
							println("Table number " + tableId + " is available!");
						} else {
							println("Table number " + tableId + " is unavailable!");
						}
					}
					
					break;
				
				// Print order invoice
				case 9:
					// Input table number / ID
					print("Enter table number: ");
					tableId = sc.nextInt();
					sc.nextLine();
					
					// Get the Table object specified by the number / ID
					table = tableManager.getTableById(tableId);
					
					// Error checking
					if (table == null) {
						println("Error: Table number " + tableId + " does not exist!\n");
						continue;
					}
					
					// The table must be assigned if it wants to print the order invoice
					if (table.getAvailability()) {
						println("Error: Table number: " + tableId + " does not have customer!\n");
						continue;
					}
	
					// Get Order object from Table 
					order = table.getOrder();
					
					// Error checking
					if (order == null) {
						println("Error: Table number " + tableId + " does not have any order!");
						continue;
					}
					
					/*
					 * 	Checking of customer membership:
					 * 	1. Check whether there is a reservation for this table and its status is "checked-in" currently
					 * 	2. If there is, it means the customer of this table is customer with reservation
					 * 		- Get the customer membership through the Reservation object
					 *  3. If there is not, it means the customer is a walk-in customer
					 *  	- Directly ask whether the customer has a membership
					 */
					
					reservation = reservationManager.getReservationByTableId(tableId, "Checked-in");
					if (reservation == null) {
						print("Membership (Y/N)? ");
						membershipString = sc.nextLine();
						
						if (membershipString.equalsIgnoreCase("y")) {
							membership = true;
						} else {
							membership = false;
						}
						
						table.setAvailability(true);
						
					} else {
						membership = reservation.getCustomer().getMembership();
						
						// Release the reservation + table (See Reservation.setStatus())
						reservation.setStatus("Finished");
					}
					
					// Close the order AND release the table
					order.setStatus("Closed");
					table.setOrder(null);
					
					
					// Create Order Invoice
					orderInvoice = orderInvoiceManager.createOrderInvoice(order, membership, tableId);
					
					// Print the order invoice
					orderInvoice.printInvoice();
					
					break;
					
				// Print revenue report by the specified period (e.g. daily or monthly)
				case 10: 
					String period;
					int check;
					
					// Input the revenue report period
					print("Enter period (MMYYYY or DDMMYYYY): ");
					period = sc.next();
					
					// Print the report and check the return value
					check = RevenueReport.printReport(period, orderInvoiceManager.getOrderInvoices());
					if (check == -1)
					{
						println("Error: There is no order invoice within the specified date or month!");
					}
					else if (check == -2)
					{
						println("Error: Wrong period input! Please give the period in the format of MMYYYY or DDMMYYYY");
					}
					
					break;
					
				default:
					println("Closing ...");
				}
				
				println();
				
				// Save to database
				// Always save any change in each iteration
				ret = Database.writeRestaurantObject(r, "Database//" + r.getName() + ".ser");
				if (ret == -1) {
					System.out.println("Error: The data is not successfully written to the Database!");
				}

			} while (1 <= choice && choice <= 10);
			
			// Close Scanner object
			sc.close();	
			
		} else {
			System.out.println("Error: Data is not succesfully fetched from the Database!");
			System.out.println("Please make sure that the corresponding serial file exists!");
		}
	}
}
