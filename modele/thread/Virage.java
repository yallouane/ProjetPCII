package modele.thread;

import java.util.logging.Level;
import java.util.logging.Logger;
import modele.Modele;
import modele.Obstacle;
import modele.Route;
import vue.Vue;

/**
 * Main est la classe principale du projet Course

 Elle initialise toute les classes, à savoir :

 - le modèle de la route : route (@see modele.Route.java) - le modèle du
 vehicule : vehicule (@see modele.Vehicule.java) - le modèle principal :
 modele (@see modele.Modele.java) - la vue : affichage (@see vue.Vue.java) -
 le controleur : deplacement (@see controleur.Deplacement.java) - le thread de
 virage : virage (@see modele.thread.Virage.java) - le thread de checkpoint :
 timer (@see modele.thread.Timer.java)
 *
 * @author gpoisson, yallouane
 * @version 1.0
 */
public class Virage extends Thread {

    public static final int SLEEP_VIRAGE = 160;
    public static final int CPT_VIRAGE = 100;

    private int cpt;

    private int alea;

    private boolean isCenter;

    private boolean running;

    Modele modele;

    public int getCpt() {
        return cpt;
    }

    public void setCpt(int cpt) {
        this.cpt = cpt;
    }

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

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public Virage(Modele modele) {
        this.modele = modele;
        new Thread(this).start();
        this.isCenter = true;
        this.cpt = 50;
        this.running = true;
        alea = modele.getRoute().randomInt(1, 3);
    }

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

    public void virage(int valeurVirageG, int valeurVirageD, double ctrlG, double ctrlD) {
        if (alea == 1) {
            if (valeurVirageG != 540) {
                valeurVirageG++;
                valeurVirageD--;
                cpt++;
                if (this.modele.getObstacle().getPositionY() >= Vue.P_HEIGHT) {
                    this.modele.getObstacle().genererObstacle();
                } else {
                    this.modele.getObstacle().setPositionY(this.modele.getObstacle().getPositionY() + 5);
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
                modele.getTimer().setTimer(1);
            }
        } else {
            if (valeurVirageG != - 540) {
                valeurVirageG--;
                valeurVirageD++;
                cpt++;
                if (this.modele.getObstacle().getPositionY() >= Vue.P_HEIGHT) {
                    this.modele.getObstacle().genererObstacle();
                } else {
                    this.modele.getObstacle().setPositionY(this.modele.getObstacle().getPositionY() + 5);
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
                modele.getTimer().setTimer(1);

            }
        }
    }

    public void incrementer() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            Logger.getLogger(Virage.class.getName()).log(Level.SEVERE, null, ex);
        }
        cpt++;
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
