package modele;

import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;
import java.util.Random;

import vue.Vue;

/**
 * <p>
 * <b>Route</b> est une classe <b>modèle</b> du MVC.
 *
 * Elle a pour attributs :
 * <ul>
 * <li>une Liste (ArrayList) de points (Point2D) désignant la limite gauche de
 * la route : routeGauche
 * </li>
 * <li>une Liste (ArrayList) de points (Point2D) désignant la limite droite de
 * la route : routeDroite
 * </li>
 * </ul>
 * et possède 3 fonctions :
 * <ul>
 * <li>la fonction randomInt, qui prend pour paramètre deux entiers mini et
 * maxi, et renvoie un entier entre mini et maxi-1 </li>
 * <li>la fonction genererLigne, qui remplit les 2 ArrayList attributs de la
 * classe par des points</li>
 * <li>la fonction genererVirage, qui prend en paramètres trois points
 * (Point2D), un point de début, de fin et de controle, et renvoie la courbe de
 * bézier correspondante</li>
 * </ul>
 * Elle possède également une constante, INC_ROUTE, qui définit l'espace entre
 * les coordonnées en Y des points des ArrayList
 * </p>
 *
 * @author gpoisson, yallouane
 * @version 1.0
 */
public class Route {

    public final static int INC_ROUTE = 200;

    /**
     * Attributs
     *
     * 2 ArrayList, qui contiennent les points des courbes qui definissent les
     * limites de la route
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
    }

    /**
     * Generation de virages
     *
     * @param debut : Point2D : Point de début de la courbe
     * @param ctrl : Point2D : Point de control de la courbe
     * @param fin : Point2D : Point de fin de la courbe
     * @return QuadCurve2D : Une courbe de bézier
     */
    public QuadCurve2D genererVirage(Point2D debut, Point2D ctrl, Point2D fin) {
        QuadCurve2D courbe = new QuadCurve2D.Double();
        courbe.setCurve(debut, ctrl, fin);
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
