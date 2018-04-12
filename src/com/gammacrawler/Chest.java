package com.gammacrawler;

import java.util.ArrayList;

public class Chest extends Item{
	ArrayList<Item> inventory;
	Sprite sprite = new Sprite("file:src/com/gammacrawler/images/chestfull.png");
	public String name = "Wooden Chest";
	public Chest(String name, Sprite sprite) {
		super(name, sprite);
		inventory = new ArrayList<>();
	}
	
	public void addTo(Item i) {
		this.inventory.add(i);
	}
	
	public void take(String name, User richard) {
		for (int i = 0; i < inventory.size(); i++) {
			if (name == this.inventory.get(i).getName()) {
				richard.inventory.add(this.inventory.get(i));
				this.inventory.remove(i);
			}
		}
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
