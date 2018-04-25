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
	static Sprite sprite = new Sprite("file:src/com/gammacrawler/images/chestfull.png");
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
		super(sprite, name);
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
<<<<<<< HEAD
				i.addToUser();
=======
				i.addToUser(Generator.player);
				Generator.player.points += 200;
>>>>>>> 5f9f6d3cf3792c9349b0aed24510f0e646f6778b
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
