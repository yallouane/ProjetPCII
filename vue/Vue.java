package vue;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import modele.Modele;
public class Vue extends JPanel{
	

    public static final int P_WIDTH = 1080;
    public static final int P_HEIGHT = 720;
    public static final int ACC_LIMITE = 150;
    public static final int LIGNEHORIZONY = 200;
    public static final int OVAL_WIDTH = 25;
    public static final int OVAL_HEIGHT = 50;
    public static final int OVAL_X = P_WIDTH/2 - OVAL_WIDTH/2;
    public static final int OVAL_Y = P_HEIGHT - ACC_LIMITE;
	
    private Modele modele;
    
	public Vue(Modele modele) {
		setPreferredSize(new Dimension(P_WIDTH, P_HEIGHT));
		this.modele = modele;
	}
	
	 @Override
	 public void paint(Graphics g) {
		revalidate();
		super.paint(g);
		g.drawLine(0, LIGNEHORIZONY, P_WIDTH, LIGNEHORIZONY);
		g.drawOval(this.modele.getVehicule().getPositionX(), this.modele.getVehicule().getPositionY(), OVAL_WIDTH, OVAL_HEIGHT);
		for(int i=0;i<this.modele.getRoute().getRouteDroite().size()-1;i++) {
			g.drawLine(this.modele.getRoute().getRouteDroite().get(i).x, this.modele.getRoute().getRouteDroite().get(i).y, this.modele.getRoute().getRouteDroite().get(i+1).x, this.modele.getRoute().getRouteDroite().get(i+1).y);
			g.drawLine(this.modele.getRoute().getRouteGauche().get(i).x, this.modele.getRoute().getRouteGauche().get(i).y, this.modele.getRoute().getRouteGauche().get(i+1).x, this.modele.getRoute().getRouteGauche().get(i+1).y);
		}
	 }
}
