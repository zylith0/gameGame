package gameGame;

import java.awt.Image;

public abstract class Enemy {
	protected int x,y,dx,dy;
	
	public Enemy(int x, int y) {
		this.x=x;
		this.y=y;
	}
	public Enemy(int x, int y, int dx, int dy){
		this.x=x;
		this.y=y;
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
}
