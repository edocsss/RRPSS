package rrpss;
import java.io.Serializable;
import java.util.Collections;
import java.util.Vector;

public class Menu implements Serializable {
	private Vector <AlaCarte> alaCartes;
	private Vector <SetPackage> setPackages;
	private int idCounter;
	
	public Menu() {
		alaCartes = new Vector<AlaCarte>();
		setPackages = new Vector<SetPackage>();
		idCounter = 1;
	}
	
	public Vector<AlaCarte> getAlaCartes() {
		return alaCartes;
	}
	
	public Vector<SetPackage> getSetPackages() {
		return setPackages;
	}
	
	public AlaCarte getAlaCarteById(int id) {
		int numAlaCartes = this.alaCartes.size();
		for (int i = 0; i < numAlaCartes; i++) {
			if (this.alaCartes.get(i).getId() == id) {
				return this.alaCartes.get(i);
			}
		}
		
		return null;
	}
	
	public SetPackage getSetPackageById(int id) {
		int numSetPackages = this.setPackages.size();
		for (int i = 0; i < numSetPackages; i++) {
			if (this.setPackages.get(i).getId() == id) {
				return this.setPackages.get(i);
			}
		}
		
		return null;
	}
	
	public void addAlaCarte(AlaCarte ac) {
		ac.setId(idCounter++);
		alaCartes.add(ac);
	}
	
	public void addSetPackage(SetPackage sp) {
		sp.setId(idCounter++);
		setPackages.add(sp);
	}
	
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
	
	public void printMenu () {
		//Something like toString -> like a breakdown of what has been ordered
		// TODO account for long name & description
		
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