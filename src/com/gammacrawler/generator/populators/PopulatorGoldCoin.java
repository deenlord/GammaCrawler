package com.gammacrawler.generator.populators;

import java.util.ArrayList;

import com.gammacrawler.Settings;
import com.gammacrawler.entity.Entity;
import com.gammacrawler.util.Point;

public class PopulatorGoldCoin extends Populator {

	int attempts;

	public PopulatorGoldCoin(int[][] tileArray, ArrayList<Entity> entities) {
		super(tileArray, entities);
		attempts = (int) ((tileArray.length * tileArray[0].length) / 6);
	}

	@Override
	public void populate() {
		for (int i = 0; i < attempts; i++) {
			int[] n = getRandomFreeSpace();
			Point p = new Point(n[0], n[1]);
			if (p != null) {
				tileArray[p.x][p.y] = Settings.COBBLES1_ID;
			}
		}
	}

}
