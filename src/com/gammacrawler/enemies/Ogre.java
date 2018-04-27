package com.gammacrawler.enemies;

import com.gammacrawler.entity.Enemy;
import com.gammacrawler.entity.Sprite;

/**
* <h3>Ogre - An Enemy</h3>
*  <p> name = "Ogre"
*  <br> damage = 20
*  <br> HP = 25
*  <br> XP = 10
*  @author deenlord
*/
public class Ogre extends Enemy {
	protected static final String name = "Ogre";

	/**
	 * creates an ogre
	 */
	public Ogre() {
		super(name, new Sprite("file:src/com/gammacrawler/images/ogre.png"), 20);
		this.setMaxHP(25);
		this.setHP(25);
		this.setXP(10);
	}

	

}
