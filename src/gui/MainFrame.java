package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

	private Dimension preferredSize = new Dimension(700, 500);
	private MenuPanel myMenu;
	private GamePanel myGamePanel;
	private JPanel content;

	public MainFrame() {
		super("NarPong v0.1 Alpha");

		Container c = this.getContentPane();

		content = new JPanel(new GridLayout(1, 1));

		myMenu = new MenuPanel(this);
		myMenu.setSize(preferredSize);

		myGamePanel = new GamePanel();
		myGamePanel.setSize(preferredSize);

		content.setFocusable(false);
		content.add(myMenu);

		c.add(content);

		this.setSize(preferredSize);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		MainFrame mf = new MainFrame();
		mf.setVisible(true);
		mf.setSize(700, 500);
	}

	public void newGame() {
		content.remove(myMenu);
		content.add(myGamePanel);
		myGamePanel.requestFocus();
		this.repaint();

	}

}
