package com.gammacrawler.generator.populators;

import java.util.ArrayList;

import com.gammacrawler.entity.Entity;
import com.gammacrawler.entity.GoldCoin;

public class PopulatorGoldCoin extends Populator {
	private int attempts;

	public PopulatorGoldCoin(int[][] tileArray, ArrayList<Entity> entities) {
		super(tileArray, entities);
		attempts = (tileArray.length * tileArray[0].length) / 40; 
	}

	@Override
	public void populate() {
		GoldCoin coin;
		for (int i = 0; i < attempts; i++) {
			int[] p = getRandomFreeSpace();
			coin = new GoldCoin();
			coin.moveToTile(p[1] + 1, p[0] + 1); // TODO: Fix entity coordinates
			entities.add(coin);
		}
	}

}
