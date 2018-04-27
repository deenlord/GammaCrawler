package com.gammacrawler.entity;

import java.util.ArrayList;

import com.gammacrawler.Direction;
import com.gammacrawler.Generator;
import com.gammacrawler.Settings;
import com.gammacrawler.StatusBar;
import com.gammacrawler.item.Weapon;

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
	 * <h3>Enemy - Is A Character </h3>
	 * <p>Passes name and sprite to super to create new Character.
	 * <br>Dies if HP <= 0 upon Weapon collision.
	 * <br>Individual enemy movement is inherited from here.
	 * 
	 * @param name - String - name of the enemy
	 * @param sprite - Sprite - sprite to be used in display
	 * @param damage - int - amount of damage enemy deals
	 */
	public Enemy(String name, Sprite sprite, int damage) {
		super(name, sprite);
		this.damage = damage;
		this.inventory = new ArrayList<>();
	}
	
	
	// Deprecated attack method since 0.4 - not using.

//	/**
//	 * reduces player health when called
//	 * @param p - User from which to take health
//	 */
//	public void attack(User p)
//	{
//		boolean successful = false;
//		int rand = (int) Math.random() * 10;
//		if ( rand <=5 )
//			successful=true;
//		
//		if(successful) {
//			p.setHP(p.getHP()- damage); 
//		}
//	}
	
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
	
	/**
	 * @param item gets added to this.inventory
	 */
	public void addToInventory(Item item) {
		this.inventory.add(item);
	}
	
	@Override
	public void collide(Entity e) {
		StatusBar.addStatus(this.getClass().getSimpleName()+" IS COLLIDING WITH " + e.getClass().getSimpleName());
		
		if (e instanceof Weapon) {
			if (((Weapon) e).isDoingDamage()) {
				if (this.getHP() <= 0) {
					die(Generator.player);
				}
			}
		}
	}
	
	@Override
	public void move(Direction dir)
	{
		//StatusBar.addStatus("Trying to move");
		this.location = this.getLocation();
		int x=(this.getLocation()[0]/Settings.TILESIZE)-1,
			y=(this.getLocation()[1]/Settings.TILESIZE)-1;
		switch (dir) {
		case NORTH:
			if (Generator.ar[y-1][x]<10 && Generator.ar[y-1][x] != Settings.STAIR_ID) {
				this.location[1] -= Settings.TILESIZE;
				this.sprite.rotateCharacter(Direction.NORTH);
				break;
			}
		case SOUTH:
			if (Generator.ar[y+1][x]<10 && Generator.ar[y+1][x] != Settings.STAIR_ID) {
				this.location[1] += Settings.TILESIZE;
				this.sprite.rotateCharacter(Direction.SOUTH);
				break;
			}
		case EAST:
			if (Generator.ar[y][x+1]<10 && Generator.ar[y][x+1] != Settings.STAIR_ID) {
				this.location[0] += Settings.TILESIZE;
				this.sprite.rotateCharacter(Direction.EAST);
				break;
			}
		case WEST:
			if (Generator.ar[y][x-1]<10 && Generator.ar[y][x-1] != Settings.STAIR_ID) {
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
			Generator.player.setXP(Generator.player.getXP() + XP);

			// Add items to players inventory on death
			for (Item i : inventory) {
				i.addToUser();
			}
		}
	}

}
