package com.gammacrawler.item;

import com.gammacrawler.Direction;
import com.gammacrawler.Settings;
import com.gammacrawler.StatusBar;
import com.gammacrawler.entity.Character;
import com.gammacrawler.entity.Entity;
import com.gammacrawler.entity.Item;
import com.gammacrawler.entity.Sprite;

/**
 * <h3>HealthPotion - A Potion</h3>
 *  <p> name = "Health Potion"
 *  <br> value = 25
 *  <br> Increases current HP
 *  @author deenlord
 */
public class HealthPotion extends Potion {
	private static final String name = "Health Potion";
	private static final int value = 25;
	
	public HealthPotion() {
		super(name, new Sprite("com/gammacrawler/images/smallhealthpotion.png"), value);
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
		c.getInventory().remove(this);
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


