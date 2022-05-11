package gameGame;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;

import javax.imageio.ImageIO;

public class MrHudson extends Enemy {

	protected Image image;

	public MrHudson(int x, int y, int ei) {
		// hp 50
		// speed 1
		// value 200
		super(x, y, 2, 0, ei, 50, 1, 200);
		initPicture();
	}

	public void initPicture() {
		try {
			image = ImageIO.read(new File("baloon.png")).getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		} catch (Exception e) {
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
