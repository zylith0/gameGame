package gameGame;

import java.awt.Image;
import java.awt.Point;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class TPanel extends JPanel{
	protected String name;
	protected Image img;
	protected int x,y;
	protected boolean highlighted;
	protected int price,id;
	
	public TPanel(String s, int p,int i, int w, int h) {
		this.name=s;
		this.price=p;
		this.id=i;
		highlighted = false;
		try {
			//change to s later when towers are decided
			img = ImageIO.read(new File(s + ".png")).getScaledInstance(w, h, Image.SCALE_SMOOTH);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public String toString() {
		//for the mouse listener name
		return name;
	}
	public Image getImage() {
		return img;
	}
	public Point getPoint() {
		
		Point p = this.getLocation();
		javax.swing.SwingUtilities.convertPointToScreen(p, this);
		return p;
	}
	public int getID() {
		return id;
	}
	public int getCost() {
		return price;
	}
	public int getRadius() {
		switch(name) {
		case "gun": return 400;
		case "machineGun": return 400;
		case "rocketLauncher": return 800;
		case "laser": return 800;
		case "iceTower": return 200;
		}
		return 0;
	}
}
