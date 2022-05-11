package gameGame;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

public abstract class Enemy {
	protected int x, y, dx, dy, hp, ei, speed, value;
	protected Point point;
	protected Rectangle bounds;

	public Enemy(int x, int y, int dx, int dy, int ei, int hp, int speed, int value) {
		this.x = x;
		this.y = y;
		this.point = new Point(x, y);
		this.dx = dx;
		this.dy = dy;
		this.hp = hp;
		this.speed = speed;
		this.value = value;
		this.bounds = new Rectangle(x, y, 40, 40);
		this.ei = ei;
	}

	public void move() {
		x = x + dx;
		y = y + dy;
	}

	public int getSpeed() {
		return speed;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

	abstract Image getImage();

	public Point getPoint() {
		point.setLocation(x, y);
		return point;
	}

	public Point getCenterPoint() {
		point.setLocation(x + 20, y + 20);
		return point;
	}

	public abstract Rectangle getBounds();

	public void damage(int d) {
		hp -= d;
	}

	public int getID() {
		return ei;
	}

	public int getValue() {
		return this.value;
	}
}
