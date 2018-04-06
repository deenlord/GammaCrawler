package com.gammacrawler;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

// import java.util.ArrayList;

/**
 * @author deenlord
 *
 */
public abstract class Character implements Moveable{
	private String name;
	private int curHP;
	private int maxHP;
	private int[] location = new int[2];
	private int tileSize;
	private ImageView imageView;
//	private ArrayList<Item> inventory;
//	private Weapon main;
//	private Shield shield;

	public Character(String name, Image image) {
		this.setName(name);
	    this.setImageView(image);
	}

	/**
	 * @return name of Character
	 */
	public String getName() {
		return name;
	}
	
	public void setTileSize(int tileSize) {
		this.tileSize = tileSize;
	}

	/**
	 * @param newName - name your Character
	 */
	public void setName(String newName) {
		name = newName;
	}

	/**
	 * @return current HP
	 */
	public int getHP() {
		return curHP;
	}

	/**
	 * @param hitpoints - set current HP to hitpoints value
	 */
	public void setHP(int hitpoints) {
		curHP = hitpoints;
	}
	
	/**
	 * @return max HP
	 */
	public int getMaxHP() {
		return maxHP;
	}
	
	/**
	 * @param hitpoints - set max HP to hitpoints value
	 */
	public void setMaxHP(int hitpoints) {
		maxHP = hitpoints;
	}
	
	/**
	 * @return current location
	 */
	public int[] getLocation() {
		return this.location;
	}
	
	/**
	 * @param x -  x coordinate
	 * @param y -  y coordinate
	 */
	protected void setLocation(int x, int y) {
		location[0] = x;
		location[1] = y;
	}
	
	
	/* (non-Javadoc)
	 * @see com.gammacrawler.Moveable#move(com.gammacrawler.Direction)
	 * Move your player the direction (sets this.location)
	 */
	public void move(Direction dir) {
		System.out.println("Trying to move");
		this.location = this.getLocation();
		switch(dir) {
			case NORTH:
				this.location[1]-=tileSize;
				break;
			case SOUTH:
				this.location[1]+=tileSize;
				break;
			case EAST:
				this.location[0]+=tileSize;
				break;
			case WEST:
				this.location[0]-=tileSize;
				break;
		}
		
	}
//	public Inventory getInventory() {
//		return inv;
//	}
//
//	public void setInventory(Inventory newinv) {
//		inv = newinv;
//	}
//
//	public Weapon getWeapon() {
//		return main;
//	}
//
//	public void setWeapon(Weapon newWeapon) {
//		inv.add(main);
//		main = newWeapon;
//	}
//
//	public Shield getShield() {
//		return shield;
//	}
//
//	public void setShield(Shield newShield) {
//		inv.add(shield);
//		shield = newShield;
//	}

	/**
	 * @return true or false (can use as exit condition for game state)
	 */
	public boolean isDead() {
		boolean dead;
		if( curHP > 0 ) {
			dead = false;
		}
		else {
			dead = true;
		}

		return dead;
	}

	public ImageView getPlayerImageView() {
		return imageView;
	}

	public void setImageView(Image image) {
		this.imageView = new ImageView(image);
	}

}
