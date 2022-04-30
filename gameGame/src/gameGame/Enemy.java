package gameGame;

import java.awt.Image;
import java.awt.Point;

public abstract class Enemy {
	protected int x,y,dx,dy;
	protected Point point;
	
	public Enemy(int x, int y) {
		this.x=x;
		this.y=y;
		this.point = new Point(x,y);
	}
	public Enemy(int x, int y, int dx, int dy){
		this.x=x;
		this.y=y;
		this.point= new Point(x,y);
		this.dx=dx;
		this.dy=dy;
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
}
