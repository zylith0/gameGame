package gameGame;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;

import javax.imageio.ImageIO;

public class Baloon extends Enemy{
	
	protected Image image;
	
	public Baloon(int x,int y, int ei) {
		super(x, y, ei);
		initPicture();
		
	}
	public Baloon(int x,int y,int dx,int dy, int ei) {
		//baloon hp is 2
		super(x,y,dx,dy,1, ei);
		initPicture();
	}
	public void initPicture() {
		try {
			image = ImageIO.read(new File("baloon.png")).getScaledInstance(40, 40, Image.SCALE_SMOOTH);
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
