package com.gammacrawler.entity;

import java.util.ArrayList;

import com.gammacrawler.Direction;
import com.gammacrawler.Generator;

/**
 * @author jakev
 *
 */
public class Chest extends Item{
	ArrayList<Item> inventory;
	public static String name = "Wooden Chest";
	
	
	/**
	 * <h3> Creates a new Chest </h3>
	 * 
	 * {@code sprite = chestfull.png}
	 * {@code name = Wooden Chest}
	 * {@code inventory = ArrayList }
	 * 
	 */
	public Chest() {
		super(new Sprite("file:src/com/gammacrawler/images/chestfull.png"), name);
		inventory = new ArrayList<>();
	}
	
	/**
	 * Adds an Item to this.inventory
	 * @param i - An Item
	 */
	public void addTo(Item i) {
		this.inventory.add(i);
	}

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

	@Override
	public void move(Direction d) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Item i : this.inventory) {
			sb.append(i.getName() + "/n");
		}
		return sb.toString();
		
	}
}
