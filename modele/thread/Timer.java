package modele.thread;

import modele.Modele;

/**
 * Main est la classe principale du projet Course
 
 Elle initialise toute les classes, à savoir : 
 
 - le modèle de la route      : route (@see modele.Route.java)
 - le modèle du vehicule      : vehicule (@see modele.Vehicule.java)
 - le modèle principal        : modele (@see modele.Modele.java)
 - la vue                     : affichage (@see vue.Vue.java)
 - le controleur              : deplacement (@see controleur.Deplacement.java)
 - le thread de virage        : virage (@see modele.thread.Virage.java)
 - le thread de checkpoint    : timer (@see modele.thread.Timer.java)
 * 
 * @author gpoisson, yallouane
 * @version 1.0
 */
public class Timer extends Thread {

    Modele modele;

    public static final int DUREE_CHECKPOINT = 30;

    int timer;
    private boolean running;

    public Timer(Modele modele) {
        this.modele = modele;
        this.timer = DUREE_CHECKPOINT;
        this.running = true;
        new Thread(this).start();
    }

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

    @Override
    public void run() {
        while(running) {
	        try {
	            Thread.sleep(1000,0);
	            this.setTimer(getTimer() - 1);
	        } catch (InterruptedException ex) {
	            System.out.print("Problème Timer");
	        }
        }
    }

    
}
