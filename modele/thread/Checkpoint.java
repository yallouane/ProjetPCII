package modele.thread;

import modele.Modele;

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
public class Checkpoint extends Thread {

    Modele modele;

    public static final int DUREE_CHECKPOINT = 100;

    int timer;

    public Checkpoint(Modele modele) {
        this.modele = modele;
        this.timer = 1;
        new Thread(this).start();
    }

    public Modele getModele() {
        return modele;
    }

    public void setModele(Modele modele) {
        this.modele = modele;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    @Override
    public void run() {
        timer++;
        if (timer % DUREE_CHECKPOINT == 0) {
            System.out.println("GAME OVER");
        }
        try {
            Thread.sleep(100, 0);
        } catch (InterruptedException ex) {
            System.out.print("Problème Defilement Sleep Checkpoint");
        }
    }

}
