package com.gammacrawler.generator.populators;

import java.util.ArrayList;

import com.gammacrawler.Enemy;
import com.gammacrawler.Point;
import com.gammacrawler.Settings;

public class PopulatorCobbles extends Populator {

	int attempts;

	public PopulatorCobbles(int[][] tileArray, ArrayList<Enemy> enemies) {
		super(tileArray, enemies);
		attempts = (int) ((tileArray.length * tileArray[0].length) / 6);
	}

	@Override
	public void populate() {
		for (int i = 0; i < attempts; i++) {
			Point p = getRandomFreeSpace();
			if (p != null) {
				tileArray[p.x][p.y] = Settings.COBBLES1_ID;
			}
		}
		for (int i = 0; i < attempts; i++) {
			Point p = getRandomFreeSpace();
			if (p != null) {
				tileArray[p.x][p.y] = Settings.COBBLES2_ID;
			}
		}
		for (int i = 0; i < attempts; i++) {
			Point p = getRandomFreeSpace();
			if (p != null) {
				tileArray[p.x][p.y] = Settings.COBBLES3_ID;
			}
		}
	}

}
