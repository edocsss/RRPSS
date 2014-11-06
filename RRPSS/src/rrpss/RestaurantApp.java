package rrpss;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class RestaurantApp 
{
	private static void print(Object line) {
	    System.out.print(line);
	}
	
	private static void println(Object line) {
	    System.out.println(line);
	}
	
	private static void println() {
	    System.out.println();
	}
	
	public static void main (String[] args) {
		Restaurant r = new Restaurant("The Lyked");
		
		// Read data from Database
		r = Database.readRestaurantObject("Database//" + r.getName() + ".ser");
		
		// Only start the program if the initial data has been successfully fetched from Database
		if (r != null) {
			TableManager tableManager = r.getTableManager();
			Menu menu = r.getMenu();
			StaffManager staffManager = r.getStaffManager();
			OrderInvoiceManager orderInvoiceManager = r.getOrderInvoiceManager();
			OrderManager orderManager = r.getOrderManager();
			ReservationManager reservationManager = r.getReservationManager();
	
			SetPackage sp;
			AlaCarte ac;
			
			Scanner sc = new Scanner(System.in);
			
			/*
			// Adding table
			tableManager.addTable(2);
			tableManager.addTable(4);
			tableManager.addTable(8);
			tableManager.addTable(10);
			tableManager.addTable(5);
			tableManager.addTable(4);
			tableManager.addTable(8);
			tableManager.addTable(8);
			tableManager.addTable(6);
			tableManager.addTable(5);
			tableManager.addTable(10);
			tableManager.addTable(12);
			tableManager.addTable(2);
			tableManager.addTable(4);
			tableManager.addTable(4);
			
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
			*/
			
			// Variable Declaration
			int choice = 0, subChoice = 0, subChoice2 = 0;
			String name, description, type, membershipString, dateTimeString;
			int id, tableId, staffId, quantity, contact, numPeople, reservationId;
			boolean membership = false;
			double price;
			Table table;
			Staff staff;
			Order order;
			OrderInvoice orderInvoice;
			Customer customer;
			Reservation reservation;
			Calendar dateTime = new GregorianCalendar();
			
			// "Real" start of the application
			menu.printMenu();
			// TODO put printMenu inside consistently: currently is spread here and there.
			
			do {
				// Save to database
				// Always save any change in each iteration
				Database.writeRestaurantObject(r, "Database\\" + r.getName() + ".ser");	
				
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
				
				choice = sc.nextInt();
				sc.nextLine(); // "flush"
				println();
				
				switch (choice) {
				case 1: // Create/Update/Remove menu item
					do {
						menu.printMenu();
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
							
							println("Ala Carte: " + name + "has been added to the Menu!\n");
							break;
							
						// Update a particular Ala Carte object
						case 2:
							// Update -> switch () based on what is going to be updated
							print("Enter the Ala Carte ID: ");
							id = sc.nextInt();
							
							ac = menu.getAlaCarteById(id);
							if (ac == null) {
								println("Error: Ala Carte with ID: " + id + " does not exist!");
								continue;
							}
							
							do {
								// Print sub menu
								println("\n1. Update name");
								println("2. Update price");
								println("3. Update description");
								println("4. Update type");
								println("5. Update all fields"); // Rather than choosing 1 and then 2 etc.
								println("6. Back");
								print("Enter your choice: ");
								subChoice2 = sc.nextInt();
								sc.nextLine(); // "flush"
								
								switch (subChoice2) {
								case 1:
									print("Enter the new name: ");
									name = sc.nextLine();
									
									ac.setName(name);
									println("The name of Ala Carte with ID: " + id + " has been modified!");
									break;
									
								case 2:
									print("Enter the new price: ");
									price = sc.nextDouble();
									
									ac.setPrice(price);
									println("The price of Ala Carte with ID: " + id + " has been modified!");
									break;
									
								case 3:
									print("Enter the new description in one line: ");
									description = sc.nextLine();
									
									ac.setDescription(description);
									println("The description of Ala Carte with ID: " + id + " has been modified!");
									break;
									
								case 4:
									print("Enter the new type: ");
									type = sc.nextLine();
									
									ac.setType(type);
									println("The type of Ala Carte with ID: " + id + " has been modified!");
									break;
									
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
							// Print Ala Carte list here (including the ID for each Ala Carte)
							print("Enter Ala Carte ID to be removed: ");
							id = sc.nextInt();
							
							// Removing an Ala Carte based on the ID
							if (menu.removeAlaCarteById(id) == -1) {
								println("Error: Ala Carte with ID: " + id + " does not exist!");
							} else {
								println("Ala Carte with ID: " + id + " has been removed from the menu!");
							}
							
							break;
						}
					} while (1 <= subChoice && subChoice <= 3);
					
					break;
					
				case 2: // Create/Update/Remove promotion
					do {
						menu.printMenu();
						println("\n1. Create Set Package");
						println("2. Update Set Package");
						println("3. Remove Set Package");
						println("4. Back");
						println("Enter your choice: ");
						
						// Submenu selection
						subChoice = sc.nextInt();
						sc.nextLine(); // "flush"					
						
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
							
							sp = new SetPackage(name, price, description);
							println("Enter a line of Ala Carte IDs to be added to the Set Package | Enter -1 to end: ");
							
							// Getting and Adding AlaCarte object
							while (sc.hasNextInt()) {
								id = sc.nextInt();
								if (id == -1) {
									break;
								}
								
								ac = menu.getAlaCarteById(id);
								
								if (ac == null) {
									println("Error: Ala Carte with ID: " + id + " does not exist!");
								} else {
									// Add AlaCarte to SetPackage
									sp.addAlaCarte(ac.copy());
									println("Ala Carte with ID: " + id + " has been added to the Set Package!");
								}
							}
							
							// Add SetPackage to the Menu
							menu.addSetPackage(sp);
							println("Set Package: " + name + " has been added to the Menu!");
							
							break;
						
						// Update a particular Set Package
						case 2:
							// Get the Set Package to be updated by ID first (menu.getSetPackageById(int id))
							// Update -> switch () based on what is going to be updated
							
							menu.printMenu();
							print("Enter the Set Package ID: ");
							id = sc.nextInt();
							
							sp = menu.getSetPackageById(id);
							if (sp == null) {
								println("Error: Set Package with ID: " + id + " does not exist!");
								continue;
							}
							
							do {
								// Print sub menu
								println("\n1. Update name");
								println("2. Update price");
								println("3. Update description");
								println("4. Update all fields"); // Rather than choosing 1 and then 2 etc.
								println("5. Back");
								print("Enter your choice: ");
								subChoice2 = sc.nextInt();
								sc.nextLine(); // "flush"
								
								switch (subChoice2) {
								case 1:
									print("Enter the new name: ");
									name = sc.nextLine();
									
									sp.setName(name);
									println("The name of Set Package with ID: " + id + " has been modified!");
									break;
									
								case 2:
									print("Enter the new price: ");
									price = sc.nextDouble();
									
									sp.setPrice(price);
									println("The price of Set Package with ID: " + id + " has been modified!");
									break;
									
								case 3:
									print("Enter the new description in one line: ");
									description = sc.nextLine();
									
									sp.setDescription(description);
									println("The description of Set Package with ID: " + id + " has been modified!");
									break;
									
								case 4:
									print("Enter the new name: ");
									name = sc.nextLine();
									
									print("Enter the new price: ");
									price = sc.nextDouble();
									sc.nextLine(); // "flush"
									
									print("Enter the new description in one line: ");
									description = sc.nextLine();
									
									sp.setName(name);
									sp.setPrice(price);
									sp.setDescription(description);
	
									println("Set Package with ID: " + id + " has been modified!");
									break;
								}
							} while (1 <= subChoice2 && subChoice2 <= 4);
									
							break;
						
						// Remove an entire Set Package from the Menu
						case 3:
							// Print Set Package only (including the SetPackage's ID)
							print("Enter Set Package ID to be removed: ");
							id = sc.nextInt();
							
							menu.removeSetPackageById(id);
							if (menu.removeAlaCarteById(id) == -1) {
								println("Error: Set Package with ID: " + id + " does not exist!");
							} else {
								println("Set Package with ID: " + id + " has been removed from the Menu!");
							}
							
							break;
						}
					} while (1 <= subChoice && subChoice <= 3);
					
					break;
					
				case 3: // Create order
					// Get Table object using TableManager.getTableById(tableId)
					print("Enter table number: ");
					tableId = sc.nextInt();
					table = tableManager.getTableById(tableId);
					
					if (table == null) {
						println("Error: Wrong table number!\n");
						continue;
					}
					
					// check reservation with tableId
					// exist & active -> with reservation
					// not exist -> check tableAvailability
					//			available -> walk-in
					//			n/a		  -> occupied (walk-in or reserved) -> GIVE ERROR CODE!
	
					// Check walk-in or with reservation
					// Search for reservation with tableId and "Active status"
					reservation = reservationManager.getReservationByTableId(tableId, "Active");
					if (reservation == null) {
						if (table.getAvailability()) {
							// Update table availability to false for walk-in customers
							table.setAvailability(false);
						}
					} else {
						// Update Reservation status to "Checked-in" for table with reservation
						reservation.setStatus("checked-in");
					}
					
					// If the table has an order -> ERROR
					if (table.getOrder() != null) {
						println("Error: Table number " + tableId + " has already had an order!");
						println("Please use the update function to update this order!");
						continue;
					}
					
					print("Enter staff ID: ");
					staffId = sc.nextInt();
					staff = staffManager.getStaffById(staffId);
					
					if (staff == null) {
						println("Error: Wrong Staff ID!");
						continue;
					}
					
					// Create new Order Object using OrderManager.createOrder (Calendar dateTime, Staff staff)
					order = orderManager.createOrder(new GregorianCalendar(), staff);
					
					// Print menu
					menu.printMenu();
					
					// Get AlaCarte and SetPackage object using Menu.getAlaCarteById(id) and Menu.getSetPackageById(id)
					// 		CHECK IF getAlaCarteById or getSetPackageById return NULL
					// 		Add all AlaCarte and SetPackage object to Order
					println("Input Ala Carte IDs and quantity (space-separated) | Enter -1 to end: ");
					while (sc.hasNextInt()) {
						id = sc.nextInt();
						
						if (id == -1) {
							break;
						}
						
						ac = menu.getAlaCarteById(id);
						quantity = sc.nextInt();
						
						if (ac == null) {
							println("Error: Ala Carte with ID: " + id + " does not exist!");
						} else if (quantity <= 0) {
							println("Error: Please enter positive integer for quantity!");
						} else {
							// Use copy so that the item attributes are not changed when the item in Menu is changed
							order.addAlaCarte(ac.copy(), quantity);
						}
					}
					
					println("Input Set Package IDs and quantity (space-separated) | Enter -1 to end: ");
					while (sc.hasNextInt()) {
						id = sc.nextInt();
						
						if (id == -1) {
							break;
						}
						
						sp = menu.getSetPackageById(id);
						quantity = sc.nextInt();
						
						if (sp == null) {
							println("Error: Set Package with ID: " + id + " does not exist!\n");
						} else if (quantity <= 0) {
							println("Error: Please enter positive integer for quantity!");
						} else {
							// Use copy so that the item attributes are not changed when the item in Menu is changed
							order.addSetPackage(sp.copy(), quantity);
						}
					}
					
					// tableObject.setOrder(order)
					table.setOrder(order);
					println("An order for table number " + tableId + " has been created!");
					
					break;
					
				case 4: // View order
					print("Enter table number: ");
					tableId = sc.nextInt();
					
					// Get Table object using TableManager.getTableById(tableId)
					table = tableManager.getTableById(tableId);
					
					if (table == null) {
						println("Error: Wrong table number!\n");
						continue;
					}
					
					// Get Order object using Table.getOrder()
					order = table.getOrder();
					
					if (order == null) {
						println("Error: Table " + tableId + " does not have any order!\n");
						continue;
					}
					
					// Order.printOrder()
					order.printOrder();
					
					break;
					
				case 5: // Add/Remove order item/s to/from order
					
					// ask for orderId
					print("Enter table number: ");
					tableId = sc.nextInt();
					
					// get Order Object from OrderManager.getOrderById(orderId)
					table = tableManager.getTableById(tableId);
					if (table == null) {
						println("Error: Wrong table number!");
						continue;
					}
					
					// Get the Order object
					order = table.getOrder();
					if (order == null) {
						println("Error: Table number " + tableId + " does not have any order!");
					}
	
					// switch: add/remove
					println("1. Add order items to order");
					println("2. Remove order items from order");
					println("3. Back");
					print("Enter your choice: ");
					
					subChoice = sc.nextInt();
					switch(subChoice) {
					case 1:
						// add:
						// 		switch: AlaCarte or SetPackage
						// 		Input alaCarteIds and setPackageIds
						//		ac = Menu.getAlaCarteById(alaCarteIds); sp = Menu.getSetPackageById(setPackageIds)
						//		Order.addAlaCarte(ac); Order.addSetPackage(sp)
						
						menu.printMenu();
						println("\nInput AlaCarte IDs and quantity (space-separated) | Enter -1 to end: ");
						while (sc.hasNextInt()) {
							id = sc.nextInt();
							
							if (id == -1) {
								break;
							}
							
							ac = menu.getAlaCarteById(id);
							quantity = sc.nextInt();
							
							if (ac == null) {
								println("Error: Ala Carte with the given ID does not exist!");
							} else if (quantity <= 0) {
								println("Error: Please enter positive integer for quantity!");
							} else {
								order.addAlaCarte(ac.copy(), quantity);
							}
						}
						
						println("Input SetPackage IDs and quantity (space-separated) | Enter -1 to end: ");
						while (sc.hasNextInt()) {
							id = sc.nextInt();
							
							if (id == -1) {
								break;
							}
							
							sp = menu.getSetPackageById(id);
							quantity = sc.nextInt();
							
							if (sp == null) {
								println("Error: Set Package with the given ID does not exist!");
							} else if (quantity <= 0) {
								println("Error: Please enter positive integer for quantity!");
							} else {
								order.addSetPackage(sp.copy(), quantity);
							}
						}
						
						break;
						
					case 2:
						// remove:
						// 		switch: AlaCarte or SetPackage
						// 		Input alaCarteIds and setPackageIds
						//		Order.removeAlaCarteById(alaCarteIds); Order.removeSetPackageByItemId(setPackageIds)
							
						menu.printMenu();
						print("Input AlaCarte IDs (space-separated) | Enter -1 to end: ");
						while (sc.hasNextInt()) {
							id = sc.nextInt();
							
							if (id == -1) {
								break;
							}
							
							if (order.removeAlaCarteByItemId(id) == 1) {
								println("Ala Carte with ID: " + id + " has been removed from the order!");
							} else {
								println("Error: Ala Carte with ID: " + id + " does not exist in the order!");
								continue;
							}
						}
						
						print("Input SetPackage IDs (space-separated) | Enter -1 to end: ");
						while (sc.hasNextInt()) {
							id = sc.nextInt();
							
							if (id == -1) {
								break;
							}
							
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
					
				case 6: // Create reservation booking
					//	input customer details: name, id, membership, contact
					println("Input customer details:");
					print("Name: ");
					name = sc.nextLine();
					
					print("ID: ");
					id = sc.nextInt();
					sc.nextLine(); // "flush"
					
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
					
					print("Contact: ");
					contact = sc.nextInt();
					sc.nextLine(); // "flush"
					
					//		create new Customer object
					customer = new Customer(name, id, membership, contact);
					
					
					//	input dateTime: date & time of reservation
					System.out.println("Input date and time to reserve (Format: dd-MM-yyyy HH:mm, e.g. 01-01-2014 09:00): ");
					SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
					dateTimeString = sc.nextLine();
					
					// Building a Calendar object to be passed on to the Reservation object later
					try {
						dateTime.setTime(format.parse(dateTimeString));
					} catch (ParseException e) {
						println("Error: Wrong date format!");
						e.printStackTrace();
						continue;
					}
					
					//	input numPeople
					print("Input number of people: ");
					numPeople = sc.nextInt();
					
					// run ReservationManager.checkExpiry() -> remove expired Reservations
					reservationManager.checkExpiry();
					
					// get Table object: TableManager.allocateTable(numPeople)
					table = tableManager.allocateTable(numPeople);
					
					if (table == null) {
						println("No table is allocated because the restaurant is full or no table with enough seats is available!");
					} else {
						reservationId = reservationManager.createReservation(customer, dateTime, table, numPeople);
						
						// Feedback
						if (reservationId != -1) {
							println("A new reservation is created with ID: " + reservationId + "!");
						} else {
							System.out.println("Error: Cannot book in past time!");
						}
					}
					
					break;
					
				case 7: // Check/Remove reservation booking
					// input reservationId
					print("Enter reservation ID: ");
					reservationId = sc.nextInt();
									
					// ask if want to removeReservation
					println("1. Check reservation");
					println("2. Remove reservation");
					println("3. Back");
					print("Enter your choice: ");
					subChoice = sc.nextInt();
					
					switch (subChoice) {
					case 1:
						if (reservationManager.checkReservation(reservationId) == -1) {
							println("Error: Reservation with ID: " + reservationId + " does not exist!");
						}
						
						break;
						
					case 2:
						if (reservationManager.cancelReservation(reservationId) == 1) {
							println("Reservation with ID: " + reservationId + " has been removed!");
						} else {
							println("Error: Reservation with ID: " + reservationId + " does not exist!");
						}
						
						break;
					}
					
					break;
					
				case 8: // Check table availability
					// input tableId
					// run TableManager.getTableById(tableId).getAvailability()
					print("Input table number: ");
					tableId = sc.nextInt();
					table = tableManager.getTableById(tableId);
					
					if (table == null) {
						println("Error: Table number " + tableId + " does not exist!\n");
					} else {
						if (table.getAvailability()) {
							println("Table number " + tableId + " is available!");
						} else {
							println("Table number " + tableId + " is unavailable!");
						}
					}
					
					break;
					
				case 9: // Print order invoice
					print("Enter table number: ");
					tableId = sc.nextInt();
					sc.nextLine(); // "flush"
					table = tableManager.getTableById(tableId);
					
					// If the tableId given is invalid
					if (table == null) {
						println("Error: Table number " + tableId + " does not exist!\n");
						continue;
					}
					
					// The table must be assigned to a customer in order to have an Order object
					if (table.getAvailability()) {
						println("Error: Table number: " + tableId + " does not have customer!\n");
						continue;
					}
	
					// Get Order object from Table 
					order = table.getOrder();
					if (order == null) {
						println("Error: Table number " + tableId + " does not have any order!");
						continue;
					}
					
					// If there is a customer on that table -> check by reservation or walk in
					reservation = reservationManager.getReservationByTableId(tableId, "Checked-in");
					if (reservation == null) {
						// walk-in customer
						print("Membership (Y/N)? ");
						membershipString = sc.nextLine();
						
						if (membershipString.equalsIgnoreCase("y")) {
							membership = true;
						} else {
							membership = false;
						}
						
					} else {
						// customer with reservation
						membership = reservation.getCustomer().getMembership();
						
						// Release the reservation + table (See Reservation.setStatus())
						reservation.setStatus("Finished");
					}
					
					// Close the order AND release the table
					order.setStatus("Closed");
					table.setOrder(null);
					
					// create Order Invoice
					orderInvoice = orderInvoiceManager.createOrderInvoice(order, membership, tableId);
					
					// Directly print the newly created OrderInvoice object
					orderInvoice.printInvoice();
					
					break;
					
				case 10: // Print sale revenue report by period (eg day or month)
					String period;
					int check;
					
					// Input the revenue report period
					print("Enter period (MMYYYY or DDMMYYYY): ");
					period = sc.next();
					
					// Print the report and check the return value (in case of error)
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
					println("Bye~");
				}
				
				println();

			} while (1 <= choice && choice <= 10);
			
			// Close Scanner object
			sc.close();	
			
		} else {
			System.out.println("Error: Data is not succesfully fetched from the Database!");
		}
	}
}
