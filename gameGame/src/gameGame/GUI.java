package gameGame;

import javax.swing.JFrame;

public class GUI {

	private static JFrame frame;

	public static void main(String[] args) {
		new GUI();
	}

	public GUI() {
		frame = new JFrame("Game");
		frame.setSize(1920, 1080);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new Board());
		frame.setVisible(true);
	}
}
