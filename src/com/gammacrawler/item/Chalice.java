package com.gammacrawler.item;

import com.gammacrawler.Direction;
import com.gammacrawler.Generator;
import com.gammacrawler.entity.Character;
import com.gammacrawler.entity.Entity;
import com.gammacrawler.entity.Item;
import com.gammacrawler.entity.Sprite;

public class Chalice extends Potion {
	private static final String name = "Gold Chalice";
	private static final int takeHP = 7;
	private static final int giveGold = 10;
	
	public Chalice() {
		super(name, new Sprite("file:src/com/gammacrawler/images/chalice.png"), 0);
	}

	@Override
	public void use(Character c) {

		// Reduce the players HP but give them money, risk vs reward.
		Generator.player.setHP(Generator.player.getHP() - takeHP);
		if (Generator.player.getHP() < 1) {
			Generator.player.setHP(0);
			Generator.player.die(Generator.player);
		}
		Generator.player.setPoints(Generator.player.getPoints() + giveGold);
	}

	@Override
	public void collide(Entity e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void move(Direction d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addToUser() {
		for (Item i : Generator.player.getInventory()) {
			if (i instanceof Chalice) {
				return;
			}
		}
		super.addToUser();
	}

}
