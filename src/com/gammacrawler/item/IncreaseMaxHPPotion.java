package com.gammacrawler.item;

import com.gammacrawler.Direction;
import com.gammacrawler.Generator;
import com.gammacrawler.entity.Character;
import com.gammacrawler.entity.Entity;
import com.gammacrawler.entity.Sprite;
import com.gammacrawler.entity.User;

public class IncreaseMaxHPPotion extends Potion {

	private static final String name = "BoostHP Potion";
	private static final int value = 25;
	private static final Sprite sprite = new Sprite("file:src/com/cammacralwer/images/smallhealthpotion.png");
	
	public IncreaseMaxHPPotion() {
		super(name, sprite, value);
	}

	
	@Override
	public void drink(Character c) {
		c.setMaxHP(c.getMaxHP() + value);
		c.setHP(c.getHP() + value);
	}

	@Override
	public void collide(Entity e) {
		// TODO Auto-generated method stub
		if (e instanceof User) {
	
			this.drink(Generator.player);
		}
	}
	
	@Override
	public void move(Direction d) {
		// TODO Auto-generated method stub

	}

}
