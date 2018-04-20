package com.gammacrawler.entity;

import java.util.ArrayList;

import com.gammacrawler.Generator;
import com.gammacrawler.item.WoodenSword;

/**
 * @author crathke4
 * 4/7
 */
public abstract class Enemy extends Character implements Moveable{
	private int damage;
	EnemyStatus status;
	EnemyAI ai=new EnemyAI();
	private ArrayList<Item> inventory;
	
	/**
	 * 	Passes name and sprite to super to create new Character
	 * @param name - name of the enemy - String
	 * @param sprite - sprite to be used in display - Sprite
	 * @param damage - amount of damage enemy deals - int
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
	 * moves the enemy using EnemyAI
	 */
	public void moveAI()
	{
		this.status=EnemyStatus.DOCILE;
		ai.check(this);
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
	
	@Override
	public void collide(Entity e) {
		System.out.println("Enemy IS COLLIDING WITH " + e.getClass().getSimpleName());
		
		if (this.getHP() <= 0) {
			this.isDead = true;
			for (Item i : this.inventory) {
				i.addToUser(Generator.player);
			}
			Generator.player.setXP(Generator.player.getXP() + this.getXP());
		}
	}

}
