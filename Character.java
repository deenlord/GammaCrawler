package com.jeromierand.gammacrawler;

abstract Class Character {
	private String name;
	private int hp;
	private Inventory inv;
	private Weapon main;
	private Shield shield;


	public String getName() {
		return name;
	}

	public void setName(String newName) {
		name = newName;
	}

	public int getHP() {
		return hp;
	}

	public void setHP(int hitpoints) {
		hp = hitpoints;
	}

	public Inventory getInventory() {
		return inv;
	}

	public void setInventory(Inventory newinv) {
		inv = newinv;
	}

	public Weapon getWeapon() {
		return main;
	}

	public void setWeapon(Weapon newWeapon) {
		inv.add(main);
		main = newWeapon;
	}

	public Shield getShield() {
		return shield;
	}

	public void setShield(Shield newShield) {
		inv.add(shield);
		shield = newShield;
	}

	public boolean isDead() {
		boolean dead;
		if( hp > 0 ) {
			dead = false
		}
		else {
			dead = true;
		}

		return dead;
	}

}
