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
	 *  name Ogre, damage 20, hp25, xp 10
	 */
	public Ogre() {
		super(name, new Sprite("file:src/com/gammacrawler/images/ogre.png"), 20);
		this.setHP(25);
		this.setXP(10);
	}
	
	@Override
	public void move(Direction dir) {
		// TODO : add enemy movement ai here
	}

	@Override
	public void collide(Entity e) {
		System.out.println("OGRE IS COLLIDING WITH " + e.getClass().getSimpleName());
		this.isDead = true;
	}

}
