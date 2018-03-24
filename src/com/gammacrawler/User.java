package com.gammacrawler;

import javafx.geometry.Point2D; 



class User extends Character {
	
	Point2D coords;

	public User(String name) {
		this.setName(name);
		this.setHP(100);
		coords = new Point2D(0, 0);
//		inv = new Inventory();
//		main = new Weapon("Wooden Sword", 1);
//		shield = new Shield("Wooden Shield", 1);

	}
	
	public Point2D getCoords() {
		return this.coords;
	}
	
	public void moveUser(Direction dir) {
		if (dir == Direction.NORTH) {
			this.coords.add(0, 1);
		}
		else if (dir == Direction.SOUTH) {
			this.coords.add(0, -1);
		}
		else if (dir == Direction.WEST) {
			this.coords.add(-1, 0);
		}
		else if (dir == Direction.EAST) {
			this.coords.add(1, 0);
		}
	}
}
