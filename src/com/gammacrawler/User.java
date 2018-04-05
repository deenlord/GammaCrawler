package com.gammacrawler;

import javafx.scene.image.Image;

/**
 * @author deenlord
 * 3/24
 */
public class User extends Character implements Moveable {
	private int exp;
	private Image img;
	private double tileSize;

	/**
	 * @param name
	 */
	public User(String name) {
		this.setName(name);
		this.setMaxHP(100);
		this.setHP(this.getMaxHP());
		this.setInitialLocation(0, 0);
		this.exp = 0;
		this.tileSize = 64;
		this.img = new Image("file:src/com/gammacrawler/images/user.png", tileSize, tileSize, false, false);
		
//		inv = new Inventory();
//		main = new Weapon("Wooden Sword", 1);
//		shield = new Shield("Wooden Shield", 1);

	}
	
//	public increaseEXP(Enemy en) {
//		this.exp+= en.maxHP *0.5;
//	}
//	
	
	/**
	 * @return experience points
	 */
	public int getExp() {
		return this.exp;
	}
	
	/**
	 * @param increment - amount to increment exp by
	 */
	public void increaseExp(int increment) {
		this.exp += increment;
	}


	public Image getImage() {
		return this.img;
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Name: " + this.getName() + "\n");
		str.append("Max HP: " + this.getMaxHP() + "\n");
		str.append("Current HP: " + this.getHP() + "\n");
		str.append("Experience: " + this.exp + "\n");
		str.append("Location: " + this.getLocation()[0] + this.getLocation()[1] + "\n");
		
		return str.toString();
		
	}
	
	
	
	
}
