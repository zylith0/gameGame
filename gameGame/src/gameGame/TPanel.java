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
	
	public TPanel(String s) {
		this.name=s;
		highlighted = false;
		try {
			//change to s later when towers are decided
			img = ImageIO.read(new File("duke.png")).getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public String toString() {
		//for the mouselistener name
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
}
