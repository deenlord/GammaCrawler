package com.gammacrawler;

import javafx.scene.image.ImageView;

/**
 * @author deenlord
 * 3/24
 */
public class User extends Character implements Moveable {
	private int exp;
	WoodenSword sword;
	HealthPotion hp1;

	/**
	 * @param name
	 */
	public User(String name) {
		this.setName(name);
		this.setMaxHP(100);
		this.setHP(this.getMaxHP());
		this.exp = 0;
		this.setTileSize((int)Main.tileSize);
		this.setSprite(new Sprite("file:src/com/gammacrawler/images/user2.png", (int) Main.tileSize));
		sword = new WoodenSword("Wooden Sword", 
				new Sprite("file:src/com/gammacrawler/images/woodensword.png", (int) Main.tileSize / 2));
		hp1 = new HealthPotion("Lite Health Potion", 
				new Sprite("file:src/com/gammacrawler/images/litehealthpotion.png",
				(int) Main.tileSize));
		this.inv = new Inventory();
		this.inv.al.add(sword);
		this.inv.al.add(hp1);
		
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
	
	public ImageView getWeapon() {
		return this.sword.sprite.getSprite();
	}
	
	
	public void attack() {
		int x = (int) ((this.getLocation()[0]) + 1 + Main.tileSize);
		int y = (int) ((this.getLocation()[1]) + 1 + (Main.tileSize) /4);
		this.getWeapon().setLayoutX(x);
		this.getWeapon().setLayoutY(y);
		this.sword.swing();
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
