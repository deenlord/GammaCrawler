package com.gammacrawler;

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
	private Sprite sprite;
	
	/**
	 * @return name of Character
	 */
	public String getName() {
		return name;
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
	public void setLocation(int x, int y) {
		location[0] = x;
		location[1] = y;
	}
	
	public void setTileSize() {
		this.tileSize = (int) Main.tileSize;
	}
	
	public void setTileSize(int tileSize) {
		this.tileSize = tileSize;
	}
	
	public void setSprite(Sprite spr) {
		this.sprite = spr;
	}
	
	public ImageView getSprite() {
		return this.sprite.getSprite();
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

}
