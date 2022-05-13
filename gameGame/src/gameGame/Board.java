package gameGame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Stream;

import javax.swing.Timer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Board extends JPanel implements ActionListener, MouseListener {

	private int DELAY = 1;
	private Timer timer;
	private Image map;
	private JPanel pathPanel, menu;
	private JLabel roundnum, healthlabel, moneyLab;
	private JButton roundBut;
	private ArrayList<Tower> towers;
	private ArrayList<Enemy> enemies;
	private ArrayList<TPanel> towerpanels;
	private ArrayList<Projectile> shots;
	private int round, ticks, health, toPlace, money, enemyID;
	private boolean placing;
	private Color radiusHighlight;
	private Map<Rectangle, String> path = new HashMap<Rectangle, String>();
	private Map<Integer, String[]> rounds = new HashMap<Integer, String[]>();
	private Queue<Enemy> toSpawn = new LinkedList<Enemy>();

	public Board() {
		round = 0;
		ticks = 0;
		money = 500;
		health = 100;
		// sets map and text file to read
		placing = false;
		toPlace = 0;
		enemyID = 0;
		radiusHighlight = new Color(128, 128, 128, 125);
		enemies = new ArrayList<Enemy>();
		towers = new ArrayList<Tower>();
		towerpanels = new ArrayList<TPanel>();
		shots = new ArrayList<Projectile>();
		initBoard();
	}

	public void initBoard() {
		// can change string for differzent mapxxxx
		readMap("map1");
		loadRounds();
		setFocusable(true);
		this.setLayout(new GridBagLayout());
		addMouseListener(this);
		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.fill = c.BOTH;
		pathPanel = new JPanel();
		pathPanel.setOpaque(false);
		this.setBackground(Color.gray);
		menu = new JPanel();
		menu.setOpaque(false);
		menu.setLayout(new GridBagLayout());
		// menu.setBackground(Color.gray);
		this.add(pathPanel, c);
		c.weightx = 0.23;
		this.add(menu, c);
		healthlabel = new JLabel("Health : " + health);
		healthlabel.setFont(new Font("Verdana", Font.PLAIN, 18));
		healthlabel.setForeground(Color.white);
		roundnum = new JLabel("Round : " + round);
		roundnum.setFont(new Font("Verdana", Font.PLAIN, 18));
		roundnum.setForeground(Color.white);
		moneyLab = new JLabel("$" + money);
		moneyLab.setFont(new Font("Verdana", Font.PLAIN, 18));
		moneyLab.setForeground(Color.white);
		pathPanel.add(healthlabel, c);
		pathPanel.add(roundnum, c);
		roundBut = new JButton("Next round");
		roundBut.addActionListener(this);
		c.insets = new Insets(5, 0, 0, 5);
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.fill = GridBagConstraints.NONE;
		c.weightx = 0;
		c.weighty = 0.1;
		menu.add(roundBut, c);
		c.weightx = 1;

		menu.add(moneyLab, c);
		c.insets = new Insets(5, 5, 800, 5);
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.ipady = 65;
		String name = "default";
		int price = 0;
		int w = 0;
		int h = 0;
		for (int i = 0; i < 5; i++) {
			if (i == 0) {
				name = "gun";
				price = 125;
				w = 60;
				h = 60;
			}
			if (i == 1) {
				name = "machineGunner";
				price = 225;
				w = 100;
				h = 100;
			}
			if (i == 2) {
				name = "rocketLauncher";
				price = 275;
				w = 60;
				h = 60;
			}
			if (i == 3) {
				name = "laser";
				price = 315;
				w = 60;
				h = 60;
			}
			if (i == 4) {
				name = "iceTower";
				price = 350;
				w = 60;
				h = 60;
			}
			towerpanels.add(new TPanel(name, price, i, w, h));
			towerpanels.get(i).addMouseListener(this);
			menu.add(towerpanels.get(i), c);
		}
		timer = new Timer(DELAY, this);
		timer.start();

	}

	private void loadRounds() {
		try {
			Scanner scnr = new Scanner(new File("rounds.txt"));
			while (scnr.hasNextLine()) {
				rounds.put(Integer.parseInt(scnr.nextLine().strip()), scnr.nextLine().strip().split(","));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println(rounds);
	}

	private void readMap(String s) {
		try {
			Scanner scnr = new Scanner(new File(s + ".txt"));
			map = ImageIO.read(new File(s + ".png")).getScaledInstance(1500, 1080, Image.SCALE_SMOOTH);
			path.clear();
			while (scnr.hasNextLine()) {
				int[] coords = Stream.of(scnr.nextLine().split(",")).mapToInt(x -> Integer.parseInt(x)).toArray();
				path.put(new Rectangle(coords[0], coords[1], coords[2], coords[3]), scnr.nextLine().strip());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paintComponent(Graphics h) {
		Graphics2D g = (Graphics2D) h;
		super.paintComponent(g);
		// path image
		g.drawImage(map, 0, 0, null);
		// menu objects
		for (TPanel t : towerpanels) {
			// g.drawImage(t.getImage(), t.getPoint().x-132,t.getPoint().y-85,null);
			if (t.highlighted) {
				// for gray circle when placing to indicate radius
				Point mouse = MouseInfo.getPointerInfo().getLocation();
				g.setColor(this.radiusHighlight);
				g.drawImage(t.getImage(), mouse.x - 30, mouse.y - 30, null);
				// need to change to be dependent on different classes
				g.fillOval(mouse.x - t.getRadius() / 2, mouse.y - t.getRadius() / 2, t.getRadius(), t.getRadius());
			}
		}
		// print towers
		for (Tower t : towers) {
			g.drawImage(t.img, t.x, t.y, null);
		}
		// draw projectiles
		for (Projectile p : shots) {
			g.setColor(Color.yellow);
			g.fillOval(p.x, p.y, p.w, p.h);
			// g.drawImage(p.getImage(),p.x, p.y, null);
		}
		g.setColor(Color.white);
		for (Enemy e : enemies) {
			g.drawImage(e.getImage(), e.x, e.y, null);
		}
		Toolkit.getDefaultToolkit().sync();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.timer) {
			ticks++;

			for (int i = 0; i < enemies.size(); i++) {
				Enemy em = enemies.get(i);
				// based on text file map
				for (Rectangle r : path.keySet()) {
					if (r.contains(em.getPoint())) {
						String s = path.get(r);
						if (s.equals("DOWN")) {
							em.setDx(0);
							em.setDy(2);
						}
						if (s.equals("LEFT")) {
							em.setDx(-2);
							em.setDy(0);
						}
						if (s.equals("RIGHT")) {
							em.setDx(em.getSpeed());
							em.setDy(0);
						}
						if (s.equals("UP")) {
							em.setDx(0);
							em.setDy(-2);
						}
					}
				}

				if (this.ticks % em.speed == 0) {
					em.move();
				}
				// 1491 is end of map
				if (em.x >= 1491) {
					if (enemies.get(i) instanceof Basic) {
						health = health - 1;
					}
					healthlabel.setText("Health : " + health);
					enemies.remove(i);
					break;
				}
			}
			// move projectiles & check for collsion ( saves a for loop )
			for (Projectile p : shots) {
				p.move();
				for (Enemy em : enemies) {
					if (em.getBounds().intersects(p.getBounds()) && !p.hasHit(em.getID())) {
						p.hit(em.ei);
						this.money = this.money + em.getValue();
						this.moneyLab.setText("$" + this.money);
						em.damage(p.damage);
						if (em.hp <= 0) {
							enemies.remove(em);
							break;
						}
					}
				}
			}
			// enemy hit detection with point distance
			// could be optimized with boudning boxes to miminize big O
			for (Tower t : towers) {
				for (Enemy em : enemies) {
					double distance = t.getPoint().distance(em.getPoint());
					// System.out.println(distance);
					if (distance <= t.getRadius()) {
						// is within radius of tower
						if (ticks % t.firerate == 0) {
							// speed is going to be magnitude 5
							int speed = 10;
							int dx = (int) Math.round(((em.getCenterPoint().x - t.getPoint().x) / distance) * speed);
							int dy = (int) Math.round(((em.getCenterPoint().y - t.getPoint().y) / distance) * speed);
							double theta = calculateAngle(em.getCenterPoint().x, em.getCenterPoint().y, t.getPoint().x,
									t.getPoint().y);
							// System.out.println(theta);
							// shots.add(new Projectile(t.getPoint().x, t.getPoint().y, (int)
							// Math.round(dx),(int) Math.round(dy), 20, 20, theta));
							shots.add(new Projectile(t.getPoint().x, t.getPoint().y, dx, dy, 20, 20, theta));
						} // System.out.println("within radius");
							// break is necessary so it only targets first enemies in radius // add for loop
							// later for potential targeting options
						break;
					}
				}
			}
			// spawning
			if (ticks % 50 == 0) {
				spawnEnemies();
			}
			this.repaint();
		}
		if (e.getSource() == this.roundBut) {
			if (this.enemies.isEmpty()) {
				this.round++;
				initRound(round);
				this.roundnum.setText("Round : " + round);
			}
		}
	}

	public static double calculateAngle(double x1, double y1, double x2, double y2) {
		double angle = Math.toDegrees(Math.atan2(x2 - x1, y2 - y1));
		// Keep angle between 0 and 360
		return (angle);
	}

	public void initRound(int r) {
		for (String s : rounds.get(r)) {
			String[] str = s.split(" ");
			for (int i = 0; i < Integer.parseInt(str[0]); i++) {
				int id = Integer.parseInt(str[1]);
				if (id == 1) {
					toSpawn.add(new Basic(0, 210, this.enemyID));
				} else if (id == 2) {
					toSpawn.add(new Fast(0, 210, this.enemyID));
				} else if (id == 3) {
					toSpawn.add(new Thick(0, 210, this.enemyID));
				} else if (id == 4) {
					toSpawn.add(new Sigma(0, 210, this.enemyID));
				} else if (id == 5) {
					toSpawn.add(new MrHudson(0, 210, this.enemyID));
				}
				enemyID++;
			}
		}
	}

	public void spawnEnemies() {
		if (!toSpawn.isEmpty()) {
			enemies.add(toSpawn.poll());
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// System.out.println(e.getX() + " | " + e.getY());
		if (e.getSource() instanceof TPanel) {
			TPanel pointer = (TPanel) e.getSource();
			if (!pointer.highlighted && this.placing == false) {
				pointer.highlighted = true;
				this.toPlace = pointer.getID();
				this.placing = true;
				pointer.setBackground(Color.black);
			} else if (!pointer.highlighted) {
				for (TPanel t : towerpanels) {
					t.highlighted = false;
					t.setBackground(Color.white);
				}
				this.toPlace = pointer.getID();
				pointer.highlighted = true;
				pointer.setBackground(Color.black);
			} else if (pointer.highlighted) {
				pointer.highlighted = false;
				this.placing = false;
				toPlace = 0;
				pointer.setBackground(Color.white);
			}
		}
		if (e.getSource() instanceof Board && this.placing) {
			if (this.money >= towerpanels.get(toPlace).getCost()) {
				money = money - towerpanels.get(toPlace).getCost();
				// need to add adjustable firerate and radius;
				/*
				 * \ 0-gun 1-machine gun 2-rocket launcher 3-lazier 4-ice tower
				 */
				if (toPlace == 0) {
					towers.add(new Tower(e.getX() - 30, e.getY() - 30, towerpanels.get(toPlace).img, 200, 50));
				}
				if (toPlace == 1) {
					towers.add(new Tower(e.getX() - 30, e.getY() - 30, towerpanels.get(toPlace).img, 200, 25));
				}
				if (toPlace == 2) {
					towers.add(new Tower(e.getX() - 30, e.getY() - 30, towerpanels.get(toPlace).img, 400, 80));
				}
				if (toPlace == 3) {
					towers.add(new Tower(e.getX() - 30, e.getY() - 30, towerpanels.get(toPlace).img, 200, 50));
				}
				if (toPlace == 4) {
					towers.add(new Tower(e.getX() - 30, e.getY() - 30, towerpanels.get(toPlace).img, 200, 50));
				}
				// update jlabel
				this.moneyLab.setText("$" + this.money);
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
