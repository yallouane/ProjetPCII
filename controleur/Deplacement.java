package controleur;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import modele.Vehicule;
import vue.Vue;

/**
 * <p>
 * La classe <b>Deplacement</b> est le <b>Controleur</b> du modele <b>MVC</b> du
 * projet <b>Course</b>.
 *
 * Elle a pour attributs :
 * <ul>
 * <li>La vue associee au controleur : affichage</li>
 * <li>le modele du vehicule : vehicule</li>
 * </ul>
 *
 * et contient tout leurs Getters/Setters.
 *
 * Elle ne possede qu'un seul constructeur, qui prend pour parametre le modele
 * du vehicule et la vue, et initialise les attributs qui leurs sont associes.
 * Elle contient aussi toutes les 3 fonctions servant au controle du vehicule,
 * sachant que deux d'entre elles ne sont pas utilisees, et donc pas codes
 * (elles ne font rien) :
 * <ul>
 * <li>la fonction keyPressed, qui est appellé des qu'une touche fleche (ou
 * ZQSD) du clavier est enfoncee, et qui appelle les methodes de vehicule en
 * fonction de la touche pressee</li>
 * </ul>
 * </p>
 *
 * @author gpoisson, yallouane
 * @version 1.0
 */
public class Deplacement implements KeyListener {

    /**
     * Attributs
     */
    private Vue affichage;
    private Vehicule vehicule;

    /**
     * Constructeur
     *
     * @param vue : Vue (@see vue.Vue.java)
     * @param vehicule : vehicule (@see modele.Vehicule.java)
     */
    public Deplacement(Vue vue, Vehicule vehicule) {
        this.affichage = vue;
        this.vehicule = vehicule;
    }

    /**
     * Est appellee si une touche du clavier est pressee.
     *
     * Si la touche est la fleche allant vers le haut ou la touche 'Z', on
     * appelle la fonction deplacement('HAUT') du vehicule attribut puis on
     * repeint la vue.
     *
     * Si la touche est la fleche allant vers le bas ou la touche 'S', on
     * appelle la fonction deplacement('BAS') du vehicule attribut puis on
     * repeint la vue.
     *
     * Si la touche est la fleche allant vers la gauche ou la touche 'Q', on
     * appelle la fonction deplacement('GAUCHE') du vehicule attribut puis on
     * repeint la vue.
     *
     * Si la touche est la fleche allant vers la droite ou la touche 'D', on
     * appelle la fonction deplacement('DROITE') du vehicule attribut puis on
     * repeint la vue
     *
     *
     * @param e : KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 38:
            case 90:
                // HAUT
                vehicule.deplacement("HAUT");
                affichage.repaint();
                break;
            case 40:
            case 83:
                // BAS
                vehicule.deplacement("BAS");
                affichage.repaint();
                break;
            case 37:
            case 81:
                // GAUCHE
                vehicule.deplacement("GAUCHE");
                affichage.repaint();
                break;
            case 39:
            case 68:
                // DROITE
                vehicule.deplacement("DROITE");
                affichage.repaint();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

}
