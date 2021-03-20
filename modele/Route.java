package modele;

import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;
import java.util.Random;

import vue.Vue;

public class Route {

    public final static int INC_ROUTE = 200;

    /**
     * Attributs (2 arrayList, qui contiennent les points des courbes qui
     * definissent les limites de la route)
     */
    private ArrayList<Point2D> routeGauche;
    private ArrayList<Point2D> routeDroite;

    /**
     * Constructeur
     */
    public Route() {
        this.routeGauche = new ArrayList<>();
        this.routeDroite = new ArrayList<>();
    }

    /**
     * Fonction générant un nombre aléatoire
     *
     * @param mini : Integer
     * @param maxi : Integer
     * @return Integer
     */
    public int randomInt(int mini, int maxi) {
        Random r = new Random();
        int res = r.nextInt(maxi - mini) + mini;
        return res;
    }

    /**
     * Generation des lignes
     */
    public void genererLigne() {
        for (int i = Vue.P_HEIGHT; i >= Vue.LIGNEHORIZONY + Route.INC_ROUTE; i -= INC_ROUTE) {
            this.routeDroite.add(new Point2D.Double(Vue.ROUTE_DROITE, (double) i));
            this.routeGauche.add(new Point2D.Double(Vue.ROUTE_GAUCHE, (double) i));
        }
        //genererVirage();
    }

    /**
     * Generation de virages
     *
     * @return QuadCurve2D
     */

    public QuadCurve2D genererVirageGG() {
        QuadCurve2D courbe = new QuadCurve2D.Double();
        Point2D.Double debutG = new Point2D.Double(Vue.ROUTE_GAUCHE, Vue.P_HEIGHT / 2);
        Point2D.Double ctrlG = new Point2D.Double(Vue.ROUTE_GAUCHE, Vue.LIGNEHORIZONY);
        Point2D.Double fin = new Point2D.Double(0, Vue.LIGNEHORIZONY);
        courbe.setCurve(debutG, ctrlG, fin);
        return courbe;
    }
    
    public QuadCurve2D genererVirageGD() {
        QuadCurve2D courbe = new QuadCurve2D.Double();
        Point2D.Double debutD = new Point2D.Double(Vue.ROUTE_DROITE, Vue.P_HEIGHT / 2);
        Point2D.Double ctrlD = new Point2D.Double(Vue.ROUTE_DROITE, Vue.LIGNEHORIZONY);
        Point2D.Double fin = new Point2D.Double(0, Vue.LIGNEHORIZONY);
        courbe.setCurve(debutD, ctrlD, fin);
        return courbe;
    }

    /**
     * Getters & Setters
     *
     * @return ArrayLists de Point2D
     */
    public ArrayList<Point2D> getRouteGauche() {
        return this.routeGauche;
    }

    public ArrayList<Point2D> getRouteDroite() {
        return this.routeDroite;
    }

    public void setRouteGauche(ArrayList<Point2D> routeGauche) {
        this.routeGauche = routeGauche;
    }

    public void setRouteDroite(ArrayList<Point2D> routeDroite) {
        this.routeDroite = routeDroite;
    }
}
