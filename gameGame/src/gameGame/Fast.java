package gameGame;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;

import javax.imageio.ImageIO;

public class Fast extends Enemy {

	protected Image image;

	public Fast(int x, int y, int ei) {
		// hp 1
		// speed 1
		// value 2
		super(x, y, 2, 0, ei, 1, 1, 2);
		initPicture();
	}

	public void initPicture() {
		try {
			image = ImageIO.read(new File("baloon2.png")).getScaledInstance(40, 40, Image.SCALE_SMOOTH);
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
