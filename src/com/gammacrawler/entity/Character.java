package com.gammacrawler.entity;

import java.util.ArrayList;

import com.gammacrawler.Direction;
import com.gammacrawler.Settings;
import com.gammacrawler.StatusBar;

import javafx.scene.image.ImageView;

// import java.util.ArrayList;

/**
 * @author deenlord, crathke4
 *
 */
public abstract class Character extends Entity {
	protected String name;
	private int curHP;
	private int maxHP;
	protected int XP;
	protected int points;
	
	/**
	 * Getter for the Characters points
	 * @return Current value of Character.points
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * Setter for Character.points
	 * @param points - Value to which Character.points will be set
	 */
	public void setPoints(int points) {
		this.points = points;
	}

	protected ArrayList<Item> inventory = new ArrayList<>();

	/**
	 * Creates a Character
	 * @param name - String to which Character.name will be set
	 * @param sprite - Sprite to be used for Character, passed to super (Entity)
	 */
	public Character(String name, Sprite sprite) {
		super(sprite);
		this.name = name;
	}

	/**
	 * Getter for the name of the Character
	 * @return name of Character
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for Character.name
	 * @param newName - String value to which Character.name will be changed
	 */
	public void setName(String newName) {
		name = newName;
	}

	/**
	 * @return The current value of Character.curHP
	 */
	public int getHP() {
		return curHP;
	}

	/** Setter for Character.curHP
	 * @param hitpoints - Value to which Character.curHP will be set.
	 * Throws exception if hitpoints id more than the maxHP of the Character
	 */
	public void setHP(int hitpoints) {
		
		if (hitpoints <= this.getMaxHP())
			curHP = hitpoints;
	}

	/**
	 * Getter for Character.maxHP
	 * @return max HP
	 */
	public int getMaxHP() {
		return maxHP;
	}

	/**
	 * Setter for Character.maxHP
	 * @param hitpoints - Value to which maxHP will be set
	 */
	public void setMaxHP(int hitpoints) {
		maxHP = hitpoints;
	}

	/**
	 * Getter for Character.XP
	 * @return current value of Character.XP
	 */
	public int getXP() {
		return this.XP;
	}

	/**
	 * Setter for Character.XP
	 * @param xp - Value to which Character.XP will be set
	 */
	public void setXP(int xp) {
		this.XP = xp;
	}

	/**
	 * Getter for Character.location
	 * @return Current int[] value of Character.location
	 */
	public int[] getLocation() {
		return this.location;
	}

	/**
	 * Setter for Character.location
	 * @param x - Value to which the first array value will be set
	 * @param y - Value to which the second array value will be set
	 */
	public void setLocation(int x, int y) {
		location[0] = x;
		location[1] = y;
	}
	

	/**
	 * Setter for Character.inventory
	 * @param inventory - ArrayList value to which Character.inventory will be set
	 */
	public void setInventory(ArrayList<Item> inventory) {
		this.inventory = inventory;
	}

	/**
	 * Getter for Character.inventory
	 * @return Current ArrayList value of Character.inventory
	 */
	public ArrayList<Item> getInventory() {
		return inventory;
	}

	/**
	 * Moves the Character's location on the map to a given tile
	 * @param tileX - Tile to which the x location will be set
	 * @param tileY - Tile to which the y location will be set
	 */
	public void moveToTile(int tileX, int tileY) {
		getImageView().setX((tileX) * Settings.TILESIZE);
		getImageView().setY((tileY) * Settings.TILESIZE);
		this.setLocation((tileX) * Settings.TILESIZE, (tileY) * Settings.TILESIZE);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setSprite(Sprite spr) {
		this.sprite = spr;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ImageView getImageView() {
		return this.sprite.getImageView();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void move(Direction dir) {
		StatusBar.addStatus("Trying to move");
		this.location = this.getLocation();
		
		switch (dir) {
		case NORTH:
			this.location[1] -= Settings.TILESIZE;
			this.sprite.rotateCharacter(Direction.NORTH);
			break;
		case SOUTH:
			this.location[1] += Settings.TILESIZE;
			this.sprite.rotateCharacter(Direction.SOUTH);
			break;
		case EAST:
			this.location[0] += Settings.TILESIZE;
			this.sprite.rotateCharacter(Direction.EAST);
			break;
		case WEST:
			this.location[0] -= Settings.TILESIZE;
			this.sprite.rotateCharacter(Direction.WEST);
			break;
		}

	}

}
