package modele;

public class Modele {
	/**
     * Attributs (La classe modele a pour attributs touts les modeles)
     */
    // Modele Vehicule
    private Vehicule vehicule;
    // Modele Route
    private Route route;

    /**
     * Constructeur
     *
     * @param vehicule
     * @param route
     */
    public Modele(Vehicule vehicule, Route route) {
        this.route = route;
        this.vehicule = vehicule;
    }

    /**
     * Getters & Setters
     *
     * @return Vehicule, route
     */
    public Vehicule getVehicule() {
        return this.vehicule;
    }

    public Route getRoute() {
        return this.route;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
}
