package gameGame;

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public class Baloon extends Enemy{
	
	protected Image image;
	
	public Baloon(int x,int y) {
		super(x, y);
		initPicture();
		
	}
	public Baloon(int x,int y,int dx,int dy) {
		super(x,y,dx,dy);
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

}
