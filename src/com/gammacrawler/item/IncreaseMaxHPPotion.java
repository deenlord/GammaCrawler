package com.gammacrawler.item;

import com.gammacrawler.Direction;
import com.gammacrawler.Generator;
import com.gammacrawler.entity.Character;
import com.gammacrawler.entity.Entity;
import com.gammacrawler.entity.Sprite;
import com.gammacrawler.entity.User;

/**
 * <h3>IncreaseMaxHPPotion - a Potion</h3>
 *  <p> name = "BoostHP Potion"
 *  <br> value = 25
 *  <br>Increases User's max HP by 25
 *  @author deenlord
 */
public class IncreaseMaxHPPotion extends Potion {

	private static final String name = "BoostHP Potion";
	private static final int value = 25;
	
	public IncreaseMaxHPPotion() {
		super(name, new Sprite("file:src/com/gammacrawler/images/litehealthpotion.png"), value);
	}

	
	@Override
	public void use(Character c) {
		c.setMaxHP(c.getMaxHP() + value);
		c.setHP(c.getHP() + value);
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
