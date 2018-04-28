package com.gammacrawler.item;

import com.gammacrawler.Direction;
import com.gammacrawler.Generator;
import com.gammacrawler.entity.Character;
import com.gammacrawler.entity.Entity;
import com.gammacrawler.entity.Item;
import com.gammacrawler.entity.Sprite;

/**
 * <h3>FightPotion - a Potion</h3>
 *  <p> name = "Fight Potion"
 *  <br> value = 25
 *  <br> increases damage weapon deals
 *  @author deenlord
 */
public class FightPotion extends Potion {
	
	private static final String name = "Fight Potion";
	private static final int value = 25;

	public FightPotion() {
		super(name, new Sprite("file:src/com/gammacrawler/images/smallfightpotion.png"), value);
	}

	// sets weapon damage to 100 for single shot kills, 
	// removes all gold potions from player's inventory
	@Override
	public void use(Character c) {
		int index  = 0;
		
		Generator.player.getWeapon().setDamage(100);
		
		for (int i = 0; i < c.getInventory().size(); i++) {
			Item item = c.getInventory().get(i);
			if(item instanceof GoldPotion) {
				index  = i;
				c.getInventory().remove(index);
			}
		}
		c.getInventory().remove(this);
	}

	@Override
	public void move(Direction d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void collide(Entity e) {
		// TODO Auto-generated method stub

	}

}
