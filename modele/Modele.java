package modele;

import modele.thread.Checkpoint;
import modele.thread.Virage;
import vue.Vue;

/**
 * Main est la classe principale du projet Course
 * 
 * Elle initialise toute les classes, à savoir : 
 * 
 * - le modèle de la route      : route (@see modele.Route.java)
 * - le modèle du vehicule      : vehicule (@see modele.Vehicule.java)
 * - le modèle principal        : modele (@see modele.Modele.java)
 * - la vue                     : affichage (@see vue.Vue.java)
 * - le controleur              : deplacement (@see controleur.Deplacement.java)
 * - le thread de virage        : virage (@see modele.thread.Virage.java)
 * - le thread de checkpoint    : timer (@see modele.thread.Checkpoint.java)
 * 
 * @author gpoisson, yallouane
 * @version 1.0
 */
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
    // Thread Timer
    private Checkpoint timer;
    // Thread Virage
    private Virage virage;
    
    private Obstacle obstacle;

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

    public Obstacle getObstacle() {
		return obstacle;
	}

	public void setObstacle(Obstacle obstacle) {
		this.obstacle = obstacle;
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

    public Checkpoint getTimer() {
        return timer;
    }

    public void setTimer(Checkpoint timer) {
        this.timer = timer;
    }

    public Virage getVirage() {
        return virage;
    }

    public void setVirage(Virage virage) {
        this.virage = virage;
    }

}
