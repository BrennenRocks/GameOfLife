/*
 * Brennen Davis and Robert Davis
 * Spring 1410 Team Project
 */
package gameOfLife;

import javax.swing.ImageIcon;

public enum Images {
	PLAY("Play.png"),
	PAUSE("Pause.png"),
	RESET("Reset.png"),
	INSTRUCTIONS("Instructions.png"),
	RANDOM("Random.png"),
	MENU("Menu.png"),
	LOAD("Load.png"),
	SAVE("Save.png"),
	NRANDOM("NRandom.png"),
	DEMO("GoLDemo.gif");
	
	private ImageIcon image;
	
	Images(String imagePath){
		this.image = new ImageIcon(GameOfLifeGui.class.getResource("/gameOfLife/images/"+imagePath));
	}

	/**
	 * @return the image
	 */
	public ImageIcon getImage() {
		return image;
	}
	
	

}
