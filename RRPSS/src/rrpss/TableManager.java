package rrpss;

import java.util.Collections;
import java.util.Vector;

public class TableManager {
	private Vector <Table> tables;
	private int numTables;
	
	public TableManager() {
		tables = new Vector <Table> ();
		numTables = 0;
	}
	
	public Vector <Table> getTables() {
		return tables;
	}
	
	public void addTable(int capacity) {
		int id = tables.size() + 1;
		tables.add(new Table(id, capacity));
		numTables++;
	}
	
	public Table getTableById (int id) {
		for (int i = 0 ; i < numTables; i++) {
			Table curTable = tables.elementAt(i);
			if (curTable.getId() == id) {
				return curTable;
			}
		}
		
		return null;
	}
	
	public void removeTable(int id) {
		Table curTable = getTableById(id);
		if (curTable != null) {
			tables.remove(curTable);
		} else {
			System.out.println("Error: Cannot remove table, table not found :(");
		}
	}
	
	private void sortTable() {
		// by capacity
		Collections.sort(tables);
	}
	
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
