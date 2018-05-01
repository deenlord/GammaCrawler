package com.gammacrawler.enemies;

import com.gammacrawler.entity.Enemy;
import com.gammacrawler.entity.Sprite;

/** 
 * <h3>Zombie Ninja - An Enemy</h3
 *  <p> name = "Zombie Ninja"
 *  <br> damage = 30
 *  <br> maxHP = 35
 *  <br> XP = 10
 *  @author deenlord, crathke4
 */
public class ZombieNinja extends Enemy {
	
	//Defines a reference-able unchangeable name for the Enemy
	protected static final String name = "Zombie Ninja";

	/**
	 * Creates a zombie-ninja
	 */
	public ZombieNinja() {
		super(name, new Sprite("com/gammacrawler/images/zombieninja.png"), 30);
		this.setMaxHP(35);
		this.setHP(35);
		this.setXP(20);
	}
}

