package com.gammacrawler.entity;

import java.util.ArrayList;

import com.gammacrawler.Direction;
import com.gammacrawler.Generator;

/**
 * @author jakev, crathke4
 *
 */
public class Chest extends Item{
	ArrayList<Item> inventory;
	public static String name = "Wooden Chest";
	
	/**
	 * <h3> Creates a new Chest </h3>
	 * <p> Adds 200 points and contents of inventory upon User collision.</p>
	 * 
	 * {@code sprite = chestfull.png}
	 * {@code name = Wooden Chest}
	 * {@code inventory = ArrayList }
	 * 
	 */
	public Chest() {
		super(new Sprite("com/gammacrawler/images/chestfull.png"), name);
		inventory = new ArrayList<>();
	}
	
	/**
	 * Adds an Item to this.inventory
	 * @param i - Item to be added
	 */
	public void addTo(Item i) {
		this.inventory.add(i);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void collide(Entity e) {
	
		if (e instanceof User) {
			for (Item i : this.inventory) {
				i.addToUser();
				Generator.player.points += 200;
			}
			
			this.die(Generator.player);	
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void move(Direction d) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Item i : this.inventory) {
			sb.append(i.getName() + "/n");
		}
		return sb.toString();
		
	}
}
