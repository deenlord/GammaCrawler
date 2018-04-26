package com.gammacrawler.enemies;

import com.gammacrawler.entity.Enemy;
import com.gammacrawler.entity.Sprite;

public class Witch extends Enemy {
	protected static final String name = "Witch";


	/** 
	 * <h3> Creates a Witch </h3>
	 *  {@code name = "Witch"}
	 *  {@code damage = 20} 
	 *  {@code HP = 25}
	 *  {@code XP = 10}
	 */
	public Witch() {
		super(name, new Sprite("file:src/com/gammacrawler/images/witch.png"), 20);
		this.setMaxHP(30);
		this.setHP(35);
		this.setXP(20);
	}

}
