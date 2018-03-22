package com.gammapos;

class User extends Character {

	public User(String name) {
		this.name = name;
		hp = 100;
		inv = new Inventory();
		main = new Weapon("Wooden Sword", 1);
		shield = new Shield("Wooden Shield", 1);

		inv.add(main);
		inv.add(shield);
	}

}
