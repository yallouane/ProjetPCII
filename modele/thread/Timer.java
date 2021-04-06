package modele.thread;

import modele.Modele;

/**
 * <p>
 * La classe <b>Timer</b> est un <b>Thread</b> qui fait parti du projet
 * <b>Course</b>.
 *
 * le timer est représenté dans la vue par un compteur en bas a gauche
 *
 * Elle a pour attributs :
 * <ul>
 * <li>le modèle principal : modele (@see modele.Modele.java)</li>
 * <li>un entier contenant un compteur : timer</li>
 * <li>un booleen qui "decide" si le thread continue ou si il doit etre
 * stoppe</li>
 * </ul>
 *
 * et contient tout leurs Getters/Setters, ainsi qu'une constante :
 * <ul>
 * <li>un entier representant la duree maximale et initiale du timer :
 * DUREE_CHECKPOINT</li>
 * </ul>
 *
 * Elle ne possede qu'un seul constructeur, qui prend pour parametre un modele
 * principal, et qui initialise le modele du timer, son compteur(a la valeur de
 * la constante DUREE_CHECKPOINT) et son booleen a "true". Il fait egalement
 * demarrer le thread. La classe contient aussi la fonction qui definit ce que
 * le thread doit faire :
 * <ul>
 * <li>la fonction run, qui decremente le compteur puis se rendort pendant 1
 * seconde</li>
 * </ul>
 * </p>
 *
 * @author gpoisson, yallouane
 * @version 1.0
 */
public class Timer extends Thread {

    /**
     * CONSTANTES
     */
    /**
     * Duree maximale et initiale du timer, remis a cette valeur chaque fois que
     * le vehicule depasse un checkpoint. Si le timer atteint 0, le jeu se
     * termine (@see vue.Vue.java, fonction paint et game_over)
     */
    public static final int DUREE_CHECKPOINT = 30;

    /**
     * Attributs
     */
    // Modele principal
    Modele modele;

    // Compteur condition de defaite : si il atteint 0, la partie se termine, c'est GAME OVER
    // Est initialise a la valeur de DUREE_CHECKPOINT
    int timer;

    // Booleen qui determine si le thread continue de s'executer ou pas. 
    //est initialise a "true", peut etre mis a "false" par une seule fonction (@see vue.Vue.java, fonction game_over)
    private boolean running;

    public Timer(Modele modele) {
        this.modele = modele;
        this.timer = DUREE_CHECKPOINT;
        this.running = true;
        new Thread(this).start();
    }

    /**
     * Getters & Setters
     */
    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public Modele getModele() {
        return modele;
    }

    public void setModele(Modele modele) {
        this.modele = modele;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    /**
     * Run definit ce que le thread doit faire. En l'occurence, il decremente le
     * compteur toute les secondes
     */
    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(1000, 0);
                this.setTimer(getTimer() - 1);
            } catch (InterruptedException ex) {
                System.out.print("Problème Timer");
            }
        }
    }

}
