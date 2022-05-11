package gameGame;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;

import javax.imageio.ImageIO;

public class Basic extends Enemy {

	protected Image image;

	public Basic(int x, int y, int ei) {
		// hp 1
		// speed 2
		// value 1
		super(x, y, 2, 0, ei, 1, 2, 1);
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
