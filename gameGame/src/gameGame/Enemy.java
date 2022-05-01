package gameGame;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

public abstract class Enemy {
	protected int x,y,dx,dy,hp,ei;
	protected Point point;
	protected Rectangle bounds;
	
	public Enemy(int x, int y, int ei) {
		this.x=x;
		this.y=y;
		this.hp=2;
		this.ei=ei;
		this.point = new Point(x,y);
		this.bounds = new Rectangle(x,y,40,40);
	}
	public Enemy(int x, int y, int dx, int dy,int hp, int ei){
		this.x=x;
		this.y=y;
		this.point= new Point(x,y);
		this.dx=dx;
		this.dy=dy;
		this.hp=hp;
		this.bounds = new Rectangle(x,y,40,40);
		this.ei=ei;
	}
	public void move() {
		x = x + dx;
		y = y + dy;
	}
	public void setDx(int dx) {
		this.dx=dx;
	}
	public void setDy(int dy) {
		this.dy=dy;
	}
	abstract Image getImage();
	
	public Point getPoint() {
		point.setLocation(x, y);
		return point;
	}
	public abstract Rectangle getBounds();
	public void damage(int d) {
		hp=hp-d;
	}
	public int getID() {
		return ei;
	}
}
