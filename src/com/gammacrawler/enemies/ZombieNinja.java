package com.gammacrawler.enemies;

import com.gammacrawler.entity.Enemy;
import com.gammacrawler.entity.Sprite;

public class ZombieNinja extends Enemy {
	protected static final String name = "Zombie Ninja";

	/** 
	 * <h3>Zombie Ninja - An Enemy</h3>
	 *  {@code name = "Zombie Ninja"}
	 *  {@code damage = 30} 
	 *  {@code maxHP = 35}
	 *  {@code XP = 10}
	 */
	public ZombieNinja() {
		super(name, new Sprite("file:src/com/gammacrawler/images/zombieninja.png"), 30);
		this.setMaxHP(35);
		this.setHP(35);
		this.setXP(20);
	}
}

