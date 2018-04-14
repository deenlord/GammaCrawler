package com.gammacrawler.entity;

import com.gammacrawler.Direction;
import com.gammacrawler.Settings;
import com.gammacrawler.item.HealthPotion;
import com.gammacrawler.item.WoodenSword;

import javafx.scene.image.ImageView;

/**
 * @author deenlord
 * 3/24
 */
public class User extends Character implements Moveable {
	protected Sprite sprite = new Sprite("file:src/com/gammacrawler/images/user2.png");
	protected WoodenSword sword;
	protected Direction direction;

	/**
	 * @param name
	 */
	public User(String name) {
		super(name);
		this.setMaxHP(100);
		this.setHP(this.getMaxHP() - 10);
		this.setXP(0);
		this.setSprite(sprite);
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
		
		this.direction = Direction.EAST;
		
		
	}
	
	public ImageView getWeapon() {
		return this.sword.getSprite().getSprite();
	}
	
	public void move(Direction dir) {
		System.out.println("Trying to move");
		int[] loc = this.getLocation();
		switch (dir) {
		case NORTH:
			loc[1] -= Settings.TILESIZE;
			break;
		case SOUTH:
			loc[1] += Settings.TILESIZE;
			break;
		case EAST:
			loc[0] += Settings.TILESIZE;
			this.direction = Direction.EAST;
			this.sprite.rotate(Direction.EAST);
			break;
		case WEST:
			loc[0] -= Settings.TILESIZE;
			this.direction = Direction.WEST;
			this.sprite.rotate(Direction.WEST);
			break;
		}
		this.setLocation(loc[0], loc[1]);

	}
	
	
	public void attack() {
		int x;
		int y;
		
	switch(this.direction) {
	case EAST:
		x = (int) ((this.getLocation()[0]) + 1 + Settings.TILESIZE);
		y = (int) ((this.getLocation()[1]) + 1 + (Settings.TILESIZE) /4);
		sword.getSprite().rotate(this.direction);
		this.getWeapon().setLayoutX(x);
		this.getWeapon().setLayoutY(y);
		this.sword.swing();
		break;
	case WEST:
		x = (int) (this.getLocation()[0] - (Settings.TILESIZE /2));
		y = (int) ((this.getLocation()[1]) + 1 + (Settings.TILESIZE) /4);
		sword.getSprite().rotate(this.direction);
		this.getWeapon().setLayoutX(x);
		this.getWeapon().setLayoutY(y);
		this.sword.swing();
		break;
	case NORTH:
		break;
	case SOUTH:
		break;
	default:
		break;
		
	}
		
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
