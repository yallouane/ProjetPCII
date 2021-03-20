package modele.thread;

import modele.Modele;
import vue.Vue;

/**
 *
 * @author gp910
 */
public class Virage extends Thread {

    Modele modele;

    @Override
    public void run() {
        while (true) {
            to_middle(modele.getVue().getValeurVirageG(), modele.getVue().getValeurVirageD());
            this.modele.getVue().repaint();
            try {
                Thread.sleep(10, 0);
            } catch (InterruptedException ex) {
                System.out.print("Problème Defilement Sleep");
            }
        }
    }

    public Virage(Modele modele) {
        this.modele = modele;
        new Thread(this).start();
    }

    public void to_middle(int valeurVirageG, int valeurVirageD) {
        if (valeurVirageG > Vue.IMP_PROF) {
            valeurVirageG--;
            while (valeurVirageD != Vue.IMP_PROF) {
                valeurVirageD++;
            }
        } else if (valeurVirageD > Vue.IMP_PROF) {
            valeurVirageD--;
            while (valeurVirageG != Vue.IMP_PROF) {
                valeurVirageG++;
            }
        }
        this.modele.getVue().setValeurVirageG(valeurVirageG);
        this.modele.getVue().setValeurVirageD(valeurVirageD);
    }

}
