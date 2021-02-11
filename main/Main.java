package main;

import javax.swing.JFrame;

import controleur.Deplacement;
import vue.*;
import modele.*;

public class Main {

	public static void main(String[] args) {
		Route route = new Route();
		route.genererLigne();
		Vehicule vehicule = new Vehicule(Vue.OVAL_X, Vue.OVAL_Y);
		Modele modele = new Modele(vehicule, route);
		Vue affichage = new Vue(modele);
		Deplacement deplacement = new Deplacement(affichage,vehicule);
		affichage.addKeyListener(deplacement);
		JFrame test = new JFrame("Course");
	    test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    test.add(affichage);
	    test.pack();
	    test.setVisible(true);
	    
	}

}
