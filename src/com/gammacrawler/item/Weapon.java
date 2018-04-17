package com.gammacrawler.item;

import com.gammacrawler.entity.Item;
import com.gammacrawler.entity.Sprite;

/**
 * @author deenlord
 *
 */
public abstract class Weapon<T> extends Item {
	int damageDealt;
	int maxDamageDealt;
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

	public abstract void animate();
	
	public abstract Sprite getSprite();
	
}
