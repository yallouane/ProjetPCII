package controleur;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import modele.Vehicule;
import vue.Vue;

public class Deplacement implements KeyListener {

    private Vue affichage;
    private Vehicule vehicule;

    public Deplacement(Vue vue, Vehicule vehicule) {
        this.affichage = vue;
        this.vehicule = vehicule;
    }

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

