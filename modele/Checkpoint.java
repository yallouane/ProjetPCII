package modele;

import vue.Vue;

/**
 * <p>
 * La classe <b>Checkpoint</b> est un modele <b>MVC</b> de checkpoint, qui fait
 * parti du projet <b>Course</b>.
 *
 * Un checkpoint est représenté dans la vue par une ligne rouge
 *
 * Elle a pour attributs :
 * <ul>
 * <li>le modèle principal : modele (@see modele.Modele.java)</li>
 * <li>un entier contenant sa position en X gauche : positionXGauche</li>
 * <li>un entier contenant sa position en X droite : positionXDroite</li>
 * <li>un entier contenant sa position en Y : positionY</li>
 * </ul>
 *
 * et contient tout leurs Getters/Setters.
 *
 * Elle ne possede qu'un seul constructeur, qui initialise la position Y du
 * checkpoint à la ligne d'horizon. Elle contient aussi la fonction servant a
 * baisser le checkpoint :
 * <ul>
 * <li>la fonction baisser, qui incrémente la position de Y (la fait baisser
 * donc), qui augmente la taille de la ligne jusqu'a ce que la ligne soit de la
 * meme taille que la route</li>
 * </ul>
 * </p>
 *
 * @author gpoisson, yallouane
 * @version 1.0
 */
public class Checkpoint {

    /**
     * Attributs
     */
    // Modele principal
    public Modele modele;

    // Positions(coordonnees) en X de la ligne
    public int positionXGauche;
    public int positionXDroite;

    //Position en Y de la ligne
    public int positionY;

    /**
     * Constructeur initialise la position en Y du checkpoint à la ligne
     * d'horizon
     */
    public Checkpoint() {
        this.positionY = Vue.LIGNEHORIZONY;
    }

    /**
     * Getters & Setters
     */
    public int getPositionXGauche() {
        return positionXGauche;
    }

    public void setPositionXGauche(int positionXGauche) {
        this.positionXGauche = positionXGauche;
    }

    public int getPositionXDroite() {
        return positionXDroite;
    }

    public void setPositionXDroite(int positionXDroite) {
        this.positionXDroite = positionXDroite;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public Modele getModele() {
        return modele;
    }

    /**
     * Initialise l'attribut modele, ainsi que les positions(coordonnées) en X
     * de la ligne. Ces coordonnées ont pour valeur initiale le milieu de la
     * fenetre
     *
     * @param modele : Modele principal
     */
    public void setModele(Modele modele) {
        this.modele = modele;
        this.positionXGauche = Vue.P_WIDTH / 2;
        this.positionXDroite = Vue.P_WIDTH / 2;
    }

    /**
     * Baisse la ligne de checkpoint, et augmente la taille de la ligne si
     * besoin est.
     *
     * La position X Gauche doit diminuer(pour que le bout de la ligne aille a
     * gauche) ssi sa valeur est superieure (a droite) de la route gauche. 
     * La position X Droite doit augmenter(pour que le bout de la ligne aille a
     * droite) ssi sa valeur est inferieure (a gauche) de la route droite.
     */
    public void baisser() {
        this.positionY = this.positionY + 1;

        this.positionXDroite = (this.positionXDroite < Vue.ROUTE_GAUCHE) ? this.positionXDroite + 1 : this.positionXDroite;
        this.positionXGauche = (this.positionXGauche > Vue.ROUTE_DROITE) ? this.positionXGauche - 1 : this.positionXGauche;
    }

}
