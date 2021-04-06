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
import modele.thread.Timer;

/**
 * <p>
 * <b>Vue</b> est la <b>Vue </b> du modele <b>MVC</b> du projet <b>Course</b>.
 *
 * Elle a pour attributs :
 * <ul>
 * <li>le modèle principal : modele (@see modele.Modele.java)</li>
 * <li>le controleur : kl (@see controleur.Deplacement.java)</li>
 * <li>un entier ayant pour valeur la position en X du du point final gauche du
 * virage : valeurVirageG</li>
 * <li>un entier ayant pour valeur la position en X du du point final droite du
 * virage : valeurVirageD</li>
 * <li>un Point2D, le point de début gauche du virage : debutG</li>
 * <li>un Point2D, le point de début droit du virage : debutD</li>
 * <li>un Point2D, le point de controle gauche du virage : ctrlG</li>
 * <li>un Point2D, le point de controle gauche du virage : ctrlD</li>
 * </ul>
 *
 * et contient tout leurs Getters/Setters, ainsi que plusieurs constantes :
 * <ul>
 * <li>un entier representant la largeur de la fenetre : P_WIDTH</li>
 * <li>un entier representant la hauteur de la fenetre : P_HEIGHT</li>
 * <li>un entier representant la largeur du vaisseau : OVAL_WIDTH</li>
 * <li>un entier representant la hauteur du vaisseau : OVAL_HEIGHT</li>
 * <li>un entier representant la position minimale en Y du vaisseau (lors de
 * l'acceleration) : ACC_LIMITE</li>
 * <li>un entier representant la position maximale en Y du vaisseau (lors de la
 * deceleration) : DEC_LIMITE</li>
 * <li>un entier representant la position en Y de la ligne d'horizon :
 * LIGNEHORIZONY</li>
 * <li>un entier representant la position de depart en Y du vaisseau en partant
 * du bas de la fenetre : POS_DEP</li>
 * <li>un entier representant la position de depart en Y du vaisseau :
 * OVAL_Y</li>
 * <li>un entier representant la position de depart en X du vaisseau :
 * OVAL_X</li>
 * <li>un reel representant la position en X de la route gauche :
 * ROUTE_DROITE</li>
 * <li>un reel representant la position en X de la route droite :
 * ROUTE_GAUCHE</li>
 * <li>un entier representant l'ecart entre les deux routes, afin de donner une
 * impression de profondeur : IMP_PROF</li>
 * </ul>
 *
 * Elle ne possede qu'un seul constructeur, qui prend pour parametre le modele
 * principal, et initialise les dimensions de la fenetre, le modele, ainsi que
 * les valeur de fin des courbes virages. Elle contient aussi toutes les
 * fonctions servant à l'affichage :
 * <ul>
 * <li>la fonction paint, qui appelle toutes les autres fonctions ci dessous,
 * affiche la vitesse en bas à gauche, met a jour les points de fin de courbe et
 * affiche les virages. Elle verifie également les conditions de game_over</li>
 * <li>la fonction affichageImageHorizon, qui affiche la ligne d'horizon (dans
 * le prototype) et l'image au dessus de l'horizon</li>
 * <li>la fonction affichageVehicule, qui affiche le vehicule </li>
 * <li>la fonction affichageRoute, qui affiche la route </li>
 * <li>la fonction couleurFond, qui affiche le fond en dessous de l'horizon
 * </li>
 * <li>la fonction affichageTimer, qui affiche le timer en bas à gauche </li>
 * <li>la fonction affichageObstacle, qui affiche et regenere les obstacle </li>
 * <li>la fonction affichageCheckpoint, qui affiche et regenere la ligne de
 * checkpoint </li>
 * <li>la fonction game_over, qui affiche GAME OVER et stoppe les threads</li>
 *
 * </ul>
 * </p>
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
     * Largeur et longueur du vaisseau
     */
    public static final int OVAL_WIDTH = 75;
    public static final int OVAL_HEIGHT = 75;

    /**
     * Position limite du vaisseau lorsqu'il accelere ou decelere
     */
    public static final int ACC_LIMITE = P_HEIGHT - 150;
    public static final int DEC_LIMITE = P_HEIGHT - 50;

    /**
     * Position en x de la ligne d'horizon (a partir du haut de la fenetre)
     */
    public static final int LIGNEHORIZONY = 200;

    /**
     * Position par default du vaisseau en Y en partant du bas de la fenetre
     * (Position de depart)
     */
    public static final int POS_DEP = 100;

    /**
     * Position de depart en x et en y du vaisseau
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
        this.valeurVirageG = IMP_PROF;
        this.valeurVirageD = IMP_PROF;
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

    /**
     * Affiche la vue. appelle toutes les fonctions d'affichages definies plus
     * bas. Gere les collisions, affiche la vitesse, les virages, mets a jour
     * les points de fin des virages, et verifie les conditions de défaite
     *
     * @param g : Graphics
     */
    @Override
    public void paint(Graphics g) {
        this.requestFocusInWindow();
        revalidate();
        super.paint(g);
        couleur_fond(g);
        affichageVehicule(g);
        affichageObstacle(g);
        affichageCheckpoint(g);
        this.modele.getObstacle().detectionObstacle();
        affichageImageHorizon(g);
        g.setFont(new Font("TimesRoman", Font.BOLD, 25));
        g.drawString("Vitesse : " + this.modele.getVehicule().getVitesse(), 0, P_HEIGHT - 10); // affichage de la vitesse
        Graphics2D g2 = (Graphics2D) g;
        affichageRoute(g2);
        Point2D middleG = new Point2D.Double(P_WIDTH / 2 + valeurVirageG, LIGNEHORIZONY);
        Point2D middleD = new Point2D.Double(P_WIDTH / 2 - valeurVirageD, LIGNEHORIZONY);
        g2.draw(modele.getRoute().genererVirage(debutG, ctrlG, middleG));
        g2.draw(modele.getRoute().genererVirage(debutD, ctrlD, middleD));
        affichageTimer(g, this.modele.getTimer());
        if (modele.getVehicule().getVitesse() <= 0 || modele.getTimer().getTimer() <= 0) {
            game_over(g);
        }
        /*g.setColor(Color.RED);
        g.drawLine(this.modele.getCheckpoint().getPositionXGauche(), this.modele.getCheckpoint().getPositionY(), this.modele.getCheckpoint().getPositionXDroite(), this.modele.getCheckpoint().getPositionY());
        g.setColor(Color.WHITE);
        System.out.println(this.modele.getCheckpoint().getPositionXGauche());
        System.out.println(this.modele.getCheckpoint().getPositionXDroite());*/
        affichageCheckpoint(g);
    }

    /**
     * Affiche l'image au dessus de l'horizon. Affichait la ligne d'horizon
     * avant l'ajout d'image
     *
     * @param g : Graphics
     */
    public void affichageImageHorizon(Graphics g) {
        try {
            //g.drawLine(0, LIGNEHORIZONY, P_WIDTH, LIGNEHORIZONY);
            BufferedImage img;
            img = ImageIO.read(new File("src/ressources/fond.jpg"));
            g.drawImage(img, 0, 0, P_WIDTH, LIGNEHORIZONY, null);
        } catch (IOException e) {
            System.out.println("Exception affichage Image Horizon : " + e.toString());
        }
    }

    /**
     * Affiche le vehicule. Affichait un oval, representant le vehicule
     *
     * @param g : Graphics
     */
    public void affichageVehicule(Graphics g) {
        try {
            //g.drawOval(this.modele.getVehicule().getPositionX(), this.modele.getVehicule().getPositionY(), OVAL_WIDTH, OVAL_HEIGHT);
            BufferedImage img;
            img = ImageIO.read(new File("src/ressources/img_vaisseau.png"));
            g.drawImage(img, this.modele.getVehicule().getPositionX(), this.modele.getVehicule().getPositionY(), OVAL_WIDTH, OVAL_HEIGHT, null);
        } catch (IOException e) {
            System.out.println("Exception affichage Image Vehicule : " + e.toString());
        }
    }

    /**
     * Affiche la route (2 lignes), qu'elle construit a partir des points
     * renvoyés par le modele de la route
     *
     * @param g2 : Graphics2D
     */
    public void affichageRoute(Graphics2D g2) {
        for (int i = 0; i < this.modele.getRoute().getRouteDroite().size() - 1; i++) {
            g2.draw(new Line2D.Double(this.modele.getRoute().getRouteDroite().get(i).getX(), this.modele.getRoute().getRouteDroite().get(i).getY(), this.modele.getRoute().getRouteDroite().get(i + 1).getX(), this.modele.getRoute().getRouteDroite().get(i + 1).getY()));
            g2.draw(new Line2D.Double(this.modele.getRoute().getRouteGauche().get(i).getX(), this.modele.getRoute().getRouteGauche().get(i).getY(), this.modele.getRoute().getRouteGauche().get(i + 1).getX(), this.modele.getRoute().getRouteGauche().get(i + 1).getY()));
        }
    }

    /**
     * Affiche "GAME OVER" au milieu de l'ecran, et stoppe les threads de virage
     * et de timer
     *
     * @param g : Graphics
     */
    public void game_over(Graphics g) {
        g.setFont(new Font("TimesRoman", Font.PLAIN, 45));
        g.drawString("GAME OVER", P_WIDTH / 2, P_HEIGHT / 2);
        this.modele.getVirage().setRunning(false);
        this.modele.getTimer().setRunning(false);
        this.removeKeyListener(this.getKl());
    }

    /**
     * Affiche l'image de fond de la route
     *
     * @param g : Graphics
     */
    public void couleur_fond(Graphics g) {
        try {
            BufferedImage img;
            img = ImageIO.read(new File("src/ressources/fond2.png"));
            g.drawImage(img, 0, LIGNEHORIZONY, P_WIDTH, P_HEIGHT - LIGNEHORIZONY, null);
        } catch (IOException e) {
            System.out.println("Exception affichage image fond : " + e.toString());
        }
    }

    /**
     * Affiche le timer en bas à gauche
     *
     * @param g : Graphics
     * @param c : Timer (celui lié a l'attribut modele)
     */
    public void affichageTimer(Graphics g, Timer c) {
        g.setFont(new Font("TimesRoman", Font.BOLD, 25));
        g.drawString("Temps : " + c.getTimer(), 0, P_HEIGHT - 40); // affichage de la vitesse
    }

    /**
     * Affiche les obstacles. Choisit un obstacle au hasard parmi une varieté
     * d'obstacles, et l'affiche quand le précédent a disparu
     *
     * @param g : Graphics
     */
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
                nb = random.nextInt(4);
                System.out.println("NB : " + nb);
                switch (nb) {
                    case 1 ->
                        this.modele.getObstacle().setFile("src/ressources/meteorite_2.png");
                    case 2 ->
                        this.modele.getObstacle().setFile("src/ressources/meteorite_3.png");
                    case 3 ->
                        this.modele.getObstacle().setFile("src/ressources/concurrent.png");
                    default ->
                        this.modele.getObstacle().setFile("src/ressources/meteorite_1.png");
                }
            }
        } catch (IOException e) {
            System.out.println("Exception affichage Image Vehicule : " + e.toString());
        }
    }

    /**
     * Affiche une ligne rouge, qui sert de checkpoint, qui reset le timer.
     * regenere le checkpoint lorsque celui_ci est passé au dela du vehicule
     *
     * @param g : Graphics
     */
    public void affichageCheckpoint(Graphics g) {
        if (this.modele.getCheckpoint().getPositionY() < this.modele.getVehicule().getPositionY()) {
            g.setColor(Color.RED);
            g.drawLine(this.modele.getCheckpoint().getPositionXGauche(), this.modele.getCheckpoint().getPositionY(), this.modele.getCheckpoint().getPositionXDroite(), this.modele.getCheckpoint().getPositionY());
            g.setColor(Color.WHITE);
        } else {
            this.modele.getTimer().setTimer(Timer.DUREE_CHECKPOINT);
            this.modele.getCheckpoint().setPositionY(LIGNEHORIZONY);
            this.modele.getCheckpoint().setPositionXDroite(P_WIDTH / 2);
            this.modele.getCheckpoint().setPositionXGauche(P_WIDTH / 2);
        }

    }

}
