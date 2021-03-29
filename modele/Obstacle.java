package modele;

import vue.Vue;

public class Obstacle extends Vehicule {

    public static final int MAXWIDTHHEIGHT = 50;

    protected int widthheight;

    protected String file;

    public Obstacle(int positionX, int positionY) {
        super(positionX, positionY);
        this.widthheight = 1;
        this.file = "src/ressources/meteorite_1.png";

    }

    public void genererObstacle() {
        this.setPositionY(Vue.LIGNEHORIZONY);
        this.setPositionX(this.modele.getRoute().randomInt((int) Vue.ROUTE_DROITE, (int) Vue.ROUTE_GAUCHE));
        this.setWidthheight(1);
    }

    public int getWidthheight() {
        return widthheight;
    }

    public void setWidthheight(int widthheight) {
        this.widthheight = widthheight;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

}
