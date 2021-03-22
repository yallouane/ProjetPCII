package controleur;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import modele.Vehicule;
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
public class Deplacement implements KeyListener {

    private Vue affichage;
    private Vehicule vehicule;

    public Deplacement(Vue vue, Vehicule vehicule) {
        this.affichage = vue;
        this.vehicule = vehicule;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 38:
            case 90:
                // HAUT
                vehicule.deplacement("HAUT");
                affichage.repaint();
                break;
            case 40:
            case 83:
                // BAS
                vehicule.deplacement("BAS");
                affichage.repaint();
                break;
            case 37:
            case 81:
                // GAUCHE
                vehicule.deplacement("GAUCHE");
                affichage.repaint();
                break;
            case 39:
            case 68:
                // DROITE
                vehicule.deplacement("DROITE");
                affichage.repaint();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

}
