package modele;

import vue.Vue;

/**
 * <p>
 * La classe <b>Obstacle</b> est un modele <b>MVC</b> d'obstacle, qui fait parti
 * du projet <b>Course</b>.
 *
 * Elle herite de Vehicule et contient donc toutes ses fonctions et attributs
 *
 * Elle a pour attributs supplémentaires:
 * <ul>
 * <li>un entier contenant sa taille(largeur et longueur etant egale) :
 * widthheight</li>
 * <li>une chaine qui contient le chemin de l'image de l'obstacle : file</li>
 * </ul>
 *
 * et contient tout leurs Getters/Setters, ainsi que deux constantes :
 * <ul>
 * <li>un entier representant la taille maximale d'un obstacle :
 * MAXWIDTHHEIGHT</li>
 * <li>un entier representant la vitesse d'un obstacle : VIT_OBSTACLE</li>
 * </ul>
 *
 * Elle ne possede qu'un seul constructeur, qui prend pour parametres la
 * position X ainsi que la position Y de l'obstacle, et initialise les
 * dimensions de l'obstacle, ainsi que le chemin par defaut de l'image. Elle
 * contient aussi les fonctions servant a generer et detecter l'obstacle :
 * <ul>
 * <li>la fonction genererObstacle, qui reset la position en Y de l'obstacle, et
 * coisit une nouvelle position X </li>
 * <li>la fonction detectionObstacle, qui detecte si un obstacle a ete touché,
 * et dans le cas echéant appelle collision (@see modele.Vehicule.java, fonction
 * collision()) </li>
 * </ul>
 * </p>
 *
 * @author gpoisson, yallouane
 * @version 1.0
 */
public class Obstacle extends Vehicule {

    /**
     * CONSTANTES
     */
    /**
     * Largeur et longueur maximale de l'obstacle
     */
    public static final int MAXWIDTHHEIGHT = 50;

    /**
     * Vitesse de l'obstacle
     */
    public static final int VIT_OBSTACLE = 5;

    /**
     * Attributs
     */
    // Taille actuelle de l'obstacle
    protected int widthheight;

    // Chemin de l'image de l'obstacle. Un obstacle peut etre un concurrent
    protected String file;

    /**
     * Constructeur initialisation de la taille de l'objet à 1 initialsation du
     * chemin de l'image à la premiere meteorite
     *
     * @param positionX : entier donnant la position X de l'obstacle
     * @param positionY : entier donnant la position Y de l'obstacle
     */
    public Obstacle(int positionX, int positionY) {
        super(positionX, positionY);
        this.widthheight = 1;
        this.file = "src/ressources/meteorite_1.png";

    }

    /**
     * Getters & Setters
     * 
     * @return widthheight : entier et file : String
     */
    public int getWidthheight() {
        return widthheight;
    }

    public void setWidthheight(int widthheight) {
        this.widthheight = widthheight;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
    
    /**
     * Generation d'obstacles. reset la position Y de l'obstacle a la ligne
     * d'horizon, donne une nouvelle position X au hasard, et remet la taille de
     * l'obstacle a 1(impression de profondeur
     */
    public void genererObstacle() {
        this.setPositionY(Vue.LIGNEHORIZONY);
        this.setPositionX(this.modele.getRoute().randomInt((int) Vue.ROUTE_DROITE, (int) Vue.ROUTE_GAUCHE));
        this.setWidthheight(1);
    }

    /**
     * Detecte les obstacles selon la position de lobstacle comparee a celle du
     * vehicule. Appelle vehicule.collision
     */
    public void detectionObstacle() {
        if (this.getPositionY() >= this.modele.getVehicule().getPositionY() && this.getPositionY() <= this.modele.getVehicule().getPositionY() + Vue.OVAL_HEIGHT) {
            if (this.getPositionX() + widthheight >= this.modele.getVehicule().getPositionX() && this.getPositionX() <= this.modele.getVehicule().getPositionX() + Vue.OVAL_WIDTH) {
                this.modele.getVehicule().collision();
            }
        }
    }
    
    

}
