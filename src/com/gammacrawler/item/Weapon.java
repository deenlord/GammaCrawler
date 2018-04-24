package com.gammacrawler.item;

import com.gammacrawler.StatusBar;
import com.gammacrawler.enemies.Ogre;
import com.gammacrawler.enemies.Slime;
import com.gammacrawler.entity.Entity;
import com.gammacrawler.entity.Item;
import com.gammacrawler.entity.Sprite;

/**
 * @author deenlord
 *
 */
public abstract class Weapon<T> extends Item {
	int damageDealt;
	int maxDamageDealt;
	private boolean isDoingDamage = false;

	public Weapon(String name, Sprite sprite) {
		super(sprite, name);
		
	}
	
	public Weapon(String name, Sprite sprite, int regDamage, int maxDamage) {
		super(sprite, name);
		this.damageDealt = regDamage;
		this.maxDamageDealt = maxDamage;
		
	}
	
	public void setDamage(int dmg) {
		this.damageDealt = dmg;
	}
	
	public int getDamage() {
		return this.damageDealt;
	}
	
	public void setMaxDamage(int max) {
		this.maxDamageDealt = max;
	}
	
	public int getMaxDamage() {
		return this.maxDamageDealt;
	}

	public boolean isDoingDamage() {
		return this.isDoingDamage;
	}

	public void setDoingDamage(boolean bool) {
		this.isDoingDamage = bool;
	}
	
	@Override
	public void collide(Entity e) {
		int rand = (int) Math.random() * 100;
		if (e instanceof Slime) {
			if (rand <= 8) {
				((Slime) e).setHP(((Slime) e).getHP() - this.getDamage());
			}
			else {
				((Slime) e).setHP(((Slime) e).getHP() - this.getMaxDamage());
			}
		}
		else if (e instanceof Ogre) {
			if (rand <= 8) {
				((Ogre) e).setHP(((Ogre) e).getHP() - this.getDamage());
				StatusBar.addStatus("damaging ogre");
			}
			else {
				((Ogre) e).setHP(((Ogre) e).getHP() - this.getMaxDamage());
			}
		}
	}

	public abstract void animate();
	
	public abstract Sprite getSprite();
	
	
	
}
