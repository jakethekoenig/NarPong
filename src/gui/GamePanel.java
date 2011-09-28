package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements KeyListener {

	private Rectangle player1;
	private Rectangle player2;
	private double ballXVel = 0, ballYVel = 0;
	private int ballRad = 4;
	private int ballX, ballY;

	private int p1m, p2m, op1m, op2m;

	public GamePanel() {
		player1 = new Rectangle(10, 270, 20, 60);
		player2 = new Rectangle(675, 270, 20, 60);
		ballX = 350;
		ballY = 300;
		ballXVel = Math.random() - 0.5 + 3.0;
		ballYVel = Math.random() - 0.5 + 1.0;

		this.setName("Game Panel");
		this.requestFocus();
		this.setFocusable(true);
		this.addKeyListener(this);
		this.setBackground(Color.black);

		final GamePanel daddy = this;

		// Game Loop
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
					}
					if (player1.y + player1.height < 470 && player1.y > 3) {
						player1.y += p1m * 2;
					} else {
						p1m = -p1m;
						player1.y += p1m * 2;
					}
					if (player2.y + player1.height < 470 && player2.y > 3) {
						player2.y += p2m * 2;
					} else {
						p2m = -p2m;
						player2.y += p2m * 2;
					}

					// Collisions
					if (player1.contains(ballX - ballRad - 2, ballY)) {
						ballXVel = -ballXVel;
					}
					if (player2.contains(ballX + ballRad + 2, ballY)) {
						ballXVel = -ballXVel;
					}
					if (ballY > 480) {
						ballYVel = -ballYVel;
					}
					if (ballY < 0) {
						ballYVel = -ballYVel;
					}
					if (ballX > 695) {
						ballXVel = -ballXVel;
					}
					if (ballX < 0) {
						ballXVel = -ballXVel;
					}

					ballX = (int) ((double) (ballX + ballXVel));
					ballY = (int) ((double) (ballY + ballYVel));

					daddy.repaint();

				}
			}
		}).start();
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.GRAY);
		g2.fillRect(0, 0, 694, 472);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setColor(Color.white);
		g2.fillRect(player1.x, player1.y, player1.width, player1.height);
		g2.fillRect(player2.x, player2.y, player2.width, player2.height);

		g2.fillOval(ballX - (ballRad / 2), ballY - (ballRad / 2), ballRad * 2, ballRad * 2);

		System.out.println("CUR:" + p1m + " ORG:" + op1m);

	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			p1m = -1;
			break;
		case KeyEvent.VK_S:
			p1m = 1;
			break;
		case KeyEvent.VK_UP:
			p2m = -1;
			break;
		case KeyEvent.VK_DOWN:
			p2m = 1;
			break;
		default:
			break;
		}

	}

	public void keyReleased(KeyEvent e) {

	}
}
