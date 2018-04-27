package com.gammacrawler.enemies;

import com.gammacrawler.entity.Enemy;
import com.gammacrawler.entity.Sprite;

/**
 * @author deenlord
 *
 */
public class Ogre extends Enemy {
	protected static final String name = "Ogre";

	/** 
	 * <h3>Ogre - An Enemy</h3>
	 *  {@code name = "Ogre"}
	 *  {@code damage = 20} 
	 *  {@code HP = 25}
	 *  {@code XP = 10}
	 */
	public Ogre() {
		super(name, new Sprite("file:src/com/gammacrawler/images/ogre.png"), 20);
		this.setMaxHP(25);
		this.setHP(25);
		this.setXP(10);
	}

	

}
