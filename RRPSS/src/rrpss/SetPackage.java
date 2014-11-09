package rrpss;

import java.util.Vector;

public class SetPackage extends Item {
	private Vector<AlaCarte> alaCartes;
	
	public SetPackage(String name, double price, String description) {
		super(name, price, description);
		this.alaCartes = new Vector<AlaCarte>();
	}
	
	public Vector<AlaCarte> getAlaCartes() {
		return alaCartes;
	}
	
	public void setAlaCartes(Vector<AlaCarte> alaCartes) {
		for (AlaCarte ac: alaCartes) {
			this.alaCartes.add(ac);
		}
	}
	
	public void addAlaCarte(AlaCarte a) {
		this.alaCartes.add(a);
	}
	
	public void removeAlaCarte(AlaCarte a) {
		this.alaCartes.remove(a);
	}
	
	public SetPackage copy() {
		SetPackage sp = new SetPackage(name, price, description);
		sp.setAlaCartes(alaCartes);
		sp.setId(this.id); // Since ID is not set in the constructor (purpose: to assign unique ID when a SetPackage is created)

		return sp;
	}
}
