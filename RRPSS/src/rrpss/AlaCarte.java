package rrpss;

public class AlaCarte extends Item {
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
		ac.setId(id);
		return ac;
	}
}