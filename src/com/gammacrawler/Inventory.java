package com.gammacrawler;

import java.util.ArrayList;

public class Inventory {
	ArrayList<Item> al;
	public Inventory() {
		al = new ArrayList<>();
	}
	
	public Inventory(ArrayList<Item> list) {
		this.al = list;
	}
	
	public ArrayList<Item> getItems() {
		return this.al;
	}
	
	public void setInventory(ArrayList<Item> list) {
		this.al = list;
	}

}
