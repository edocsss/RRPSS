package rrpss;

public class AlaCarte extends Item implements Comparable<AlaCarte> {
	private String type;
	
	public AlaCarte(String name, double price, String description, String type) {
		super(name, price, description);
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public AlaCarte copy() {
		AlaCarte ac = new AlaCarte(name, price, description, type);
		ac.setId(this.id); // Since ID is not set in the constructor (purpose: to assign unique ID when an AlaCarte is created)
		
		return ac;
	}
	/**
	 * This method is used for sorting the alacarte item by type at Menu class
	 */
	@Override
	public int compareTo(AlaCarte ac) {
		return this.type.compareTo(ac.type);
	}
}
