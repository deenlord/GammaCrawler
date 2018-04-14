package com.gammacrawler.entity;

import com.gammacrawler.Direction;
import com.gammacrawler.Settings;

public class Ogre extends Enemy {
	protected static final String name = "Ogre";
	protected static final int damage = 20;

	public Ogre() {
		super(name, new Sprite("file:src/com/gammacrawler/images/ogre.png"));
		this.setHP(25);
		this.setXP(10);
	}

	public static int getDamage() {
		return damage;
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

	@Override
	public void collide(Entity e) {
		// TODO Auto-generated method stub
		
	}

}
