package rrpss;

public class Customer extends Person {
	private boolean membership;
	private int contact;
	
	public Customer(String name, int id, boolean membership, int contact) {
		super(name, id);
	}
	
	public boolean getMembership(){
		return membership;
	}
	
	public int getContact() {
		return contact;
	}
	
	public void setMembership(boolean membership) {
		this.membership = membership;
	}
	
	public void setContact(int contact) {
		this.contact = contact;
	}
	
}
