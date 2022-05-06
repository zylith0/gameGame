package gameGame;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Projectile {
	protected int x,y,dx,dy,damage,w,h,theta;
	protected Rectangle bounds;
	protected Point point;
	protected ArrayList<Integer> hits;
	protected BufferedImage image;
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
		AffineTransform tx = AffineTransform.getRotateInstance(this.theta, 0, 0);
		//tx.scale(0.5, 0.5);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR); 
		BufferedImage newimage = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
		op.filter(image, newimage);
		this.image = newimage;
	}
	public void loadImage() {
		try {
			image =  ImageIO.read(new File("bullet.png"));
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
		//return hits.contains(ei); for infinite pierce
		//going for single pierce
		return !hits.isEmpty();
	}
	public Image getImage() {
		return image;
	}
}
