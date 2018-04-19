package com.gammacrawler.item;

import com.gammacrawler.Direction;
import com.gammacrawler.entity.Character;
import com.gammacrawler.entity.Entity;
import com.gammacrawler.entity.Sprite;
import com.gammacrawler.entity.User;

public class XPPotion extends Potion {

	private static final String name = "BoostXP Potion";
	private static final int value = 100;
	private static final Sprite sprite = new Sprite("file:src/com/cammacralwer/images/smallhealthpotion.png");
	
	public XPPotion() {
		super(name, sprite, value);
	}

	
	@Override
	public void drink(Character c) {
		c.setXP(c.getXP() + value);

	}

	@Override
	public void collide(Entity e) {
		// TODO Auto-generated method stub
		if (e.getClass().getSimpleName().equals("User")) {
			User richard = (User) e;
			this.drink(richard);
		}
	}
	
	@Override
	public void move(Direction d) {
		// TODO Auto-generated method stub

	}

}
