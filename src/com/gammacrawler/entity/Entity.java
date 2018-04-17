package com.gammacrawler.entity;

import com.gammacrawler.Direction;
import com.gammacrawler.Settings;

import javafx.scene.image.ImageView;

public abstract class Entity implements Moveable {
	protected int[] location = new int[2];
	protected Sprite sprite;
	public boolean isDead = false;

	/**
	 * @param name
	 * @param sprite
	 */
	public Entity(Sprite sprite) {
		this.sprite = sprite;
	}

	@Override
	public void move(Direction d) {
		System.out.println("Trying to move");
		this.location = this.getLocation();
		switch (d) {
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

	public int[] getTileLocation() {
		int x = location[0] / Settings.TILESIZE;
		int y = location[0] / Settings.TILESIZE;
		return new int[]{x, y};
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

	/**
	 * @return current location
	 */
	public int[] getLocation() {
		return this.location;
	}

	public void setSprite(Sprite spr) {
		this.sprite = spr;
	}

	public ImageView getImageView() {
		return this.sprite.getImageView();
	}

	public abstract void collide(Entity e); // Test to see if it works...

	/**
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
