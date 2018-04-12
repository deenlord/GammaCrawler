package com.gammacrawler.generator.populators;

import java.util.ArrayList;

import com.gammacrawler.Enemy;
import com.gammacrawler.Point;

public class PopulatorSkulls extends Populator {

	public PopulatorSkulls(int[][] tileArray, ArrayList<Enemy> enemies) {
		super(tileArray, enemies);
	}

	@Override
	public void populate() {
		for (int i = 0; i < 100; i++) {
			Point p = getRandomFreeSpace();
			if (!doesBlockRoute(p, 0)) {
				tileArray[p.x][p.y] = 3;
			}
		}
	}

}
