package com.gammacrawler.entity;

import com.gammacrawler.Settings;

import javafx.scene.image.ImageView;

/**
 * <h3>Entity</h3>
 * @author WolfieWaffle
 *
 */
public abstract class Entity implements Moveable {
	protected int[] location = new int[2];
	protected Sprite sprite;
	public boolean isDead = false;

	/**
	 * @param sprite - Entity's sprite
	 */
	public Entity(Sprite sprite) {
		this.sprite = sprite;
	}

	/**
	 * Called to get this.sprite
	 * @return item Sprite
	 */
	public Sprite getSprite() {
		return this.sprite;
	}

	/**
	 * This will take a tile coordinate (0 - (length - 1)) and convert it into
	 * pixel coordinates, then move the entity to that location.
	 * 
	 * @param tileX - x coordinate
	 * @param tileY - y coordinate
	 */
	public void moveToTile(int tileX, int tileY) {
		getImageView().setX((tileX) * Settings.TILESIZE);
		getImageView().setY((tileY) * Settings.TILESIZE);
		this.setLocation((tileX) * Settings.TILESIZE, (tileY) * Settings.TILESIZE);
	}

	/**
	 * @return int[]{x, y} based on this.location
	 */
	public int[] getTileLocation() {
		int x = location[0] / Settings.TILESIZE;
		int y = location[0] / Settings.TILESIZE;
		return new int[]{x, y};
	}

	/**
	 * @param x - x coordinate
	 * @param y - y coordinate
	 */
	public void setLocation(int x, int y) {
		location[0] = x;
		location[1] = y;
	}

	/**
	 * @return current location
	 */
	public int[] getLocation() {
		return this.location;
	}

	/**
	 * Called to set the Sprite
	 * @param spr - Sprite - Sets the current Sprite to spr
	 */
	public void setSprite(Sprite spr) {
		this.sprite = spr;
	}

	/**
	 * Called to get the ImageView
	 * @return - ImageView of this.sprite
	 */
	public ImageView getImageView() {
		return this.sprite.getImageView();
	}

	/**
	 * Called to detect a collision
	 * @param e - Entity - Collision detection happens at the sub-class level
	 */
	public abstract void collide(Entity e); // Test to see if it works...

	/**
	 * Called to kill an entity
	 * @param killer - where xp/gold/items are given
	 */
	public void die(Entity killer) {
		this.isDead = true;
	};

	/**
	 * Called to check this.isDead (HP <= 0)
	 * @return true or false (can use as exit condition for game state)
	 */
	public boolean isDead() {
		boolean dead = false;

		if (isDead) {
			dead  = true;
		} else {
			if (this instanceof Character) {
				if (((Character) this).getHP() > 0) {
					dead = false;
				} else {
					dead = true;
				}

			}
		}

		return dead;
	}

}
