package gui;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements KeyListener {

	public GamePanel() {
		this.setName("Game Panel");
		this.requestFocus();
		this.setFocusable(true);
		this.addKeyListener(this);
		this.setBackground(Color.black);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());

	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println(e.getKeyCode());

	}

}
