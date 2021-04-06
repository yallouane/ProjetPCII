package modele.thread;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import modele.Modele;
import vue.Vue;
import modele.Route;
import modele.Vehicule;

/**
 * @deprecated
 * <p>
 * <b>Defilement</b> est une classe <b>depreciée.</b>
 *
 * Elle est <b>soit à refaire, soit à retirer</b>
 *
 * Elle a pour attributs :
 * <ul>
 * <li>le modèle principal : modele (@see modele.Modele.java)</li>
 * </ul>
 * et hérite de la classe Thread. Elle contient donc la fonction run, qui
 * contient le code a faire executer par le thread, ainsi que 2 fonctions :
 * <ul>
 * <li>la fonction ajout_rajout, qui fait reculer les points constituant la
 * route vers le bas, et en rajoute si il doit le faire. </li>
 * <li>la fonction defilementRoute, qui appelle la fonction ajout_rajout et met
 * à jour les attributs du modèle route lié à l'attibut modèle de la classe</li>
 * </ul>
 * </p>
 * @author gpoisson, yallouane
 * @version 1.0
 */
public class Defilement extends Thread {

    public static final int Y_SPEED = 10000;
    private Modele modele;

    public Defilement(Modele modele) {
        this.modele = modele;
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (true) {
            defilementRoute(Vue.P_HEIGHT, this.modele.getVehicule().getVitesse());
            this.modele.getVue().repaint();
            try {
                Thread.sleep(10, 0);
            } catch (InterruptedException ex) {
                System.out.print("Problème Defilement Sleep");
            }
        }
    }

    public void defilementRoute(int hauteurEcran, int vitesse) {
        double vitessedefilement = ((float) (vitesse / (float) (Vehicule.VIT_LIMITE + 1)) * 35);
//        System.out.println("Vitesse : " + vitesse);
//        System.out.println("Vitesse defilement : " + vitessedefilement);
        ArrayList<Point2D> routeDroite = this.modele.getRoute().getRouteDroite();
        ArrayList<Point2D> routeGauche = this.modele.getRoute().getRouteGauche();
        ajout_rajout(routeDroite, routeGauche, vitessedefilement, hauteurEcran); // pour retirer les points et en ajouter de nouveau
        this.modele.getRoute().setRouteDroite(routeDroite);
        this.modele.getRoute().setRouteGauche(routeGauche);
    }

    public void ajout_rajout(ArrayList<Point2D> routeDroite, ArrayList<Point2D> routeGauche, double vitessedefilement, int hauteurEcran) {
        for (int i = 0; i < routeDroite.size(); i++) {

            // on recule les points selon la vitesse de defilement
            routeDroite.get(i).setLocation(routeDroite.get(i).getX(), routeDroite.get(i).getY() + vitessedefilement);
            routeGauche.get(i).setLocation(routeGauche.get(i).getX(), routeGauche.get(i).getY() + vitessedefilement);

            // si le point disparait de l'écran "visible", on en rajoute un nouveau
            if (routeDroite.get(i).getY() > (float) (hauteurEcran + Route.INC_ROUTE)) {
                routeDroite.remove(i);
                double entier = Route.INC_ROUTE + vitessedefilement;
                double droitey = ((routeDroite.get(routeDroite.size() - 1).getY() - entier)) < Vue.LIGNEHORIZONY ? Vue.LIGNEHORIZONY : ((routeDroite.get(routeDroite.size() - 1).getY() - entier));
                Point2D tmp = new Point2D.Double(routeDroite.get(routeDroite.size() - 1).getX(), droitey);
                routeDroite.add(tmp);
                routeGauche.remove(i);
                double gauchey = ((routeGauche.get(routeGauche.size() - 1).getY() - entier)) < Vue.LIGNEHORIZONY ? Vue.LIGNEHORIZONY : ((routeGauche.get(routeGauche.size() - 1).getY() - entier));
                Point2D tmp2 = new Point2D.Double(routeGauche.get(routeGauche.size() - 1).getX(), gauchey);
                routeGauche.add(tmp2);
            }
        }
    }
}
