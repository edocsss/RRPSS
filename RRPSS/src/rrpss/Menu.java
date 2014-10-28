package rrpss;
import java.util.Vector;

public class Menu {
	private Vector <AlaCarte> alaCartes;
	private Vector <SetPackage> setPackages;
	
	public Menu() {
		alaCartes = new Vector<AlaCarte>();
		setPackages = new Vector<SetPackage>();
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
			if (this.alaCartes.get(i).getId() == id) {
				return this.setPackages.get(i);
			}
		}
		
		return null;
	}
	public void addAlaCarte(AlaCarte a) {
		a.setId(alaCartes.size() + setPackages.size() + 1);
	}
	public void addSetPackage(SetPackage s) {
		s.setId(alaCartes.size() + setPackages.size() + 1);
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
}