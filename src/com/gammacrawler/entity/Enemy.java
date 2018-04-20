package com.gammacrawler.entity;

import java.util.ArrayList;

import com.gammacrawler.Direction;
import com.gammacrawler.Generator;
import com.gammacrawler.Settings;
import com.gammacrawler.generator.Board;

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
		this.getImageView().setX(this.getLocation()[0]);
		this.getImageView().setY(this.getLocation()[1]);
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
		System.out.println(this.getClass().getSimpleName()+" IS COLLIDING WITH " + e.getClass().getSimpleName());
		if(e instanceof User)
		{
			if (this.getHP() <= 0) {
				this.isDead = true;
				for (Item i : this.inventory) {
					i.addToUser(Generator.player);
				}
				Generator.player.setXP(Generator.player.getXP() + this.getXP());
			}
		}
	}
	
	@Override
	public void move(Direction dir)
	{
		System.out.println("Trying to move");
		this.location = this.getLocation();
		int x=this.getLocation()[0],
			y=this.getLocation()[1];
		switch (dir) {
		case NORTH:
			if (Generator.ar[y-1][x]<10) {
				this.location[1] -= Settings.TILESIZE;
				this.sprite.rotateCharacter(Direction.NORTH);
				break;
			}
		case SOUTH:
			if (Generator.ar[y+1][x]<10) {
				this.location[1] += Settings.TILESIZE;
				this.sprite.rotateCharacter(Direction.SOUTH);
				break;
			}
		case EAST:
			if (Generator.ar[y][x+1]<10) {
				this.location[0] += Settings.TILESIZE;
				this.sprite.rotateCharacter(Direction.EAST);
				break;
			}
		case WEST:
			if (Generator.ar[y][x-1]<10) {
				this.location[0] -= Settings.TILESIZE;
				this.sprite.rotateCharacter(Direction.WEST);
				break;
			}
		}
	}
	@Override
	public void die(Entity killer) {
		super.die(killer);
		// If it is a player increase their XP
		if (killer instanceof User) {
			User u = (User) killer;
			u.setXP(u.getXP() + XP);
		}
	}
}
