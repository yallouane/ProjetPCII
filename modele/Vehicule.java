package modele;

public class Vehicule {
	private int positionX;
	private int positionY;
	private int vitesse;
	
	public Vehicule(int positionX,int positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.vitesse = 1;
	}

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
}
