package gameGame;

import java.awt.Point;
import java.awt.Rectangle;

public class Projectile {
	protected int x,y,dx,dy,damage,w,h;
	protected Rectangle bounds;
	protected Point point;
	public Projectile(int x,int y, int dx, int dy) {
		this.x=x;
		this.y=y;
		this.dx=dx;
		this.dy=dy;
		this.w=10;
		this.h=10;
		this.damage=1;
		this.point = new Point(x,y);
		this.bounds = new Rectangle(x,y,w,h);
	}
	public Projectile(int x,int y, int dx, int dy, int w, int h) {
		this.x=x;
		this.y=y;
		this.dx=dx;
		this.dy=dy;
		this.w=w;
		this.h=h;
		this.damage=1;
		this.point = new Point(x,y);
		this.bounds = new Rectangle(x,y,w,h);
	}
	public void move() {
		x=x+dx;
		y=y+dy;
	}
	public Rectangle getBounds() {
		return bounds;
	}
	public Point getPoint() {
		this.point.setLocation(x, y);
		return this.point;
	}
}
