package gameGame;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Duke extends Tower{
	protected Image duke;
	protected JLabel imageicon;
	public Duke() {
		super();
		try {
			duke = ImageIO.read(new File("duke.png")).getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
		imageicon = new JLabel(new ImageIcon(duke));
	}
	public JLabel getLabel() {
		return imageicon;
	}
}
