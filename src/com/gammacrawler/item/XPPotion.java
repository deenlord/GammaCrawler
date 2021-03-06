package com.gammacrawler.item;

import com.gammacrawler.Direction;
import com.gammacrawler.Generator;
import com.gammacrawler.entity.Character;
import com.gammacrawler.entity.Entity;
import com.gammacrawler.entity.Sprite;
import com.gammacrawler.entity.User;

/**
 * <h3>XPPotion - a Potion</h3>
 * <p>name = BoostXP Potion
 * <br> value = 100
 * <br> increases user XP by 100
 * 
 * @author jakev
 *
 */
public class XPPotion extends Potion {

	private static final String name = "BoostXP Potion";
	private static final int value = 100;
	
	public XPPotion() {
		super(name, new Sprite("com/gammacrawler/images/smallxppotion.png"), value);
	}

	
	@Override
	public void use(Character c) {
		c.setXP(c.getXP() + value);
		c.getInventory().remove(this);
	}

	@Override
	public void collide(Entity e) {
		// TODO Auto-generated method stub
		if (e instanceof User) {
			
			this.use(Generator.player);
		}
	}
	
	@Override
	public void move(Direction d) {
		// TODO Auto-generated method stub

	}

}
