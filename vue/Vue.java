package vue;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import modele.Modele;

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
    public static final double ROUTE_DROITE = OVAL_X + 125;
    public static final double ROUTE_GAUCHE = OVAL_X - 125;

    /**
     * Attributs
     */
    // Modele de la vue
    private Modele modele;

    /**
     * Constructeur
     *
     * @param modele
     */
    public Vue(Modele modele) {
        setPreferredSize(new Dimension(P_WIDTH, P_HEIGHT));
        this.modele = modele;
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

    public void paint(Graphics g) {
        this.requestFocusInWindow();
        revalidate();
        super.paint(g);
        affichageVehicule(g);
        g.drawString(this.modele.getVehicule().getVitesse() + "", 150, 150); // affichage de la vitesse
        Graphics2D g2 = (Graphics2D) g;
        affichageRoute(g2);
        g2.draw(modele.getRoute().genererVirageGG());
        g2.draw(modele.getRoute().genererVirageGD());
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
