package gameGame;

import java.awt.Image;

public abstract class Tower {
	protected int x,y;
	protected Image img;
	
	public Tower(int x, int y, Image img) {
		this.x=x;
		this.y=y;
		this.img=img;
	}
}
