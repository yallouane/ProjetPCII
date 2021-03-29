package vue;

import controleur.Deplacement;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import modele.Modele;
import modele.Route;
import modele.thread.Checkpoint;

/**
 * Main est la classe principale du projet Course
 *
 * Elle initialise toute les classes, à savoir :
 *
 * - le modèle de la route : route (@see modele.Route.java) - le modèle du
 * vehicule : vehicule (@see modele.Vehicule.java) - le modèle principal :
 * modele (@see modele.Modele.java) - la vue : affichage (@see vue.Vue.java) -
 * le controleur : deplacement (@see controleur.Deplacement.java) - le thread de
 * virage : virage (@see modele.thread.Virage.java) - le thread de checkpoint :
 * timer (@see modele.thread.Checkpoint.java)
 *
 * @author gpoisson, yallouane
 * @version 1.0
 */
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

    //KeyListener (controleur) de la vue
    private Deplacement kl;

    private int valeurVirageG;// Incrementer pour aller à droite, decrementer pour aller a gauche
    private int valeurVirageD;// Incrementer pour aller à gauche, decrementer pour aller a droite

    private Point2D debutG = new Point2D.Double(ROUTE_GAUCHE, LIGNEHORIZONY + Route.INC_ROUTE + 10);
    private Point2D debutD = new Point2D.Double(ROUTE_DROITE, LIGNEHORIZONY + Route.INC_ROUTE + 10);
    private Point2D ctrlG = new Point2D.Double(ROUTE_GAUCHE, LIGNEHORIZONY + Route.INC_ROUTE / 2);
    private Point2D ctrlD = new Point2D.Double(ROUTE_DROITE, LIGNEHORIZONY + Route.INC_ROUTE / 2);

    /**
     * Constructeur
     *
     * @param modele
     */
    public Vue(Modele modele) {
        setPreferredSize(new Dimension(P_WIDTH, P_HEIGHT));
        this.modele = modele;
        this.valeurVirageG = 5;
        this.valeurVirageD = 5;
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

    public Point2D getDebutG() {
        return debutG;
    }

    public void setDebutG(Point2D debutG) {
        this.debutG = debutG;
    }

    public Point2D getDebutD() {
        return debutD;
    }

    public void setDebutD(Point2D debutD) {
        this.debutD = debutD;
    }

    public Point2D getCtrlG() {
        return ctrlG;
    }

    public void setCtrlG(Point2D ctrlG) {
        this.ctrlG = ctrlG;
    }

    public Point2D getCtrlD() {
        return ctrlD;
    }

    public void setCtrlD(Point2D ctrlD) {
        this.ctrlD = ctrlD;
    }

    public Deplacement getKl() {
        return kl;
    }

    public void setKl(Deplacement kl) {
        this.kl = kl;
    }

    @Override
    public void paint(Graphics g) {
        this.requestFocusInWindow();
        revalidate();
        super.paint(g);
        couleur_fond(g);
        affichageVehicule(g);
        affichageObstacle(g);
        affichageImageHorizon(g);
        g.setFont(new Font("TimesRoman", Font.BOLD, 25));
        g.drawString("Vitesse : " + this.modele.getVehicule().getVitesse(), 0, P_HEIGHT - 10); // affichage de la vitesse
        Graphics2D g2 = (Graphics2D) g;
        affichageRoute(g2);
        Point2D middleG = new Point2D.Double(P_WIDTH / 2 + valeurVirageG, LIGNEHORIZONY);
        Point2D middleD = new Point2D.Double(P_WIDTH / 2 - valeurVirageD, LIGNEHORIZONY);
        g2.draw(modele.getRoute().genererVirage(debutG, ctrlG, middleG));
        g2.draw(modele.getRoute().genererVirage(debutD, ctrlD, middleD));
        afficherTimer(g, this.modele.getTimer());
        if (modele.getVehicule().getVitesse() <= 0 || modele.getTimer().getTimer() <= 0) {
            game_over(g);
        }
    }

    public void affichageImageHorizon(Graphics g) {
        try {
            BufferedImage img;
            img = ImageIO.read(new File("src/ressources/fond.jpg"));
            g.drawImage(img, 0, 0, P_WIDTH, LIGNEHORIZONY, null);
        } catch (IOException e) {
            System.out.println("Exception affichage Image Horizon : " + e.toString());
        }
    }

    public void affichageVehicule(Graphics g) {
        try {
            BufferedImage img;
            g.drawLine(0, LIGNEHORIZONY, P_WIDTH, LIGNEHORIZONY);
            img = ImageIO.read(new File("src/ressources/img_vaisseau.png"));
            g.drawImage(img, this.modele.getVehicule().getPositionX(), this.modele.getVehicule().getPositionY(), OVAL_WIDTH, OVAL_HEIGHT, null);
        } catch (IOException e) {
            System.out.println("Exception affichage Image Vehicule : " + e.toString());
        }
    }

    public void affichageRoute(Graphics2D g2) {
        for (int i = 0; i < this.modele.getRoute().getRouteDroite().size() - 1; i++) {
            g2.draw(new Line2D.Double(this.modele.getRoute().getRouteDroite().get(i).getX(), this.modele.getRoute().getRouteDroite().get(i).getY(), this.modele.getRoute().getRouteDroite().get(i + 1).getX(), this.modele.getRoute().getRouteDroite().get(i + 1).getY()));
            g2.draw(new Line2D.Double(this.modele.getRoute().getRouteGauche().get(i).getX(), this.modele.getRoute().getRouteGauche().get(i).getY(), this.modele.getRoute().getRouteGauche().get(i + 1).getX(), this.modele.getRoute().getRouteGauche().get(i + 1).getY()));
        }
    }

    public void game_over(Graphics g) {
        g.setFont(new Font("TimesRoman", Font.PLAIN, 45));
        g.drawString("GAME OVER", P_WIDTH / 2, P_HEIGHT / 2);
        this.modele.getVirage().setRunning(false);
        this.modele.getTimer().setRunning(false);
        this.removeKeyListener(this.getKl());
    }

    public void couleur_fond(Graphics g) {
        try {
            BufferedImage img;
            g.drawLine(0, LIGNEHORIZONY, P_WIDTH, LIGNEHORIZONY);
            img = ImageIO.read(new File("src/ressources/fond2.png"));
            g.drawImage(img, 0, LIGNEHORIZONY, P_WIDTH, P_HEIGHT - LIGNEHORIZONY, null);
            g.setColor(Color.WHITE);
        } catch (IOException e) {
            System.out.println("Exception affichage image fond : " + e.toString());
        }
    }

    public void afficherTimer(Graphics g, Checkpoint c) {
        g.setFont(new Font("TimesRoman", Font.BOLD, 25));
        g.drawString("Temps : " + c.getTimer(), 0, P_HEIGHT - 40); // affichage de la vitesse
    }

    public void affichageObstacle(Graphics g) {
        BufferedImage m;
        g.drawLine(0, LIGNEHORIZONY, P_WIDTH, LIGNEHORIZONY);
        try {
            m = ImageIO.read(new File(this.modele.getObstacle().getFile()));
            g.drawImage(m, this.modele.getObstacle().getPositionX(), this.modele.getObstacle().getPositionY(), this.modele.getObstacle().getWidthheight(), this.modele.getObstacle().getWidthheight(), null);
            if (this.modele.getObstacle().getPositionY() == P_HEIGHT) {
                this.modele.getObstacle().genererObstacle();
                Random random = new Random();
                int nb;
                nb = random.nextInt(2);
                System.out.println("NB : " + nb);
                switch (nb) {
                    case 1 ->
                        this.modele.getObstacle().setFile("src/ressources/meteorite_2.png");
                    default ->
                        this.modele.getObstacle().setFile("src/ressources/meteorite_1.png");
                }
            }
        } catch (IOException e) {
            System.out.println("Exception affichage Image Vehicule : " + e.toString());
        }
    }

}
