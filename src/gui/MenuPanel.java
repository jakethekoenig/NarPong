package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MenuPanel extends JPanel implements MouseMotionListener, MouseListener {

	private ImageIcon background;
	private MainFrame bigDaddy;
	private Rectangle newGameBounds;
	private Rectangle highScoreBounds;
	private boolean newGameHover;
	private boolean highScoreHover;

	public MenuPanel(MainFrame parent) {
		bigDaddy = parent;
		background = new ImageIcon(getClass().getResource("/res/img/NarPong_Menu.png"));
		newGameBounds = new Rectangle(new Point(233, 176), new Dimension(247, 44));
		highScoreBounds = new Rectangle(new Point(216, 245), new Dimension(280, 44));

		this.setPreferredSize(new Dimension(background.getIconWidth(), background.getIconHeight()));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(Color.black);
		g2.fillRect(0, 0, 800, 600);

		Dimension d = this.getSize();

		int x = (int) ((double) d.width / 2.0 - 300.0);
		int y = (int) ((double) d.height / 2.0 - 200.0);

		g2.drawImage(background.getImage(), x, y, 600, 400, Color.black, null);

		g2.setColor(Color.gray);

		if (newGameHover) {
			g2.drawRect(newGameBounds.x, newGameBounds.y, newGameBounds.width, newGameBounds.height);
		}

		if (highScoreHover) {
			g2.drawRect(highScoreBounds.x, highScoreBounds.y, highScoreBounds.width, highScoreBounds.height);
		}
	}

	public void mouseMoved(MouseEvent e) {
		if (newGameBounds.contains(e.getPoint())) {
			newGameHover = true;
		} else {
			newGameHover = false;
		}

		if (highScoreBounds.contains(e.getPoint())) {
			highScoreHover = true;
		} else {
			highScoreHover = false;
		}

		this.repaint();
	}

	public void mouseClicked(MouseEvent e) {
		if (newGameBounds.contains(e.getPoint())) {
			bigDaddy.newGame();
		}
		if (highScoreBounds.contains(e.getPoint())) {
			System.out.println("CLICK HIGHSCORE");
		}
	}

	public void mouseDragged(MouseEvent e) { /* unused */
	}

	public void mousePressed(MouseEvent e) { /* unused */
	}

	public void mouseReleased(MouseEvent e) { /* unused */
	}

	public void mouseEntered(MouseEvent e) { /* unused */
	}

	public void mouseExited(MouseEvent e) { /* unused */
	}

}
