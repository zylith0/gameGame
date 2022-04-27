package gameGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Board extends JPanel implements ActionListener{
	
	private int DELAY = 10;
	private Timer timer;
	private BufferedImage path;
	private JPanel pathPanel,menu;
	
	public Board() {
		initBoard();
	}
	public void initBoard(){
		setFocusable(true);
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth=2;
		c.gridheight=1;
		c.weightx=1;
		c.weighty=1;
		c.fill = c.BOTH;
		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridwidth=2;
		c2.gridheight=1;
		c2.weightx=0.3;
		c2.weighty=1;
		c2.fill = c2.BOTH;
		try {
			path = ImageIO.read(new File("path.png"));
		}
		catch(Exception e) {e.printStackTrace();}
		pathPanel = new JPanel();
		pathPanel.setBackground(Color.black);
		menu = new JPanel();
		menu.setBackground(Color.green);
		this.add(pathPanel,c);
		this.add(menu,c2);
		timer = new Timer(DELAY,this);
		timer.start();
	}
	@Override
    public void paintComponent(Graphics g) {
		g.drawImage(path, 0,0, null);
        super.paintComponent(g);
        Toolkit.getDefaultToolkit().sync();
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		this.repaint();
		
	}
}
