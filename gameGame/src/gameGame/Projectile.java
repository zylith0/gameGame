package gameGame;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Projectile {
	protected int x,y,dx,dy,damage,w,h;
	protected Rectangle bounds;
	protected Point point;
	protected ArrayList<Integer> hits;
	public Projectile(int x,int y, int dx, int dy, int w, int h) {
		hits = new ArrayList<Integer>();
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
		this.bounds.setBounds(x, y, w, h);
		return bounds;
	}
	public Point getPoint() {
		this.point.setLocation(x, y);
		return this.point;
	}
	public void hit(int ei) {
		hits.add(ei);
	}
	public boolean hasHit(int ei) {
		return hits.contains(ei);
	}
}
