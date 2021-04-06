package modele;

import vue.Vue;

/**
 * <p>
 * La classe <b>Vehicule</b> est un modele <b>MVC</b> de vehicule, qui fait
 * parti du projet <b>Course</b>.
 *
 * Elle a pour attributs :
 * <ul>
 * <li>le modèle principal : modele (@see modele.Modele.java)</li>
 * <li>un entier contenant sa position en X : positionX</li>
 * <li>un entier contenant sa position en Y : positionY</li>
 * <li>un entier contenant sa vitesse : vitesse</li>
 * </ul>
 *
 * et contient tout leurs Getters/Setters, ainsi que plusieurs constantes :
 * <ul>
 * <li>une chaine qui sert lors du contact avec le controleur, contient la
 * valeur "HAUT" : HAUT </li>
 * <li>une chaine qui sert lors du contact avec le controleur, contient la
 * valeur "BAS" : BAS </li>
 * <li>une chaine qui sert lors du contact avec le controleur, contient la
 * valeur "GAUCHE" : GAUCHE </li>
 * <li>une chaine qui sert lors du contact avec le controleur, contient la
 * valeur "DROITE" : DROITE </li>
 * <li>un entier representant l'increment a la position lorsqu'on appuie sur une
 * touche : POS_INC</li>
 * <li>un entier representant l'increment (ou le decrement) a la vitesse
 * lorsqu'on appuie sur une touche (haut ou bas) : VIT_INC</li>
 * <li>un entier representant la vitesse maximale du vehicule : VIT_LIMITE</li>
 * <li>un entier representant le decrement a la vitesse du vehicule lors d'une
 * collision : COLLISION</li>
 * </ul>
 *
 * Elle ne possede qu'un seul constructeur, qui prend pour parametres la
 * position X ainsi que la position Y de l'obstacle, et initialise les
 * coordonnes du vehicule, ainsi que la vitesse à 10. Elle contient aussi la
 * fonction servant a changer les coordonnees du vehicule, ainsi que sa vitesse:
 * <ul>
 * <li>la methode deplacement, qui prend en parametre une chaine (c.a.d une
 * chaine ayant pour valeur la valeur de l'une des chaines constante) et la
 * compare aux constantes, et en fonction de celle correspondante, change la
 * coordonnee associee et la vitesse dans le cas de haut et bas </li>
 * <li>la fonction collision, qui est appelle si un obstacle a ete touché, et
 * qui diminue la vitesse grace a la constante COLLISION (@see
 * modele.Obstacle.java, fonction detectionObstacle()) </li>
 * </ul>
 * </p>
 *
 * @author gpoisson, yallouane
 * @version 1.0
 */
public class Vehicule {

    /**
     * CONSTANTES
     */
    /**
     * Servants au deplacement
     */
    public static final String HAUT = "HAUT";
    public static final String BAS = "BAS";
    public static final String GAUCHE = "GAUCHE";
    public static final String DROITE = "DROITE";

    /**
     * Servants a l'affichage
     */
    /**
     * Contient la valeur ajoutee ou enlevee aux coordonnees lorsque une touche
     * est pressee
     */
    public static final int POS_INC = 5;

    /**
     * Contient la valeur ajoutee ou enlevee a la vitesse lorsque la touche haut
     * ou bas est pressee
     */
    public static final int VIT_INC = 1;

    /**
     * Contient la valeur maximale de la vitesse du vehicule
     */
    public static final int VIT_LIMITE = 150;

    /**
     * Contient la valeur qui sera enlevee a la vitesse lors de collisions
     */
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
     * initialise la vitesse du vehicule a 10
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

    /**
     * Decremente la vitesse du vehicule de la valeur de la constante COLLISION
     */
    public void collision() {
        this.vitesse = this.vitesse - COLLISION;
    }

    /**
     * Methode definissant le deplacement du vehicule lorsque le controleur
     * detecte qu'une touche a ete pressee
     *
     * @param valeur : String comparé aux chaines constantes, si valeur n'est
     * pas égale à l'une d'entre elle, rien ne se passe
     */
    public void deplacement(String valeur) {
        if (null != valeur) {
            switch (valeur) {
                case HAUT:
                    if (this.positionY >= Vue.ACC_LIMITE) {
                        this.positionY -= POS_INC;
                    }
                    if (this.vitesse <= VIT_LIMITE - VIT_INC) {
                        this.vitesse += VIT_INC;
                    }
                    break;
                case BAS:
                    if (this.positionY <= Vue.DEC_LIMITE) {
                        this.positionY += POS_INC;
                    }
                    if (this.vitesse > 0) {
                        this.vitesse -= VIT_INC;
                    }
                    if (this.vitesse <= 0) {
                        System.out.println("Game Over");
                    }
                    break;
                case GAUCHE:
                    if (this.positionX <= Vue.ROUTE_DROITE) {
                        this.positionX = (int) Vue.ROUTE_DROITE;
                    } else {
                        this.positionX -= 5;
                    }
                    break;
                case DROITE:
                    if (this.positionX >= 598) {
                        this.positionX = 598;
                    } else {
                        this.positionX += 5;
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
