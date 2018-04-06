package com.gammacrawler;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author deenlord
 * 3/24
 */
public class User extends Character implements Moveable {
	private int exp;


	/**
	 * @param name
	 */
	public User(String name) {
		this.setName(name);
		this.setMaxHP(100);
		this.setHP(this.getMaxHP());
		this.exp = 0;
		this.setTileSize((int)Main.tileSize);
		this.setSprite(new Sprite("file:src/com/gammacrawler/images/user.png", (int)Main.tileSize));
	}
	
	/**
	 * @return experience points
	 */
	public int getExp() {
		return this.exp;
	}
	
	public void setExp(int xp) {
		this.exp += xp;
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Name: " + this.getName() + "\n");
		str.append("Max HP: " + this.getMaxHP() + "\n");
		str.append("Current HP: " + this.getHP() + "\n");
		str.append("Experience: " + this.exp + "\n");
		str.append("Location: " + this.getLocation()[0] + " " + this.getLocation()[1] + "\n");
		
		return str.toString();
		
	}


	
	
	
	
}
