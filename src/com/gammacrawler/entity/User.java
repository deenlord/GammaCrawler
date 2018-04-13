package com.gammacrawler.entity;

import com.gammacrawler.Settings;
import com.gammacrawler.item.HealthPotion;
import com.gammacrawler.item.WoodenSword;

import javafx.scene.image.ImageView;

/**
 * @author deenlord
 * 3/24
 */
public class User extends Character implements Moveable {

	WoodenSword sword;

	/**
	 * @param name
	 */
	public User(String name) {
		super(name);
		this.setMaxHP(100);
		this.setHP(this.getMaxHP() - 10);
		this.setXP(0);
		this.setSprite(new Sprite("file:src/com/gammacrawler/images/user2.png"));
		sword = new WoodenSword("Wooden Sword", 
				new Sprite("file:src/com/gammacrawler/images/woodensword.png", Settings.TILESIZE / 2));
		this.getInventory().add(sword);
		HealthPotion health = new HealthPotion();
		health.addToUser(this);
		health.drink(this);
		System.out.println(this.getHP());
		
		for (Item i : this.getInventory()) {
			System.out.println(i.getName());
		}
		
		
	}
	
	public ImageView getWeapon() {
		return this.sword.getSprite().getSprite();
	}
	
	
	public void attack() {
		int x = (int) ((this.getLocation()[0]) + 1 + Settings.TILESIZE);
		int y = (int) ((this.getLocation()[1]) + 1 + (Settings.TILESIZE) /4);
		this.getWeapon().setLayoutX(x);
		this.getWeapon().setLayoutY(y);
		this.sword.swing();
	}
	
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Name: " + this.getName() + "\n");
		str.append("Max HP: " + this.getMaxHP() + "\n");
		str.append("Current HP: " + this.getHP() + "\n");
		str.append("Experience: " + this.getXP() + "\n");
		str.append("Location: " + this.getLocation()[0] + " " + this.getLocation()[1] + "\n");
		
		return str.toString();
		
	}


	
	
	
	
}
