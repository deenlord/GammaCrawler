package com.gammacrawler.generator.populators;

import java.util.ArrayList;

import com.gammacrawler.Enemy;
import com.gammacrawler.Point;

public class PopulatorCobbles extends Populator {

	int attempts;

	public PopulatorCobbles(int[][] tileArray, ArrayList<Enemy> enemies) {
		super(tileArray, enemies);
		attempts = (int) ((tileArray.length * tileArray[0].length) / 5);
	}

	@Override
	public void populate() {
		for (int i = 0; i < attempts; i++) {
			Point p = getRandomFreeSpace();
			if (p != null) {
				tileArray[p.x][p.y] = 3;
			}
		}
		for (int i = 0; i < attempts; i++) {
			Point p = getRandomFreeSpace();
			if (p != null) {
				tileArray[p.x][p.y] = 4;
			}
		}
		for (int i = 0; i < attempts; i++) {
			Point p = getRandomFreeSpace();
			if (p != null) {
				tileArray[p.x][p.y] = 5;
			}
		}
	}

}
