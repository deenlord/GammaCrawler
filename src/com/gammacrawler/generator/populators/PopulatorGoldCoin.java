package com.gammacrawler.generator.populators;

import java.util.ArrayList;

import com.gammacrawler.entity.Entity;
import com.gammacrawler.entity.GoldCoin;

public class PopulatorGoldCoin extends Populator {

	public PopulatorGoldCoin(int[][] tileArray, ArrayList<Entity> entities) {
		super(tileArray, entities);
	}

	@Override
	public void populate() {
		GoldCoin coin;
		for (int i = 0; i < 10; i++) {
			int[] p = getRandomFreeSpace();
			coin = new GoldCoin();
			coin.moveToTile(3, i);
			entities.add(coin);
		}
	}

}
