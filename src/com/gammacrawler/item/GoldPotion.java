package com.gammacrawler.item;

import com.gammacrawler.Direction;
import com.gammacrawler.Generator;
import com.gammacrawler.entity.Character;
import com.gammacrawler.entity.Entity;
import com.gammacrawler.entity.Item;
import com.gammacrawler.entity.Sprite;

public class GoldPotion extends Potion {
	private static final String name = "Gold Potion";
	private static final int value = 25;
	private static final Sprite sprite = new Sprite("file:src/com/cammacralwer/images/smallhealthpotion.png");
	
	public GoldPotion() {
		super(name, sprite, value);
	}

	@Override
	public void use(Character c) {
		// TODO Auto-generated method stub
		int index  = 0;
		
		Generator.player.setPoints(Generator.player.getPoints() + value);
		
		for (int i = 0; i < c.getInventory().size(); i++) {
			Item in = c.getInventory().get(i);
			if(in instanceof GoldPotion) {
				index  = i;
				c.getInventory().remove(index);
			}
		}
		c.getInventory().remove(this);
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
