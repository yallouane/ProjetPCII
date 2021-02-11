package modele;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import vue.Vue;

public class Route {
	/**
     * Attributs (2 arrayList, qui contiennent les points des lignes qui
     * definissent les limites de la route)
     */
    private ArrayList<Point> routeGauche;
    private ArrayList<Point> routeDroite;

    /**
     * Constructeur
     */
    public Route() {
        this.routeGauche = new ArrayList<Point>();
        this.routeDroite = new ArrayList<Point>();
    }

    /**
     * Generation des lignes
     */
    public void genererLigne() {
        for (int i = Vue.P_HEIGHT; i >= Vue.LIGNEHORIZONY; i -= 20) {
            //Random rand = new Random(); 
            //int nombreAleatoire = rand.nextInt(425 - 380 + 1) + 380;
            this.routeDroite.add(new Point(Vue.ROUTE_DROITE, i));
            this.routeGauche.add(new Point(Vue.ROUTE_GAUCHE, i));
        }
    }

    /**
     * Getters & Setters
     *
     * @return ArrayLists de Point
     */
    public ArrayList<Point> getRouteGauche() {
        return this.routeGauche;
    }

    public ArrayList<Point> getRouteDroite() {
        return this.routeDroite;
    }

    public void setRouteGauche(ArrayList<Point> routeGauche) {
        this.routeGauche = routeGauche;
    }

    public void setRouteDroite(ArrayList<Point> routeDroite) {
        this.routeDroite = routeDroite;
    }
}
