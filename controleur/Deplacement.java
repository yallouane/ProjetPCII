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
		if(e.getKeyCode() == 38 || e.getKeyCode() == 90) { // HAUT
			vehicule.deplacement("HAUT");
			affichage.repaint();
		}
		else if(e.getKeyCode() == 40 || e.getKeyCode() == 83) { // BAS
			vehicule.deplacement("BAS");
			affichage.repaint();
		}
		else if(e.getKeyCode() == 37 || e.getKeyCode() == 81) { // GAUCHE
			vehicule.deplacement("GAUCHE");
			affichage.repaint();
		}
		else if(e.getKeyCode() == 39 || e.getKeyCode() == 68) { // DROITE
			vehicule.deplacement("DROITE");
			affichage.repaint();
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
