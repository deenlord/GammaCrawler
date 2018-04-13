package com.gammacrawler.item;

import com.gammacrawler.entity.Item;
import com.gammacrawler.entity.Sprite;

/**
 * @author deenlord
 *
 */
public class Weapon extends Item {
	int damageDealt;
	int maxDamageDealt;
	public Weapon(String name, Sprite sprite) {
		super(name, sprite);
		
	}
	
	public Weapon(String name, Sprite sprite, int regDamage, int maxDamage) {
		super(name, sprite);
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
	
	
	
}
