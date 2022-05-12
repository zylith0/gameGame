package gameGame;

import java.awt.Graphics2D;
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
	protected int x, y, dx, dy, damage, w, h;
	protected double theta;
	protected Rectangle bounds;
	protected Point point;
	protected ArrayList<Integer> hits;
	protected BufferedImage image;

	public Projectile(int x, int y, int dx, int dy, int w, int h, double theta) {
		hits = new ArrayList<Integer>();
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.w = w;
		this.h = h;
		this.theta = theta;
		this.damage = 1;
		this.point = new Point(x, y);
		this.bounds = new Rectangle(x, y, w, h);
		loadImage(this.theta);
	}
	public void loadImage(double theta) {
		//System.out.println(theta);
		try {
			image =  ImageIO.read(new File("bullet.png"));
		}
		catch(Exception e) {e.printStackTrace();}
		//get width twice because the image could be scaled 90 degrees in which it would be tall
		BufferedImage newImage = new BufferedImage(image.getWidth(),image.getHeight(),image.getType());
		Graphics2D g = newImage.createGraphics();
		g.scale(0.1, 0.1);
		g.rotate(Math.toRadians(theta),w/2,h/2);
		g.drawImage(this.image,null,0,0);
		this.image = newImage;
	}
	public void move() {
		x = x + dx;
		y = y + dy;
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
		// return hits.contains(ei); for infinite pierce
		// going for single pierce
		return !hits.isEmpty();
	}

	public Image getImage() {
		return image;
	}
}
