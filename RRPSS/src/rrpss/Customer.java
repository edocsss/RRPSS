package rrpss;

public class Customer extends Person {
	private boolean membership;
	private String contact;
	
	public Customer(String name, int id, boolean membership, String contact) {
		super(name, id);
		this.contact = contact;
		this.membership = membership;
	}
	
	public boolean getMembership(){
		return membership;
	}
	
	public String getContact() {
		return contact;
	}
	
	public void setMembership(boolean membership) {
		this.membership = membership;
	}
}
