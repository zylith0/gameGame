package gameGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Board extends JPanel implements ActionListener, MouseListener{
	
	private int DELAY = 10;
	private Timer timer;
	private Image path;
	private JPanel pathPanel,menu;
	private ArrayList<Tower> towers;
	
	public Board() {
		initBoard();
		towers = new ArrayList<Tower>();
	}
	public void initBoard(){
		setFocusable(true);
		this.setLayout(new GridBagLayout());
		addMouseListener(this);
		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth=1;
		c.gridheight=1;
		c.weightx=1;
		c.weighty=1;
		c.fill = c.BOTH;
		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridwidth=1;
		c2.gridheight=1;
		c2.weightx=0.3;
		c2.weighty=1;
		c2.fill = c2.BOTH;
		try {
			path = ImageIO.read(new File("path.png")).getScaledInstance(1500, 1080, Image.SCALE_SMOOTH);
		}
		catch(Exception e) {e.printStackTrace();}
		pathPanel = new JPanel();
		pathPanel.setOpaque(false);
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
		super.paintComponent(g);
		g.drawImage(path, 0, 0, null);
        Toolkit.getDefaultToolkit().sync();
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		this.repaint();
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(e.getX() + " | " + e.getY());
		Duke num = new Duke();
		towers.add(num);
		menu.add(num.getLabel());
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
