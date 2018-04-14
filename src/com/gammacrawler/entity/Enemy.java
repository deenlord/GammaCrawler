package com.gammacrawler.entity;

/**
 * @author crathke4
 * 4/7
 */
public abstract class Enemy extends Character implements Moveable {
	// TODO: These javadocs
	
	public Enemy(String name, Sprite sprite) {
		super(name, sprite);
	}

	/**
	 * if attack is successful, player loses HP
	 */
	public void attack(User p)
	{
		boolean successful=true;
		if(successful) {
			p.setHP(getHP()-(int)((Math.random()*10)/p.getMaxHP()));  //TODO: the math here probably isn't entirely right, 
																			  //should take a reasonable but random amount of health away from user
		}
	}

}
