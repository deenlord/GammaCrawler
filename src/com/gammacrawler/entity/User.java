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
	public int invisibleTurns = 0;

	/** init User sets HP to 100 XP to 0, gives them a sword
	 * @param name  - name of user
	 */
	public User(String name) {
		super(name, sprite);
		this.setMaxHP(100);
		this.setHP(this.getMaxHP());
		this.setXP(0);
		this.setSprite(sprite);
		this.setPoints(0);
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
	
	public void updateDirection(Direction dir) {
		this.direction = dir;
	}
	
	/** 
	 * handles weapon animation east west north and south of user
	 */
	public void attack() {
		int x;
		int y;
		weapon.setDoingDamage(true);

	switch(this.direction) {
		case EAST:
			x = (int) ((this.getLocation()[0]) + 1 + Settings.TILESIZE);
			y = (int) ((this.getLocation()[1]) + 1 + (Settings.TILESIZE) /4);
			weapon.getSprite().rotateWeapon(this.direction);
			weapon.getSprite().getImageView().setLayoutX(x);
			weapon.getSprite().getImageView().setLayoutY(y);
			weapon.setLocation(this.getLocation()[0] + Settings.TILESIZE, this.getLocation()[1]);
			weapon.animate();
			break;
		case WEST:
			System.out.println("Attacking west");
			x = (int) (this.getLocation()[0] - (Settings.TILESIZE /2));
			y = (int) ((this.getLocation()[1]) + 1 + (Settings.TILESIZE) /4);
			weapon.getSprite().rotateWeapon(this.direction);
			weapon.getSprite().getImageView().setLayoutX(x);
			weapon.getSprite().getImageView().setLayoutY(y);
			weapon.setLocation(this.getLocation()[0] - Settings.TILESIZE, this.getLocation()[1]);
			weapon.animate();
			break;
		case NORTH:
			System.out.println("Attacking NORTH");
			x = (int) (this.getLocation()[0]);
			y = (int) ((this.getLocation()[1]) - Settings.TILESIZE + (Settings.TILESIZE) /4);
			weapon.getSprite().rotateWeapon(this.direction);
			weapon.getSprite().getImageView().setLayoutX(x);
			weapon.getSprite().getImageView().setLayoutY(y);
			weapon.setLocation(this.getLocation()[0], this.getLocation()[1] - Settings.TILESIZE);
			weapon.animate();
			break;
			
		case SOUTH:
			x = (int) ((this.getLocation()[0]));
			y = (int) ((this.getLocation()[1]) + Settings.TILESIZE + (Settings.TILESIZE) /4);
			weapon.getSprite().rotateWeapon(Direction.SOUTH);
			weapon.getSprite().getImageView().setVisible(true);
			weapon.getSprite().getImageView().setLayoutX(x);
			weapon.getSprite().getImageView().setLayoutY(y);
			weapon.setLocation(this.getLocation()[0], this.getLocation()[1] + Settings.TILESIZE); // TODO: Fix entity coordinates
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
		for (Item item : this.inventory) {
			str.append("Inventory Item: " + item.getName() + "\n");
		}
		
		return str.toString();
		
	}

	@Override
	public void collide(Entity e) {
		// TODO Auto-generated method stub)
		if (e instanceof Enemy && invisibleTurns < 1) {
			this.setHP(this.getHP() - ((Enemy) e).getDamage());
			if (this.getHP() <= 0) {
				this.die(e);
				
				// TODO: Change Scene to game over scene!
			}
		}
		
	}
	
}
