package com.gammacrawler.entity;

/**
 * @author crathke4
 * 4/7
 */
public abstract class Enemy extends Character implements Moveable {
	
	/**
	 * 	Passes name and sprite to super to create new Character
	 * @param name - name of the enemy
	 * @param sprite - sprite to be used in display
	 */
	public Enemy(String name, Sprite sprite) {
		super(name, sprite);
	}

	/**
	 * reduces player health when called
	 * @param p - User from which to take health
	 */
	public void attack(User p)
	{
		boolean successful=true;
		if(successful) {
			p.setHP(getHP()-(int)((Math.random()*10)/(p.getMaxHP()/5))); 
		}
	}

}
