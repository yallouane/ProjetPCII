package modele;

public class Modele {
	private Vehicule vehicule;
	private Route route;
	
	public Modele(Vehicule vehicule, Route route) {
		this.route = route;
		this.vehicule = vehicule;
	}
	
	public Vehicule getVehicule() {
		return this.vehicule;
	}
	
	public Route getRoute() {
		return this.route;
	}
	
}
