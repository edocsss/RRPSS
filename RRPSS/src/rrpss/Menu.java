package rrpss;
import java.io.Serializable;
import java.util.Collections;
import java.util.Vector;
/**
 * Manages the {@link alaCartes} and {@link setPackages} of the {@link menu} by providing accessor(get methods) of the list of List of alaCarte & setPackage, and individual alaCarte & setPackage,
 * various methods to add new alaCartes/setPackages and remove existing alaCartes/setPackages.
 * @author Liu Liling
 * @see Serializable
 */
public class Menu implements Serializable {
    /**
     * List of alaCarte and setPackage, implemented in {@link Vector} data structure.
     * Each entry consists of a reference to existing {@link alaCartes}/{@link setPackages}object.
     */
	private Vector <AlaCarte> alaCartes;
	
    /**
     * Id of alaCartes, which is equal to the size of {@link Menu#alaCarte alaCartes} vector.
     */
	private Vector <SetPackage> setPackages;   
	
    /**
     * Id of setPackages, which is equal to the size of {@link Menur#setPackage setPackages} vector.
     */
	private int idCounter;
	
	/**
     * Constructs an {@code Menu} object and
     * initialize the attributes {@code alaCartes}/{@code setPackage} and set {@code idCounter} to 1.
     */
	public Menu() {
		alaCartes = new Vector<AlaCarte>();
		setPackages = new Vector<SetPackage>();
		idCounter = 1;
	}
	
    /**
     * Returns list of alaCartes in {@link Vector} data structure
     * @return list of alaCartes in {@link Vector} data structure
     */
	public Vector<AlaCarte> getAlaCartes() {
		return alaCartes;
	}
	
    /**
     * Returns list of setPackages in {@link Vector} data structure
     * @return list of setPackages in {@link Vector} data structure
     */
	public Vector<SetPackage> getSetPackages() {
		return setPackages;
	}
	
	 /**
     * Using alaCarte id to retreive a single {@link alaCarte} object from the menu.
     * @param  id   the alaCarte id to be retreived.
     * @return {@AlaCarte} object of given id.
     */	
	public AlaCarte getAlaCarteById(int id) {
		int numAlaCartes = this.alaCartes.size();
		for (int i = 0; i < numAlaCartes; i++) {
			if (this.alaCartes.get(i).getId() == id) {
				return this.alaCartes.get(i);
			}
		}
		
		return null;
	}
	
	/**
    * Using setPackage id to retreive a single {@link setPackage} object from the menu.
    * @param  id   the setPackage id to be retreived.
    * @return {@SetPackage} object of given id.
    */	
	public SetPackage getSetPackageById(int id) {
		int numSetPackages = this.setPackages.size();
		for (int i = 0; i < numSetPackages; i++) {
			if (this.setPackages.get(i).getId() == id) {
				return this.setPackages.get(i);
			}
		}
		
		return null;
	}
	
	/**
     * Add a alaCarte to the vector
     * @param	ac	alaCarte object to be added
     */	
	public void addAlaCarte(AlaCarte ac) {
		ac.setId(idCounter++);
		alaCartes.add(ac);
	}
	
	/**
     * Add a setPackage to the vector
     * @param	sp	setPackage object to be added
     */	
	public void addSetPackage(SetPackage sp) {
		sp.setId(idCounter++);
		setPackages.add(sp);
	}
	
	/**
    * Using alaCarte id to select a alaCarte by its id and remove it from the alaCarte list ({@code alaCarte})
    * @param id    alaCarte id, positive integer less than or equal to {@code numAlaCarte}
    */	
	public int removeAlaCarteById(int id) {
		int numAlaCartes = this.alaCartes.size();
		for (int i = 0; i < numAlaCartes; i++) {
			if (this.alaCartes.get(i).getId() == id) {
				this.alaCartes.remove(i);
				return 1;
			}
		}
		
		return -1;
	}
	
	/**
    * Using setPackage id to select a setPackage by its id and remove it from the setPackage list ({@code setPackage})
    * @param id    setPackage id, positive integer less than or equal to {@code numSetPackage}
    * @return	1 if successful, -1 otherwise.
    */		
	public int removeSetPackageById(int id) {
		int numSetPackages = this.setPackages.size();
		for (int i = 0; i < numSetPackages; i++) {
			if (this.setPackages.get(i).getId() == id) {
				this.setPackages.remove(i);
				return 1;
			}
		}
		
		return -1;
	}
	
    /**
    * Prints the current Menu information to standard output in a nice format.
    */
	public void printMenu () {
		// sort by type
		Collections.sort(alaCartes);
		
		System.out.println("                                                        MENU                                                         ");
		System.out.println("=====================================================================================================================");
		System.out.println(String.format("%-5s%-35s%-10s%-50s%-15s",
				"ID", "Name", "Price", "Description", "Type"));
		System.out.println("=====================================================================================================================");
		
		System.out.println("Ala Carte:");
		for (AlaCarte ac: alaCartes) {
			System.out.println(String.format("%-5s%-35s%-10.2f%-50s%-15s",
					ac.id, ac.name, ac.price, ac.description, ac.getType()));
		}
		
		System.out.println("\nSet Package:");
		for (SetPackage sp: setPackages) {
			System.out.println(String.format("%-5s%-35s%-10.2f%-50s",
					sp.id, sp.name, sp.price, sp.description));
			for (AlaCarte ac: sp.getAlaCartes()) {
				System.out.println(String.format("%-5s%-5s%-40s%-50s%-15s",
						" ", ac.id, ac.name, ac.description, ac.getType()));
			}
		}
		
		System.out.println("=====================================================================================================================");
		System.out.println("");
	}
}