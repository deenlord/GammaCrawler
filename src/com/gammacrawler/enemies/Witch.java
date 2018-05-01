package com.gammacrawler.enemies;

import com.gammacrawler.entity.Enemy;
import com.gammacrawler.entity.Sprite;

/** 
 * <h3>Witch - An Enemy</h3>
 *  <p> name = "Witch"
 *  <br> damage = 30
 *  <br> maxHP = 35
 *  <br> XP = 20
 *  @author deenlord, crathke4
 */
public class Witch extends Enemy {
	
	//Defines a reference-able unchangeable name for the Enemy
	protected static final String name = "Witch";
	
	/**
	 * Creates a witch
	 */
	public Witch() {
		super(name, new Sprite("com/gammacrawler/images/witch.png"), 30);
		this.setMaxHP(45);
		this.setHP(45);
		this.setXP(45);
	}

}
