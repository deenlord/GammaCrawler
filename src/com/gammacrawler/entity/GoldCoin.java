package com.gammacrawler.entity;

public class GoldCoin extends Entity {
	public static final int GOLD_POINT_VALUE = 10;

	public GoldCoin(Sprite sprite) {
		super(sprite);
	}

	@Override
	public void collide(Entity e) {
		if (e instanceof User) {
			((User) e).curHP -= 10;
		}
	}

}
