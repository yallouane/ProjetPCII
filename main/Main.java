package main;

import javax.swing.JFrame;

import controleur.Deplacement;
import vue.*;
import modele.*;
import modele.thread.Defilement;

public class Main {

    public static void main(String[] args) {
        Route route = new Route();
        route.genererLigne();
        Vehicule vehicule = new Vehicule(Vue.OVAL_X, Vue.OVAL_Y);
        Modele modele = new Modele(vehicule, route);
        Vue affichage = new Vue(modele);
        modele.setVue(affichage);
        Deplacement deplacement = new Deplacement(affichage, vehicule);
        Defilement defilement = new Defilement(modele);
        affichage.addKeyListener(deplacement);
        JFrame test = new JFrame("Course");
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        test.add(affichage);
        test.pack();
        test.setVisible(true);

    }

}
