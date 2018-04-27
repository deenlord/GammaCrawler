package com.gammacrawler.item;

import com.gammacrawler.Generator;
import com.gammacrawler.StatusBar;
import com.gammacrawler.enemies.Ogre;
import com.gammacrawler.enemies.Slime;
import com.gammacrawler.enemies.Witch;
import com.gammacrawler.enemies.ZombieNinja;
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
		
		// unfortunately, we have not figured out a way to damage
		// just any enemy. I would have liked this to be more elegant
		// #Deenlord 4/26/18
		if (isDoingDamage) {
			int rand = (int) Math.random() * 100;
			if (e instanceof Slime) {
				if (rand <= 8) {
					((Slime) e).setHP(((Slime) e).getHP() - this.getDamage());
					StatusBar.addStatus("Slicing Slime");
				}
				else {
					((Slime) e).setHP(((Slime) e).getHP() - this.getMaxDamage());
				}
			}
			else if (e instanceof Ogre) {
				if (rand <= 8) {
					((Ogre) e).setHP(((Ogre) e).getHP() - this.getDamage());
					StatusBar.addStatus("Stabbing Ogre");
				}
				else {
					((Ogre) e).setHP(((Ogre) e).getHP() - this.getMaxDamage());
				}
			}
			else if (e instanceof Witch) {
				if (rand <= 8) {
					((Witch) e).setHP(((Witch) e).getHP() - this.getDamage());
					StatusBar.addStatus("Burning Witch");
				}
				else {
					((Witch) e).setHP(((Witch) e).getHP() - this.getMaxDamage());
				}
			}
			else if (e instanceof ZombieNinja) {
				if (rand <= 8) {
					((ZombieNinja) e).setHP(((ZombieNinja) e).getHP() - this.getDamage());
					StatusBar.addStatus("Damaging ZombieNinja");
				}
				else {
					((ZombieNinja) e).setHP(((ZombieNinja) e).getHP() - this.getMaxDamage());
				}
			}
		}
	}

	public abstract void animate();
	
	
	
}
