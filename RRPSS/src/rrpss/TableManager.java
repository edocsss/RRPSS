package rrpss;

import java.io.Serializable;
import java.util.Collections;
import java.util.Vector;

/**
 * Manages the {@link Table} of the {@link Restaurant} by providing getters of the list of tables and individual table,
 * various methods to add new table, remove existing table, and allocate table for an order.
 * 
 * @author 	Kenrick
 * @see	 	Serializable
 *
 */
public class TableManager implements Serializable {
	/**
	 * List of tables, implemented in {@link Vector} data structure.
	 * Each entry consists of a reference to existing {@link Table} object.
	 */
	private Vector <Table> tables;
	
	/**
	 * Number of tables, which is equal to the size of {@link TableManager#tables tables} vector.
	 */
	private int numTables;
	
	/**
	 * Constructs an {@code TableManager} object and
	 * initialize the attributes {@code tables} to empty and {@code numTables} to zero.
	 */
	public TableManager() {
		tables = new Vector <Table> ();
		numTables = 0;
	}
	
	/**
	 * Returns list of tables in {@link Vector} data structure
	 * @return list of tables in {@link Vector} data structure
	 */
	public Vector <Table> getTables() {
		return tables;
	}
	
	/**
	 * Adds a table to the vector with given capacity.
	 * @param capacity	the intended capacity of table, should be even integer ranging from 2 to 10.
	 */
	public void addTable(int capacity) {
		numTables++;
		int id = numTables;
		tables.add(new Table(id, capacity));
	}
	
	/**
	 * Using table id (table number) to retreive a single {@link Table} object from the manager.
	 * @param id	the table id (or table number) to be retreived.
	 * @return	a {@Table} object of given id.
	 */
	public Table getTableById (int id) {
		for (Table curTable: tables) {
			if (curTable.getId() == id) {
				return curTable;
			}
		}
		
		return null;
	}
	
	/**
	 * Using table id (table number) to select a table by its id and remove it from the table list ({@code tables})
	 * @param id	table id (table number), positive integer less than or equal to {@code numTables}
	 */
	public void removeTable(int id) {
		Table curTable = getTableById(id);
		if (curTable != null) {
			tables.remove(curTable);
			numTables--;
		} else {
			System.out.println("Error: Cannot remove table, table not found :(");
		}
	}
	
	/**
	 * Sorts the entries inside {@code tables} by capacity using {@link Collections#sort Collections.sort}
	 * by implementing {@link Comparable} class in {@link Table} class
	 * and having a compareTo method inside {@link Table} class.
	 */
	private void sortTable() {
		// by capacity
		Collections.sort(tables);
	}
	
	/**
	 * Returns a {@link Table} object that satistifies the number of person and the availability of the table. Returns {@code null} if fail to find one.
	 * 
	 * <p>Table is first sorted and then iterated through from the least capacity. Next, return the table if and only if 
	 * the table is available for allocation and 
	 * number of people that is being allocated ({@code numPeople}) is less than the capacity of the table.
	 * If no table is returned from the iteration, then {@code null} will be returned.</p>
	 * @param numPerson		number of people that is being allocated a table
	 * @return 	<li>a {@link Table} object that satistifies the number of person and the availability of the table.</li>
	 * 			<li>{@code null} if fail to find one.</li>
	 */
	public Table allocateTable(int numPerson) {
		sortTable();
		for (int i = 0 ; i < numTables; i++) {
			Table curTable = tables.get(i);
			if (curTable.getAvailability() && numPerson <= curTable.getCapacity()) {
				curTable.setAvailability(false);
				return curTable;
			}
		}
		
		return null;
	}
}
