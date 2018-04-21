package com.gammacrawler.entity;

import java.util.ArrayList;

import com.gammacrawler.Direction;
import com.gammacrawler.Generator;

public class Chest extends Item{
	ArrayList<Item> inventory;
	static Sprite sprite = new Sprite("file:src/com/gammacrawler/images/chestfull.png");
	public static String name = "Wooden Chest";
	
	
	public Chest() {
		super(sprite, name);
		inventory = new ArrayList<>();
	}
	
	public void addTo(Item i) {
		this.inventory.add(i);
	}
	

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Item i : this.inventory) {
			sb.append(i.getName() + "/n");
		}
		return sb.toString();
		
	}

	@Override
	public void collide(Entity e) {
		// TODO finish this. #deenlord 4/18
		if (e instanceof User) {
			
			for (Item i : this.inventory) {
				i.addToUser(Generator.player);
			}
			this.die(Generator.player);
			
		}
	}

	@Override
	public void move(Direction d) {
		// TODO Auto-generated method stub
		
	}
}
