package vue;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import modele.Modele;
import modele.Route;

public class Vue extends JPanel {

    /**
     * CONSTANTES
     */
    /**
     * Largeur et longueur de la fenetre
     */
    public static final int P_WIDTH = 1080;
    public static final int P_HEIGHT = 800;

    /**
     * Largeur et longueur de l'ovale
     */
    public static final int OVAL_WIDTH = 75;
    public static final int OVAL_HEIGHT = 75;

    /**
     * Position par default de l'ovale en x (Position de depart)
     */
    public static final int POS_DEP = 100;
    /**
     * Position limite de l'ovale lorsqu'il accelere ou decelere
     */
    public static final int ACC_LIMITE = P_HEIGHT - 150;
    public static final int DEC_LIMITE = P_HEIGHT - 50;

    /**
     * Position en x de la ligne d'horizon (a partir du haut de la fenetre)
     */
    public static final int LIGNEHORIZONY = 200;

    /**
     * Position de d�part en x et en y de l'ovale
     */
    public static final int OVAL_X = P_WIDTH / 2 - OVAL_WIDTH / 2;
    public static final int OVAL_Y = P_HEIGHT - POS_DEP;

    /**
     * Position des deux lignes, limite de la route
     */
    public static final double ROUTE_DROITE = 3 * P_WIDTH / 8;
    public static final double ROUTE_GAUCHE = 5 * P_WIDTH / 8;
    
    /**
     * Constante entiere servant à l'impression de profondeur
     */
    public static final int IMP_PROF = 5;

    /**
     * Attributs
     */
    // Modele de la vue
    private Modele modele;
    
    private int valeurVirageG;// Incrementer pour aller à droite, decrementer pour aller a gauche
    private int valeurVirageD;// Incrementer pour aller à gauche, decrementer pour aller a droite

    /**
     * Constructeur
     *
     * @param modele
     */
    public Vue(Modele modele) {
        setPreferredSize(new Dimension(P_WIDTH, P_HEIGHT));
        this.modele = modele;
        this.valeurVirageG = -400;
        this.valeurVirageD = 400;
    }

    /**
     * Getters & Setters
     *
     * @return Modele
     */
    public Modele getModele() {
        return modele;
    }

    public void setModele(Modele modele) {
        this.modele = modele;
    }

    public int getValeurVirageG() {
        return valeurVirageG;
    }

    public void setValeurVirageG(int valeurVirageG) {
        this.valeurVirageG = valeurVirageG;
    }

    public int getValeurVirageD() {
        return valeurVirageD;
    }

    public void setValeurVirageD(int valeurVirageD) {
        this.valeurVirageD = valeurVirageD;
    }
    
    
    
    

    @Override
    public void paint(Graphics g) {
        this.requestFocusInWindow();
        revalidate();
        super.paint(g);
        affichageVehicule(g);
        g.drawString(this.modele.getVehicule().getVitesse() + "", 150, 150); // affichage de la vitesse
        Graphics2D g2 = (Graphics2D) g;
        affichageRoute(g2);
        Point2D middleG = new Point2D.Double(P_WIDTH / 2 + valeurVirageG, LIGNEHORIZONY);
        Point2D middleD = new Point2D.Double(P_WIDTH / 2 - valeurVirageD, LIGNEHORIZONY);
        Point2D debutG = new Point2D.Double(ROUTE_GAUCHE, LIGNEHORIZONY + Route.INC_ROUTE);
        Point2D debutD = new Point2D.Double(ROUTE_DROITE, LIGNEHORIZONY + Route.INC_ROUTE);
        Point2D ctrlG = new Point2D.Double(ROUTE_GAUCHE, LIGNEHORIZONY + Route.INC_ROUTE / 2);
        Point2D ctrlD = new Point2D.Double(ROUTE_DROITE, LIGNEHORIZONY + Route.INC_ROUTE / 2);
        g2.draw(modele.getRoute().genererVirageGG(debutG, ctrlG, middleG));
        g2.draw(modele.getRoute().genererVirageGD(debutD, ctrlD, middleD));
    }

    public void affichageVehicule(Graphics g) {
        try {
            BufferedImage img;
            g.drawLine(0, LIGNEHORIZONY, P_WIDTH, LIGNEHORIZONY);
            img = ImageIO.read(new File("src/ressources/img_vaisseau.png"));
            g.drawImage(img, this.modele.getVehicule().getPositionX(), this.modele.getVehicule().getPositionY(), OVAL_WIDTH, OVAL_HEIGHT, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void affichageRoute(Graphics2D g2) {
        for (int i = 0; i < this.modele.getRoute().getRouteDroite().size() - 1; i++) {
            g2.draw(new Line2D.Double(this.modele.getRoute().getRouteDroite().get(i).getX(), this.modele.getRoute().getRouteDroite().get(i).getY(), this.modele.getRoute().getRouteDroite().get(i + 1).getX(), this.modele.getRoute().getRouteDroite().get(i + 1).getY()));
            g2.draw(new Line2D.Double(this.modele.getRoute().getRouteGauche().get(i).getX(), this.modele.getRoute().getRouteGauche().get(i).getY(), this.modele.getRoute().getRouteGauche().get(i + 1).getX(), this.modele.getRoute().getRouteGauche().get(i + 1).getY()));
        }
    }
}
