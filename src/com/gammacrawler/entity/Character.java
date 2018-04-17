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
public abstract class Character extends Entity {
	protected String name;
	private int curHP;
	private int maxHP;
	protected int XP;
	protected ArrayList<Item> inventory = new ArrayList<>();

	/**
	 * @param name
	 * @param sprite
	 */
	public Character(String name, Sprite sprite) {
		super(sprite);
		this.name = name;
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
		curHP = Math.min(Math.max(0, hitpoints), maxHP);
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
	
	@Override
	public void setSprite(Sprite spr) {
		this.sprite = spr;
	}
	
	@Override
	public ImageView getImageView() {
		return this.sprite.getImageView();
	}

	/**
	 * @param inventory
	 */
	public void setInventory(ArrayList<Item> inventory) {
		this.inventory = inventory;
	}

	@Override
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
	 * @return the character's inventory.
	 */
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
		getImageView().setX((tileX) * Settings.TILESIZE);
		getImageView().setY((tileY) * Settings.TILESIZE);
		this.setLocation((tileX) * Settings.TILESIZE, (tileY) * Settings.TILESIZE);
	}

}
