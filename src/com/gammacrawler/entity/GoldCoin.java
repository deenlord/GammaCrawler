package com.gammacrawler.entity;

import com.gammacrawler.Direction;
import com.gammacrawler.Generator;
import com.gammacrawler.Settings;
import com.gammacrawler.StatusBar;

/**
 * <h3> Gold Coin - Serve as points</h3
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
			System.out.println("PICKED UP GOLD!");
			Generator.player.setPoints(Generator.player.getPoints() + Settings.GOLD_POINT_VALUE);
			StatusBar.addStatus("PICKED UP GOLD!");
			Generator.player.setPoints(Generator.player.getPoints() + Settings.GOLD_POINT_VALUE);
			die(e);
		}
	}

	@Override
	public void move(Direction d) {
		// TODO Auto-generated method stub
		
	}

}
