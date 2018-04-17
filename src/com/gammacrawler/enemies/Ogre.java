package com.gammacrawler.enemies;

import com.gammacrawler.Direction;
import com.gammacrawler.Settings;
import com.gammacrawler.entity.Enemy;
import com.gammacrawler.entity.Entity;
import com.gammacrawler.entity.Sprite;

/**
 * @author deenlord
 *
 */
public class Ogre extends Enemy {
	protected static final String name = "Ogre";

	/** Creates an Ogre
	 * @param name = "Ogre"
	 * @param damage = 20
	 * @param hp = 25
	 * @param xp = 10
	 */
	public Ogre() {
		super(name, new Sprite("file:src/com/gammacrawler/images/ogre.png"), 20);
		this.setHP(25);
		this.setXP(10);
	}


//	@Override
//	public void move(Direction dir) {
//		System.out.println("Trying to move");
//		this.location = this.getLocation();
//		switch (dir) {
//		case NORTH:
//			this.location[1] -= Settings.TILESIZE;
//			break;
//		case SOUTH:
//			this.location[1] += Settings.TILESIZE;
//			break;
//		case EAST:
//			this.location[0] += Settings.TILESIZE;
//			this.sprite.rotate(Direction.EAST);
//			break;
//		case WEST:
//			this.location[0] -= Settings.TILESIZE;
//			this.sprite.rotate(Direction.WEST);
//			break;
//		}
//	}

	@Override
	public void collide(Entity e) {
		System.out.println("OGRE IS COLLIDING WITH " + e.getClass().getSimpleName());
		this.isDead = true;
	}

}
