package com.gammacrawler.entity;

import com.gammacrawler.Direction;

public class GoldCoin extends Entity {
	public static final int GOLD_POINT_VALUE = 10;

	public GoldCoin() {
		super(new Sprite("file:src/com/gammacrawler/images/goldcoin.png"));
	}

	@Override
	public void collide(Entity e) {
		if (e instanceof User) {
			System.out.println("PICKED UP GOLD!");
			((User) e).setPoints(((User) e).getPoints() + GOLD_POINT_VALUE);
			die(e);
		}
	}

	@Override
	public void move(Direction d) {
		// TODO Auto-generated method stub
		
	}

}
