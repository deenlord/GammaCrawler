package com.gammacrawler.entity;

import java.util.ArrayList;

import com.gammacrawler.Direction;
import com.gammacrawler.Settings;

import javafx.scene.image.ImageView;

// import java.util.ArrayList;

/**
 * @author deenlord
 *
 */
public abstract class Character implements Moveable {
	private String name;
	private int curHP;
	private int maxHP;
	private int XP;
	private int[] location = new int[2];
	private Sprite sprite;
	private ArrayList<Item> inventory = new ArrayList<>();

	public Character(String name) {
		this.name = name;
	}

	/**
	 * @param name
	 * @param sprite
	 */
	public Character(String name, Sprite sprite) {
		this.name = name;
		this.sprite = sprite;
	}

	/**
	 * @return name of Character
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param newName
	 *            - name your Character
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
	 * @param hitpoints
	 *            - set current HP to hitpoints value
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
	 * @param hitpoints
	 *            - set max HP to hitpoints value
	 */
	public void setMaxHP(int hitpoints) {
		maxHP = hitpoints;
	}

	/**
	 * @return current XP
	 */
	public int getXP() {
		return this.XP;
	}

	/**
	 * @param xp
	 *            - sets this.XP to xp
	 */
	public void setXP(int xp) {
		this.XP = xp;
	}

	/**
	 * @return current location
	 */
	public int[] getLocation() {
		return this.location;
	}

	/**
	 * @param x
	 *            - x coordinate
	 * @param y
	 *            - y coordinate
	 */
	public void setLocation(int x, int y) {
		location[0] = x;
		location[1] = y;
	}

	public void setSprite(Sprite spr) {
		this.sprite = spr;
	}

	public ImageView getImageView() {
		return this.sprite.getSprite();
	}

	public void setInventory(ArrayList<Item> inventory) {
		this.inventory = inventory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gammacrawler.Moveable#move(com.gammacrawler.Direction) Move your
	 * player the direction (sets this.location)
	 */
	public void move(Direction dir) {
		System.out.println("Trying to move");
		this.location = this.getLocation();
		switch (dir) {
		case NORTH:
			this.location[1] -= Settings.TILESIZE;
			break;
		case SOUTH:
			this.location[1] += Settings.TILESIZE;
			break;
		case EAST:
			this.location[0] += Settings.TILESIZE;
			this.sprite.rotate(Direction.EAST);
			break;
		case WEST:
			this.location[0] -= Settings.TILESIZE;
			this.sprite.rotate(Direction.WEST);
			break;
		}

	}

	/**
	 * @return true or false (can use as exit condition for game state)
	 */
	public boolean isDead() {
		boolean dead;
		if (curHP > 0) {
			dead = false;
		} else {
			dead = true;
		}

		return dead;
	}

	public ArrayList<Item> getInventory() {
		return inventory;
	}

	/**
	 * This will take a tile coordinate (0 - (length - 1)) and convert it into
	 * pixel coordinates, then move the entity to that location.
	 * 
	 * @param tileX
	 * @param tileY
	 */
	public void moveToTile(int tileX, int tileY) {
		getImageView().setX((tileX + 1) * Settings.TILESIZE);
		getImageView().setY((tileY + 1) * Settings.TILESIZE);
		this.setLocation((tileX + 1) * Settings.TILESIZE, (tileY + 1) * Settings.TILESIZE);
	}

}
