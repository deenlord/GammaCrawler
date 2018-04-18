package com.gammacrawler.enemies;

import com.gammacrawler.Direction;
import com.gammacrawler.Settings;
import com.gammacrawler.entity.Enemy;
import com.gammacrawler.entity.Entity;
import com.gammacrawler.entity.Sprite;
import com.gammacrawler.item.WoodenSword;

/**
 * @author deenlord
 *
 */
public class Ogre extends Enemy {
	protected static final String name = "Ogre";

	/** Creates an Ogre
	 *  name Ogre, damage 20, hp25, xp 10
	 */
	public Ogre() {
		super(name, new Sprite("file:src/com/gammacrawler/images/ogre.png"), 20);
		this.setMaxHP(25);
		this.setHP(25);
		this.setXP(10);
	}
	
	@Override
	public void move(Direction dir) {
		// TODO : add enemy movement ai here
	}

	@Override
	public void collide(Entity e) {
		System.out.println("OGRE IS COLLIDING WITH " + e.getClass().getSimpleName());
		if (e.getClass().getSimpleName().equals("WoodenSword")) {
			System.out.println("Creating new sword and applying damage");
			WoodenSword sw = new WoodenSword();
			System.out.println("Ogre HP: " + this.getHP());
			
			int rand = (int) Math.random() * 100;
			if (rand <= 8) {
				this.setHP(this.getHP() - sw.getDamage());
			}
			else {
				this.setHP(this.getHP() - sw.getMaxDamage());
			}
			
			System.out.println(this.getHP());
		}
		
		if (this.getHP() <= 0) {
			this.isDead = true;
		}
	}

}
