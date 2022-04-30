package gameGame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.Point;
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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Board extends JPanel implements ActionListener, MouseListener{
	
	private int DELAY = 1;
	private Timer timer;
	private Image path;
	private JPanel pathPanel,menu;
	private JLabel roundnum,healthlabel,moneyLab;
	private JButton roundBut;
	private ArrayList<Tower> towers;
	private ArrayList<Enemy> enemies;
	private ArrayList<TPanel> towerpanels;
	private ArrayList<Projectile> shots;
	private int round,ticks,toSpawn,health,toPlace,money;
	private boolean placing;
	private Color radiusHighlight;
	
	public Board() {
		round =0;
		ticks=0;
		toSpawn=0;
		money=500;
		health=100;
		placing=false;
		toPlace=0;
		radiusHighlight = new Color(128,128,128,125);
		enemies = new ArrayList<Enemy>();
		towers = new ArrayList<Tower>();
		towerpanels = new ArrayList<TPanel>();
		shots = new ArrayList<Projectile>();
		initBoard();
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
		try {
			path = ImageIO.read(new File("path.png")).getScaledInstance(1500, 1080, Image.SCALE_SMOOTH);
		}
		catch(Exception e) {e.printStackTrace();}
		pathPanel = new JPanel();
		pathPanel.setOpaque(false);
		this.setBackground(Color.gray);
		menu = new JPanel();
		menu.setOpaque(false);
		menu.setLayout(new GridBagLayout());
		//menu.setBackground(Color.gray);
		this.add(pathPanel,c);
		c.weightx=0.23;
		this.add(menu,c);
		healthlabel = new JLabel("Health : " + health);
		roundnum = new JLabel("Round : " +round);
		moneyLab = new JLabel("$" + money);
		moneyLab.setFont(new Font("Verdana", Font.PLAIN,18));
		moneyLab.setForeground(Color.white);
		pathPanel.add(healthlabel,c);
		pathPanel.add(roundnum,c);
		roundBut = new JButton("Next round");
		roundBut.addActionListener(this);
		c.insets = new Insets(5,0,0,5);
		c.anchor=GridBagConstraints.FIRST_LINE_END;
		c.fill=GridBagConstraints.NONE;
		c.weightx=0;
		c.weighty=0.1;
		menu.add(roundBut,c);
		c.weightx=1;
		
		menu.add(moneyLab,c);
		c.insets = new Insets(5,5,800,5);
		c.weightx=1;
		c.weighty=1; 
		c.fill=GridBagConstraints.HORIZONTAL;
		c.anchor=GridBagConstraints.CENTER;
		c.ipady=65;
		String name="default";
		int price = 0;
		for(int i=0;i<5;i++) {
			if(i==0) {name="Tower1";price=125;}
			if(i==1) {name="Tower2";price=225;}
			if(i==2) {name="Tower3";price=275;}
			if(i==3) {name="Tower4";price=315;}
			if(i==4) {name="Tower5";price=350;}
			towerpanels.add(new TPanel(name,price,i));
			towerpanels.get(i).addMouseListener(this);
			menu.add(towerpanels.get(i),c);
		}
		timer = new Timer(DELAY,this);
		timer.start();
		
	}
	@Override
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//path image
		g.drawImage(path, 0, 0, null);
		//menu objects
		for(TPanel t: towerpanels) {
			//g.drawImage(t.getImage(), t.getPoint().x-132,t.getPoint().y-85,null);
			if(t.highlighted) {
				Point mouse = MouseInfo.getPointerInfo().getLocation();
				g.setColor(this.radiusHighlight);
				g.drawImage(t.getImage(),mouse.x-30,mouse.y-30,null);
				//need to change to be dependent on different clases
				g.fillOval(mouse.x-200, mouse.y-200, 400, 400);
			}
		}
		//print towers
		for(Tower t: towers) {
			g.drawImage(t.img,t.x,t.y,null);
		}
		//draw projectiles
		for(Projectile p: shots) {
			g.setColor(Color.yellow);
			g.fillOval(p.x+p.w/2, p.y-p.h/2, p.w, p.h);
		}
		g.setColor(Color.white);
		for(Enemy e: enemies) {
			g.drawImage(e.getImage(),e.x,e.y,null);
		}
        Toolkit.getDefaultToolkit().sync();
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.timer) {
			ticks++;
			//enemy movement
			for(int i=0;i<enemies.size();i++) {
				//will be based on map but for now the turn is 503,518
				if(enemies.get(i).x>503&&enemies.get(i).y>216) {
					enemies.get(i).dy=-1;
					enemies.get(i).dx=0;
				}
				else if(enemies.get(i).y<=215) {
					enemies.get(i).dy=0;
					enemies.get(i).dx=1;
				}
				enemies.get(i).move();
				//1066 is end of map
				if(enemies.get(i).x>=1066) {
					if(enemies.get(i) instanceof Baloon) {
						health = health - 1;
					}
					healthlabel.setText("Health : " + health);
					enemies.remove(i);
					break;
				}
			}
			//move projectiles
			for(Projectile p: shots) {
				p.move();
			}
			//enemy hit detection with point distance
			//could be optimized with boudning boxes to miminize big O
			for(Tower t: towers) {
				for(Enemy em: enemies) {
					double distance = t.getPoint().distance(em.getPoint());
					//System.out.println(distance);
					if(distance<=t.getRadius()) {
						//is within radius of tower
						if(ticks % t.firerate==0) {
							//speed is going to be magnitude 5
							int speed = 5;
							double dx = (em.x-t.x)/distance*speed;
							double dy = (em.y-t.y)/distance*speed;
							shots.add(new Projectile(t.x,t.y,(int)Math.round(dx),(int)Math.round(dy)));
						}
						//System.out.println("within radius");
						break;
					}
				}
			}
			//spawning
			if(ticks%65==0) {
				spawnEnemies(round);
			}
			this.repaint();
		}
		if(e.getSource()==this.roundBut) {
			if(this.enemies.isEmpty()) {
				this.round++;
				if(round==1) {
					toSpawn=5;
				}
				this.roundnum.setText("Round : " + round);
			}
		}
	}
	public void spawnEnemies(int r) {
		if(r==1&toSpawn>0) {
			enemies.add(new Baloon(0,515,1,0));
			toSpawn--;
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() instanceof TPanel) {
			TPanel pointer = (TPanel)e.getSource();
			if(!pointer.highlighted && this.placing==false) {
				pointer.highlighted=true;
				this.toPlace=pointer.getID();
				this.placing=true;
				pointer.setBackground(Color.black);
			}
			else if(!pointer.highlighted) {
				for(TPanel t: towerpanels) {
					t.highlighted=false;
					t.setBackground(Color.white);
				}
				this.toPlace=pointer.getID();
				pointer.highlighted=true;
				pointer.setBackground(Color.black);
			}
			else if(pointer.highlighted){
				pointer.highlighted=false;
				this.placing=false;
				toPlace=0;
				pointer.setBackground(Color.white);
			}
		}
		if(e.getSource() instanceof Board && this.placing) {
			if(this.money>=towerpanels.get(toPlace).getCost()) {
				money=money-towerpanels.get(toPlace).getCost();
				towers.add(new Duke(e.getX()-30,e.getY()-30,towerpanels.get(toPlace).img,200,50));
				//update jlabel
				this.moneyLab.setText("$"+this.money);
			}
			
		}
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
