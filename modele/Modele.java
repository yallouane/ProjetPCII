package modele;

import vue.Vue;

public class Modele {

    /**
     * Attributs (La classe modele a pour attributs touts les modeles
     */
    //Vue du modele
    private Vue vue;
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
     * @return Vehicule, route, vue
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

    public Vue getVue() {
        return vue;
    }

    public void setVue(Vue view) {
        this.vue = view;
    }

}
