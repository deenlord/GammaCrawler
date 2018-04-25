package com.gammacrawler.entity;

import com.gammacrawler.Direction;
import com.gammacrawler.Generator;
<<<<<<< HEAD
import com.gammacrawler.Settings;
=======
import com.gammacrawler.StatusBar;
>>>>>>> 5f9f6d3cf3792c9349b0aed24510f0e646f6778b

/**
 * Collectable gold coins
 * @author WolfieWaffle
 *
 */
public class GoldCoin extends Entity {

	/**
	 * Creates a Gold Coin (Value in Settings.GOLD_POINT_VALUE)
	 */
	public GoldCoin() {
		super(new Sprite("file:src/com/gammacrawler/images/goldcoin.png"));
	}

	@Override
	public void collide(Entity e) {
		if (e instanceof User) {
<<<<<<< HEAD
			System.out.println("PICKED UP GOLD!");
			Generator.player.setPoints(Generator.player.getPoints() + Settings.GOLD_POINT_VALUE);
=======
			StatusBar.addStatus("PICKED UP GOLD!");
			Generator.player.setPoints(Generator.player.getPoints() + GOLD_POINT_VALUE);
>>>>>>> 5f9f6d3cf3792c9349b0aed24510f0e646f6778b
			die(e);
		}
	}

	@Override
	public void move(Direction d) {
		// TODO Auto-generated method stub
		
	}

}
