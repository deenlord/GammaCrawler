package com.gammacrawler.item;

import com.gammacrawler.Direction;
import com.gammacrawler.Generator;
import com.gammacrawler.entity.Character;
import com.gammacrawler.entity.Entity;
import com.gammacrawler.entity.Item;
import com.gammacrawler.entity.Sprite;

public class GhostPotion extends Potion {
	private static final String name = "Ghost Potion";
	private static final int value = 50;
	private static final Sprite sprite = new Sprite("file:src/com/cammacralwer/images/ghostpotion.png");
	
	public GhostPotion() {
		super(name, sprite, value);
	}

	@Override
	public void drink(Character c) {
		// TODO Auto-generated method stub
		int index  = 0;

		Generator.player.invisibleTurns = 3;
		Generator.player.getImageView().setOpacity(0.5);

		for (int i = 0; i < c.getInventory().size(); i++) {
			Item in = c.getInventory().get(i);
			if(in instanceof GhostPotion) {
				index  = i;
				c.getInventory().remove(index);
			}
		}

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
