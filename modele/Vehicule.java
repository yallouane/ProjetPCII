package modele;

import vue.Vue;

/**
 * Main est la classe principale du projet Course
 * 
 * Elle initialise toute les classes, à savoir : 
 * 
 * - le modèle de la route      : route (@see modele.Route.java)
 * - le modèle du vehicule      : vehicule (@see modele.Vehicule.java)
 * - le modèle principal        : modele (@see modele.Modele.java)
 * - la vue                     : affichage (@see vue.Vue.java)
 * - le controleur              : deplacement (@see controleur.Deplacement.java)
 * - le thread de virage        : virage (@see modele.thread.Virage.java)
 * - le thread de checkpoint    : timer (@see modele.thread.Checkpoint.java)
 * 
 * @author gpoisson, yallouane
 * @version 1.0
 */
public class Vehicule {

    /**
     * CONSTANTES servant au déplacement du véhicule (vertical et horizontal)
     */
    public static final String HAUT = "HAUT";
    public static final String BAS = "BAS";
    public static final String GAUCHE = "GAUCHE";
    public static final String DROITE = "DROITE";

    //Constantes définissant 
    public static final int POS_INC = 5;
    public static final int VIT_INC = 1;
    public static final int VIT_LIMITE = 150;
    public static final int COLLISION = 4;
    /**
     * Attributs
     */
    // Position en x et en y du véhicule
    protected int positionX;
    protected int positionY;

    // Vitesse du vehicule
    protected int vitesse;

    // Modele du vehicule
    protected Modele modele;

    /**
     * Constructeur
     *
     * @param positionX
     * @param positionY
     */
    public Vehicule(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.vitesse = 10;
    }

    /**
     * Getters & Setters
     *
     * @return Integers
     */
    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    public Modele getModele() {
        return modele;
    }

    public void setModele(Modele modele) {
        this.modele = modele;
    }
    
    public void collision(){
        this.vitesse = this.vitesse - COLLISION;
    }

    /**
     * Methodes
     */
    /**
     * methode definissant le deplacement du vehicule lorsque le controleur
     * detecte qu'une touche a ete pressee
     *
     * @param valeur
     */
    public void deplacement(String valeur) {
        if (null != valeur) {
            switch (valeur) {
                case HAUT -> {
                    if (this.positionY >= Vue.ACC_LIMITE) {
                        this.positionY -= POS_INC;
                    }
                    if (this.vitesse <= VIT_LIMITE - VIT_INC) {
                        this.vitesse += VIT_INC;
                    }
                }
                case BAS -> {
                    if (this.positionY <= Vue.DEC_LIMITE) {
                        this.positionY += POS_INC;
                    }
                    if (this.vitesse > 0) {
                        this.vitesse -= VIT_INC;
                    }
                    if (this.vitesse <= 0) {
                        System.out.println("Game Over");
                    }
                }
                case GAUCHE -> {
                    if(this.positionX<= Vue.ROUTE_DROITE) {
                        this.positionX = (int) Vue.ROUTE_DROITE;
                    }
                    else {
                        this.positionX -= 5;
                    }
                }
                case DROITE -> {
                    if(this.positionX >= 598) {
                        this.positionX = 598;
                    }
                    else {
                        this.positionX += 5;
                    }
                }
                default -> {
                }
            }
        }
    }
}
