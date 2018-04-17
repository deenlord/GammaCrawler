package com.gammacrawler.entity;

public class GoldCoin extends Entity {
	public static final int GOLD_POINT_VALUE = 10;

<<<<<<< HEAD
	public GoldCoin() {
		super(new Sprite("file:src/com/gammacrawler/images/goldcoin.png"));
=======
	public GoldCoin(Sprite sprite) {
		super(sprite);
>>>>>>> 520be500adbeb24c6cd66de97b22acb45dab4b1b
	}

	@Override
	public void collide(Entity e) {
		if (e instanceof User) {
<<<<<<< HEAD
			System.out.println("PICKED UP GOLD!");
			
=======
			((User) e).curHP -= 10;
>>>>>>> 520be500adbeb24c6cd66de97b22acb45dab4b1b
		}
	}

}
