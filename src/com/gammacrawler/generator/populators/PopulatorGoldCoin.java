package com.gammacrawler.generator.populators;

import java.util.ArrayList;

<<<<<<< HEAD
import com.gammacrawler.entity.Entity;
import com.gammacrawler.entity.GoldCoin;

public class PopulatorGoldCoin extends Populator {

	public PopulatorGoldCoin(int[][] tileArray, ArrayList<Entity> entities) {
		super(tileArray, entities);
=======
import com.gammacrawler.Settings;
import com.gammacrawler.entity.Entity;
import com.gammacrawler.util.Point;

public class PopulatorGoldCoin extends Populator {

	int attempts;

	public PopulatorGoldCoin(int[][] tileArray, ArrayList<Entity> entities) {
		super(tileArray, entities);
		attempts = (int) ((tileArray.length * tileArray[0].length) / 6);
>>>>>>> 520be500adbeb24c6cd66de97b22acb45dab4b1b
	}

	@Override
	public void populate() {
<<<<<<< HEAD
		GoldCoin coin;
		for (int i = 0; i < 10; i++) {
			int[] p = getRandomFreeSpace();
			coin = new GoldCoin();
			coin.moveToTile(3, i);
			entities.add(coin);
=======
		for (int i = 0; i < attempts; i++) {
			int[] n = getRandomFreeSpace();
			Point p = new Point(n[0], n[1]);
			if (p != null) {
				tileArray[p.x][p.y] = Settings.COBBLES1_ID;
			}
>>>>>>> 520be500adbeb24c6cd66de97b22acb45dab4b1b
		}
	}

}
