package rrpss;

/**
 * Stores the information of all {@link Customer}, which includes the membership status, contact, and the reservation made. 
 * Class {@link Customer} inherits from superclass {@link Person} 
 * 
 * @author YanHan
 */
public class Customer extends Person {
	/**
	 * Distinguish if object of {@link Customer} is under membership status 
	 */
	private boolean membership;
	
	/**
	 * Contact details of this Customer 
	 */
	private String contact;
	

	/**
	 * Constructs a {@link Customer} object with membership status, contact detail, and 
	 * with name and id inherited from superclass {@link Person} 
	 * 
	 * @param name 			Indicate identity of this object 
	 * @param id 			Distinguish one object from another 
	 * @param membership 	Indicate membership status of object 
	 * @param contact 		Indicate contact detail of object 
	 */
	public Customer(String name, int id, boolean membership, String contact) {
		super(name, id);
		this.contact = contact;
		this.membership = membership;
	}

	/**
	 * Returns the {@link Customer#membership} status of object 
	 * @return the {@link Customer#membership} status of object 
	 */
	public boolean getMembership(){
		return membership;
	}

	/**
	 * Returns the {@link Customer#contact} of object
	 * @return the {@link Customer#contact} of object 
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * Set the {@link Customer#membership} attribute for this object
	 * 
	 * @param membership Boolean used to distinguish privilege status of {@link Customer} 
	 */
	public void setMembership(boolean membership) {
		this.membership = membership;
	}
}
