package modele;

import vue.Vue;

public class Vehicule {
	
	 /**
     * CONSTANTES servant au déplacement du véhicule (vertical et horizontal)
     */
    
    public static final String HAUT = "HAUT";
    public static final String BAS = "BAS";
    public static final String GAUCHE = "GAUCHE";
    public static final String DROITE = "DROITE";
    public static final int POS_INC = 5;
    public static final int VIT_INC = 1;
    public static final int VIT_LIMITE = 150;

    /**
     * Attributs
     */
    // Position en x et en y du véhicule
    private int positionX;
    private int positionY;

    // Vitesse du vehicule
    private int vitesse;

    /**
     * Constructeur
     *
     * @param positionX
     * @param positionY
     */
    public Vehicule(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.vitesse = 10;
    }
    
    /**
     * Getters & Setters
     * @return Integers
     */
    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }
	
    /**
     * methode definissant le deplacement du vehicule lorsque le controleur
     * detecte qu'une touche a ete pressee
     *
     * @param valeur
     */
	public void deplacement(String valeur) {
		 if (valeur == HAUT) {
	            if (this.positionY >= Vue.ACC_LIMITE) {
	                this.positionY -= POS_INC;
	            }
	            if (this.vitesse <= VIT_LIMITE - VIT_INC) {
	                this.vitesse += VIT_INC;
	            } 
		}
		else if(valeur == BAS ) {
			if(this.positionY <= Vue.DEC_LIMITE) {
				this.positionY += POS_INC;
			}
			if(this .vitesse > 0)  {
					this.vitesse -= VIT_INC;
			}
			if(this.vitesse <= 0) {
				System.out.println("game Over");
			}
		}
		else if(valeur == GAUCHE) {
			this.positionX -=5;
		}
		else if(valeur == DROITE) {
			this.positionX +=5;
		}
	}
	
}
