package gameGame;

import java.awt.Image;
import java.awt.Point;

public class Tower {
	protected int x, y, radius, firerate;
	protected Image img;
	protected Point location;

	public Tower(int x, int y, Image img, int r, int f) {
		this.x = x;
		this.y = y;
		this.img = img;
		location = new Point(x + (img.getWidth(null) / 2), y + (img.getHeight(null) / 2));
		this.radius = r;
		this.firerate = f;
	}

	public int getRadius() {
		return radius;
	}

	public Point getPoint() {
		return location;
	}
}
