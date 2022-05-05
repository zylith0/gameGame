package gameGame;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;

import javax.imageio.ImageIO;

public class Baloon3 extends Enemy{
	
	protected Image image;
	
	public Baloon3(int x,int y, int ei) {
		super(x, y, ei);
		initPicture();
		
	}
	public Baloon3(int x,int y,int dx,int dy, int ei, int speed) {
		super(x,y,dx,dy,15, ei,speed);
		initPicture();
	}
	public void initPicture() {
		try {
			image = ImageIO.read(new File("baloon3.png")).getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public Image getImage() {
		return image;
	}
	public Rectangle getBounds() {
		this.bounds.setBounds(x, y, 40, 40);
		return this.bounds;
	}
}
