package gameGame;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Duke extends Tower{
	public Duke(int x, int y, Image img) {
		super(x,y,img);
	}
	public Duke(int x,int y, Image img, int r, int f) {
		super(x,y,img,r,f);
	}
}
