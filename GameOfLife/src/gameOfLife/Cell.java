/*
 * Brennen Davis and Robert Davis
 * Spring 1410 Team Project
 */


package gameOfLife;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class Cell extends JButton {
	private boolean alive;
	private boolean previouslyAlive = false;
	private Color live = Color.GREEN;
	private Color previouslyLive = Color.GRAY;
	private Color deadColor = Color.BLACK;
	
	public Cell() {
		setBackground(deadColor);
		setOpaque(true);
		setBorderPainted(false);

		addMouseListener(new MouseAdapter() {
			boolean btnToggle = true;

			@Override
			public void mousePressed(MouseEvent e) {
				if (btnToggle == true) {
					setAlive();
				} else if (btnToggle == false) {
					setBackground(deadColor);
					alive = false;
				}
				btnToggle = !btnToggle;

			}
		});

	}
	
	public void reset() {
		this.alive = false;
		this.previouslyAlive = false;
		setBackground(deadColor);
	}
	
	public boolean isAlive() {
		return this.alive;
	}
	
	public void setAlive() {
		this.alive = true;
		setBackground(live);
	}
	
	public void setDead() {
		if (this.alive == true) {
			this.previouslyAlive = true;
		}
		this.alive = false;
		if (previouslyAlive == true){
			setBackground(previouslyLive);
		} else {
			setBackground(deadColor);
		}
	}
	
	public void updateNodeColor() {
		if (alive == true) {
			setBackground(live);
		} else {
			if (previouslyAlive == true){
				setBackground(previouslyLive);
			} else {
				setBackground(deadColor);
			}
		}
	}
}
