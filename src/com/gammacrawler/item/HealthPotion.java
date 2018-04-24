package com.gammacrawler.item;

import com.gammacrawler.Direction;
import com.gammacrawler.StatusBar;
import com.gammacrawler.entity.Character;
import com.gammacrawler.entity.Entity;
import com.gammacrawler.entity.Item;
import com.gammacrawler.entity.Sprite;

/**
 * @author jakev
 *
 */
public class HealthPotion extends Potion {
	private static final String name = "Health Potion";
	private static final int value = 25;
	private static final Sprite sprite = new Sprite("file:src/com/cammacralwer/images/smallhealthpotion.png");
	
	public HealthPotion() {
		super(name, sprite, value);
	}

	@Override
	public void use(Character c) {
		int val = c.getHP();
		int max = c.getMaxHP();
		int returnMe = val + value;
		int index = 0;
		
		if (returnMe > max) {
			returnMe = max;
		}
		
		c.setHP(returnMe);
		StatusBar.addStatus("Health Potion Applied: User HP = " + c.getHP());
	}

	@Override
	public void collide(Entity e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move(Direction d) {
		// TODO Auto-generated method stub
		
	}
}


