package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements KeyListener {

	private Rectangle player1;
	private Rectangle player2;
	private double ballXVel = 0, ballYVel = 0;
	private double ballRad = 4;
	private double ballX, ballY;
	private double player2speed = 1.0;

	private int p1m, p2m, op1m, op2m;

	public GamePanel() {
		player1 = new Rectangle(10, 270, 20, 60);
		player2 = new Rectangle(664, 270, 20, 60);
		ballX = 350;
		ballY = 300;
		ballXVel = (Math.random() - 0.5) + 3;
		ballYVel = (Math.random() - 0.5) + 2;

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

					if (ballXVel > 0) {
						double predictedImpact = ballYVel / ballXVel * (694 - ballX) + ballY;
						if (predictedImpact < 0)
							predictedImpact = -predictedImpact;
						else if (predictedImpact > 472)
							predictedImpact = 472 - (predictedImpact - 472);

						if (player2.y + player2.height < predictedImpact && Math.abs(predictedImpact - player2.y) > 15) {
							player2.y += player2speed;
						} else {
							player2.y -= player2speed;
						}

					}

					if (player1.y + player1.height < 470 && player1.y > 3) {
						player1.y += p1m * 6;
					} else {
						if (player1.y <= 3) {
							player1.y = 4;
						} else {
							player1.y = 470 - player1.height - 1;
						}
					}
					if (player2.y + player1.height < 470 && player2.y > 3) {
						player2.y += p2m * 6;
					}

					if (player1.contains(ballX - ballRad - 2.0 * ballXVel, ballY)) {
						ballXVel = -ballXVel;
						ballX = player1.x + player1.width + ballRad * 2 + 1;
					} else if (player2.contains(ballX + ballRad, ballY)) {
						ballXVel = -ballXVel;
					}

					ballX = (int) ((double) (ballX + ballXVel));
					ballY = (int) ((double) (ballY + ballYVel));

					// Collisions

					if (ballY > 480) {
						ballYVel = -ballYVel;
					}
					if (ballY < 3) {
						ballYVel = -ballYVel;
						ballY = 4;
					}
					if (ballX > 695) {
						ballXVel = -ballXVel;
					}
					if (ballX < 0) {
						ballXVel = -ballXVel;
					}

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

		g2.setColor(Color.red);
		// g2.drawLine((int) ballX, (int) ballY, (int) (double) (ballX +
		// ballXVel * 1000.0), (int) (double) (ballY + ballYVel * 1000.0));

		g2.setColor(Color.white);
		g2.fillRect(player1.x, player1.y, player1.width, player1.height);
		g2.fillRect(player2.x, player2.y, player2.width, player2.height);

		g2.fillOval((int) (ballX - (ballRad / 2)), (int) (ballY - (ballRad / 2)), (int) ballRad * 2, (int) ballRad * 2);

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
		default:
			break;
		}

	}

	public void keyReleased(KeyEvent e) {
		p1m = 0;
	}
}
