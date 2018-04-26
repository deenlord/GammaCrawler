package com.gammacrawler.enemies;

import com.gammacrawler.entity.Enemy;
import com.gammacrawler.entity.Sprite;

public class ZombieNinja extends Enemy {
	protected static final String name = "Zombie Ninja";


	/** 
	 * <h3> Creates a ZombieNinja </h3>
	 *  {@code name = "Zombie Ninja"}
	 *  {@code damage = 20} 
	 *  {@code HP = 25}
	 *  {@code XP = 10}
	 */
	public ZombieNinja() {
		super(name, new Sprite("file:src/com/gammacrawler/images/zombieninja.png"), 20);
		this.setMaxHP(30);
		this.setHP(35);
		this.setXP(20);
	}

}

