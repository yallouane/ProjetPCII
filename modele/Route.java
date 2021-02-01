package modele;

import java.awt.Point;
import java.util.ArrayList;

public class Route {
	private ArrayList<Point> routeGauche;
	private ArrayList<Point> routeDroite;
	
	public Route() {
		this.routeGauche = new ArrayList<Point>();
		this.routeDroite = new ArrayList<Point>();
	}
	
	public void genererLigne(int P_HEIGHT, int LIGNEHORIZONY, int POSITIONOVALE) {
		for(int i=P_HEIGHT;i>=LIGNEHORIZONY; i -= 20) {
			this.routeDroite.add(new Point(POSITIONOVALE+200,i));
			this.routeGauche.add(new Point(POSITIONOVALE-200,i));
		}
	}
	
	public ArrayList<Point> getRouteGauche(){
		return this.routeGauche;
	}
	public ArrayList<Point> getRouteDroite(){
		return this.routeDroite;
	}
}
