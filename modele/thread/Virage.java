package modele.thread;

import java.util.logging.Level;
import java.util.logging.Logger;
import modele.Modele;
import modele.Obstacle;
import modele.Route;
import vue.Vue;

/**
 * <p>
 * La classe <b>Virage</b> est un <b>Thread</b> qui fait parti du projet
 * <b>Course</b>.
 *
 * le virage est représenté dans la vue par deux courbes de Beziers
 *
 * Elle a pour attributs :
 * <ul>
 * <li>le modèle principal : modele (@see modele.Modele.java)</li>
 * <li>un entier contenant un compteur : cpt</li>
 * <li>un entier contenant un nombre au hasard : alea</li>
 * <li>un booleen qui definit quelle fonction doit etre appellee : isCenter</li>
 * <li>un booleen qui "decide" si le thread continue ou si il doit etre stoppe :
 * running</li>
 * </ul>
 *
 * et contient tout leurs Getters/Setters, ainsi que deux constantes :
 * <ul>
 * <li>un entier representant la duree de sommeil du thread : SLEEP_VIRAGE</li>
 * <li>un entier representant la duree minimale entre deux virages :
 * CPT_VIRAGE</li>
 * </ul>
 *
 * Elle ne possede qu'un seul constructeur, qui prend pour parametre un modele
 * principal, et qui initialise le modele attribut, isCenter a "true", cpt a 50,
 * et alea un nombre aleatoire, et son booleen running a "true". Il fait
 * egalement demarrer le thread. La classe contient aussi la fonction qui
 * definit ce que le thread doit faire :
 * <ul>
 * <li>la fonction run, execute virage ou to_middle en fonction de la valeur de
 * isCenter</li>
 * <li>la fonction virage, genere un virage et remet cpt a zero, isCenter a
 * false et reset alea</li>
 * <li>la fonction to_middle, remet les points au milieu de la fenetre, et remet
 * isCenter a true</li>
 * </ul>
 * </p>
 *
 * @author gpoisson, yallouane
 * @version 1.0
 */
public class Virage extends Thread {

    /**
     * CONSTANTES
     */
    /**
     * Definit le temps quand virage dort
     */
    public static final int SLEEP_VIRAGE = 160;

    /**
     * definit le temps minimal entre chaque virage
     */
    public static final int CPT_VIRAGE = 100;

    /**
     * Attributs
     */
    // Compteur du temps entre chaque virage
    private int cpt;

    // Nombre aleatoire, definit si le virage est a droite ou a gauche
    private int alea;

    // true si un virage nest pas en cours, false sinon
    private boolean isCenter;

    // true si le jeu n'est pas terminé (@see vue.Vue.java, fonction paint et game_over)
    private boolean running;

    //modele principal associe au virage
    Modele modele;

    /**
     * Constructeur
     *
     * initialise cpt a 50 pour moins d'attente avant le premier virage demarre
     * le thread
     *
     * @param modele : Modele principal (@see modele.Modele.java)
     */
    public Virage(Modele modele) {
        this.modele = modele;
        this.isCenter = true;
        this.cpt = 50;
        this.running = true;
        alea = modele.getRoute().randomInt(1, 3);
        new Thread(this).start();
    }

    /**
     * Getters & Setters
     *
     * @return booleans, ints, modele
     */
    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public int getCpt() {
        return cpt;
    }

    public void setCpt(int cpt) {
        this.cpt = cpt;
    }

    public int getAlea() {
        return alea;
    }

    public void setAlea(int alea) {
        this.alea = alea;
    }

    public boolean isIsCenter() {
        return isCenter;
    }

    public void setIsCenter(boolean isCenter) {
        this.isCenter = isCenter;
    }

    public Modele getModele() {
        return modele;
    }

    public void setModele(Modele modele) {
        this.modele = modele;
    }

    /**
     * Run definit ce que le thread doit faire. En l'occurence, il execute
     * tomiddle ou virage en fonction de isCenter et incremente compteur cpt
     * toute les secondes/(SLEEP_VIRAGE + vitesse du vehicule). Plus le vehicule
     * vas vite, plus les virages se genere vite, plus les obstacles et
     * checkpoints vont vites
     */
    @Override
    public void run() {
        while (running) {
            if (isCenter && cpt >= CPT_VIRAGE) {
                virage(modele.getVue().getValeurVirageG(), modele.getVue().getValeurVirageD(), modele.getVue().getCtrlG().getY(), modele.getVue().getCtrlD().getY());
            } else {
                to_middle(modele.getVue().getValeurVirageG(), modele.getVue().getValeurVirageD(), modele.getVue().getCtrlG().getY(), modele.getVue().getCtrlD().getY());
            }
            incrementer();
            this.modele.getVue().repaint();
            try {
                Thread.sleep(SLEEP_VIRAGE - modele.getVehicule().getVitesse(), 0);
            } catch (InterruptedException ex) {
                System.out.print("Problème Defilement Sleep");
            }
        }
    }

    /**
     * Appellee apres un virage, remet la route droite
     *
     * @param valeurVirageG : position en X du point de fin gauche de la courbe,
     * la position Y etant fixee a la ligne d'horizon
     * @param valeurVirageD : position en X du point de fin droit de la courbe,
     * la position Y etant fixee a la ligne d'horizon
     * @param ctrlG : position en Y du point de controle gauche de la courbe, la
     * position X etant fixee a la route gauche
     * @param ctrlD : position en Y du point de controle droite de la courbe, la
     * position X etant fixee a la route droite
     */
    public void to_middle(int valeurVirageG, int valeurVirageD, double ctrlG, double ctrlD) {
        if (valeurVirageG > Vue.IMP_PROF) {
            valeurVirageG--;
            valeurVirageD++;
        } else if (valeurVirageD > Vue.IMP_PROF) {
            valeurVirageD--;
            valeurVirageG++;
        } else {
            if (valeurVirageG < 5) {
                valeurVirageG++;
            } else if (valeurVirageG > 5) {
                valeurVirageG--;
            } else if (valeurVirageD < 5) {
                valeurVirageD++;
            } else if (valeurVirageD > 5) {
                valeurVirageD--;
            }
        }
        if (ctrlG < (Vue.LIGNEHORIZONY + Route.INC_ROUTE / 2)) {
            ctrlG++;
            ctrlD++;
        }
        this.modele.getVue().setValeurVirageG(valeurVirageG);
        this.modele.getVue().setValeurVirageD(valeurVirageD);
        this.modele.getVue().getCtrlG().setLocation(this.modele.getVue().getCtrlG().getX(), ctrlG);
        this.modele.getVue().getCtrlD().setLocation(this.modele.getVue().getCtrlD().getX(), ctrlD);
        if (valeurVirageG == Vue.IMP_PROF && valeurVirageD == Vue.IMP_PROF) {
            isCenter = true;
        }
    }

    /**
     * Appellee tant que le virage n'est pas termine, ie tant qu eles points de
     * fin ne sont pas a une des extremites de la fenetre
     *
     * @param valeurVirageG : position en X du point de fin gauche de la courbe,
     * la position Y etant fixee a la ligne d'horizon
     * @param valeurVirageD : position en X du point de fin droit de la courbe,
     * la position Y etant fixee a la ligne d'horizon
     * @param ctrlG : position en Y du point de controle gauche de la courbe, la
     * position X etant fixee a la route gauche
     * @param ctrlD : position en Y du point de controle droite de la courbe, la
     * position X etant fixee a la route droite
     */
    public void virage(int valeurVirageG, int valeurVirageD, double ctrlG, double ctrlD) {
        if (alea == 1) {
            if (valeurVirageG != 540) {
                valeurVirageG++;
                valeurVirageD--;
                cpt++;
                this.modele.getCheckpoint().baisser();
                if (this.modele.getObstacle().getPositionY() >= Vue.P_HEIGHT) {
                    this.modele.getObstacle().genererObstacle();
                } else {
                    this.modele.getObstacle().setPositionY(this.modele.getObstacle().getPositionY() + Obstacle.VIT_OBSTACLE);
                    if (this.modele.getObstacle().getWidthheight() >= Obstacle.MAXWIDTHHEIGHT) {
                        this.modele.getObstacle().setWidthheight(this.modele.getObstacle().getWidthheight() + 1);
                    }
                }
                this.modele.getVue().setValeurVirageG(valeurVirageG);
                this.modele.getVue().setValeurVirageD(valeurVirageD);
                if (ctrlG > Vue.LIGNEHORIZONY) {
                    ctrlG--;
                    ctrlD--;
                }
                this.modele.getVue().getCtrlG().setLocation(this.modele.getVue().getCtrlG().getX(), ctrlG);
                this.modele.getVue().getCtrlD().setLocation(this.modele.getVue().getCtrlD().getX(), ctrlD);
            } else {
                alea = modele.getRoute().randomInt(1, 3);
                isCenter = false;
                cpt = 0;
            }
        } else {
            if (valeurVirageG != - 540) {
                valeurVirageG--;
                valeurVirageD++;
                cpt++;
                this.modele.getCheckpoint().baisser();
                if (this.modele.getObstacle().getPositionY() >= Vue.P_HEIGHT) {
                    this.modele.getObstacle().genererObstacle();
                } else {
                    this.modele.getObstacle().setPositionY(this.modele.getObstacle().getPositionY() + Obstacle.VIT_OBSTACLE);
                    if (this.modele.getObstacle().getWidthheight() >= Obstacle.MAXWIDTHHEIGHT) {
                        this.modele.getObstacle().setWidthheight(this.modele.getObstacle().getWidthheight() + 1);
                    }
                }
                this.modele.getVue().setValeurVirageG(valeurVirageG);
                this.modele.getVue().setValeurVirageD(valeurVirageD);
                if (ctrlG > Vue.LIGNEHORIZONY) {
                    ctrlG--;
                    ctrlD--;
                }
                this.modele.getVue().getCtrlG().setLocation(this.modele.getVue().getCtrlG().getX(), ctrlG);
                this.modele.getVue().getCtrlD().setLocation(this.modele.getVue().getCtrlD().getX(), ctrlD);
            } else {
                alea = modele.getRoute().randomInt(1, 3);
                isCenter = false;
                cpt = 0;

            }
        }
    }

    /**
     * incremente le compteur, baisse les obstacles, augmente leur taille et les
     * regenere si besoin
     */
    public void incrementer() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            Logger.getLogger(Virage.class.getName()).log(Level.SEVERE, null, ex);
        }
        cpt++;
        this.modele.getCheckpoint().baisser();
        if (this.modele.getObstacle().getPositionY() >= Vue.P_HEIGHT) {
            this.modele.getObstacle().genererObstacle();
        } else {
            this.modele.getObstacle().setPositionY(this.modele.getObstacle().getPositionY() + 5);
            if (this.modele.getObstacle().getWidthheight() <= Obstacle.MAXWIDTHHEIGHT) {
                this.modele.getObstacle().setWidthheight(this.modele.getObstacle().getWidthheight() + 1);
            }
        }
    }

}
