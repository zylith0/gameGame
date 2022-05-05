package gameGame;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Projectile {
	protected int x,y,dx,dy,damage,w,h,theta;
	protected Rectangle bounds;
	protected Point point;
	protected ArrayList<Integer> hits;
	protected Image image;
	public Projectile(int x,int y, int dx, int dy, int w, int h, int theta) {
		hits = new ArrayList<Integer>();
		this.x=x;
		this.y=y;
		this.dx=dx;
		this.dy=dy;
		this.w=w;
		this.h=h;
		this.theta=theta; 
		this.damage=1;
		this.point = new Point(x,y);
		this.bounds = new Rectangle(x,y,w,h);
		loadImage();
	}
	public void loadImage() {
		try {
			image = ImageIO.read(new File("bullet.png")).getScaledInstance(w, h, Image.SCALE_SMOOTH);;
		}
		catch(Exception e) {e.printStackTrace();}
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
	public Image getImage() {
		return image;
	}
	public void setRotation(double x) {
		AffineTransform tx = AffineTransform.getRotateInstance(this.theta, w/2, h/2);
	}
}
