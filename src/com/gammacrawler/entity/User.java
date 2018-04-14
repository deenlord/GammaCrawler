package com.gammacrawler.entity;

import com.gammacrawler.Direction;
import com.gammacrawler.Settings;
import com.gammacrawler.item.Weapon;
import com.gammacrawler.item.WoodenSword;

/**
 * User is the player. 
 * 
 * @author deenlord, crathke4
 * 3/24
 */
public class User extends Character implements Moveable {
	protected static Sprite sprite = new Sprite("file:src/com/gammacrawler/images/user2.png");
	protected Weapon<?> weapon;
	protected Direction direction;

	/** init User sets HP to 100 XP to 0, gives them a sword
	 * @param name 
	 */
	public User(String name) {
		super(name, sprite);
		this.setMaxHP(100);
		this.setHP(this.getMaxHP());
		this.setXP(0);
		this.setSprite(sprite);
		weapon = new WoodenSword();
		this.getInventory().add(weapon);
		for (Item i : this.getInventory()) {
			System.out.println(i.getName());
		}
		
		this.direction = Direction.EAST;
		
		
	}
	
	/**
	 * @param w - A Weapon - will override the player's weapon
	 */
	public void setWeapon(Weapon<?> w) {
		this.weapon = w;
	}
	
	/**
	 * @return this.weapon
	 */
	public Weapon<?> getWeapon() {
		return this.weapon;
	}
	
	/* (non-Javadoc)
	 * @see com.gammacrawler.entity.Character#move(com.gammacrawler.Direction)
	 */
	@Override
	public void move(Direction dir) {
		System.out.println("Trying to move");
		int[] loc = this.getLocation();
		switch (dir) {
		case NORTH:
			loc[1] -= Settings.TILESIZE;
			this.direction = Direction.NORTH;
			break;
		case SOUTH:
			loc[1] += Settings.TILESIZE;
			this.direction = Direction.SOUTH;
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
	
	
	/** 
	 * handles weapon animation east west north and south of user
	 */
	public void attack() {
		int x;
		int y;
		
	switch(this.direction) {
		case EAST:
			x = (int) ((this.getLocation()[0]) + 1 + Settings.TILESIZE);
			y = (int) ((this.getLocation()[1]) + 1 + (Settings.TILESIZE) /4);
			weapon.getSprite().rotate(Direction.EAST);
			weapon.getSprite().getImageView().setLayoutX(x);
			weapon.getSprite().getImageView().setLayoutY(y);
			weapon.animate();
			break;
		case WEST:
			System.out.println("Attacking west");
			x = (int) (this.getLocation()[0] - (Settings.TILESIZE /2));
			y = (int) ((this.getLocation()[1]) + 1 + (Settings.TILESIZE) /4);
			weapon.getSprite().rotate(Direction.WEST);
			weapon.getSprite().getImageView().setLayoutX(x);
			weapon.getSprite().getImageView().setLayoutY(y);
			weapon.animate();
			break;
		case NORTH:
			System.out.println("Attacking NORTH");
			x = (int) (this.getLocation()[0] - (Settings.TILESIZE /2));
			y = (int) ((this.getLocation()[1]) + 1 + (Settings.TILESIZE) /4);
			weapon.getSprite().rotate(Direction.NORTH);
			weapon.getSprite().getImageView().setVisible(true);
			weapon.getSprite().getImageView().setLayoutX(x);
			weapon.getSprite().getImageView().setLayoutY(y);
			weapon.animate();
			break;
		case SOUTH:
			x = (int) ((this.getLocation()[0]) + 1 + Settings.TILESIZE);
			y = (int) ((this.getLocation()[1]) + 1 + (Settings.TILESIZE) /4);
			weapon.getSprite().rotate(Direction.SOUTH);
			weapon.getSprite().getImageView().setVisible(true);
			weapon.getSprite().getImageView().setLayoutX(x);
			weapon.getSprite().getImageView().setLayoutY(y);
			weapon.animate();
			break;
		default:
			break;
			
		}		
	}
	
	/*
	 * (non-Javadoc)
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Name: " + this.getName() + "\n");
		str.append("Max HP: " + this.getMaxHP() + "\n");
		str.append("Current HP: " + this.getHP() + "\n");
		str.append("Experience: " + this.getXP() + "\n");
		str.append("Location: " + this.getLocation()[0] + " " + this.getLocation()[1] + "\n");
		
		return str.toString();
		
	}

	@Override
	public void collide(Entity e) {
		// TODO Auto-generated method stub
		
	}
	
}
