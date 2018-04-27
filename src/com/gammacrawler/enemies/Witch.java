package com.gammacrawler.enemies;

import com.gammacrawler.entity.Enemy;
import com.gammacrawler.entity.Sprite;

public class Witch extends Enemy {
	protected static final String name = "Witch";
	
	/** 
	 * <h3>Witch - An Enemy</h3>
	 *  {@code name = "Witch"}
	 *  {@code damage = 30} 
	 *  {@code maxHP = 35}
	 *  {@code XP = 20}
	 */
	public Witch() {
		super(name, new Sprite("file:src/com/gammacrawler/images/witch.png"), 30);
		this.setMaxHP(45);
		this.setHP(45);
		this.setXP(45);
	}

}
