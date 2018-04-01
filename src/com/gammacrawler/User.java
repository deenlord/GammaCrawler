package com.gammacrawler;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author deenlord
 * 3/24
 */
public class User extends Character {
	private int exp;
	private Image img;
	private ImageView imgView;
	
	public User() {
		new User("Dartanian");
		
	}

	/**
	 * @param name
	 */
	public User(String name) {
		this.setName(name);
		this.setMaxHP(100);
		this.setHP(this.getMaxHP());
		this.setInitialLocation(0, 0);
		this.exp = 0;
		this.img = new Image("https://cdn3.iconfinder.com/data/icons/food-set-3/91/Food_C230-128.png");
		this.imgView = new ImageView();
		this.imgView.setImage(this.img);
		imgView.setFitWidth(25);
		imgView.setPreserveRatio(true);
		imgView.setCache(true);
		
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
	
	public ImageView getImageView() {
		return this.imgView;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
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
