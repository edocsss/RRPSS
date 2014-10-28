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
		this.alaCartes = alaCartes;
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
		sp.setId(id);
		return sp;
	}
}
