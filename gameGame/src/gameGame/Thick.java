package gameGame;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;

import javax.imageio.ImageIO;

public class Thick extends Enemy {

	protected Image image;

	public Thick(int x, int y, int ei) {
		// hp 3
		// speed 3
		// value 4
		super(x, y, 2, 0, ei, 3, 3, 4);
		initPicture();
	}

	public void initPicture() {
		try {
			image = ImageIO.read(new File("baloon3.png")).getScaledInstance(40, 40, Image.SCALE_SMOOTH);
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
