package modele;

import vue.Vue;

/**
 *
 * @author gp910
 */
public class Checkpoint {

    public Modele modele;

    public int positionXGauche;
    public int positionXDroite;
    public int positionY;

    public Checkpoint() {
        this.positionY = Vue.LIGNEHORIZONY;
    }

    public void baisser() {
        this.positionY = this.positionY + 1;

        this.positionXDroite = (this.positionXDroite < Vue.ROUTE_GAUCHE) ? this.positionXDroite + 1 : this.positionXDroite - 1;
        this.positionXGauche = (this.positionXGauche > Vue.ROUTE_DROITE) ? this.positionXGauche - 1 : this.positionXGauche + 1;
    }

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

    public void setModele(Modele modele) {
        this.modele = modele;
        this.positionXGauche = Vue.P_WIDTH / 2;
        this.positionXDroite = Vue.P_WIDTH / 2;
    }

}
