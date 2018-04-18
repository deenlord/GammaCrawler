package com.gammacrawler.entity;

import java.util.ArrayList;

/**
 * @author crathke4
 * 4/7
 */
public abstract class Enemy extends Character {
	private int damage;
	private ArrayList<Item> inventory;
	/**
	 * 	Passes name and sprite to super to create new Character
	 * @param name - name of the enemy
	 * @param sprite - sprite to be used in display
	 */
	public Enemy(String name, Sprite sprite, int damage) {
		super(name, sprite);
		this.damage = damage;
		this.inventory = new ArrayList<>();
	}

	/**
	 * reduces player health when called
	 * @param p - User from which to take health
	 */
	public void attack(User p)
	{
		boolean successful = false;
		int rand = (int) Math.random() * 10;
		if ( rand <=5 )
			successful=true;
		
		if(successful) {
			p.setHP(p.getHP()- damage); 
		}
	}
	
	/**
	 * @return the damage the enemy deals
	 */
	public int getDamage() {
		return this.damage;
	}
	
	public void addToInventory(Item item) {
		this.inventory.add(item);
	}
	
	
	public ArrayList<Item> getInventory() {
		return this.inventory;
	}

}
