package modele;

import modele.thread.Timer;
import modele.thread.Virage;
import vue.Vue;

/**
 * <p>
 * La classe <b>Modele</b> est le <b>modele</b> du modele <b>MVC</b> du projet
 * <b>Course</b>.
 *
 * elle regroupe (est liee a) toutes les classes modele
 *
 * Elle a pour attributs :
 * <ul>
 * <li>La vue associee au modele : vue (@see vue.Vue.java)</li>
 * <li>le modele du vehicule : vehicule (@see modele.Vehicule.java)</li>
 * <li>le modele de la route : route (@see modele.Route.java)</li>
 * <li>le thread du timer : timer (@see modele.thread.Timer.java)</li>
 * <li>le thread du virage : virage (@see modele.thread.Virage.java)</li>
 * <li>le modele de l'obstacle : obstacle (@see modele.Obstacle.java)</li>
 * <li>le modele du checkpoint : checkpoint (@see modele.Checkpoint.java)</li>
 * </ul>
 *
 * et contient tout leurs Getters/Setters.
 *
 * Elle ne possede qu'un seul constructeur, qui prend pour parametre le modele
 * du vehicule et celui de la route, et initialise les attributs qui leurs sont
 * associes. Tout les autres attributs sont initialises avec des setters
 * </p>
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
    private Timer timer;
    // Thread Virage
    private Virage virage;
    // Modele Obstacle
    private Obstacle obstacle;
    // Modele Checkpoint
    private Checkpoint checkpoint;

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
     * @return Vehicule, Route, Vue, Timer, Virage, Obstacle, Checkpoint
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

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public Virage getVirage() {
        return virage;
    }

    public void setVirage(Virage virage) {
        this.virage = virage;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public Checkpoint getCheckpoint() {
        return checkpoint;
    }

    public void setCheckpoint(Checkpoint checkpoint) {
        this.checkpoint = checkpoint;
    }

}
