package com.gammacrawler.enemies;

import com.gammacrawler.entity.Enemy;
import com.gammacrawler.entity.Sprite;

/**
 * @author deenlord
 *
 */
public class Slime extends Enemy {
	
	private static final String name = "Slime";
	
	/** 
	 * <h3>Slime</h3>
	 *  {@code name = "Slime"}
	 *  {@code damage = 5} 
	 *  {@code maxHP = 15}
	 *  {@code XP = 5}
	 */
	public Slime() {
		// damage = 5
		super(name, new Sprite("file:src/com/gammacrawler/images/slime.png"), 5);
		this.setMaxHP(15);
		this.setHP(15);
		this.setXP(5);
	}

}
