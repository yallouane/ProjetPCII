package main;

import javax.swing.JFrame;

import controleur.Deplacement;
import vue.*;
import modele.*;
import modele.thread.Checkpoint;
import modele.thread.Virage;

/**
 * <p>
 * <b>Main</b> est la <b>classe principale</b> du projet <b>Course</b>.
 *
 * Elle initialise toute les classes, à savoir :
 * <ul>
 * <li>le modèle de la route : route (@see modele.Route.java)</li>
 * <li>le modèle du vehicule : vehicule (@see modele.Vehicule.java)</li>
 * <li>le modèle principal : modele (@see modele.Modele.java)</li>
 * <li>la vue : affichage (@see vue.Vue.java)</li>
 * <li>le controleur : deplacement (@see controleur.Deplacement.java)</li>
 * <li>le thread de virage : virage (@see modele.thread.Virage.java)</li>
 * <li>le thread de checkpoint : timer (@see modele.thread.Checkpoint.java)</li>
 * </ul>
 * </p>
 *
 * @author gpoisson, yallouane
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {
        Route route = new Route();
        route.genererLigne();
        Vehicule vehicule = new Vehicule(Vue.OVAL_X, Vue.OVAL_Y);
        Modele modele = new Modele(vehicule, route);
        vehicule.setModele(modele);
        Vue affichage = new Vue(modele);
        modele.setVue(affichage);
        Deplacement deplacement = new Deplacement(affichage, vehicule);
        affichage.setKl(deplacement);
        Virage virage = new Virage(modele);
        Checkpoint timer = new Checkpoint(modele);
        modele.setTimer(timer);
        modele.setVirage(virage);
        affichage.addKeyListener(deplacement);
        JFrame test = new JFrame("Course");
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        test.add(affichage);
        test.pack();
        test.setVisible(true);

    }

}
