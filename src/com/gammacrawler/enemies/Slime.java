package com.gammacrawler.enemies;

import com.gammacrawler.entity.Enemy;
import com.gammacrawler.entity.Sprite;

/** 
 * <h3>Slime - An Enemy</h3>
 *  <p> name = "Slime"
 *  <br> damage = 5
 *  <br> maxHP = 15
 *  @author deenlord
 */
public class Slime extends Enemy {
	
	private static final String name = "Slime";
	

	public Slime() {
		// damage = 5
		super(name, new Sprite("com/gammacrawler/images/slime.png"), 5);
		this.setMaxHP(15);
		this.setHP(15);
		this.setXP(5);
	}

}
