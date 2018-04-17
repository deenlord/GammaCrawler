package com.gammacrawler.entity;

public class GoldCoin extends Entity {
	public static final int GOLD_POINT_VALUE = 10;

	public GoldCoin() {
		super(new Sprite("file:src/com/gammacrawler/images/goldcoin.png"));
	}

	@Override
	public void collide(Entity e) {
		if (e instanceof User) {
			System.out.println("PICKED UP GOLD!");
			((User) e).setPoints(((User) e).getPoints()+GOLD_POINT_VALUE);
			this.isDead = true;
		}
	}

}
